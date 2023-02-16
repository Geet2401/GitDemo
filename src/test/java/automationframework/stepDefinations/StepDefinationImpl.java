package automationframework.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import automationframework.TestComponents.BaseTest;
import automationframework.pageobjects.CartPage;
import automationframework.pageobjects.CheckoutPage;
import automationframework.pageobjects.LandingPage;
import automationframework.pageobjects.ProductCatalogue;
import automationframework.pageobjects.confirmationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImpl extends BaseTest{
	public LandingPage landingPage;
	public ProductCatalogue productcatalogue;
	public CartPage cartpage;
	public confirmationPage confirmationpage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage=launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password)
	{
		productcatalogue=landingpage.loginApplication(username,password);
	}
	
	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_cart(String productName)
	{
		List<WebElement>products=productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
	}
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName)
	{
		cartpage=productcatalogue.goToCartPage();
		
		boolean match=cartpage.VerifyProductDisplay(productName);
		AssertJUnit.assertTrue(match);
		CheckoutPage checkoutpage=cartpage.goToCheckout();
		checkoutpage.selectCountry("india");
		confirmationpage=checkoutpage.submitOrder();
	}
	
	@Then("{string} message is displayed on the ConfirmationPage")
	public void message_is_displayed_confirmation_page(String string)
	{
		String confirmmessage=confirmationpage.getConfirmationMessage();
		
		Assert.assertTrue(confirmmessage.equalsIgnoreCase(string));
	}
	@Then("^\"([^\"]*)\" message is displayed$")
	public void something_message_is_displayed(String strArg1)
	{
		Assert.assertEquals("Incorrect email or password", landingpage.getErrorMessage());
		driver.close();
	}
}
