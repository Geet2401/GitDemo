package automationframework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationframework.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
	WebDriver driver;
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
    @FindBy(xpath="//*[@class='form-group']")
    WebElement country;
    
    @FindBy(xpath="//*[@class='actions']/a")
    WebElement submit;
    
    @FindBy(xpath="//*[@class='ta-item list-group-item ng-star-inserted'][2]")
    WebElement selectCountry;
    
    By results=By.xpath("//*[@class='ta-results list-group ng-star-inserted']");
    
    
    public void selectCountry(String countryName)
    {
    	Actions act_country=new Actions(driver);
    	act_country.sendKeys(country,countryName).build().perform();
    	waitForElementToAppear(results);
    	selectCountry.click();
    }
    public confirmationPage submitOrder()
    {
    	submit.click();
    	return new confirmationPage(driver);
    }
}
