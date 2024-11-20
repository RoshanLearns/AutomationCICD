package roshanAcademy.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import roshanAcademy.abstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {
	
WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		//initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutBtn;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	public Boolean verifyOrderDisplay(String productName) {

		Boolean match=orderProducts.stream().anyMatch(orderProduct->orderProduct.getText().equalsIgnoreCase(productName));
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
