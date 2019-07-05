package testBase;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;
import basePage.BasePage;
import utility.Config;
import utility.Constant;
import utility.ExcelUtils;
import utility.Utils;

public class TestBase {
	
	
	public WebDriver driver;
	Properties prop;
	BasePage basepage;

	
	
	
	public WebDriver openBrowser() {
		
		System.setProperty("webdriver.chrome.driver","src\\test\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20000, TimeUnit.SECONDS);
		return driver;
	}
	
	public Properties getprop() throws Exception {
		Config con = new Config();
		prop=Config.loadPropertyFile();
		return prop;
		
	}
	
	public void softverification(String Actual,String Expected,int iTestCaseRow) throws Exception {
		if(Expected.equalsIgnoreCase(Actual)) {
			BasePage.bResult=true;
			System.out.println("value are matching");
			System.out.println("act and expe match"+BasePage.bResult);
			ExcelUtils.setCellData("Pass",iTestCaseRow,Constant.Col_Result);
			
			
		}else {
			SoftAssert softassert = new SoftAssert();
			softassert.assertTrue(Expected.equals(Actual));
			System.out.println("this is actual"+" " +Actual);
			System.out.println("this is Expected"+Expected);
			System.out.println("first test");
			BasePage.bResult=false;
			System.out.println(BasePage.bResult);
			if(BasePage.bResult==false) {
				System.out.println(BasePage.bResult);
				ExcelUtils.setCellData("Fail",iTestCaseRow,Constant.Col_Result);
			}
			
			softassert.assertAll();	
						
		}
		
		
	}
	
	public static String checkRunMode(int itestCaseRow) {
		return ExcelUtils.getCellData(itestCaseRow,Constant.Col_RunMode);
		
	}
	


}
