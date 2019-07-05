package utility;

import java.io.FileInputStream;
import java.util.Properties;

import testBase.TestBase;

public class Config extends TestBase{
	
	public static Properties prop;
	
	
	public static Properties loadPropertyFile() throws Exception {
		
		Properties prop = new Properties();
		FileInputStream fi = new FileInputStream("src\\main\\java\\config\\config.properties");
		prop.load(fi);
		return prop;
	}
	
	
	
	

}
