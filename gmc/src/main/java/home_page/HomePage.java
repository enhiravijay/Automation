package home_page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import basePage.BasePage;

public class HomePage extends BasePage{

	public WebDriver driver;
	
	public HomePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		PageFactory.initElements(driver, this);
	}

	
}
