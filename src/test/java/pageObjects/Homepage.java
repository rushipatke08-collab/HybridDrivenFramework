package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage {

	WebDriver driver;
	
	public Homepage (WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[text()='My Account']")
	WebElement MyaccountBtn;
	
	@FindBy(xpath = "//a[text()='Register']")
	WebElement registerBtn;
	
	@FindBy(xpath = "//a[text()='Login']")
	WebElement Login;
	
	public void clickMyaccout() {
		MyaccountBtn.click();
	}
	
	public void clickOnRegister() {
		registerBtn.click();
		
	}
	
	public void clickOnLogin() {
		Login.click();
	}
}
