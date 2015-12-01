package my.dao.pool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * DemoBean: lnn
 * Date: 13-1-24
 * Time: 下午10:01
 * To change this template use File | Settings | File Templates.
 */
public class DBManager {
    private final static Logger logger = LoggerFactory.getLogger(DBManager.class);
    private static final Map<String, DBPool> POOLS = new ConcurrentHashMap<String, DBPool>();
    private static DBPool DEFAULT;
    
    public static final String DEFAULT_POOL_NAME="default";

    static {
    	InputStream resourceAsStream=null;
        try {
            Properties properties = new Properties();
            resourceAsStream = DBManager.class.getResourceAsStream("/db.conf");
//            if(resourceAsStream==null){
//            	//classpath里找不到，则从tomcat webapps/conf 目录下找
//            	String path = RequestContext.get().getRequest().getSession()
//        				.getServletContext().getRealPath("/");
//            	File f = new File(path).getParentFile();
//            	resourceAsStream=new FileInputStream(new File(f,"conf/db.conf"));
//            }
            
			properties.load(resourceAsStream);
            for (Object key : properties.keySet()) {
                String skey = (String) key;
                String value = properties.getProperty(skey);
                if ("default".equals(skey.toLowerCase())) {
                    DEFAULT = new DBPool(value);
                    POOLS.put("default", DEFAULT);
                } else {
                    POOLS.put(skey.toLowerCase(), new DBPool(value));
                }
            }
        } catch (Exception e) {
            logger.error("init DBManager error:", e);
            throw new DBException(e);
        }finally{
        	if(resourceAsStream!=null){
        		try {
					resourceAsStream.close();
				} catch (IOException e) {
				}
        	}
        }
    }


    public static final Connection getConnection()   {
        try {
			return DEFAULT.getConnection();
		} catch (SQLException e) {
			throw new DBException(e);
		}
    }


    public static final Connection getConnection(String poolName)  {
        try {
			return POOLS.get(poolName).getConnection();
		} catch (SQLException e) {
			throw new DBException(e);
		}
    }
    
    public static DBPool getPool(String poolName){
    	return POOLS.get(poolName);
    }
    
    public static DBPool getDefaultPool(){
    	return POOLS.get(DEFAULT_POOL_NAME);
    }


    public static final void closeConnection() {
        DEFAULT.closeConnection();
    }

    public static final void closeConnection(String poolName) {
        POOLS.get(poolName).closeConnection();
    }
    
    public static final void closeAllConnection(){
    	for(DBPool pool:POOLS.values()){
    		pool.closeConnection();
    	}
    }
    
    public static final void commitAll(){
    	for(DBPool pool:POOLS.values()){
    		pool.commit();
    	}
    }

    public static final void closeDataSource() {
        DEFAULT.closeDataSource();
    }

    public static final void closeDataSource(String poolName) {
        POOLS.get(poolName).closeDataSource();
        POOLS.remove(poolName);
    }


	public static void close(PreparedStatement ps) {
		 if(ps!=null){
			 try {
				ps.close();
			} catch (SQLException e) {
			}
		 }
	}
	
	public static void close(ResultSet rs) {
		 if(rs!=null){
			 try {
				rs.close();
			} catch (SQLException e) {
			}
		 }
	}
	
	public static void close(ResultSet rs,PreparedStatement ps) {
		close(rs);
		close(ps);
	}


	public static void rollbackAll() {
		for(DBPool pool:POOLS.values()){
    		pool.rollback();
    	}
		
	}
}
