package automationframework.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import automationframework.TestComponents.BaseTest;
import automationframework.TestComponents.Retry;
import automationframework.pageobjects.CartPage;
import automationframework.pageobjects.CheckoutPage;
import automationframework.pageobjects.LandingPage;
import automationframework.pageobjects.ProductCatalogue;
import automationframework.pageobjects.confirmationPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups={"Error Handling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException
	   {
			//String ProductName="ZARA COAT 3";
		    landingpage.loginApplication("geetahans22@gmail.com", "India@1236664");
			AssertJUnit.assertEquals("Incorrect email or password", landingpage.getErrorMessage());
			
		}
	public void ProductErrorValidation() throws IOException
	   {
			String ProductName="ZARA COAT 3";
		    LandingPage landingpage=launchApplication();
			ProductCatalogue productcatalogue=landingpage.loginApplication("geetanjalihans21@gmail.com", "India@1234");
			
			
			List<WebElement>products=productcatalogue.getProductList();
			productcatalogue.addProductToCart(ProductName);
			productcatalogue.addProductToCart(ProductName);
			CartPage cartpage=productcatalogue.goToCartPage();
		
			boolean match=cartpage.VerifyProductDisplay("ZARA COAT 33");
			AssertJUnit.assertFalse(match);
	   }

}
