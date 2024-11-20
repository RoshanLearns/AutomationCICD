package roshanAcademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import roshanAcademy.abstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {

	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(xpath="//section[@class='ta-results list-group ng-star-inserted']//span[text()=' India']")
	WebElement countrySelect;
	
	By searchResults = By.xpath("//section[@class='ta-results list-group ng-star-inserted']//span");
	
	public void selectCountry(Actions a, String countryName) {
		a.sendKeys(country, countryName).build().perform();
		
		waitForElementToAppear(searchResults);
		
		countrySelect.click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,2000)", "");
		
		
		
		
	}
	
	public ConfirmationPage submitOrder(Actions a) {
		a.moveToElement(submit).build().perform();
		submit.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}

}
