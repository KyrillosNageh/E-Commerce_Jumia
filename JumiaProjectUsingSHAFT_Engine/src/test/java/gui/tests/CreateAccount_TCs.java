package gui.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shaft.driver.DriverFactory;
import com.shaft.gui.browser.BrowserActions;
import com.shaft.tools.io.JSONFileManager;
import com.shaft.validation.Validations;

import gui.web.pages.CreateAccountPage;
import gui.web.pages.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;

@Feature("GUI")
public class CreateAccount_TCs {

	/****************************************************************************
	*  >>	Variables
	*****************************************************************************/
	private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private JSONFileManager userTestData = new JSONFileManager("src\\test\\resources\\testDataFiles\\UsersData.json");;
	
	/****************************************************************************
	*  >>	Test Cases
	*****************************************************************************/
	@Test(description = "Valid create account by using E-mail")
	@Description("When the User enters valid data and clicks on continue, Then the account should be created successfully.")
	@Story("Create Account")
	@Severity(SeverityLevel.CRITICAL)
	@TmsLink("JUM-5")
	public void createAccountWithValidData()
	{
		new HomePage(driver.get())
		.navigate()
		.closeHomePagePopUp()
		.clickSignInBtn()
		.navigaateToCreateAccountWithEmail(userTestData.getTestData("Valid_E-mail"))
		.createAccountWithEmail(userTestData.getTestData("Valid_password"),
				userTestData.getTestData("Valid_fristName"),
				userTestData.getTestData("Valid_lastName"),
				userTestData.getTestData("Valid_Phone"), 
				userTestData.getTestData("Valid_gender"),
				userTestData.getTestData("Valid_birthDate"));
		
		Validations.assertThat()
		.element(driver.get(), CreateAccountPage.accoutCreated_Msg())
		.text().contains(userTestData.getTestData("Valid_fristName"))
		.perform();   
	}

	/*****************************************************************************/
	
	@BeforeMethod
	public void beforeMethod(){
		driver.set(DriverFactory.getDriver());   
		BrowserActions.navigateToURL(driver.get(), "https://www.google.com/ncr", "https://www.google.com");
	}

	@AfterMethod
	public void afterMethod(){
		BrowserActions.closeCurrentWindow(driver.get());
	}
	
	
}
