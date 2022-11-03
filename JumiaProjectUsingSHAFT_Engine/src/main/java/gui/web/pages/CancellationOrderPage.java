package gui.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shaft.gui.element.ElementActions;
import com.shaft.validation.Validations;

import io.qameta.allure.Step;

public class CancellationOrderPage {


	/****************************************************************************
	*  >>	Variables
	*****************************************************************************/
	private WebDriver driver;
	
	
	/****************************************************************************
	*  >>	Constructor
	*****************************************************************************/
	public CancellationOrderPage (WebDriver driver)
	{
		this.driver =driver;
	}
	
	/****************************************************************************
	*  >>	Locators
	*****************************************************************************/
	private static By quantity_selector()
	{
		return By.id("fi-quantity");
	}
	
	private static By reasonToCancelOrder_selector()
	{
		return By.id("fi-reason");
	}
	
	private static By submit_Btn()
	{
		return By.xpath("//*[text()='Submit']");
	}
	
	private static By orderCancelledSuccssfully_Msg()
	{
		return By.xpath("//*[text()='Your request has been submitted successfully!']");
	}
	
	private static By goBackToOrderDetails_Btn()
	{
		return By.xpath("//*[text()='Go back to Order Details']");
	}
	
	/****************************************************************************
	*  >>	Keywords
	*****************************************************************************/
	
	@Step("Click cancel item Button.")
	public CancellationOrderPage clickCancelItemBtn()
	{
		new ElementActions(driver)
		.select(quantity_selector(), "1")
		.select(reasonToCancelOrder_selector(), "Found a better offer in Jumia")
		.click(submit_Btn());
		
		verifyOrderCancelleddSuccessfullyMsg();

		new ElementActions(driver)
		.click(goBackToOrderDetails_Btn());
		
		return this;
	}
	
	@Step("Click cancel item Button.")
	public OrderDetailsPage clickGoBackToOrderDetailsBtn()
	{
		new ElementActions(driver)
		.click(goBackToOrderDetails_Btn());
		
		return new OrderDetailsPage(driver);
	}
	/****************************************************************************
	*  >>	Verification method
	*****************************************************************************/
	
//	Your account has been created!
	@Step("Verify order Cancelled successfuly Msg.")
	public void verifyOrderCancelleddSuccessfullyMsg()
	{
		Validations.assertThat()
		.element(driver, orderCancelledSuccssfully_Msg())
		.isVisible()
		.perform();
	}
}
