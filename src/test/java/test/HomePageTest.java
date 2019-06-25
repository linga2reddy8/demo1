package test;
//import com.aventstack.extentreports.reporter.configuration.ChartLocation;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import org.apache.commons.io.FileUtils;

public class HomePageTest {

    WebDriver driver;

    HomePage objHomePage;
    ExtentHtmlReporter htmlReporter;
    ExtentReports extent;
    ExtentTest test;
    
    @BeforeTest
    public void setup(){
    	System.setProperty("webdriver.chrome.driver","H:\\Selenium\\Other\\version\\chromedriver.exe");
    	driver = new ChromeDriver();
    	Reporter.log("chrome driver passed to driver");
        driver.manage().timeouts().implicitlyWait(3	, TimeUnit.SECONDS);

        driver.get("http://automationpractice.com");
        Reporter.log("url opened");
        driver.manage().window().maximize();
        Reporter.log("window maximized");
        setupExtentReports();  
      
    }
    
    
    private void setupExtentReports() {
    	  htmlReporter = new ExtentHtmlReporter("H:/Selenium/extentreports/testReport.html");
          extent = new ExtentReports();
          extent.attachReporter(htmlReporter);
          extent.setSystemInfo("OS", "Windows");
          extent.setSystemInfo("Browser", "Chrome");
          //htmlReporter.config().setChartVisibilityOnOpen(true);
          htmlReporter.config().setDocumentTitle("Extent Report Demo1");
          htmlReporter.config().setReportName("Test Report Extent");
         // htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
          
          htmlReporter.config().setTheme(Theme.STANDARD);
          htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    	
    }
    
    @Test(priority=1)
        public void verifyifHomePageDisplayed()
    {
    	objHomePage = new HomePage(driver);
    	test = extent.createTest("Test Case 1 - verifyifHomePageDisplayed", "PASSED test case");
    	
    	if (objHomePage.existsSignInElement())
    	{
    		Reporter.log("home page opened successful");
    }
    	else {
    		Reporter.log("home page not opened and failed");
    	}
    	Assert.assertTrue(objHomePage.existsSignInElement(),"Element Not Found in home page");
    	
    	}
   
    public void takingScreenShot()
    {
    	TakesScreenshot scrShot1 =((TakesScreenshot)driver);
    	File scrfile1 = scrShot1.getScreenshotAs(OutputType.FILE); 
    	File destfile1 = new File("H:/Selenium/screenshots/"+System.currentTimeMillis()+".png");
    	 try {
			FileUtils.copyFile(scrfile1, destfile1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 System.out.println("screen shot saved again sucesfully");
    }
    
    @AfterMethod
    public void afterMethodTakingScreenShot()
    {
    	 takingScreenShot(); 
    	 Reporter.log("first screen shot taken");
    	 JavascriptExecutor js = (JavascriptExecutor) driver;
    	 js.executeScript("window.scrollBy(0,700)");
    	 Reporter.log("window scrolled down");
    	 takingScreenShot(); 
    	 Reporter.log("second screenshot taken");
    	 //TakesScreenshot scrShot2 =((TakesScreenshot)driver);
    	 //File scrfile2 = scrShot2.getScreenshotAs(OutputType.FILE);
    	 //File destfile2 = new File("H:/Selenium/screenshots/"+System.currentTimeMillis()+".png");
    	
    }
    @AfterMethod
    public void getTestStatus(ITestResult result) {
    	if(result.getStatus()==ITestResult.FAILURE)
    	{
    		test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
    		test.fail(result.getThrowable());
    	}
    	else if(result.getStatus()==ITestResult.SUCCESS)
    	{
    		test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+"SUCCESS",ExtentColor.BLACK));
    		
    	}
    	else {
    	test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+"SKIPPED", ExtentColor.ORANGE));
    	}
    	    	}
    
    
    @Test(priority=2)
    public void VerifySigninPageDisplayed() {
    	test = extent.createTest("Test Case 2 - VerifySigninPageDisplayed", "PASSED test case");
    	objHomePage = new HomePage(driver);
    	objHomePage.signinClick();
    	//wait(10);
    	LoginPage objLoginPage = new LoginPage(driver);
    
    	Assert.assertTrue(objLoginPage.existsRegisteredElement(),"Element not found and its not in login page");
    	Reporter.log("successfully in login page");
    }
    @Test(priority=3)
    public void accountSignIn()
    {
    	test = extent.createTest("Test Case 3 - accountSignIn", "PASSED test case");
    	LoginPage objLoginPage = new LoginPage(driver);
    	MyAccountPage objMyAccountPage = new MyAccountPage(driver);
    	objLoginPage.setUserName();
    	objLoginPage.setPassword();
    	objLoginPage.clickLogin();  	
    	objMyAccountPage.existIcon();
    	Assert.assertTrue(objMyAccountPage.existIcon(),"Element not found and its not in my account page");
    	Reporter.log("succesfully in my account page");
    	  		
    	
    }
    
    @AfterTest
    public void clean() {
       	
    	Reporter.log("after test executed");
    	extent.flush();
    	 driver.quit();
    }
}

