package my.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import my.dao.pool.PoolStatistics;
import my.util.CryptUtils;
import my.util.I18N;
import my.util.MD5FileUtil;
import my.util.SysParam;

public class RequestContext implements WebContext {
	private static Logger logger = LoggerFactory.getLogger(RequestContext.class);
	private static final String COOKIE_LOGIN = "rtmapbcmlogin";// cookie中的用户token
	private final static int MAX_AGE = 86400 * 365;
	private final static byte[] E_KEY = "uukom-user-c".getBytes();
	private final static String UTF_8 = "UTF-8";
	private static final ThreadLocal<RequestContext> requestContext = new NamedThreadLocal<RequestContext>(
			"RequestContext");
	private long start = System.currentTimeMillis();

	public static void begin(HttpServletRequest request, HttpServletResponse response, String loginUrl) {
		RequestContext context = new RequestContext(request, response, loginUrl);
		requestContext.set(context);

		request.setAttribute("LANG", request.getLocale().toString());

		logger.debug("requext context begin");
	}

	public static RequestContext get() {
		return requestContext.get();
	}

	public static void end() {
		RequestContext context = get();
		if (context != null) {
			context.reset();
			requestContext.remove();
		}
//		DBManager.closeAllConnection();
//		if (SysParam.isDevMode()) {
//			logger.debug("requext context end");
//
//		}
		logger.debug("connection count:{}", PoolStatistics.nowCount());
	}

	public RequestContext(HttpServletRequest request, HttpServletResponse response, String loginUrl) {
		this.request = request;
		this.response = response;
		this.loginUrl = loginUrl;
		this.loginUser = null;

		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				cookies.put(cookie.getName(), cookie);
			}
		}
	}

	public void reset() {

		request = null;
		response = null;
		loginUrl = null;
		loginUser = null;
		remoteIp = null;
		cookies.clear();
		cookies = null;

		long timeUsed = System.currentTimeMillis() - start;
		logger.debug("time used:" + timeUsed);

		if (timeUsed > 200) {
			logger.error("耗时超过200ms");
		}

	}

	private HttpServletRequest request;
	private HttpServletResponse response;
	private String loginUrl;
	private IUser loginUser;
	private String remoteIp;
	private Map<String, Cookie> cookies = new HashMap<String, Cookie>();

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public IUser getLoginUer() {
		if (loginUser == null) {
			loginUser = getUserFromCookie();
		}
		return loginUser;
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

		if (remoteIp == null) {
			remoteIp = RequestUtils.getRemoteAddr(getRequest());
		}

		return remoteIp;
	}

	public Cookie cookie(String name) {
		if (StringUtils.isBlank(name))
			return null;

		return getCookies().get(name);
	}

	public Map<String, Cookie> getCookies() {

		return cookies;
	}

	protected String getCookieValue(String name) {
		Cookie cookie = this.cookie(name);
		return cookie == null ? null : cookie.getValue();
	}

	public void cookie(String name, String value, int max_age, boolean all_sub_domain) {
		RequestUtils.setCookie(getRequest(), getResponse(), name, value, max_age, all_sub_domain);
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
		return (v != null) ? v : ((def_value.length > 0) ? def_value[0] : null);
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

	public String i18n(String code, Object... args) {
		return I18N.i18n(code, args);
	}

	public String i18n(String code, String defaultMessage, Object... args) {
		return i18n(code, defaultMessage, args);
	}

	public void forbidden() throws IOException {
		error(HttpServletResponse.SC_FORBIDDEN);
	}

	public void not_found() throws IOException {
		error(HttpServletResponse.SC_NOT_FOUND);
	}

	public void error(int code, String... msg) throws IOException {
		if (msg.length > 0)
			response.sendError(code, msg[0]);
		else
			response.sendError(code);
	}

	public IUser getUserFromCookie() {
		try {
			Cookie cookie = cookie(COOKIE_LOGIN);
			if (cookie != null && StringUtils.isNotBlank(cookie.getValue())) {
				return userFromUUID(cookie.getValue());
			}
		} catch (Exception e) {
			logger.error("error", e);
		}
		return null;
	}

	public IUser userFromUUID(String uuid) {
		if (StringUtils.isBlank(uuid))
			return null;
		String ck = decrypt(uuid);
		final String[] items = ck.split("\\|");
		if (items.length == 5) {
			//String ua = header("user-agent");
			//int ua_code = (ua == null) ? 0 : ua.hashCode();
			//int old_ua_code = Integer.parseInt(items[items.length - 2]);
			//if (ua_code == old_ua_code) {
				return new IUser() {
					@Override
					public String getUserName() {
						return items[0];
					}

					@Override
					public String getUserXm() {
						return items[1];
					}

					@Override
					public String getRoleNames() {
						return "";
					}

					@Override
					public String getUserId() {
						return "";
					}
				};
		//	}
		}
		return null;
	}

	public String genLoginKey(IUser user, String ip, String user_agent) {
		StringBuilder sb = new StringBuilder();
		sb.append(user.getUserName());
		sb.append('|');
		sb.append(user.getUserXm());
		sb.append('|');
		sb.append(user.getRoleNames());
		sb.append('|');
		sb.append(ip);
		sb.append('|');
		sb.append((user_agent == null) ? 0 : user_agent.hashCode());
		sb.append('|');
		sb.append(user.getUserId());
		return encrypt(sb.toString());
	}

	/**
	 * 加密
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String value) {
		byte[] data = CryptUtils.encrypt(value.getBytes(), E_KEY);
		try {
			return URLEncoder.encode(new String(Base64.encodeBase64(data)), UTF_8);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * 解密
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String value) {
		try {
			value = URLDecoder.decode(value, UTF_8);
			if (StringUtils.isBlank(value))
				return null;
			byte[] data = Base64.decodeBase64(value.getBytes());
			return new String(CryptUtils.decrypt(data, E_KEY));
		} catch (UnsupportedEncodingException excp) {
			logger.error("error", excp);
			return null;
		}
	}

	public void saveUserInCookie(IUser user, boolean save) {
		String new_value = genLoginKey(user, getRemoteIp(), header("user-agent"));
		int max_age = save ? MAX_AGE : -1;
		deleteCookie(COOKIE_LOGIN, true);
		cookie(COOKIE_LOGIN, new_value, max_age, true);
	}

	public void deleteUserInCookie() {
		deleteCookie(COOKIE_LOGIN, true);
	}

	public <T> T form(Class<T> beanClass) {
		try {
			T bean = beanClass.newInstance();
			BeanUtils.populate(bean, request.getParameterMap());
			return bean;
		} catch (Exception e) {
			logger.error("error", e);
			throw new ActionException(e.getMessage());
		}
	}

	public void setCookies(Map<String, Cookie> cookies) {
		this.cookies = cookies;
	}

	private static final int bufferSize = 4096;

	public void download(File target) {
		if (target == null || !target.exists() || target.isDirectory() || target.length() < 1) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// String contentType = "application/x-msdownload";
		// String enc = "utf-8";
		// String filename = URLEncoder.encode(target.getName(), enc);
		// getResponse().setHeader("Content-Disposition",
		// "attachment; filename=\"" + filename + "\"");

		String filename = target.getName();

		long pos = 0;
		long fileSize = target.length();

		String md5 = MD5FileUtil.getFileMD5String(target);
		String etag = "W/\"" + md5 + "\"";
		Calendar c = Calendar.getInstance();

		c.add(Calendar.MONTH, 12);

		response.reset();

		response.setDateHeader("Date", new Date().getTime());
		response.setHeader("ETag", etag);
		response.setDateHeader("Expires", c.getTime().getTime());
		response.setDateHeader("Last-Modified", target.lastModified());

		if (getRequest().getHeader("If-None-Match") != null) {
			String match = getRequest().getHeader("If-None-Match");
			if (match.equals(etag)) {
				getResponse().setStatus(HttpServletResponse.SC_NOT_MODIFIED);
				return;
			}
		}

		if (getRequest().getHeader("If-Modified-Since") != null) {
			long d = getRequest().getDateHeader("If-Modified-Since");
			if (target.lastModified() - d > 0) {
				getResponse().setStatus(HttpServletResponse.SC_NOT_MODIFIED);
				return;
			}
		}
		response.setContentType(request.getSession().getServletContext().getMimeType(filename));
		response.setHeader("Accept-Ranges", "bytes");
		response.setHeader("Cache-Control", "public");
		response.setHeader("Cache-Control", "max-age=315360000");
		response.setHeader("Connection", "Keep-Alive");
		response.setHeader("Content-Length", fileSize + "");

		if (getRequest().getHeader("Range") != null) {
			// 若客户端传来Range，说明之前下载了一部分，设置206状态(SC_PARTIAL_CONTENT)
			getResponse().setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
			pos = Long.parseLong(getRequest().getHeader("Range").replaceAll("bytes=", "").replaceAll("-", ""));
		}

		if (pos != 0) {
			String contentRange = new StringBuffer("bytes ").append(new Long(pos).toString()).append("-")
					.append(new Long(fileSize - 1).toString()).append("/").append(new Long(fileSize).toString())
					.toString();
			response.setHeader("Content-Range", contentRange);
		}

		InputStream input = null;

		try {
			input = new FileInputStream(target);
			if (pos > 0) {
				// 略过已经传输过的字节
				input.skip(pos);
			}
			byte[] buf = new byte[bufferSize];
			ServletOutputStream out = getResponse().getOutputStream();
			int readLength;
			while (((readLength = input.read(buf)) != -1)) {
				out.write(buf, 0, readLength);
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error("e", e);
			throw new RuntimeException(e);
		} finally {
			if (input != null) {
				try {
					input.close();
					input = null;
				} catch (IOException e) {
				}
			}
		}

	}

	public void dump() {
		if (SysParam.isDevMode()) {
			Map<String, String[]> map = getRequest().getParameterMap();
			StringBuilder sb = new StringBuilder("");
			Iterator<String> i = map.keySet().iterator();
			while (i.hasNext()) {
				String key = i.next();
				String[] values = getRequest().getParameterValues(key);
				if (values != null) {
					for (String v : values) {
						sb.append(key);
						sb.append("=");
						sb.append(v);
						sb.append("\r\n");
					}
				}
			}

			logger.info(sb.toString());
		}
	}

}