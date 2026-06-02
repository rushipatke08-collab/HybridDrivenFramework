package utilities;

import java.util.Date;
import java.util.List;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestBase.BaseClass;

public class ExtentReportUtility implements ITestListener{

	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		/*
		SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt = new Date();
		String currentdatetimestamp = df.format(dt);
		*/
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		repName = "Test-Report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); // Specify the location of the report
		
		sparkReporter.config().setDocumentTitle("Opencart Automation Report");
		sparkReporter.config().setReportName("Opencart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Web application", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
			
		}
	}
	
	public void onTestSuccess(ITestResult result) {
		
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // to display group in report
		test.log(Status.PASS, result.getName()+"got Successfully Executed");
		
	}
	
	public void onTestFailure(ITestResult result) {
		
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+"got Failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			
			String imgpath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgpath);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void onTestSkipped(ITestResult result) {
		
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+"Got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
	}
	
	public void onFinish(ITestContext testContext) {
		
		extent.flush();
		
		
		
		try {
	        // प्रोजेक्टचा अचूक पॅथ मिळवण्यासाठी System.getProperty("user.dir") वापरा
	        String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
	        File extentReport = new File(pathOfExtentReport);
	        
	        if(extentReport.exists()) {
	            Desktop.getDesktop().browse(extentReport.toURI());
	        } else {
	            System.out.println("रिपोर्ट फाईल सापडली नाही.");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		/*
		 * try { 
		 * 
		 * URL url = new  URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
		 *
		 *
		 * // Create the email message
		 * ImageHtmlEmail email = new ImageHtmlEmail();
		 * email.setDataSourceResolver(new DataSourceUrlResolver(url));
		 * email.setHostName("smtp.googlemail.com");
		 * email.setSmtpPort(465);
		 * email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com","password"));
		 * email.setSSLOnConnect(true);
		 * email.setFrom("pavanoltraining@gmail.com"); //Sender
		 * email.setSubject("Test Results");
		 * email.setMsg("Please find Attached Report....");
		 * email.addTo("pavankumar.busayqa@gmail.com"); //Receiver
		 * email.attach(url, "extent report", "please check report...");
		 * email.send(); // send the email
		 * }
		 * catch(Exception e) { e.printStackTrace(); }
		 */
	}
}
