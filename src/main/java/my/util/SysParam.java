package my.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;

import my.dao.pool.DBManager;

/**
 * 系统参数
 * 
 * @author nannan.li
 * 
 */
public class SysParam {
	// 工作空间路径
	public static final String workspace = "d:/my/test/source/";
	// 系统启动时间
	private static long starttime = System.currentTimeMillis();
	private static String startDate = DateUtils.formatDate(new Date());

	private static Config SYS = new Config("/sys.properties");

	public static long getStarttime() {
		return starttime;
	}

	public static String getStartDate() {
		return startDate;
	}

	public static void setStarttime(long starttime) {
		SysParam.starttime = starttime;
		startDate = DateUtils.formatDate(new Date(starttime));
	}

	public static String getBuildDate() {

		return getSysParam("build-date");
	}

	public static String getSystemTitle() {
		return getSysParam("system-title");
	}

	public static String getSmtpHost() {
		return getSysParam("smtp_host");
	}

	public static String getMail() {
		return getSysParam("mail");
	}

	public static String getMailPassword() {
		return getSysParam("mail_password");
	}

	public static String getSYSCODE() {
		return getSysParam("code");
	}

	/**
	 * 获得系统参数值，先重数据库表p_param里找，找不到的话到sys.properties里找
	 * 
	 * @param paramName
	 * @return
	 */
	public static String getSysParam(String paramName) {
//		Param p = Param.instance.load(paramName.toUpperCase());
//		if (p == null) {
//			p = new Param();
//			p.setCode(paramName.toUpperCase());
//			p.setCodevalue(SYS.getValue(paramName, ""));
//			p.insert();
//		}
//		return p.getCodevalue();
		
		return SYS.getValue(paramName, "");
	}

	public static String getVersion() {
		return getSysParam("version");
	}

	public static boolean isDevMode() {
		return "true".equals(SYS.getValue("devMode"));
	}

	public static boolean isDebugMode() {
		return "true".equals(SYS.getValue("debugMode"));
	}

	public static boolean isDemoMode() {
		return "true".equals(SYS.getValue("demoMode"));
	}

	public static boolean isSignupEnabled() {
		return "true".equals(getSysParam("signupEnabled"));
	}

	/**
	 * 同步系统参数到数据库如果数据库中不存在这个参数的话
	 */
	public static void sync() {
		Method[] method = SysParam.class.getDeclaredMethods();

		for (Method m : method) {
			if (Modifier.isPublic(m.getModifiers())
					&& Modifier.isStatic(m.getModifiers())
					&& m.getParameterTypes().length == 0
					&& !m.getName().equals("sync")) {
				try {
					m.invoke(null);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}

		}
		DBManager.commitAll();
	}

	public static void main(String[] args) {
		SysParam.sync();
	}
}
