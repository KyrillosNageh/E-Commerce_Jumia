package gui.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.shaft.driver.DriverFactory;
import com.shaft.gui.browser.BrowserActions;
import com.shaft.tools.io.JSONFileManager;
import com.shaft.validation.Validations;
import gui.web.pages.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;

public class Login_TCs {
	/****************************************************************************
	*  >>	Variables
	*****************************************************************************/
	private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private JSONFileManager userTestData = new JSONFileManager("src\\test\\resources\\testDataFiles\\UsersData.json");;
	
	/****************************************************************************
	*  >>	Test Cases
	*****************************************************************************/


	@Test(description = "Valid login by using valid E-mail and valid passowrd", dependsOnGroups = {"CreateAccount_TCs.createAccountWithValidData"})
	@Description("When the User enters valid E-mail/PhonNumber,Passoword and clicks on Login."
			+ "   Then the account should be created successfully.")
	@Story("Login")
	@Severity(SeverityLevel.CRITICAL)
	@TmsLink("JUM-5")
	public void loginWithValidData()
	{
		new HomePage(driver.get())
		.navigate()
		.closeHomePagePopUp()
		.clickSignInBtn()
		.loginAsDefaultUserWithEmail(userTestData.getTestData("Valid_E-mail"),
				userTestData.getTestData("Valid_password"));
		
		
		Validations.assertThat()
		.element(driver.get(), HomePage.registeredUserName_Txt())
		.text().contains(userTestData.getTestData("Valid_fristName"))
		.perform();   
	}
	
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