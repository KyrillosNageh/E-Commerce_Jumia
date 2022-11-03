package gui.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shaft.gui.element.ElementActions;

import io.qameta.allure.Step;

public class ProductsCartPage {

	/****************************************************************************
	*  >>	Variables
	*****************************************************************************/
	private WebDriver driver;
	
	
	/****************************************************************************
	*  >>	Constructor
	*****************************************************************************/
	public ProductsCartPage (WebDriver driver)
	{
		this.driver =driver;
	}
	
	
	/****************************************************************************
	*  >>	Locators
	*****************************************************************************/
	private static By checkout_Btn()
	{
		return By.xpath("//a[contains(text(),'Checkout')]");
	}

	// Keywords
	
	@Step("Click checkout button.")
	public CheckoutPage clickCheckoutBtn()
	{
		 new ElementActions(driver)
		.click(checkout_Btn());
		
		 return new CheckoutPage(driver);
	}
}
