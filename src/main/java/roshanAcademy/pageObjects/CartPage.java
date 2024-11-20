package roshanAcademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import roshanAcademy.abstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		//initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutBtn;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	public Boolean verifyProductDisplay(String productName) {

		Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
	public CheckoutPage checkoutButton(Actions a) {

		//Actions a = new Actions(driver);
		
		
		a.moveToElement(checkoutBtn).build().perform();
		checkoutBtn.click();
		CheckoutPage checkOutPage = new CheckoutPage(driver);
		return checkOutPage;
	}
	

}
