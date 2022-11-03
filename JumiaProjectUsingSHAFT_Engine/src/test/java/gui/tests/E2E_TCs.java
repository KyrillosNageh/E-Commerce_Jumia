package gui.tests;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shaft.driver.DriverFactory;
import com.shaft.gui.browser.BrowserActions;
import com.shaft.tools.io.JSONFileManager;
import gui.web.pages.HomePage;
import io.qameta.allure.Description;


public class E2E_TCs
{
	private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private JSONFileManager userTestData = new JSONFileManager("src\\test\\resources\\testDataFiles\\UsersData.json");;
	
	@Test(description = "Valid as a Logged user could add item to cart then checkout.")
	@Description("as a user create Account then search about spacific product and add it to cart then checkout.")
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
				userTestData.getTestData("Valid_birthDate"))
		.searchProductName(userTestData.getTestData("Product_Name"))
		.addProductToCartThenNavigateToCartPage()
		.clickCheckoutBtn()
		.setAndConfirmCheckoutInformation("Cairo", "Gesr Al Suez")
		.clickSeeOrderDetailsBtn();	
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
