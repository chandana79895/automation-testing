package zapcg.Capillary.EarnPointPageTestCases;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import zapcg.Capillary.Base.BaseTest;
import zapcg.Capillary.PageObject.earnPointsPage;
import zapcg.Capillary.PageObject.loginPage;
import zapcg.Capillary.PageObject.memberDetailsPage;
import zapcg.Capillary.PageObject.memberLookupPage;


@Listeners(zapcg.Capillary.ListenersConfiguration.Listeners.class)			

public class EarnPoint_Valid_TestCases extends BaseTest{
	
	public loginPage lp;
	 public String currentBrowser;
	 memberDetailsPage	mdp=new memberDetailsPage(driver);
	 earnPointsPage epp=new earnPointsPage(driver);
	 memberLookupPage mlp=new memberLookupPage(driver);
	
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
			mdp.clickOnEnterReceiptButton();
			epp=new earnPointsPage(driver);
		
	       

	   }
	 
	 
	 @Test(priority=1)
		public void verifyNonEditableLocationField() throws InterruptedException {
		
		 epp.locationFieldNonEditable(driver);
		 
		 
		 }
	 
	 @Test(priority=2)
		public void verifyNonEditableDateField() throws InterruptedException {
		
		 epp.dateFieldNonEditable(driver);
		 
		 
		 }
	 @Test(priority=3)
	 public void verifyNonEditableTimeField() throws InterruptedException {
		 
		 epp.timeFieldNonEditable(driver);
	 }
	
	 
	 @Test(priority=4)
		public void verifyEarnPointsNavigationFromMeberDetailsScreen() {
		
		 epp.verifySuccessfullNavigationFromMemberDetailsToEarnPointsPage(driver);
				
		
	}
	 
	 
	 @Test(priority=5)
		public void verifyMemberDetailsContentDisplayingOnEarnPointScreen() {
		
		 epp.expandMemberDetails(driver);
		 epp.verifyTheMemberDetailsContentDisplaying(driver);	
	}
	 
	 
	 
	 
	 @Test(priority=6)
		public void verifyHeaderHyperlink() {
		
		 epp.clickOnHyperlink();
		 epp.headerHyperlinkVerificationOnEarnPointsScreen(driver);	
	}
	 

	 @Test(priority=7)
		public void verifyBackButtonNavigation() {
		
		 epp.clickOnBackButton();
		 epp.verifySuccessfullNavigationForBackButton(driver);	
	}
	
	 
	 @Test(priority=8)
		public void verifyHamBurgerMemberLookupOption() throws InterruptedException {
	
		 epp.clickOnHamburgerIcon();
		 epp.chooseMemberLookupOption();
		 Thread.sleep(1000);
		 epp.verifySuccessfullNavigationFromEarnPointsToMemberLookupScreen(driver);
		 		
		
	}
	 
	 @Test(priority=9)
		public void verifyHamBurgerLocationOption() throws InterruptedException {
	
		 epp.clickOnHamburgerIcon();
		 epp.chooseLocationFromHamburger();
		 Thread.sleep(1000);
		 epp.verifySuccessfullNavigationFromEarnPointToLocationScreen(driver);
		 		
		
	}
	 
	 
	 
	 @Test(priority=10)
		public void verifyHamBurgerLogoutOption() throws InterruptedException {
	
		 epp.clickOnHamburgerIcon();
		 epp.chooseLogout();
		 Thread.sleep(1000);
		 epp.verifyLogoutOptionOnEarnPointsScreen(driver);
		 		
		
	}
	
	 
	 @Test(priority=11)
		public void verifyDateEqualToSystemCurrentDate() throws InterruptedException {
		 epp.dateVerification(driver);
		
		 		
		
	}
	
	 @Test(priority=12)
		public void verifyTimeEqualToSystemCurrentTime() throws InterruptedException {
	
		epp.timeVerification(driver);
		 		
		
	}
	 @Test(priority=13)
		public void verifyTransactionAmountShouldNotBeEmpty() throws InterruptedException {
		 epp.clickOnSubmitButton();
		epp.txnFieldShouldNotBeEmpty(driver);
		 		
		
	}

	
	 
	 
	 @Test(priority=14)
	 public void emptyTxnAmountButGoToPassPointValue()
	 {
		 epp.enterGoToPassPoint("12");
		 epp.clickOnSubmitButton();
		 epp.ifTxnFieldEmptyThanGoToPassPointShouldBeEmpty(driver);
		 
	 }
	 
	   
	 @Test(priority=15)
	 public void verifySuccessMessageForOnlyTransactionAmount()
	 {
		 epp.enterTransactionAmount(90);
		 epp.clickOnSubmitButton();
		 epp.verifySuccessMessageForValidTxnAmount("transaction has been successfully submitted. They have earned");
	 }
	 
	
	 @Test(priority=16)
	 public void verifyTaxAssumedAmount() {
		
		 
		 double transactionAmount = 50;

	        // Enter transaction amount
	        epp.enterTransactionAmount(transactionAmount);

	        // Get displayed Tax Assumed Amount (rounded down)
	        int actualTaxAssumedAmount = epp.getTaxAssumedAmount();

	        // Print transaction amount and tax assumed amount
	        System.out.println("Transaction Amount: " + transactionAmount);
	        System.out.println("Tax Assumed Amount: " + actualTaxAssumedAmount);

	     // Calculate expected tax assumed amount (for verification)
	        int expectedTaxAssumedAmount = epp.calculateExpectedTaxAssumedAmount((int) transactionAmount);
	        System.out.println("Expected Tax Assumed Amount: " + expectedTaxAssumedAmount);
	     // Compare actual and expected Tax Assumed Amounts using if-else
	        if (expectedTaxAssumedAmount == actualTaxAssumedAmount) {
	            System.out.println("Test Passed: Actual Tax Assumed Amount matches the Expected Tax Assumed Amount.");
	        } else {
	            System.out.println("Test Failed: Actual Tax Assumed Amount does not match the Expected Tax Assumed Amount.");
	        }

	        // Compare actual and expected Tax Assumed Amounts
	        assertEquals(expectedTaxAssumedAmount, actualTaxAssumedAmount); // No delta needed for integer comparison
	    
		 
		 
	 }
	 
	 
	
	 
	 @Test(priority=15)
	 public void verifySuccessMessageForRedeemPoint()
	 {
		 epp.enterTransactionAmount(50);
		 epp.enterGoToPassPoint("1");
		 
		 epp.clickOnSubmitButton();
		 epp.verifySuccessMessageIfRedeemingPoints("transaction has been successfully submitted. They have earned points and have spent points");
	 }
	
	 
	 @Test(priority=15)
	 public void verifyEligibleEarningPoints()
	 {
		 int transactionAmount = 50; // Example transaction amount
	     int goToPassPointUsed = 1; // Example Go To Pass points used

	     	// Enter transaction amount
	        epp.enterTransactionAmount(transactionAmount);

	        // Enter Go To Pass points used
	        epp.enterGoToPassPointsUsed(goToPassPointUsed);

	        // Get displayed tax assumed amount
	        int actualTaxAssumedAmount = epp.getTaxAssumedAmount();

	        // Get displayed eligible earning points (rounded to nearest integer)
	        int actualEligibleEarningPoints = epp.eligibleForEarningPointsValue();

	        // Print transaction amount, tax assumed amount, go to pass points used, and eligible earning points
	        System.out.println("Transaction Amount: " + transactionAmount);
	        System.out.println("Tax Assumed Amount: " + actualTaxAssumedAmount);
	        System.out.println("Go To Pass Points Used: " + goToPassPointUsed);
	        System.out.println("Actual Eligible Earning Points: " + actualEligibleEarningPoints);

	        // Calculate expected eligible earning points (for verification)
	        int expectedEligibleEarningPoints = epp.calculateEligibleEarningPoint(transactionAmount, actualTaxAssumedAmount, goToPassPointUsed);
	        System.out.println("Expected Eligible Earning Points: " + expectedEligibleEarningPoints);

	        // Compare actual and expected eligible earning points using if-else
	        if (expectedEligibleEarningPoints == actualEligibleEarningPoints) {
	            System.out.println("Test Passed: Actual Eligible Earning Points match the Expected Eligible Earning Points.");
	        } else {
	            System.out.println("Test Failed: Actual Eligible Earning Points do not match the Expected Eligible Earning Points.");
	        }

	        // Assert to ensure test fails in case of mismatch
	        assertEquals(expectedEligibleEarningPoints, actualEligibleEarningPoints);
	  
		 
		 }
	   
	 
	 
	 @Test(priority=16)
	 
	 public void verifyExceedThresoldSuccessMessage()
	 {
		    int transactionAmount = 250000; 
	    
	        epp.enterTransactionAmount(transactionAmount);
	        epp.clickOnSubmitButton();
	        epp.clickOnContinueSuccessButton();
	        mlp.enterMemberId("GT000003673");

	        // Navigate back to Member Details screen and get updated total earning points
	        int updatedTotalEarningPoints = mdp.getTotalEarningPoints();
	        System.out.println("Updated Total Earning Points: " + updatedTotalEarningPoints);

	   
		 
		 
		 
	 }
}

