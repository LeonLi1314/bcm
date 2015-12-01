package my.util;

import java.util.Locale;

public class DefaultLocale {
	public static Locale locale=Locale.SIMPLIFIED_CHINESE;
	
	
	public static Locale getDefaultLocale(){
		return locale;
	}
	
	
	public static void setDefaultLocale(Locale lc){
		locale=lc;
	}
	
}
