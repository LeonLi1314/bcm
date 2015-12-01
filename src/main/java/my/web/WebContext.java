package my.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WebContext {

	public HttpServletRequest getRequest();

	public HttpServletResponse getResponse();

	public String getLoginUrl();

	public IUser getLoginUer();
	
	public void reset();
	
}
