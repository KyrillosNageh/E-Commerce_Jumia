package gui.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shaft.gui.element.ElementActions;
import com.shaft.validation.Validations;

import io.qameta.allure.Step;

public class OrderDetailsPage {

	/****************************************************************************
	*  >>	Variables
	*****************************************************************************/
	private WebDriver driver;
	
	
	/****************************************************************************
	*  >>	Constructor
	*****************************************************************************/
	public OrderDetailsPage (WebDriver driver)
	{
		this.driver =driver;
	}
	
	/****************************************************************************
	*  >>	Locators
	*****************************************************************************/
	private static By cnacelItem_Btn()
	{
		return By.name("csrfToken");
	}
	
	private static By orders_Btn()
	{
		return By.xpath("//a[text()='Orders']");
	}
	
	private static By youHaveplacedNoOrders_Msg()
	{
		return By.xpath("//a[text()='You have placed no orders yet!']");
	}
	
	private static By continueShopping_Btn()
	{
		return By.xpath("//a[text()='Continue Shopping']");
	}	
	
	/****************************************************************************
	*  >>	Keywords
	*****************************************************************************/
	
	@Step("Click cancel item Button.")
	public CancellationOrderPage clickCancelItemBtn()
	{
		new ElementActions(driver)
		.click(cnacelItem_Btn());
		
		return new CancellationOrderPage(driver); 
	}
	
	@Step("Click cancel item Button.")
	public OrderDetailsPage checkOpenOrders()
	{
		new ElementActions(driver)
		.click(orders_Btn());
		
		verifyYouHavePlacedNoOrders();
		return new OrderDetailsPage(driver);
	}
	
	@Step("Click continue Shopping Button.")
	public HomePage clickContinueShoppingBtn()
	{
		new ElementActions(driver)
		.click(continueShopping_Btn());
		
		return new HomePage(driver); 
	}
	
	/****************************************************************************
	*  >>	Verification method
	*****************************************************************************/
	
//	Your account has been created!
	@Step("Verify You have placed no orders Msg.")
	public void verifyYouHavePlacedNoOrders()
	{
		Validations.assertThat()
		.element(driver,youHaveplacedNoOrders_Msg())
		.isVisible()
		.perform();
	}
	
}
