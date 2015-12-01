package my.dao.pool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import my.util.SysParam;

/**
 * 数据库管理
 */
public class DBPool {

	private final static Logger logger = LoggerFactory.getLogger(DBPool.class);
	private final ThreadLocal<Connection> conns = new ThreadLocal<Connection>();
	private DataSource dataSource;
	private String type = "oracle";
	private volatile boolean init = false;
	private String fileName;

	public DBPool(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 初始化连接池
	 * 
	 * @param fileName
	 */
	private synchronized void initDataSource() {
		if (init) {
			return;
		} else {
			InputStream resourceAsStream = null;
			try {

				Properties dbProperties = new Properties();
				resourceAsStream = DBPool.class.getResourceAsStream(String
						.format("/%s", fileName));
				// if(resourceAsStream==null){
				// //classpath里找不到，则从tomcat webapps/conf 目录下找
				// String path = RequestContext.get().getRequest().getSession()
				// .getServletContext().getRealPath("/");
				// File f = new File(path).getParentFile();
				// resourceAsStream=new FileInputStream(new
				// File(f,"conf/"+fileName));
				// }
				dbProperties.load(resourceAsStream);

				Properties cp_props = new Properties();
				for (Object key : dbProperties.keySet()) {
					String skey = (String) key;
					if (skey.startsWith("jdbc.")) {
						String name = skey.substring(5);
						cp_props.put(name, dbProperties.getProperty(skey));
						if (!"jdbc.password".equals(skey)) {
							logger.debug("{}:{}", skey,
									dbProperties.getProperty(skey));
						}
					}
					if (skey.equals("type")) {
						if ("sqlserver".equalsIgnoreCase((String) dbProperties
								.get("type"))) {
							type = "sqlserver";
						} else if ("mysql"
								.equalsIgnoreCase((String) dbProperties
										.get("type"))) {
							type = "mysql";
						} else if (!"oracle"
								.equalsIgnoreCase((String) dbProperties
										.get("type"))) {
							type = "oracle";
							logger.warn("unkom db type");
						}
					}
				}

				// if (dataSource.getClass().getName().indexOf("c3p0") > 0) {
				// Disable JMX in C3P0
				System.setProperty(
						"com.mchange.v2.c3p0.management.ManagementCoordinator",
						"com.mchange.v2.c3p0.management.NullManagementCoordinator");
				// }

				dataSource = (DataSource) Class.forName(
						cp_props.getProperty("datasource")).newInstance();

				logger.info("Using DataSource : "
						+ dataSource.getClass().getName());
				BeanUtils.populate(dataSource, cp_props);

				init = true;

				Connection conn = getConnection();
				DatabaseMetaData mdm = conn.getMetaData();
				logger.info("Connected to " + mdm.getDatabaseProductName()
						+ " " + mdm.getDatabaseProductVersion());
				closeConnection();
				System.out.println(dataSource);
			} catch (Exception e) {
				throw new DBException(e);
				// logger.error("error", e);
			} finally {
				if (resourceAsStream != null) {
					try {
						resourceAsStream.close();
					} catch (IOException e) {
					}
				}
			}

		}
	}

	/**
	 * 断开连接池
	 */
	public final void closeDataSource() {
		if (!init) {
			return;
		}
		try {
			dataSource.getClass().getMethod("close").invoke(dataSource);
		} catch (NoSuchMethodException e) {
		} catch (Exception e) {
			logger.error("Unabled to destroy DataSource!!! ", e);
		}
	}

	/**
	 * 同一个线程共享一个连接
	 * 
	 * @return
	 * @throws SQLException
	 */
	public final Connection getConnection() throws SQLException {
		if (!init) {
			initDataSource();
		}
		Connection conn = conns.get();
		if (conn == null || conn.isClosed()) {
			if (SysParam.isDebugMode()) {
				logger.debug(
						"getConnection!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!",
						new Throwable());
			}
			conn = dataSource.getConnection();
			conns.set(conn);
			conn.setAutoCommit(false);

			PoolStatistics.add();
		}
		return conn;
	}

	/**
	 * 每次都获取新连接，需手工关闭
	 * 
	 * @return
	 * @throws SQLException
	 */
	public final Connection getUserManagerConnection() throws SQLException {
		if (!init) {
			initDataSource();
		}
		Connection conn = dataSource.getConnection();
		conn.setAutoCommit(false);

		PoolStatistics.add();
		return conn;
	}

	/**
	 * 关闭一个用户自管理的连接，只能关闭通过getUserManagerConnection的连接
	 * 
	 * @param conn
	 */
	public final void closeConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.setAutoCommit(true);
				conn.close();
				if (SysParam.isDebugMode()) {
					logger.info(
							"closeConnection!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!",
							new Throwable());
				}
				PoolStatistics.close();
			}
		} catch (Exception e) {
			logger.error("Unabled to close connection!!! ", e);
			PoolStatistics.close();
		}
	}

	/**
	 * 1 关闭当前线程共享的连接
	 */
	public final void closeConnection() {
		if (!init) {
			return;
		}
		Connection conn = conns.get();
		try {
			if (conn != null && !conn.isClosed()) {
				if (SysParam.isDebugMode()) {
					logger.info(
							"closeConnection!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!",
							new Throwable());
				}
				conn.setAutoCommit(true);
				conn.close();
				PoolStatistics.close();
			}
		} catch (Exception e) {
			PoolStatistics.close();
			logger.error("Unabled to close connection!!! ", e);
		} finally {
			conns.set(null);
			
		}
	}

	public String getType() {
		if (!init) {
			initDataSource();
		}
		return type.toLowerCase();
	}

	public boolean isOracle() {
		if (!init) {
			initDataSource();
		}
		return type.equals("oracle");
	}

	public void setType(String type) {
		this.type = type;
	}

	public void commit() {
		if (!init) {
			return;
		}
		Connection conn = conns.get();
		try {
			if (conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			logger.error("Unabled to commit connection!!! ", e);
		}
	}

	public void rollback() {
		if (!init) {
			return;
		}
		Connection conn = conns.get();
		try {
			if (conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			logger.error("Unabled to rollback connection!!! ", e);
		}

	}

}
