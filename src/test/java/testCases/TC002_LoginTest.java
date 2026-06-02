package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginTest extends BaseClass {


	@Test(groups = {"Sanity","Master"})
	public void verify_logintest() {
		logger.info("*** Test Starting TC002_LoginTest ***");

		try {

			// Homepage
			Homepage hp = new Homepage(driver);
			hp.clickMyaccout();
			hp.clickOnLogin();

			//Login Page
			LoginPage lp = new LoginPage(driver);
			lp.enterEmail(p.getProperty("email"));
			lp.enterPassword(p.getProperty("password"));
			lp.clickOnSubmit();

			// My Account Page
			MyAccountPage macc = new MyAccountPage(driver);
			boolean	targetPage = macc.isMyAccountPageDisplayed();
			
		//	Assert.assertTrue(targetPage);
	       // ------ OR ------  
			
			Assert.assertEquals(targetPage, true,"Log In Failed");
		} catch (Exception e) {
				
			Assert.fail();
		}
		
		logger.info("-------- Finished TC002_LoginTest ------");
	}
}
