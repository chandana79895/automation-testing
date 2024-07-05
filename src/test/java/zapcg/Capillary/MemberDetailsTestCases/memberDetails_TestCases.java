package zapcg.Capillary.MemberDetailsTestCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import zapcg.Capillary.Base.BaseTest;
import zapcg.Capillary.PageObject.loginPage;
import zapcg.Capillary.PageObject.memberDetailsPage;
import zapcg.Capillary.PageObject.memberLookupPage;

public class memberDetails_TestCases extends BaseTest{
	
	public loginPage lp;
	 public String currentBrowser;
	 memberDetailsPage	mdp=new memberDetailsPage(driver);
	
	 @BeforeMethod
		@Parameters({"browser", "deviceName"})
	   public void initialize(String browser, String deviceName) throws InterruptedException {
		 	setUp(browser, deviceName); // Use the setup method to initialize the browser
	       initialization(browser);
	       driver.get(baseUrl);
	       Thread.sleep(1000); // For demonstration purposes, avoid using Thread.sleep in real tests
	     
	       lp = new loginPage(driver);
	       lp.changeDefaultLanguage();
	       lp.chooseEnglishLanguage();
	       lp.login("zapcom_test2", "storeportal");
	       memberLookupPage mlp = new memberLookupPage(driver);
	       mlp.enterMemberId("GT000003673");
			mlp.clickOnSearchButton();
			mdp=new memberDetailsPage(driver);
			
	       

	   }
	 
	 

	 
	 @Test(priority=1)
		public void verifyMemberDetailsNavigationFromMeberLookupScreen() {
		
		 mdp.verifySuccessfullNavigationFromMemberLookupToMemberDetailsPage(driver);
				
		
	}
	 
	 
	 @Test(priority=2)
		public void verifyMemberDetailsDisplaying() {
		
		 mdp.verifyMemberDetailsDisplaying(driver);
				
		
	}
	 
	 
	 //Verify all the member details and print if the details are available on the screen
	 @Test(priority=3)
		public void verifyMemberDetailsContentDisplaying() {
		 mdp.verifyTheMemberDetailsContentDisplaying(driver);
				
		
	}
	 
	 @Test(priority=4)
		public void verifyEnabledOfEnterReceiptDetailsButton() {
		mdp.verifyEnterReceiptButtonEnabled(); 
				
		
	}
	 
	 @Test(priority=5)
		public void verifyHeaderOfTheMemberDetailsScreen() {
		 mdp.clickOnHyperlink();
		mdp.headerHyperlinkVerificationOnMemberDetailsScreen(driver, "https://d1msv2sqknn4w4.cloudfront.net/location-search"); 
				
		
	}
	 
	 //Expected behavior not yet decided.
	 @Test(priority=6)
		public void verifyExpiryDateEqualToSystemDateThenNoRedeem() {
		 		
		
	}
	 
	 
	 
	 @Test(priority=7)
		public void verifyHamBurgerMemberLookupOption() throws InterruptedException {
	
		 mdp.clickOnHamburgerIcon();
		 mdp.chooseMemberLookupOption();
		 Thread.sleep(1000);
		 mdp.verifySuccessfullNavigationFromMemberDetailsToMemberLookupScreen(driver, "https://d1msv2sqknn4w4.cloudfront.net/member-search");
		 		
		
	}
	 
	 @Test(priority=8)
		public void verifyHamBurgerLocationOption() throws InterruptedException {
		
		 mdp.clickOnHamburgerIcon();
		 mdp.chooseLocationFromHamburger();
		 Thread.sleep(1000);
		 mdp.verifySuccessfullNavigationFromMemberDetailsToLocationScreen(driver,"https://d1msv2sqknn4w4.cloudfront.net/location-search");
		
	}
	 
	 @Test(priority=9)
		public void verifyHamBurgerLogoutOption() throws InterruptedException {
		 
		 mdp.clickOnHamburgerIcon();
		 mdp.chooseLogout();
		 Thread.sleep(1000);
		mdp.verifyLogoutOptionOnMemberDetailsScreen(driver, "https://d1msv2sqknn4w4.cloudfront.net/login");
	}
	 
	 
	 @Test(priority=10)
		public void verifyBackButton() throws InterruptedException {
		 
		 mdp.clickOnBackButton();
		 Thread.sleep(1000);
		mdp.verifySuccessfullNavigationForBackButton(driver, "https://d1msv2sqknn4w4.cloudfront.net/member-search");
	}
	 
/*
 				this is not working
	 @Test(priority=19)
		public void verifyLanguageChangeFromEnglishToJapanese() throws InterruptedException {
		 mdp.clickOnHamburgerIcon();
		 mdp.verifyJapaneseLanguageChangedFromEnglish();
		 }
	 
	 */
	 
	 
	 

}
