package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestBase.BaseClass;

public class MyAccountPage extends BaseClass{
	
//	WebDriver driver;
//	public MyAccountPage(WebDriver driver) {
//		
//		this.driver= driver;
//		PageFactory.initElements(driver, this);
//	}
//	
//	@FindBy(xpath = "//h2[text()='My Account']")
//	WebElement msgHeading;
//	
//	@FindBy(xpath = "//*[@id=\"top-links\"]/ul/li[2]/ul/li[5]/a")
//	WebElement logout;
//	
//	public boolean isMyAccountPageDisplayed() {
//		
//		try {
//			return (msgHeading.isDisplayed()); 
//		} catch (Exception e) {
//			return false;
//		}
//	}
//	
//	public void clickOnLogout() {
//		logout.click();
//	}
	
	
	WebDriver driver;
	WebDriverWait wait;
	
	// Constructor
	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// ५ ते १० सेकंदांचा Explicit Wait ऑब्जेक्ट तयार केला
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	// Elements Locators
	@FindBy(xpath = "//h2[text()='My Account']")
	WebElement msgHeading;
	
	// टॉप मेन्यूवरील 'My Account' ड्रॉपडाउन लिस्ट
	@FindBy(xpath = "//span[text()='My Account']")
	WebElement dropdownMyAccount;
	
	// सोपा आणि अचूक मुख्य लॉगआउट XPath
	@FindBy(xpath = "//a[text()='Logout']")
	WebElement linkLogout;
	
	// Action Methods
	public boolean isMyAccountPageDisplayed() {
		try {
			return (msgHeading.isDisplayed()); 
		} catch (Exception e) {
			return false;
		}
	}
	
	public void clickOnLogout() {
		try {
			// १. आधी माय अकाउंट ड्रॉपडाउन मेन्यूवर क्लिक करा जेणेकरून लिस्ट ओपन होईल
			wait.until(ExpectedConditions.elementToBeClickable(dropdownMyAccount));
			dropdownMyAccount.click();
			
			// २. लॉगआउट पर्याय स्क्रीनवर पूर्णपणे दिसेपर्यंत (Clickable होईपर्यंत) थांबा
			wait.until(ExpectedConditions.elementToBeClickable(linkLogout));
			
			// ३. लॉगआउट वर क्लिक करा
			linkLogout.click();
		} catch (Exception e) {
			System.out.println("लॉगआउट करताना समस्या आली: " + e.getMessage());
			throw e; // जेणेकरून टेस्ट फेल झाली तर रिपोर्टमध्ये कळेल
		}
	}
}
