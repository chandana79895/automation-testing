package zapcg.Capillary.LoginTestCases;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import zapcg.Capillary.Base.BaseTest;
import zapcg.Capillary.PageObject.loginPage;



@Listeners(zapcg.Capillary.ListenersConfiguration.Listeners.class)			

public class Login_Valid_TestCases extends BaseTest {
	
	 public loginPage lp;
	 public String currentBrowser;
	   
	
	 @BeforeMethod
		@Parameters({"browser", "deviceName"})
	   public void initialize(String browser, String deviceName) throws InterruptedException {
		// logger.info("Initializing test method");
		 setUp(browser, deviceName); // Use the setup method to initialize the browser
	       initialization(browser);
	       driver.get(baseUrl);
	       Thread.sleep(1000); // For demonstration purposes, avoid using Thread.sleep in real tests
	       lp = new loginPage(driver);
	   
	       lp.changeDefaultLanguage();
	       lp.chooseEnglishLanguage();
	       

	   }
	
	

	@Test(priority = 1, groups = "Login")
    public void testValidLogin_TestCase1() throws InterruptedException {
	        lp.login("zapcom_test2", "storeportal");
	      
	        lp.verifySuccessfullNavigationFromLogin(driver);
	        driver.close();
		
        
		}
	
	
	
}
	
	

