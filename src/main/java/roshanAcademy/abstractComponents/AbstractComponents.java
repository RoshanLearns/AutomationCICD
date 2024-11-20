package roshanAcademy.abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import roshanAcademy.pageObjects.CartPage;
import roshanAcademy.pageObjects.OrderPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="[routerlink*=cart]")
	WebElement headerCart;
	
	@FindBy(css="[routerlink*=myorders]")
	WebElement orderCart;
	
	public void waitForElementToAppear(By findBy) {

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement element) {

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

	wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToDisappear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
		}

	public CartPage goToCartPage() {
		
		headerCart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrderPage() {
		
		orderCart.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
}
