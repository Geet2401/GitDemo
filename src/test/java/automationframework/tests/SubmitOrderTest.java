package automationframework.tests;

import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import automationframework.TestComponents.BaseTest;
import automationframework.pageobjects.CartPage;
import automationframework.pageobjects.CheckoutPage;
import automationframework.pageobjects.LandingPage;
import automationframework.pageobjects.OrderPage;
import automationframework.pageobjects.ProductCatalogue;
import automationframework.pageobjects.confirmationPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest{

	String productName="ZARA COAT 3";
	@Test(dataProvider="getData",groups={"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException
	   {
			
		    LandingPage landingpage=launchApplication();
			ProductCatalogue productcatalogue=landingpage.loginApplication(input.get("email"), input.get("password"));
			
			
			List<WebElement>products=productcatalogue.getProductList();
			productcatalogue.addProductToCart(input.get("product"));
			//productcatalogue.addProductToCart(input.get("product"));
			CartPage cartpage=productcatalogue.goToCartPage();
		
			boolean match=cartpage.VerifyProductDisplay(input.get("product"));
			AssertJUnit.assertTrue(match);
			CheckoutPage checkoutpage=cartpage.goToCheckout();
			checkoutpage.selectCountry("india");
			confirmationPage confirmationpage=checkoutpage.submitOrder();
			String confirmmessage=confirmationpage.getConfirmationMessage();
			
			Assert.assertTrue(confirmmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
			
	}
		//To verify ZARA COAT 3 is displaying in orders page
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productcatalogue=landingpage.loginApplication("geetahans22@gmail.com", "India@1234");
		OrderPage orderPage=productcatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}
	
	

	@DataProvider
	public Object[][] getData() throws IOException
	{
		/*HashMap<String,String> map=new HashMap<String,String>();
		map.put("email", "geetahans22@gmail.com");
		map.put("password", "India@1234");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String,String> map1=new HashMap<String,String>();
		map1.put("email", "geetanjali.hans21@gmail.com");
		map1.put("password", "India@1234");
		map1.put("product", "ADIDAS ORIGINAL");*/
		
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\automationframework\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
		//
		//@DataProvider
		//public Object[][] getData()
		//{
		//return new Object[][] {{"geetahans22@gmail.com","India@1234","ZARA COAT 3"},{"geetanjali.hans21@gmail.com","India@1234","ADIDAS ORIGINAL"}};
	//}
	}
}
