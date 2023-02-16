package automationframework.tests;

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

import automationframework.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

	public static WebDriver driver;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			//WebDriverManager.chromedriver().setup();
			//WebDriver driver=new ChromeDriver();
			String ProductName="ZARA COAT 3";
			System.setProperty("webdriver.chrome.driver","C:\\Users\\geetanjali.hans\\Downloads\\chromedriver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize(); 
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get("https://rahulshettyacademy.com/client");
			LandingPage landingpage=new LandingPage(driver);
			driver.findElement(By.id("userEmail")).sendKeys("geetahans22@gmail.com");
			driver.findElement(By.id("userPassword")).sendKeys("India@1234");
			driver.findElement(By.id("login")).click();
			
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
			
			List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
			WebElement prod=products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
			prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
			
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
			//.ng animating
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
			driver.findElement(By.xpath("//*[@routerlink='/dashboard/cart']")).click();
			
			List<WebElement> cartProducts=driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
			Boolean match=cartProducts.stream().anyMatch(cartprod->cartprod.getText().equalsIgnoreCase(ProductName));
			Assert.assertTrue(match);
			
			driver.findElement(By.xpath("//*[@class='totalRow']/button")).click();
			
			Actions act_country=new Actions(driver);
			WebElement country=driver.findElement(By.xpath("//*[@class='form-group']"));
			act_country.sendKeys(country, "india").build().perform();
		
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='ta-results list-group ng-star-inserted']")));
			driver.findElement(By.xpath("//*[@class='ta-item list-group-item ng-star-inserted'][2]")).click();
			driver.findElement(By.xpath("//*[@class='actions']/a")).click();
			
			String confirmessage=driver.findElement(By.xpath("//*[@class='hero-primary']")).getText();
			
			Assert.assertTrue(confirmessage.equalsIgnoreCase("THANK YOU FOR THE ORDER"));
			driver.close();
	}

}
