package RoshanAcademy.SeleniumFrameworkDesign;

import io.github.bonigarcia.wdm.WebDriverManager;
import roshanAcademy.pageObjects.LandingPage;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTestOriginal {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();

		driver.manage().window().maximize();
		

		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		
		LandingPage landingPage = new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("roshan177@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Rahul1!2");
		driver.findElement(By.id("login")).click();
		
		String productName = "ZARA COAT 3";
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream()
				.filter(product->product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3"))
				.findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner")));
		
		driver.findElement(By.cssSelector("[routerlink*=cart]")).click();
		
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		Thread.sleep(5000);
		
		Actions a = new Actions(driver);
		
		WebElement checkoutButton = driver.findElement(By.cssSelector(".totalRow button"));
		a.moveToElement(checkoutButton).build().perform();
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("window.scrollBy(0,100)");
		//js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		checkoutButton.click();
		
		
		
		//driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("ind");
		//List<WebElement> countryLists = driver.findElements(By.xpath("//section[@class='ta-results list-group ng-star-inserted']//span"));
		
		//countryLists.stream().collect();
		
		//Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='ta-results list-group ng-star-inserted']//span")));
		
		driver.findElement(By.xpath("//section[@class='ta-results list-group ng-star-inserted']//span[text()=\" India\"]")).click();
		
		js.executeScript("window.scrollBy(0,2000)", "");
		
		WebElement submit = driver.findElement(By.cssSelector(".action__submit"));
		a.moveToElement(submit).build().perform();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
		
		
	
	}

}
