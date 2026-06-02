package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class registerAccountPage {

	WebDriver driver;
	
	public registerAccountPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "input-firstname")
	WebElement Firstname;
	
	@FindBy(id = "input-lastname")
	WebElement Lastname;
	
	@FindBy(id = "input-email")
	WebElement Email;
	
	@FindBy(id = "input-telephone")
	WebElement Telephone;
	
	@FindBy(id = "input-password")
	WebElement pass;
	
	@FindBy(id = "input-confirm")
	WebElement cnfpass;
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement privacyPolicy;
	
	@FindBy(xpath = "//input[@class='btn btn-primary']")
	WebElement Continue;
	
	public void setFirstname(String fname) {
		Firstname.sendKeys(fname);
	}
	
	public void setLastname(String lname) {
		Lastname.sendKeys(lname);
	}
	
	public String setEmail(String email) {
		Email.sendKeys(email);
		return email;
	}
	
	public void setTelephone(String phone) {
		Telephone.sendKeys(phone);
	}
	
	public void setPassword(String passwrd) {
		pass.sendKeys(passwrd);
	}
	
	public void setCnfPass(String CnfPassword) {
		cnfpass.sendKeys(CnfPassword);
	}
	
	public void clickPrivacyCheckbox() {
		privacyPolicy.click();
	}
	
	public void clickContinue() {
		Continue.submit();
	}
}
