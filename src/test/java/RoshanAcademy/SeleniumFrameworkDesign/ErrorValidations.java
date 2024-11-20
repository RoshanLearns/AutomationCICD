package RoshanAcademy.SeleniumFrameworkDesign;

import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import org.testng.AssertJUnit;
import io.github.bonigarcia.wdm.WebDriverManager;
import roshanAcademy.pageObjects.CartPage;
import roshanAcademy.pageObjects.CheckoutPage;
import roshanAcademy.pageObjects.ConfirmationPage;
import roshanAcademy.pageObjects.LandingPage;
import roshanAcademy.pageObjects.ProductCatalogue;

import java.io.IOException;
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
import org.testng.annotations.Test;

import RoshanAcademy.testComponents.BaseTest;

public class ErrorValidations extends BaseTest {
	
	@Test(groups= {"ErrorHandling"},retryAnalyzer=RoshanAcademy.testComponents.Retry.class)
	public void loginErrorValidation() throws InterruptedException, IOException {

		landingPage.applicationLogin("roshan177@gmail.com", "ahul1!2");
		Assert.assertEquals("ncorrect email or password.", landingPage.getErrorMessage());
		

	}

}
