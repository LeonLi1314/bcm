package my.web;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import my.dao.pool.DBException;
import my.dao.pool.DBManager;
import my.util.I18N;

/**
 * 必须是线程安全的实现
 * 
 * @author lnn
 * 
 */
@Controller
public class BaseController implements WebContext {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	protected ModelAndView toLoginView() {
		return new ModelAndView(new RedirectView(getLoginUrl()), "tourl", this.getRequest().getRequestURI());
	}

	public HttpServletRequest getRequest() {
		RequestContext context = RequestContext.get();
		return context == null ? null : context.getRequest();
	}

	public HttpServletResponse getResponse() {
		RequestContext context = RequestContext.get();
		return context == null ? null : context.getResponse();
	}

	public String getLoginUrl() {
		RequestContext context = RequestContext.get();
		return context == null ? null : context.getLoginUrl();
	}

	public IUser getLoginUer() {
		RequestContext context = RequestContext.get();
		if (context == null)
			return null;
		return context.getLoginUer();
	}

	/**
	 * 保存登录信息
	 * 
	 * @param req
	 * @param res
	 * @param user
	 * @param save
	 */
	public void saveUserInCookie(IUser user, boolean save) {
		RequestContext context = RequestContext.get();
		if (context != null) {
			context.saveUserInCookie(user, save);
		}
	}

	public void deleteUserInCookie() {
		RequestContext context = RequestContext.get();
		if (context != null) {
			context.deleteUserInCookie();
		}
	}

	public String header(String name) {
		return getRequest().getHeader(name);
	}

	public void header(String name, String value) {
		getResponse().setHeader(name, value);
	}

	public void header(String name, int value) {
		getResponse().setIntHeader(name, value);
	}

	public void header(String name, long value) {
		getResponse().setDateHeader(name, value);
	}

	public void deleteCookie(String name, boolean all_domain) {
		RequestUtils.deleteCookie(getRequest(), getResponse(), name, all_domain);
	}

	public String getRemoteIp() {

		RequestContext context = RequestContext.get();
		if (context == null)
			return null;

		return context.getRemoteIp();
	}

	public Cookie cookie(String name) {
		if (StringUtils.isBlank(name))
			return null;

		return getCookies().get(name);
	}

	private Map<String, Cookie> getCookies() {
		RequestContext context = RequestContext.get();
		if (context == null)
			return null;
		return context.getCookies();
	}

	protected String getCookieValue(String name) {
		Cookie cookie = this.cookie(name);
		return cookie == null ? null : cookie.getValue();
	}

	public void cookie(String name, String value, int max_age, boolean all_sub_domain) {
		RequestUtils.setCookie(getRequest(), getResponse(), name, value, max_age, all_sub_domain);
	}

	/**
	 * 加密
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public String encrypt(String value) {
		return RequestContext.encrypt(value);
	}

	/**
	 * 解密
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String value) {
		return RequestContext.decrypt(value);
	}

	/**
	 * 从cookie中读取保存的用户信息
	 * 
	 * @param req
	 * @return
	 */
	public IUser getUserFromCookie() {
		return getLoginUer();
	}

	public void closeCache() {
		header("Pragma", "No-cache");
		header("Cache-Control", "no-cache");
		header("Expires", 0L);
	}

	public long id() {
		return param("id", 0L);
	}

	public String param(String name, String... def_value) {
		String v = getRequest().getParameter(name);

		if (v == null) {
			if (def_value.length > 0) {
				return def_value[0];
			} else {
				return null;
			}

		} else {
			if (StringUtils.isNotEmpty(v))
				return v;
			return null;
		}
	}

	public boolean bparam(String name) {
		String r = param(name, "0");
		return "1".equals(r) || "true".equalsIgnoreCase(r);
	}

	public long param(String name, long def_value) {
		return NumberUtils.toLong(param(name), def_value);
	}

	public int param(String name, int def_value) {
		return NumberUtils.toInt(param(name), def_value);
	}

	public byte param(String name, byte def_value) {
		return (byte) NumberUtils.toInt(param(name), def_value);
	}

	public String[] params(String name) {
		return getRequest().getParameterValues(name);
	}

	public String paramsJoin(String name) {
		String[] str = getRequest().getParameterValues(name);
		return StringUtils.join(str, ";");
	}

	public long[] lparams(String name) {
		String[] values = params(name);
		if (values == null)
			return null;
		return (long[]) ConvertUtils.convert(values, long.class);
	}

	protected Date getDate(String name) {
		String date = param(name);
		return (Date) ConvertUtils.convert(date, Date.class);
	}

	public String uri() {
		return getRequest().getRequestURI();
	}

	public String contextPath() {
		return getRequest().getContextPath();
	}

	public void redirect(String uri) throws IOException {
		getResponse().sendRedirect(uri);
	}

	public void forward(String uri) throws ServletException, IOException {
		RequestDispatcher rd = getRequest().getRequestDispatcher(uri);
		rd.forward(getRequest(), getResponse());
	}

	public void include(String uri) throws ServletException, IOException {
		RequestDispatcher rd = getRequest().getRequestDispatcher(uri);
		rd.include(getRequest(), getResponse());
	}

	/**
	 * spring context里取一个bean
	 * 
	 * @param name
	 * @param cls
	 * @return
	 */
	public <T> T getBean(String name, Class<T> cls) {
		return RequestContextUtils.getWebApplicationContext(getRequest()).getBean(name, cls);
	}

	protected void response(Model m, boolean succ, String message, Object data) {
		m.addAttribute("ok", succ);
		m.addAttribute("msg", message);
		m.addAttribute("data", data);
	}

	protected void responseResult(Model m, boolean succ) {
		m.addAttribute("ok", succ);
	}

	protected void responseMsg(Model m, boolean succ, String message) {
		m.addAttribute("ok", succ);
		m.addAttribute("msg", message);
	}

	protected void responseData(Model m, Object data) {
		m.addAttribute("ok", true);
		m.addAttribute("data", data);
	}

	protected void responseValidError(Model m, BindingResult r) {
		m.addAttribute(r.getFieldErrors());
	}

	protected void responseMsg(ModelAndView m, boolean succ, String message) {
		m.addObject("ok", succ);
		m.addObject("msg", message);
	}

	// @ExceptionHandler
	// public ModelAndView resolveException(EmptyResultDataAccessException e,
	// WebRequest req) {
	// logger.error(e.getMessage(), e);
	// ModelAndView m = new ModelAndView("error/404");
	// responseMsg(m, false, e.getMessage());
	// return m;
	// }
	//
	// @ExceptionHandler
	// public ModelAndView resolveException(Exception e, WebRequest req) {
	// logger.error(e.getMessage(), e);
	// ModelAndView m = new ModelAndView("error/500");
	// m.addObject("href", req.getHeader("referer"));
	// responseMsg(m, false, e.getMessage());
	// return m;
	// }

	public String i18n(String code, Object... args) {
		return I18N.i18n(code, args);
	}

	public String i18n(String code, String defaultMessage, Object... args) {
		return i18n(code, defaultMessage, args);
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		// binder.registerCustomEditor(Date.class, propertyEditor);
	}

	/**
	 * @return now time yyyy-MM-dd HH:mm:ss
	 */
	protected String getDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

	/**
	 * @return now date yyyy-MM-dd
	 */
	protected String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}

	protected Date parse(String str) {
		if (StringUtils.isNotBlank(str)) {
			if (str.length() == 19) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					return sdf.parse(str);
				} catch (ParseException e) {
					logger.error("error", e);
				}
			} else if (str.length() == 10) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					return sdf.parse(str);
				} catch (ParseException e) {
					logger.error("error", e);
				}
			}
		}

		return null;
	}

	static {
		ConvertUtils.register(new SqlDateConverter(null), java.sql.Date.class);
		ConvertUtils.register(new Converter() {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf_time = new SimpleDateFormat("yyyy-MM-dd HH:mm");

			@SuppressWarnings("rawtypes")
			public Object convert(Class type, Object value) {
				if (value == null)
					return null;
				if (value instanceof Date)
					return (value);
				try {
					return sdf_time.parse(value.toString());
				} catch (ParseException e) {
					try {
						return sdf.parse(value.toString());
					} catch (ParseException e1) {
						return null;
					}
				}
			}
		}, java.util.Date.class);
	}

	public <T> T form(Class<T> beanClass) {
		try {
			return RequestContext.get().form(beanClass);
		} catch (Exception e) {
			throw new my.web.ActionException(e.getMessage());
		}
	}

	public void reset() {
		RequestContext.end();
	}

	/**
	 * 模板方法，执行成功自动提交，执行失败自动回滚
	 * 
	 * @param callbak
	 * @return
	 */
	protected AjaxMsg run(CallBack callbak) {
		AjaxMsg msg = null;
		try {
			msg = callbak.call();
			DBManager.commitAll();
		} catch (DBException e) {
			logger.error("error", e);
			DBManager.rollbackAll();
			msg = AjaxMsg.error("数据库异常！" + e.getLocalizedMessage());
		} catch (Exception e) {
			logger.error("error", e);
			DBManager.rollbackAll();
			msg = AjaxMsg.error("服务端业务异常！" + e.getLocalizedMessage());
		} finally {
			DBManager.closeAllConnection();
		}

		closeCache();
		return msg;
	}

	public static interface CallBack {
		public AjaxMsg call() throws Exception;
	}

	// 下载一个静态文件
	protected void download(File file) {
		RequestContext.get().download(file);
	}

	protected void printJson(String jsonString) {
		try {
			closeCache();
			getResponse().setContentType("application/json; charset=UTF-8");
			getResponse().getWriter().println(jsonString);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
