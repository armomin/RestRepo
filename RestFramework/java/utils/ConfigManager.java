package utils;

import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ConfigManager {
	private ConfigManager() {
	}

	private static Properties props = new Properties();
	private static String strFileName = "./resources/config.properties";
	static String strValue;

	public static String getProperty(String strKey) {
		try {
			File f = new File(strFileName);
			if (f.exists()) {
				FileInputStream in = new FileInputStream(f);
				props.load(in);
				strValue = props.getProperty(strKey);
				in.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("propert file not found");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("cannot read property");
		}
		return strValue;
	}

	
	
	
	public static void setProperty(String strKey,String strValue) {
		try {
			File f = new File(strFileName);
			if (f.exists()) {
				FileInputStream in = new FileInputStream(f);
				props.load(in);
				props.setProperty(strKey,strValue);
				props.store(new FileOutputStream(strFileName), strValue);
				in.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("propert file not found");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("cannot read property");
		}
		
	}

}
