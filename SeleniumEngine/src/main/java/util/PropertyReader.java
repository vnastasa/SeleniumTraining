package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

	public String getProp(String property) {
		InputStream is = getClass().getClassLoader().getResourceAsStream("credentials.properties");
		Properties props = new Properties();
		try {
			props.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return props.getProperty(property);
	}
}
