package login_page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import basePage.BasePage;
import home_page.HomePage;
import utility.Constant;
import utility.ExcelUtils;

public class LoginPage extends BasePage  {
	
	public WebDriver driver;
	
	@FindBy(xpath="//*[@name='email']")
	WebElement emailTextBox;
	
	@FindBy(xpath="//*[@name='password']")
	WebElement passwordTextBox;
	
	@FindBy(xpath="//*[@class='btn btn-lg btn-success btn-block']")
	WebElement loginSubmitButton;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public HomePage loginfn(int RowNum) throws Exception {
		
		try {
			emailTextBox.click();
			emailTextBox.clear();
			String email = ExcelUtils.getCellData(RowNum, Constant.Col_UserName);
			System.out.println(email);
			emailTextBox.sendKeys(email);
			passwordTextBox.click();
			passwordTextBox.clear();
			String password = ExcelUtils.getCellData(RowNum, Constant.Col_Password);
			System.out.println(password);
			passwordTextBox.sendKeys(password);
			loginSubmitButton.click();
			Thread.sleep(2000);
			return new HomePage(driver);
			
		} catch (Exception e) {
			// TODO: handle exception
			throw(e);
			
		}
		
	}
}
