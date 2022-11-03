package gui.web.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.shaft.gui.element.ElementActions;
import com.shaft.validation.Validations;

import io.qameta.allure.Step;



public class SearchResultsPage {

	/****************************************************************************
	*  >>	Variables
	*****************************************************************************/
	private WebDriver driver;
	public static String searchResult;
	
	
	/****************************************************************************
	*  >>	Constructor
	*****************************************************************************/
	public SearchResultsPage (WebDriver driver)
	{
		this.driver =driver;
	}
	
	
	/****************************************************************************
	*  >>	Locators
	*****************************************************************************/
//	private static By addToCart_Btn()
//	{
//		return By.xpath("//button[text()='Add To Cart']");
//	}
	
	private static By addToCart_Btn()
	{
		return By.xpath("//*[@class='add btn _prim -pea _i -fw']");
	}
	
	public static By productsName_Text(int index)
	{
		return By.xpath("(//*[@class='name'])["+index+"]");
	}
	
	private static By search_TextBar()
	{
		return By.id("fi-q");
	}
	
	private static By search_Btn()
	{
		return By.xpath("//button[text()='Search']");
	}
	
	private static By cart_Link()
	{
		return By.xpath("//*[text()='Cart']");
	}
	
	private static By prodactDataCatalog_Img(int index)
	{
		return By.xpath("//*[@class='prd _fb col c-prd']["+index+"]");
	}
	/*	Validation Items	*/
	private static By productAddedToCart_Msg()
	{
		return By.xpath("//*[contains(text(),'Product added successfully')]");
	}
	
	// Keywords
	
	@Step("Get Name/Text of the product displayed on the page.")
	public  SearchResultsPage getNameOfTheProduct(int index)
	{
		searchResult= new ElementActions(driver)
				.getText(productsName_Text(index)) ;
		return this;
	}
	
	
	@Step("Search about specific product.")
	public  SearchResultsPage searchProductName(String productName)
	{
		new ElementActions(driver)
		.type(search_TextBar(), productName)
		.click(search_Btn());
		
		return this;
	}

	@Step("Add product to cart and click on cart icon to Navigate to cart Page.")
	public ProductsCartPage addProductToCartThenNavigateToCartPage()
	{
		new ElementActions(driver)
		.click(prodactDataCatalog_Img(1))
		.click(addToCart_Btn());
		
		
		 Validations.assertThat()
		 .element(driver, productAddedToCart_Msg())
		 .exists()
		 .perform();
		 
		 new ElementActions(driver)
		 .click(cart_Link());
		
		 return new ProductsCartPage(driver);
	}
	
	
}
