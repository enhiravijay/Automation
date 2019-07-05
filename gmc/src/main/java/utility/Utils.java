package utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utils {
	
	public static String takeScreenShot(WebDriver driver,String ScName) throws Exception {
		try {
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String destination = System.getProperty("user.dir") + "/Screenshots" + ScName + dateName + ".png";
			File finalDestination = new File(destination);
			FileUtils.copyFile(srcFile,finalDestination);
			return destination;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception();
		}
		
	}

}
