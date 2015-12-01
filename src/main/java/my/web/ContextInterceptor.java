package my.web;

import java.net.URLEncoder;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.helper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;

import my.ann.Token;

//import my.cache.CacheManager;

/*
 *  <bean id="contextInterceptor" class="my.web.ContextInterceptor">
 <!-- 用户类型 注入 -->
 <!-- 登录页面 -->
 <property name="loginUrl" value="login.htm"/>
 <!-- 必须登录后才能访问的页面 -->
 <property name="regexUrls">
 <list>
 <value>/admin.*</value>
 <value>/index.*</value>
 </list>
 </property>
 </bean>
 <bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping">
 <property name="interceptors">
 <list>
 <ref bean="contextInterceptor"/>
 </list>
 </property>
 </bean>
 */
public class ContextInterceptor extends HandlerInterceptorAdapter {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private String loginUrl;

	/**
	 * 利用正则映射到需要拦截的路径
	 */
	private String[] regexUrls;

	/**
	 * 利用正则映射到需要拦截的路径
	 * 
	 * @param mappingURL
	 */
	public void setRegexUrls(String[] regexUrls) {
		this.regexUrls = regexUrls;
	}

	public String[] getRegexUrls() {
		return regexUrls;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		IUser user = null;
		RequestContext.begin(new LocaleRequestWrapper(request, response), response, getLoginUrl());
		user = RequestContext.get().getLoginUer();

		String strUrl = request.getRequestURI();

		logger.debug(strUrl);

		String contextpath = request.getContextPath();
		if (contextpath.length() > 0) {
			strUrl = strUrl.substring(contextpath.length());
		}
		String[] regexUrls = getRegexUrls();
		if (user == null && !StringUtil.isBlank(strUrl) && regexUrls != null && regexUrls.length > 0) {
			for (String regex : regexUrls) {
				if (StringUtil.isBlank(regex)) {
					continue;
				}
				if (strUrl.matches(regex)) {
					logger.info("{} match regex {},so go to login page", strUrl, regex);
					String strToUrl = request.getContextPath() + getLoginUrl() + "?FromUrl="
							+ URLEncoder.encode(request.getRequestURI(), "utf-8");
					response.sendRedirect(strToUrl);

					// response.getWriter().println(
					// JSON.toJSONString(AjaxMsg.error("need login")));

					return false;
				}
			}
		} else if (user != null) {
			// for (String regex : regexUrls) {
			// if (StringUtil.isBlank(regex)) {
			// continue;
			// }
			// if (strUrl.matches(regex)) {
			// }
			// }

			// String value = user.getSbid();
			//
			// String oldv = CacheManager.get(String.class, "userid_sbid",
			// user.getUserName());
			//
			// if (!value.equals(oldv)) {
			//
			// response.setContentType("application/json; charset=UTF-8");
			// response.getWriter().println(
			// JSON.toJSONString(AjaxMsg.error("用户已经在其他设备登录!")));
			//
			// return false;
			// }

			if (handler != null && handler instanceof HandlerMethod) {
				HandlerMethod method = (HandlerMethod) handler;
				if (method.getBean() instanceof AuthController) {
					AuthController auth = (AuthController) method.getBean();
					if (!auth.auth(method.getMethod().getName(), user)) {
						response.sendError(HttpServletResponse.SC_FORBIDDEN);
						return false;
					}
				}

				Token token = method.getMethod().getAnnotation(Token.class);
				if (token != null) {
					if (!TokenManager.isTokenValid(request)) {
						response.setContentType("application/json; charset=UTF-8");
						response.getWriter().println(JSON.toJSONString(AjaxMsg.error("请不要重复提交表单。")));
					}
				}
			}
			return true;
		}

		return true;
	}

	/**
	 * This implementation is empty.
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// if (handler != null && handler instanceof HandlerMethod) {
		// HandlerMethod method = (HandlerMethod) handler;
		// if (method.getBean() instanceof WebContext) {
		// WebContext context = (WebContext) method.getBean();
		// context.reset();
		// }
		// }
	}

	/**
	 * This implementation is empty.
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		RequestContext.end();
	}

	public static class LocaleRequestWrapper extends HttpServletRequestWrapper {
		private final static int MAX_AGE = 86400 * 365;
		HttpServletResponse response;

		public LocaleRequestWrapper(HttpServletRequest request, HttpServletResponse response) {
			super(request);
			this.response = response;
		}

		/**
		 * parameter->cookie->request
		 */
		public Locale getLocale() {

			Locale result = (Locale) getRequest().getAttribute("lang");
			if (result != null) {
				return result;
			}

			String locale = getRequest().getParameter("lang");

			if (locale != null) {
				RequestUtils.setCookie((HttpServletRequest) getRequest(), response, "lang", locale, MAX_AGE);
			} else {
				javax.servlet.http.Cookie cookie = RequestUtils.getCookie((HttpServletRequest) getRequest(), "lang");

				if (cookie != null) {
					locale = cookie.getValue();
				}

				if (locale == null) {
					locale = ((HttpServletRequest) getRequest()).getLocale().toString();
				}
			}

			if ("zh_CN".equals(locale)) {
				getRequest().setAttribute("lang", Locale.CHINA);
				getRequest().setAttribute("langstr", Locale.CHINA.toString());
				return Locale.CHINA;
			} else if ("en".equals(locale)) {
				getRequest().setAttribute("lang", Locale.US);
				getRequest().setAttribute("langstr", "en");
				return Locale.US;
			} else {
				getRequest().setAttribute("lang", Locale.CHINA);
				getRequest().setAttribute("langstr", Locale.CHINA.toString());
				return Locale.CHINA;// 默认中国
			}
		}
	}
}
