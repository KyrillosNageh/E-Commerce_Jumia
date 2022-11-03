package gui.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shaft.gui.element.ElementActions;
import com.shaft.validation.Validations;

import io.qameta.allure.Step;

public class CreateAccountPage {

	/****************************************************************************
	*  >>	Variables
	*****************************************************************************/
	private WebDriver driver;
	
	
	/****************************************************************************
	*  >>	Constructor
	*****************************************************************************/
	public CreateAccountPage (WebDriver driver)
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
	
	private static By password_TxtField()
	{
		return By.name("password");
	}
	
	private static By confirmPassword_TxtField()
	{
		return By.xpath("//*[@aria-labelledby='confirm-password']");
	}
	
	private static By continuePersonalDetails_Btn()
	{
		return By.xpath("//*[@class='mdc-button mdc-button--touch mdc-button--raised to-personal-details-part-2 mdc-ripple-upgraded']");
	}
	
	private static By continueCreateAccount_Btn()
	{
		return By.xpath("//*[@class='mdc-button mdc-button--touch mdc-button--raised to-personal-details mdc-ripple-upgraded']");
	}
	
	private static By continueConfirmPersonalDetails_Btn()
	{
		return By.id("confirmBtn");
	}
	
	private static By fristName_TxtField()
	{
		return By.id("input_first_name");
	}
	
	private static By lastName_TxtField()
	{
		return By.id("input_last_name");
	}
	
	private static By phoneNumber_TxtField()
	{
		return By.name("phone[number]");
	}
	
	private static By gender_Btn()
	{
		return By.id("gender");
	}
	
	private static By gender_Selector(String gendar)
	{
		return By.xpath("//*[@class='mdc-deprecated-list-item__text' and contains(text(),'"+gendar+"')]");
	}
	
	private static By birth_date()
	{
		return By.id("input_birth_date");
	}
	
	private static By acceptTermsCondition_CheckBox()
	{
		return By.id("acceptTC");
	}
	
	private static By skip_Btn()
	{
		return By.id("skip-pin");
	}
	
	private static By getStarted_Btn()
	{
		return By.xpath("//*[@type='submit']");
	}
	
	
	/*	Validation Items	*/
	public static By accoutCreated_Msg()
	{
		return By.xpath("//*[@class='my-name']");
	}
	
	private static By accountCreatedSuccssfully_Msg(String name)
	{
		return By.xpath("//*[contain(text(),'" + name + ", Your account has been created!')]");
	}
	
	/****************************************************************************
	*  >>	Keywords
	*****************************************************************************/
	@Step("Create account with E-mail.")
	public HomePage createAccountWithEmail(String password, String fristName, String lastName,
			String Phone,String gender, String birthDate)
	{
		new ElementActions(driver)
		.type(password_TxtField(), password)
		.type(confirmPassword_TxtField(), password)
		.click(continueCreateAccount_Btn())
		.type(fristName_TxtField(), fristName)
		.type(lastName_TxtField(), lastName)
		.type(phoneNumber_TxtField(), Phone)
		.click(continuePersonalDetails_Btn())
		.click(gender_Btn())
		.click(gender_Selector(gender));
		driver.findElement(birth_date()).sendKeys(birthDate);

		new ElementActions(driver)
		.click(acceptTermsCondition_CheckBox())
		.click(continueConfirmPersonalDetails_Btn())
		.click(skip_Btn())
		.click(getStarted_Btn());
		
	
		return new HomePage(driver);
	}
	
	@Step("Create account with Phone number.")
	public HomePage createAccountWithPhone(String password, String fristName, String lastName,
			String email,String gender, String birthDate)
	{
		new ElementActions(driver)
		.type(password_TxtField(), password)
		.type(confirmPassword_TxtField(), password)
		.click(continueCreateAccount_Btn())
		.type(fristName_TxtField(), fristName)
		.type(lastName_TxtField(), lastName)
		.type(email_TxtField(), email)
		.click(continuePersonalDetails_Btn())
		.click(gender_Btn())
		.click(gender_Selector(gender));
		driver.findElement(birth_date()).sendKeys(birthDate);

		new ElementActions(driver)
		.click(acceptTermsCondition_CheckBox())
		.click(continueConfirmPersonalDetails_Btn())
		.click(skip_Btn());
		
		verifyAccountCreatedSuccessfullyMsg(fristName);
	
		return new HomePage(driver);
	}
	
	
	/****************************************************************************
	*  >>	Verification method
	*****************************************************************************/
	
//	Your account has been created!
	@Step("Verify Account created successfuly Msg.")
	public void verifyAccountCreatedSuccessfullyMsg(String userName)
	{
		Validations.assertThat()
		.element(driver, accountCreatedSuccssfully_Msg(userName))
		.isVisible()
		.perform();
	}
}
