package RoshanAcademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import RoshanAcademy.testComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import roshanAcademy.pageObjects.CartPage;
import roshanAcademy.pageObjects.CheckoutPage;
import roshanAcademy.pageObjects.ConfirmationPage;
import roshanAcademy.pageObjects.LandingPage;
import roshanAcademy.pageObjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		landingPage=launchApplication();
		
	}
	
	@Given("^logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		productCatalogue = landingPage.applicationLogin(username, password);
		
	}
	
	@When("^I add the product (.+) to Cart$")
	public void I_add_the_product_to_Cart(String productName) {
		List<WebElement> productList = productCatalogue.getProductList();

		productCatalogue.addProductToCart(productName);
		
	}
	
	@When("^checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) throws InterruptedException {
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);

		Assert.assertTrue(match);

		Thread.sleep(5000);

		Actions a = new Actions(driver);

		CheckoutPage checkOutPage = cartPage.checkoutButton(a);

		JavascriptExecutor js = (JavascriptExecutor) driver;

		String countryName = "india";
		checkOutPage.selectCountry(a, countryName);
		confirmationPage = checkOutPage.submitOrder(a);
	}
	
	@Then("{string} message is displayed on the confirmation page")
	public void message_is_displayed_on_the_confirmation_page(String string) {
		String confirmMessage = confirmationPage.getMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void error_message_is_displayed(String string) {
		Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.close();
	}

}
