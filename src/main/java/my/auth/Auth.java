package my.auth;

import my.web.IUser;

public interface Auth {
	
	/**判断是否有权限访问某一个方法
	 * @param methodName
	 * @param user
	 * @return
	 */
	public boolean auth(String methodName,IUser user);
	
	public String getMenuId();
	public String getActionName(String methodName);
}
