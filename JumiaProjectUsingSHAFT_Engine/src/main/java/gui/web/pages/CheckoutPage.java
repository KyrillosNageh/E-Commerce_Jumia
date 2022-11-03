package gui.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shaft.gui.element.ElementActions;
import com.shaft.validation.Validations;

import io.qameta.allure.Step;

public class CheckoutPage {
	/****************************************************************************
	*  >>	Variables
	*****************************************************************************/
	private WebDriver driver;

	
	/****************************************************************************
	*  >>	Constructor
	*****************************************************************************/
	public CheckoutPage (WebDriver driver)
	{
		this.driver =driver;
	}
	
	/****************************************************************************
	*  >>	Locators
	*****************************************************************************/
	private static By customerAddressCity_Txt()
	{
		return By.id("ShippingAddressForm_fk_customer_address_city");
	}
	
	private static By ShippingAddressForm_address_Txt()
	{
		return By.id("ShippingAddressForm_address1");
	}
	
	private static By saveAndContinue_Btn()
	{
		return By.id("osh-opc-btn-save-address-shipping");
	}
	
	private static By ProceedToNextStep_Btn()
	{
		return By.id("osh-opc-btn-save");
	}
	
	private static By ConfirmOrder_Btn()
	{
		return By.xpath("//*[text()='Confirm Order']");
	}
	
	private static By seeOrderDetails_Btn()
	{
		return By.xpath("//*[text()='SEE ORDER DETAILS']");
	}
	
	private static By CashOnDelivery_RadiBox()
	{
		return By.xpath("//*[contains(text(),'Cash On Delivery')]");
	}
	
	/*	Validation Items	*/
	private static By checkoutSuccssfully_Msg()
	{
		return By.xpath("//*[contains(text(),'Thank you for placing an order on Jumia!')]");
	}
	
	// Keywords
	
	@Step("Set and confirm checkout information.")
	public CheckoutPage setAndConfirmCheckoutInformation(String shippingAddress, String city)
	{
		 new ElementActions(driver)
		 .typeAppend(ShippingAddressForm_address_Txt(), shippingAddress)
		 .select(customerAddressCity_Txt(), city)
		 .click(saveAndContinue_Btn())
		 .click(ProceedToNextStep_Btn())
		 .click(CashOnDelivery_RadiBox())
		 .click(ConfirmOrder_Btn());
		
		 Validations.assertThat()
		 .element(driver, CheckoutPage.checkoutSuccssfully_Msg())
		 .exists()
		 .perform();
		 
		 return this;
	}
	
	@Step("Click See Order Details Button.")
	public OrderDetailsPage clickSeeOrderDetailsBtn()
	{
		 new ElementActions(driver)
		 .click(seeOrderDetails_Btn());
		
		return new OrderDetailsPage(driver);
	}
	
}
