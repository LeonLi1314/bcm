package my.util;

import java.util.Locale;

import my.web.RequestContext;

import org.springframework.web.servlet.support.RequestContextUtils;


/*
 * 国际化
 */
public class I18N {
	
	
	public static String msg(String code) {
		if(RequestContext.get()!=null){
			Locale locale = RequestContextUtils.getLocale(RequestContext.get().getRequest());
			return RequestContextUtils.getWebApplicationContext(RequestContext.get().getRequest())
					.getMessage(code, null,
							locale);
		}
		return ContextHolder.getApplicationContext().getMessage(code, null,
				DefaultLocale.getDefaultLocale());
	}
	
	public static String i18n(String code, Object... args) {
		if(RequestContext.get()!=null){
			return RequestContextUtils.getWebApplicationContext(RequestContext.get().getRequest())
					.getMessage(code, args,
							RequestContextUtils.getLocale(RequestContext.get().getRequest()));
		}
		return ContextHolder.getApplicationContext().getMessage(code, args,
				DefaultLocale.getDefaultLocale());
	}

	public static String i18n(String code, String defaultMessage,
			Object... args) {
		if(RequestContext.get()!=null){
			return RequestContextUtils.getWebApplicationContext(RequestContext.get().getRequest())
					.getMessage(code, args,defaultMessage,
							RequestContextUtils.getLocale(RequestContext.get().getRequest()));
		}
		return ContextHolder.getApplicationContext().getMessage(code, args,
				defaultMessage, DefaultLocale.getDefaultLocale());
	}
}
