package gui.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shaft.gui.browser.BrowserActions;
import com.shaft.gui.element.ElementActions;

import io.qameta.allure.Step;

public class HomePage {

	/****************************************************************************
	*  >>	Variables
	*****************************************************************************/
	private WebDriver driver;
	private final String home_url= "https://www.jumia.com.eg";
	
	
	/****************************************************************************
	*  >>	Constructor
	*****************************************************************************/
	public HomePage (WebDriver driver)
	{
		this.driver =driver;
	}
	
	
	/****************************************************************************
	*  >>	Locators
	*****************************************************************************/
	private static By closePopUp_MsgBtn()
	{
		return By.xpath("//section[@aria-labelledby='pop-ttl'] //*[@aria-label ='newsletter_popup_close-cta']");
	}
	
	private static By account_btn()
	{
		return By.xpath("//*[@for='dpdw-login']");
	}
	
	private static By signIn_btn()
	{
		return By.xpath("//*[text()='Sign In']");
	}
	
	public static By registeredUserName_Txt()
	{
		return By.xpath("//label[@class='trig -df -i-ctr -fs16' and contains(text(),'Hi')]");
	}
	
	/****************************************************************************
	*  >>	Keywords
	*****************************************************************************/
	@Step("Navigate to Jumia url >> https://www.jumia.com.eg")
	public HomePage navigate()
	{
		BrowserActions.navigateToURL(driver, home_url);
		return this;
	}
	
	@Step("Close pop up of subscribe to our newsletter.")
	public HomePage closeHomePagePopUp()
	{
		ElementActions.click(driver, closePopUp_MsgBtn());
		return this;
	}
	
	@Step("Click on sign in link..")
	public LoginPage clickSignInBtn()
	{
		new ElementActions(driver).click(account_btn());
		new ElementActions(driver).click(signIn_btn());
		
		return new LoginPage(driver);
	}
}
