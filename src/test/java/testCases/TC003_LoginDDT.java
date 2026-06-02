package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{

	/*  Data is valid - login success - test pass - logout
	Data is valid -- login fails -- test fail

	Data is invalid - login success - test fail- logout
	Data is invalid -- login failed - test pass -
	 */

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups = "datadriven")
	public void varif_loginDDT(String email, String pwd, String exp) {

	    logger.info("*** Starting TC003_LoginDDT ***");

	    try {

	        Homepage hp = new Homepage(driver);
	        hp.clickMyaccout();
	        hp.clickOnLogin();

	        LoginPage lp = new LoginPage(driver);
	        lp.enterEmail(email);
	        lp.enterPassword(pwd);
	        lp.clickOnSubmit();

	        MyAccountPage macc = new MyAccountPage(driver);

	        boolean targetPage = macc.isMyAccountPageDisplayed();

	        // VALID DATA
	        if(exp.equalsIgnoreCase("valid")) {

	            if(targetPage == true) {

	               
	                Assert.assertTrue(true);
	                macc.clickOnLogout();

	            } else {

	                Assert.assertTrue(false);
	            }
	        }

	        // INVALID DATA
	         if(exp.equalsIgnoreCase("invalid")) {

	            if(targetPage == true) {

	               
	                Assert.assertTrue(false);
	                macc.clickOnLogout();
	            } else {

	                Assert.assertTrue(true);
	            }
	        }

	    }
	    catch(Exception e) {

	        e.printStackTrace();
	       Assert.assertTrue(false);
	    }
	    logger.info("*** Finished TC003_LoginDDT ***");
	}
}
