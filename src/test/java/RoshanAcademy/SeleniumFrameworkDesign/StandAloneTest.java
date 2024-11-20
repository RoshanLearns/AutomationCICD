package RoshanAcademy.SeleniumFrameworkDesign;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import io.github.bonigarcia.wdm.WebDriverManager;
import roshanAcademy.pageObjects.CartPage;
import roshanAcademy.pageObjects.CheckoutPage;
import roshanAcademy.pageObjects.ConfirmationPage;
import roshanAcademy.pageObjects.LandingPage;
import roshanAcademy.pageObjects.OrderPage;
import roshanAcademy.pageObjects.ProductCatalogue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import RoshanAcademy.testComponents.BaseTest;

public class StandAloneTest extends BaseTest {
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void standAloneTest(HashMap<String, String> input) throws InterruptedException, IOException {
		//public void standAloneTest(String username, String pwd, String productName)
		ProductCatalogue productCatalogue = landingPage.applicationLogin(input.get("username"), input.get("pwd"));

		

		List<WebElement> productList = productCatalogue.getProductList();

		productCatalogue.addProductToCart(input.get("productName"));

		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));

		Assert.assertTrue(match);

		Thread.sleep(5000);

		Actions a = new Actions(driver);

		CheckoutPage checkOutPage = cartPage.checkoutButton(a);

		JavascriptExecutor js = (JavascriptExecutor) driver;

		String countryName = "india";
		checkOutPage.selectCountry(a, countryName);
		ConfirmationPage confirmationPage = checkOutPage.submitOrder(a);

		String confirmMessage = confirmationPage.getMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));


	}
	
	@Test(dependsOnMethods = {"standAloneTest"})
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.applicationLogin("roshan177@gmail.com", "Rahul1!2");

		
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		/*
		 * HashMap<String, String> map = new HashMap<String, String>();
		 * map.put("username", "roshan177@gmail.com"); map.put("pwd", "Rahul1!2");
		 * map.put("productName", "ZARA COAT 3");
		 * 
		 * HashMap<String, String> map2 = new HashMap<String, String>();
		 * map2.put("username", "roshan177@gmail.com"); map2.put("pwd", "Rahul1!2");
		 * map2.put("productName", "ADIDAS ORIGINAL");
		 */
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\RoshanAcademy\\data\\PurchaseData.json");
		return new Object[][] { {data.get(0)},{data.get(1)} };
		//return new Object[][] { {"roshan177@gmail.com","Rahul1!2","ZARA COAT 3"}, {"roshan177@gmail.com","Rahul1!2","ADIDAS ORIGINAL"}};
	}
	
	

}
