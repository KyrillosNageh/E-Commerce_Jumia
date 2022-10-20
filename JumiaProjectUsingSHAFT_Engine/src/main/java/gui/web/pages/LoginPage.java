package gui.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shaft.gui.element.ElementActions;

import io.qameta.allure.Step;

public class LoginPage {
	/****************************************************************************
	*  >>	Variables
	*****************************************************************************/
	private WebDriver driver;
	
	
	/****************************************************************************
	*  >>	Constructor
	*****************************************************************************/
	public LoginPage (WebDriver driver)
	{
		this.driver =driver;
	}
	
	
	/****************************************************************************
	*  >>	Locators
	*****************************************************************************/
	private static By email_TxtField()
	{
		return By.name("email");
	}
	
	private static By phoneNumber_TxtField()
	{
		return By.name("phone[number]");
	}
	
	private static By password_TxtField()
	{
		return By.name("password");
	}
	
	private static By continue_Btn()
	{
		return By.xpath("//*[@class='mdc-button mdc-button--touch mdc-button--raised access-btn mdc-ripple-upgraded']");
	}
	
	private static By login_Btn()
	{
		return By.id("loginButton");
	}
	
	private static By loginWithPhone_Btn()
	{
		return By.id("login-with-phone");
	}
	
	/****************************************************************************
	*  >>	Keywords
	*****************************************************************************/
	@Step("Login as default User with E-mail.")
	public HomePage loginAsDefaultUserWithEmail(String email , String password)
	{
		new ElementActions(driver)
		.type(email_TxtField(), email)
		.click(continue_Btn())
		.type(password_TxtField(), password)
		.click(login_Btn());

		return new HomePage(driver);
	}
	
	@Step("Login as default User with phone number.")
	public HomePage loginAsDefaultUserWithPhoneNumber(String phoneNumber , String password)
	{
		new ElementActions(driver)
		.click(loginWithPhone_Btn())
		.type(phoneNumber_TxtField(), phoneNumber)
		.click(continue_Btn())
		.type(password_TxtField(), password)
		.click(login_Btn());

		return new HomePage(driver);
	}
	
	@Step("Navigate to create account with E-mail.")
	public CreateAccountPage navigaateToCreateAccountWithEmail(String email )
	{
		new ElementActions(driver)
		.type(email_TxtField(), email)
		.click(continue_Btn());

		return new CreateAccountPage(driver);
	}
	
}
