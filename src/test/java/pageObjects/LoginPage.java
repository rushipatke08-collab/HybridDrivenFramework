package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "input-email")
	WebElement Email;
	
	@FindBy(id = "input-password")
	WebElement password;
	
	@FindBy(xpath = "//input[@type='submit']")
	WebElement submitBtn;
	
	public void enterEmail(String email) {
		Email.sendKeys(email);
	}
	
	public void enterPassword(String pass) {
		password.sendKeys(pass);
	}
	
	public void clickOnSubmit() {
		submitBtn.submit();
		
	}
}
