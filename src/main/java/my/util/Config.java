package my.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 
 * 从classpath加载配置文件
 * @author linan
 *
 */
public class Config {
	private final Properties p;

	public Config(String name) {
		p = new Properties();
		InputStreamReader reader = null;
		try {
			InputStream resourceAsStream = Config.class
					.getResourceAsStream(name);
//			if (resourceAsStream == null) {
//				String path = RequestContext.get().getRequest().getSession()
//						.getServletContext().getRealPath("/");
//				File f = new File(path).getParentFile();
//				resourceAsStream = new FileInputStream(new File(f, "conf"
//						+ name));
//			}
			reader = new InputStreamReader(resourceAsStream);
			p.load(reader);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
			}
		}
	}

	public String getValue(String name) {
		return p.getProperty(name);
	}
	
	public String getValue(String name,String defaultvalue) {
		String v = p.getProperty(name);
		if(v==null){
			return defaultvalue;
		}
		return p.getProperty(name);
	}
	
	
	public int getIntValue(String name,String defaultvalue) {
		 String v = getValue(name, defaultvalue);
		 return v==null||v.length()==0?0:Integer.valueOf(v.trim());
	}

}
