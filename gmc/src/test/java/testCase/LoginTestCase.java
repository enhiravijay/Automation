package testCase;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.model.Media;

import login_page.LoginPage;
import testBase.TestBase;
import utility.Constant;
import utility.ExcelUtils;

public class LoginTestCase extends TestBase {

	public WebDriver driver;
	public Properties prop;
	public Constant constant;
	public int iTestCaseRow = 1;
	public int iTestCaseRow1 = 2;
	public int iTestCaseRow3 = 3;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTestInterruptedException testexception;
	
	// just adding comment to check git.
	@BeforeSuite
	public void beforeSuite() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/MyOwnReport.html", true);
		extent.loadConfig(new File("extent-config.xml"));
		extent.addSystemInfo("Environment", "Qa-Sanity");
	}

	@BeforeMethod
	public void beforeMethod(Method method) throws Exception {
		driver = openBrowser();
		prop = getprop();
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "Sheet1");
		System.out.println(prop);
		driver.get(prop.getProperty("qaurl"));
		test = extent.startTest((this.getClass().getSimpleName() + " :: " + method.getName()), method.getName());
		test.assignAuthor("Vijay C");
	}

	@Test(priority = 1, description = "superadmin negative login")
	public void Login() throws Exception {

		String sf = checkRunMode(iTestCaseRow1);
		System.out.println(sf);
		try {
			if (sf.equalsIgnoreCase("Y")) {
				LoginPage lp = new LoginPage(driver);
				lp.loginfn(iTestCaseRow1);
				Thread.sleep(2000);
				String Actual = driver.getCurrentUrl();
				System.out.println(Actual);
				softverification(Actual, "http://qa.gmc.rupeeboss.com/dashboard", iTestCaseRow1);
				test.log(LogStatus.PASS, "Test is passed");

			} else {
				System.out.println("Runmode for this is set as No");
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	@Test(priority = 2, description = "admin positive login")
	public void Login1() throws Exception {

		String sf = checkRunMode(iTestCaseRow);
		System.out.println(sf);
		try {
			if (sf.equalsIgnoreCase("Y")) {
				LoginPage lp = new LoginPage(driver);
				lp.loginfn(iTestCaseRow);
				Thread.sleep(2000);
				String Actual = driver.getCurrentUrl();
				System.out.println(Actual);
				softverification(Actual, "http://qa.gmc.rudpeeboss.com/dashboard", iTestCaseRow);
				test.log(LogStatus.FAIL, "Test is failed");
			} else {
				System.out.println("Runmode for this is set as No");
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	@Test(enabled = false, description = "admi3n positive login")
	public void Login3() throws Exception {

		String sf = checkRunMode(iTestCaseRow3);
		try {
			if (sf.equalsIgnoreCase("Y")) {
				LoginPage lp = new LoginPage(driver);
				lp.loginfn(iTestCaseRow);
				Thread.sleep(2000);
				String Actual = driver.getCurrentUrl();
				System.out.println(Actual);
				softverification(Actual, "http://qa.gmc.rupeeboss.com/dashboard", iTestCaseRow3);
				test.log(LogStatus.INFO, "This is third test");
			} else {
				System.out.println("Runmode for this is set as No");
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}
	
	@Test(description = "force fail")
	public void forcefail() throws Exception {

		assertTrue(false);

	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws Throwable {
		
		if(result.getStatus()==ITestResult.FAILURE) {
			String temp = utility.Utils.takeScreenShot(driver, result.getName());
			test.log(LogStatus.FAIL,result.getThrowable().getMessage(),test.addScreenCapture(temp));
		}
		extent.endTest(test);
		extent.flush();
		driver.close();
		driver.quit();
	}

	@AfterSuite
	public void afterSuite() {
		extent.close();
	}

}
