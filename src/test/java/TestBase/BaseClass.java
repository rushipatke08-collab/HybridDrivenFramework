package TestBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;

import org.apache.logging.log4j.LogManager; // Log4j2
import org.apache.logging.log4j.Logger;     // Log4j2 
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseClass {

//public WebDriver driver;
//public Logger logger;
//public Properties p;
//
//	@BeforeClass
//	@Parameters({"os","browser"})
//	public void setup(String os, String br) throws IOException {
//// To load config.properties file 
//		FileReader file = new FileReader("./src//test//resources//config.properties");
//		p = new Properties();
//		p.load(file);
//		
//		logger = LogManager.getLogger(this.getClass()); // To get the Log of each class
//		
//// driver = new ChromeDriver();
//		
//// use Switch case to get value of browser from master.xml to run test 
//		switch (br.toLowerCase()) {
//		case "chrome": driver = new ChromeDriver(); break;
//		case "firefox": driver = new FirefoxDriver(); break;
//		case "Edge" : driver = new EdgeDriver(); break;
//
//		default: System.out.println("Invlid Browser");
//			return;
//		}
//		driver.manage().deleteAllCookies();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//	//	driver.get("https://tutorialsninja.com/demo/");
//
//// to get the value from config.propertie file 
//		driver.get(p.getProperty("appUrl"));
//		driver.manage().window().maximize();
//	}
//	
//	@AfterClass
//	public void tearDown() {
//		driver.close();
//	}
//	
//	// to generate random string use user define method which is available in common lang3 package
//	
//		public String randomString() {
//			String generatedString = RandomStringUtils.randomAlphabetic(5);
//			return generatedString;
//		}
//		public String randomNumeric() {
//			String generatedNumber = RandomStringUtils.randomNumeric(10);
//			return generatedNumber;
//		}
//		
//		public String randomPassord() {
//			String generatedString = RandomStringUtils.randomAlphabetic(4);
//			String generatedNumber = RandomStringUtils.randomNumeric(3);
//			return (generatedString+"@"+generatedNumber);
//		}
	
	
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeMethod (groups = {"Sanity","Regression","Master"})
	// प्रत्येक टेस्ट डेटा सेटसाठी नवीन ब्राउझर उघडेल
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException {
		
		// config.properties फाईल लोड करण्यासाठी
		FileReader file = new FileReader("./src/test/resources/config.properties");
		p = new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass()); // प्रत्येक क्लासचे लॉग मिळवण्यासाठी

//  --------------------To Run the Test on Remote Envirenment (Selenium Gride)------------		
		
//		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
//			
//			DesiredCapabilities capabilities = new DesiredCapabilities();
//			
//			//to set the platform and browser explicitly 
//			
////			capabilities.setPlatform(Platform.WIN11);
////		    capabilities.setBrowserName("chrome");
//			
//			// To get the os(Oparating system) and br(browser) from xml file
//			if(os.equalsIgnoreCase("windows")) {
//				
//				capabilities.setPlatform(Platform.WIN11);
//				
//			}
//			
//			else if(os.equalsIgnoreCase("mac")) {
//				
//				capabilities.setPlatform(Platform.MAC);
//				
//			}
//			else if(os.equalsIgnoreCase("linux")) {
//				capabilities.setPlatform(Platform.LINUX);
//			}
//			else {
//				System.out.println("No match Oparating system");
//				return;
//			}
//			
//			switch (br.toLowerCase()) {
//			case "chrome": capabilities.setBrowserName("chrome"); break;
//			case "edge" : capabilities.setBrowserName("MicrosoftEdge");break;
//			case "firefox" : capabilities.setBrowserName("firefox"); break;
//		
//			default: System.out.println("No Matching Browser");
//				return;
//			}
//			
//			driver = new RemoteWebDriver(new URL("http://localhost:4444"),capabilities);
//			
//		}

		
		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {

		    MutableCapabilities capabilities = new MutableCapabilities();

		    capabilities.setCapability("browserName", br.toLowerCase());

		    driver = new RemoteWebDriver(
		            new URL("http://localhost:4444"),
		            capabilities);
		}
		
// ----------To Run the test on Local Envirenment --------------------		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			
			switch (br.toLowerCase()) {
			case "chrome": 
				driver = new ChromeDriver(); 
				break;
			case "firefox": 
				driver = new FirefoxDriver(); 
				break;
			case "edge": // सुधारणा: इथे 'edge' स्मॉल केसमध्ये केले आहे
				driver = new EdgeDriver(); 
				break;
			default: 
				System.out.println("Invalid Browser: " + br);
				return;
		}
		}
		
		// XML मधून येणाऱ्या ब्राउझर व्हॅल्यूनुसार योग्य ड्रायव्हर सुरू करणे
// ----------To Run the test on Local Envirenment --------------------
//		switch (br.toLowerCase()) {
//			case "chrome": 
//				driver = new ChromeDriver(); 
//				break;
//			case "firefox": 
//				driver = new FirefoxDriver(); 
//				break;
//			case "edge": // सुधारणा: इथे 'edge' स्मॉल केसमध्ये केले आहे
//				driver = new EdgeDriver(); 
//				break;
//			default: 
//				System.out.println("Invalid Browser: " + br);
//				return;
//		}
//		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		// config.properties फाईल मधून URL मिळवून ओपन करणे
		driver.get(p.getProperty("appUrl"));
	}
	
	@AfterMethod (groups = {"Sanity","Regression","Master"})
	// प्रत्येक टेस्ट डेटा सेट संपल्यावर ब्राउझर बंद करेल
	public void tearDown() {
		if (driver != null) {
			driver.quit(); // सुधारणा: close() ऐवजी quit() वापरले जेणेकरून मेमरी लीक होणार नाही
		}
	}
	
	// युझर डिफाईन पद्धती (Random Data Generation साठी)
	public String randomString() {
		return RandomStringUtils.randomAlphabetic(5);
	}
	
	public String randomNumeric() {
		return RandomStringUtils.randomNumeric(10);
	}
	
	public String randomPassord() {
		String generatedString = RandomStringUtils.randomAlphabetic(4);
		String generatedNumber = RandomStringUtils.randomNumeric(3);
		return (generatedString + "@" + generatedNumber);
	}
	
	public String captureScreen(String tname) throws IOException{
		
	
		String timeStamp = new SimpleDateFormat("yyyymmddhhmmss").format(new Date());
		
		TakesScreenshot takeScreenShot =(TakesScreenshot) driver;
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+ tname + "-" + timeStamp +".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	}
}
