package testCases;


import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.Homepage;
import pageObjects.registerAccountPage;

public class TC001_Account_Reg_page extends BaseClass {

	
	@Test(groups = {"Regression","Master"})
	public void test() {
		
		logger.info(" ***** Starting Test TC001_Account_Reg_page ***** ");
	// we are using single data here so we don't need Excel for data 
		try {
		Homepage hp = new Homepage(driver);
		logger.info(" ***** Click on My Account Link ***** ");
        hp.clickMyaccout();
        
        logger.info(" ***** Click on Registration Link ***** ");
        hp.clickOnRegister();
        
		registerAccountPage regpage = new registerAccountPage(driver);
        logger.info(" +++++ Enter all Information +++++");
        
        regpage.setFirstname(randomString().toUpperCase());
		regpage.setLastname(randomString().toUpperCase());
		String mail =regpage.setEmail(randomString()+"@"+"gmail.com");
		System.out.println(mail);
		regpage.setTelephone(randomNumeric());
		
		String password = randomPassord();
		System.out.println(password);
		regpage.setPassword(password);
		regpage.setCnfPass(password);
		regpage.clickPrivacyCheckbox();
		regpage.clickContinue();
		

		}
		catch(Exception e)
		{
		
			logger.error("...Test Failed...");
			logger.debug("Debug Log...");
		}
		
        logger.info(" ***** Test TC001_Account_Reg_page Completed ***** ");

	}
	
	
	
}
