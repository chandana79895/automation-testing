package zapcg.Capillary.PageObject;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class earnPointsPage {
	
		public WebDriver driver;

		
		@FindBy(xpath="//button[@id='EPMENUI2']")
		WebElement headerHyperlink;
		
		@FindBy(xpath="//button[@id='EPMENUI3']")
		WebElement hamburgerIconMemberOnEarnPointPage;
		
		@FindBy(xpath="//ul/li/div[@id='MENMODHI0']")
		  WebElement hamburgerIconMemberLookupOption;
		    
		 @FindBy(xpath="//ul/li/div[@id='MENMODHI1']")
		 WebElement hamburgerIconLocationOption;
		 
		 @FindBy(xpath="//div[@id='MENMODHI2']")
		 WebElement hamburgerLanguagePreference;
		    
		 @FindBy(xpath="//ul/li/div[@id='MENMODHI3']")
		WebElement logoutFromEarnPointsScreen;
		
		@FindBy(xpath="//button[@id='EPMENUI1']")
		WebElement backButton;
		
		@FindBy(xpath="//h2[@id='EPMBRCNM']")
		WebElement memberName;
		
		@FindBy(xpath="")
		WebElement expandMemberDetailsButton;
		
		@FindBy(xpath="//div[@id='EPCONT']")
		WebElement earnPointSection;
		
		@FindBy(xpath="//input[@id='EP035IN']")
		WebElement location;
		
		@FindBy(xpath="//input[@id='EP037IN']")
		WebElement date;
		
		@FindBy(xpath="//input[@id='EP038IN']")
		WebElement time;
		
		@FindBy(xpath="//input[@id='EP039IN']")
		WebElement txnAmount;
		
		@FindBy(xpath="//p[@id='EP040DV']")
		WebElement taxAssumedAmount;
		
		@FindBy(xpath="//input[@id='EP041IN']")
		WebElement goToPassPoint;
		
		@FindBy(xpath="//p[@id='EP042DV']")
		WebElement eligibleEarningPoint;
		
		@FindBy(xpath="//textarea[@id='EP043IN']")
		WebElement notes;
		
		@FindBy(xpath="//button[@id='EP045B']")
		WebElement submitButton;
		
		@FindBy(xpath="//button[@id='EP046B']")
		WebElement cancelButton;
		
		@FindBy(xpath="//p[@id='EPMDLMSG' and contains(text(), 'transaction has been successfully submitted. They have earned')]")
		WebElement successMessage;
		
		@FindBy(xpath="//p[@id='EPMDLMSG' and contains(text(), 'transaction has been successfully submitted. They have earned 35 points and have spent 1 points.')]")
		WebElement successRedeemMessage;
		
		//Success! Customer/Member transaction exceeds threshold. The transaction has been submitted for approval
		@FindBy(xpath="//p[@id='EPMDLMSG' and contains(text(), 'Success! Customer/Member transaction exceeds threshold. The transaction has been submitted for approval')]")
		WebElement thresoldSuccessMessage;
		
		@FindBy(xpath="//p[@id='EP041-helper-text' and contains(text(),'Entered Go To Pass points used is more than the Transaction Amount')]")
		WebElement goToPassValueMoreThanTxnAmount;
		
		@FindBy(xpath="//p[@id='EP041-helper-text' and contains(text(),'Insufficient valid points')]")
		WebElement goToPassValueMoreThanAvailablePoints;
		
		@FindBy(xpath="//button[@id='EPMDLB']")
		WebElement successContinueButton;
		
		
	
		private By memberNameSection = By.xpath("//h2[@id='EPMBRCNM']"); 
	    private By availablePointsLocator = By.xpath("//h3[@id='EPMBRCPT']"); 
	    private By membershipIdLocator = By.xpath("//h6[@id='EPMBRCMID']");
	    private By pointsExpiryDateLocator = By.xpath("//h6[@id='EPMBRCPEX']"); 
		
		
		//Initializing the Page Objects:
				public earnPointsPage(WebDriver driver){
					this.driver=driver;
					PageFactory.initElements(driver, this);
				}
				
				public void verifySuccessfullNavigationFromMemberDetailsToEarnPointsPage(WebDriver driver) {
					try {
						// Create an instance of WebDriverWait with a timeout of 10 seconds
				        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

				        // Wait for the URL to change to the expected URL
				        WebElement pageLoaded = wait.until(ExpectedConditions.visibilityOf(earnPointSection));

				        if (pageLoaded != null && pageLoaded.isDisplayed()) {
				        	// Page is successfully loaded if the WebElement is visible
				            System.out.println("Successfully navigated to the Earn Point screen");
				        } else {
				            System.out.println("The expected element did not become visible within the timeout period");
				            Assert.fail("Navigation from member details screen to Earn point screen was not successful as the expected element did not become visible.");
				        }
				    } catch (Exception e) {
				        System.out.println("An error occurred during navigation: " + e.getMessage());
				        Assert.fail("An error occurred during navigation verification: " + e.getMessage());
				    }
					
				}
				
	
				public void expandMemberDetails(WebDriver driver)
				{
					// Create an instance of WebDriverWait with a timeout of 10 seconds
		            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		            // Wait for the login button to be clickable (enabled)
		            WebElement clickOnMemberName = wait.until(ExpectedConditions.elementToBeClickable(memberName));
		            clickOnMemberName.click();
		            
					
				}
				
				
				public void verifyTheMemberDetailsContentDisplaying(WebDriver driver) {
					 
					 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
				      	WebElement memberNameArea=null;
				        WebElement availablePoints = null;
				        WebElement membershipId = null;
				        WebElement pointsExpiryDate = null;
				        
				        try {
				            // Wait for the elements to be visible and catch any exceptions
				            memberNameArea = wait.until(ExpectedConditions.visibilityOfElementLocated(memberNameSection));
				            availablePoints = wait.until(ExpectedConditions.visibilityOfElementLocated(availablePointsLocator));
				            membershipId = wait.until(ExpectedConditions.visibilityOfElementLocated(membershipIdLocator));
				            pointsExpiryDate = wait.until(ExpectedConditions.visibilityOfElementLocated(pointsExpiryDateLocator));
				            
				            // Verify that all the elements are displayed and print their details
				            if (memberNameArea.isDisplayed() && availablePoints.isDisplayed() && membershipId.isDisplayed() && pointsExpiryDate.isDisplayed()) {
				                System.out.println("Member details are displaying.");
				                System.out.println("Member Name: " + memberName.getText());
				                System.out.println("Available Points: " + availablePoints.getText());
				                System.out.println("Membership ID: " + membershipId.getText());
				                System.out.println("Points Expiry Date: " + pointsExpiryDate.getText());
				            } else {
				                System.out.println("Some expected elements did not become visible within the timeout period.");
				                Assert.fail("Member details are not fully displaying.");
				            }
				        } catch (Exception e) {
				            if (memberNameSection == null || !((WebElement) memberNameSection).isDisplayed()) {
				                System.out.println("Member details section is not visible.");
				            }
				            if (memberName == null || !memberName.isDisplayed()) {
				                System.out.println("Member name is not visible.");
				            }
				            if (availablePoints == null || !availablePoints.isDisplayed()) {
				                System.out.println("Available points are not visible.");
				            }
				            if (membershipId == null || !membershipId.isDisplayed()) {
				                System.out.println("Membership ID is not visible.");
				            }
				            if (pointsExpiryDate == null || !pointsExpiryDate.isDisplayed()) {
				                System.out.println("Points expiry date is not visible.");
				            }
				            System.out.println("An error occurred on Member details screen: " + e.getMessage());
				            Assert.fail("An error occurred during Member details verification: " + e.getMessage());
				        }
				}
				
				
				public void clickOnHyperlink() {
					headerHyperlink .click();
				    	
				    }
				    
				
				public void headerHyperlinkVerificationOnEarnPointsScreen(WebDriver driver, String expectedUrl) {
			    	
			    	try {
						// Create an instance of WebDriverWait with a timeout of 10 seconds
				        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

				        // Wait for the URL to change to the expected URL
				        boolean isUrlChanged = wait.until(ExpectedConditions.urlToBe("https://d1msv2sqknn4w4.cloudfront.net/location-search"));

				        if (isUrlChanged) {
				            String currentUrl = driver.getCurrentUrl();
				            System.out.println("After clicking on Header-Hyperlink on Earn Points screen, Navigated to Location screen: " + currentUrl);
				            String expectedUrl1 = "https://d1msv2sqknn4w4.cloudfront.net/location-search";
				            Assert.assertEquals(currentUrl, expectedUrl1, "The URL incorrect for location screen");
				        } else {
				            System.out.println("The URL did not change to the expected URL within the timeout period.i.e, no success navigation");
				            Assert.fail("Navigation was not successful as the URL did not change to the expected URL.");
				        }
				    } catch (Exception e) {
				        System.out.println("An error occurred during navigation to location page: " + e.getMessage());
				        Assert.fail("An error occurred during navigation to location page: " + e.getMessage());
				    
					}
			    	
			    	
			    	
			    }
			

				 public void clickOnBackButton() {
				    	try {
				            // Create an instance of WebDriverWait with a timeout of 10 seconds
				            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

				            // Wait for the hamburger icon to be clickable
				            WebElement hamburgerIcon = wait.until(ExpectedConditions.elementToBeClickable(backButton));

				            // Click on the hamburger icon
				            hamburgerIcon.click();
				            System.out.println("Back button clicked successfully from Earn point page.");
				        } catch (Exception e) {
				            System.out.println("Failed to click on the Back button option on Earn Point page: " + e.getMessage());
				            throw e;
				        }
				    	
				    }
				
				 
				 public void verifySuccessfullNavigationForBackButton(WebDriver driver, String expectedUrl) {
						try {
							// Create an instance of WebDriverWait with a timeout of 10 seconds
					        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

					        // Wait for the URL to change to the expected URL
					        boolean isUrlChanged = wait.until(ExpectedConditions.urlToBe("https://d1msv2sqknn4w4.cloudfront.net/member-details"));

					        if (isUrlChanged) {
					            String currentUrl = driver.getCurrentUrl();
					            System.out.println("Back button successfully navigated to the Member details screen: " + currentUrl);
					            String expectedUrl1 = "https://d1msv2sqknn4w4.cloudfront.net/member-details";
					            Assert.assertEquals(currentUrl, expectedUrl1, "The URL of member details screen is incorrect. Back button not working.");
					        } else {
					            System.out.println("The URL did not change to the expected URL within the timeout period.i.e,Back button issue,");
					            Assert.fail("Back button issue: Navigation was not successful as the URL did not change to the expected URL.");
					        }
					    } catch (Exception e) {
					        System.out.println("An error occurred during page verification: " + e.getMessage());
					        Assert.fail("An error occurred during page verification: " + e.getMessage());
					    
						}
						
					}
				 
				 
				 
				 public void clickOnHamburgerIcon()
				    {
				    	
					 try {
				            // Create an instance of WebDriverWait with a timeout of 10 seconds
				            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

				            // Wait for the hamburger icon to be clickable
				            WebElement hamburgerIcon = wait.until(ExpectedConditions.elementToBeClickable(hamburgerIconMemberOnEarnPointPage));

				            // Click on the hamburger icon
				            hamburgerIcon.click();
				            System.out.println("Hamburger icon clicked successfully.");
				        } catch (Exception e) {
				            System.out.println("Failed to click on the hamburger icon on Earn points page: " + e.getMessage());
				            throw e;
				        }
				    
				    }
				 
				 
				 public void chooseMemberLookupOption() {
					    
				    	try {
				            // Create an instance of WebDriverWait with a timeout of 10 seconds
				            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

				            // Wait for the hamburger icon to be clickable
				            WebElement hamburgerIcon = wait.until(ExpectedConditions.elementToBeClickable(hamburgerIconMemberLookupOption));

				            // Click on the hamburger icon
				            hamburgerIcon.click();
				            System.out.println("Member lookup option from Hamburger icon clicked successfully.");
				        } catch (Exception e) {
				            System.out.println("Failed to click on the lookup option from hamburger icon on Earn points page: " + e.getMessage());
				            throw e;
				        }
				    	
				    	
				    }
				 
				 public void verifySuccessfullNavigationFromEarnPointsToMemberLookupScreen(WebDriver driver, String expectedUrl) {
						try {
							// Create an instance of WebDriverWait with a timeout of 10 seconds
					        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

					        // Wait for the URL to change to the expected URL
					        boolean isUrlChanged = wait.until(ExpectedConditions.urlToBe("https://d1msv2sqknn4w4.cloudfront.net/member-search"));

					        if (isUrlChanged) {
					            String currentUrl = driver.getCurrentUrl();
					            System.out.println("Using hamburgurIcon, choose Member lookup option,and successfully navigated to the Member Lookup screen: " + currentUrl);
					            String expectedUrl1 = "https://d1msv2sqknn4w4.cloudfront.net/member-search";
					            Assert.assertEquals(currentUrl, expectedUrl1, "The URL of member lookup screen is incorrect. Navigation not successful.");
					        } else {
					            System.out.println("The URL did not change to the expected URL within the timeout period.i.e, no success navigation from Earn point to member lookup screen,");
					            Assert.fail("Navigation was not successful as the URL did not change to the expected URL.");
					        }
					    } catch (Exception e) {
					        System.out.println("An error occurred during page verification: " + e.getMessage());
					        Assert.fail("An error occurred during page verification: " + e.getMessage());
					    
						}
						
					}
				 
				 public void chooseLocationFromHamburger() {
				    	
				    	try {
				            // Create an instance of WebDriverWait with a timeout of 10 seconds
				            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

				            // Wait for the hamburger icon to be clickable
				            WebElement hamburgerIcon = wait.until(ExpectedConditions.elementToBeClickable(hamburgerIconLocationOption));

				            // Click on the hamburger icon
				            hamburgerIcon.click();
				            System.out.println("Location option from Hamburger icon clicked successfully.");
				        } catch (Exception e) {
				            System.out.println("Failed to click on the Location option from hamburger icon on Earn points page: " + e.getMessage());
				            throw e;
				        }
				    }
				 
				 public void verifySuccessfullNavigationFromEarnPointToLocationScreen(WebDriver driver, String expectedUrl) {
						try {
							// Create an instance of WebDriverWait with a timeout of 10 seconds
					        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

					        // Wait for the URL to change to the expected URL
					        boolean isUrlChanged = wait.until(ExpectedConditions.urlToBe("https://d1msv2sqknn4w4.cloudfront.net/location-search"));

					        if (isUrlChanged) {
					            String currentUrl = driver.getCurrentUrl();
					            System.out.println("Using hamburgurIcon, choose Location option,and successfully navigated to the Location screen: " + currentUrl);
					            String expectedUrl1 = "https://d1msv2sqknn4w4.cloudfront.net/location-search";
					            Assert.assertEquals(currentUrl, expectedUrl1, "The URL of location screen is incorrect. Navigation not successful.");
					        } else {
					            System.out.println("The URL did not change to the expected URL within the timeout period.i.e, no success navigation from Earn point to member location screen,");
					            Assert.fail("Navigation was not successful as the URL did not change to the expected URL.");
					        }
					    } catch (Exception e) {
					        System.out.println("An error occurred during login verification: " + e.getMessage());
					        Assert.fail("An error occurred during page verification: " + e.getMessage());
					    
						}
				    }
				 
				 
				 
				 public void chooseLogout()
					{
						
						try {
				            // Create an instance of WebDriverWait with a timeout of 10 seconds
				            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

				            // Wait for the hamburger icon to be clickable
				            WebElement hamburgerIcon = wait.until(ExpectedConditions.elementToBeClickable(logoutFromEarnPointsScreen));

				            // Click on the hamburger icon
				            hamburgerIcon.click();
				            System.out.println("Logout option from Hamburger icon clicked successfully.");
				        } catch (Exception e) {
				            System.out.println("Failed to click on the Logout option from hamburger icon on Member details page: " + e.getMessage());
				            throw e;
				        }
					}
				 
				 
				 
				 
				 public void verifyLogoutOptionOnEarnPointsScreen(WebDriver driver, String expectedUrl) {
				    	
				    	try {
							// Create an instance of WebDriverWait with a timeout of 30 seconds
					        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

					        // Wait for the URL to change to the expected URL
					        boolean isUrlChanged = wait.until(ExpectedConditions.urlToBe("https://d1msv2sqknn4w4.cloudfront.net/login"));

					        if (isUrlChanged) {
					            String currentUrl = driver.getCurrentUrl();
					            System.out.println("Using hamburgurIcon, choose Logout,and successfully navigated to the Login screen: " + currentUrl);
					            String expectedUrl1 = "https://d1msv2sqknn4w4.cloudfront.net/login";
					            Assert.assertEquals(currentUrl, expectedUrl1, "The URL of login screen is incorrect. Navigation not successful.");
					        } else {
					            System.out.println("The URL did not change to the expected URL within the timeout period.i.e, no success navigation from earn point to Login screen,");
					            Assert.fail("Navigation was not successful as the URL did not change to the expected URL.");
					        }
					    } catch (Exception e) {
					        System.out.println("An error occurred during verification: " + e.getMessage());
					        Assert.fail("An error occurred during page verification: " + e.getMessage());
					    
						}
						
				    	
				    	
				    }
	
				 
				 public void locationFieldNonEditable(WebDriver driver) throws InterruptedException
				 {
					 try {
				            
				            // Check if the field is disabled
				            boolean isDisabled = location.getAttribute("disabled") != null;
				            
				            // Print the value of the disabled field
				            String fieldValue = location.getAttribute("value");
				            System.out.println("The value of the disabled field is: " + fieldValue);
				            
				            // Assert that the field is disabled
				            Assert.assertTrue(isDisabled, "The Location field should be disabled");
				            System.out.println("The Location field is correctly disabled.");
				        } catch (NoSuchElementException e) {
				            System.err.println("The Location field on Earn points page was not found: " + e.getMessage());
				            Assert.fail("The Location field on Earn points page was not found.");
				        } catch (Exception e) {
				            System.err.println("An unexpected error occurred: " + e.getMessage());
				            Assert.fail("An unexpected error occurred.");
				        }
					 
				 }

				 
				 public void dateFieldNonEditable(WebDriver driver) throws InterruptedException
				 {
					 try {
				            
				            // Check if the field is disabled
				            boolean isDisabled = date.getAttribute("disabled") != null;
				            
				            // Print the value of the disabled field
				            String fieldValue = date.getAttribute("value");
				            System.out.println("The value of the disabled field is: " + fieldValue);
				            
				            // Assert that the field is disabled
				            Assert.assertTrue(isDisabled, "The Date field should be disabled");
				            System.out.println("The Date field is correctly disabled.");
				        } catch (NoSuchElementException e) {
				            System.err.println("The Date field on Earn points page was not found: " + e.getMessage());
				            Assert.fail("The Date field on Earn points page was not found.");
				        } catch (Exception e) {
				            System.err.println("An unexpected error occurred: " + e.getMessage());
				            Assert.fail("An unexpected error occurred.");
				        }
					 
				 }
				 
				 public void timeFieldNonEditable(WebDriver driver) throws InterruptedException
				 {
					 try {
				            
				            // Check if the field is disabled
				            boolean isDisabled = time.getAttribute("disabled") != null;
				            
				            // Print the value of the disabled field
				            String fieldValue = time.getAttribute("value");
				            System.out.println("The value of the disabled field is: " + fieldValue);
				            
				            // Assert that the field is disabled
				            Assert.assertTrue(isDisabled, "The Time field should be disabled");
				            System.out.println("The Time field is correctly disabled.");
				        } catch (NoSuchElementException e) {
				            System.err.println("The Time field on Earn points page was not found: " + e.getMessage());
				            Assert.fail("The Time field on Earn points page was not found.");
				        } catch (Exception e) {
				            System.err.println("An unexpected error occurred: " + e.getMessage());
				            Assert.fail("An unexpected error occurred.");
				        }
					 
				 }
				 
				 public void dateVerification(WebDriver driver) {
					 try {
				           
				            
				            // Retrieve the date from the field
				            String dateValue = date.getAttribute("value");
				            System.out.println("The date displayed in the field is: " + dateValue);
				            
				            // Get the current system date
				            LocalDate systemDate = LocalDate.now();
				            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd"); 
				            String systemDateString = systemDate.format(formatter);
				            System.out.println("The system date is: " + systemDateString);
				            
				            // Compare the dates
				            if (dateValue.equals(systemDateString)) {
				                System.out.println("The date in the field matches the system date.");
				            } else {
				                System.out.println("The date in the field does not match the system date.");
				            }

				            // Assert the dates
				            Assert.assertEquals(dateValue, systemDateString, "The date in the field should match the system date");

				        } catch (NoSuchElementException e) {
				            System.err.println("The date field was not found: " + e.getMessage());
				            Assert.fail("The date field was not found.");
				        } catch (DateTimeParseException e) {
				            System.err.println("Error parsing the date: " + e.getMessage());
				            Assert.fail("Error parsing the date.");
				        } catch (Exception e) {
				            System.err.println("An unexpected error occurred: " + e.getMessage());
				            Assert.fail("An unexpected error occurred.");
				        }
					 
					 
				 }
				 
				 
				 public void timeVerification(WebDriver driver) {
					 
					 try {
				            
				            // Retrieve the time from the field
				            String timeValue = time.getAttribute("value");
				            System.out.println("The time displayed in the field is: " + timeValue);
				            
				            // Get the current system time
				            LocalTime systemTime = LocalTime.now();
				            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); // Adjust the pattern as per the time format
				            String systemTimeString = systemTime.format(formatter);
				            System.out.println("The system time is: " + systemTimeString);
				            
				            // Compare the times
				            if (timeValue.equals(systemTimeString)) {
				                System.out.println("The time in the field matches the system time.");
				            } else {
				                System.out.println("The time in the field does not match the system time.");
				            }

				            // Assert the times
				            Assert.assertEquals(timeValue, systemTimeString, "The time in the field should match the system time");

				        } catch (NoSuchElementException e) {
				            System.err.println("The time field was not found: " + e.getMessage());
				            Assert.fail("The time field was not found.");
				        } catch (DateTimeParseException e) {
				            System.err.println("Error parsing the time: " + e.getMessage());
				            Assert.fail("Error parsing the time.");
				        } catch (Exception e) {
				            System.err.println("An unexpected error occurred: " + e.getMessage());
				            Assert.fail("An unexpected error occurred.");
				        }
					 
				 }
				 
				 
				 public void txnFieldShouldNotBeEmpty(WebDriver driver) {
					 try {

					        // Get the border color of the element
					        String borderColor = txnAmount.getCssValue("border-color");
					        System.out.println("Border color of the txn amount field is: " + borderColor);

					        // Expected border color in RGB format, replace with the actual expected value
					        String expectedBorderColor = "rgb(46, 56, 35)"; // Example: Red color

					     // Verify the border color using if-else
					        if (borderColor.equals(expectedBorderColor)) {
					            System.out.println("The transaction amount field is highlighted in red color.");
					        } else {
					            System.out.println("The field is NOT highlighted in red color.");
					            System.out.println("Expected border color: " + expectedBorderColor);
					            System.out.println("Actual border color: " + borderColor);
					        }

					        // Asserting to ensure the test fails if the color does not match
					        Assert.assertEquals(borderColor, expectedBorderColor, "The field border color does not match the expected value.Validation not working for empty transaction amount field");
					        
					    } catch (NoSuchElementException e) {
					        System.out.println("Field element txn amount was not found on the page.");
					        Assert.fail("Field element was not found on the page.");
					    } catch (Exception e) {
					        System.out.println("An unexpected error occurred: " + e.getMessage());
					        Assert.fail("An unexpected error occurred: " + e.getMessage());
					    }
					 
				 }
				 
				 
				 public void clickOnSubmitButton()
				 {
					 
					 try {
				            // Create an instance of WebDriverWait with a timeout of 10 seconds
				            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

				            // Wait for the hamburger icon to be clickable
				            WebElement clickSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
				            clickSubmitButton.click();
				       
				            System.out.println("Sumit button clicked successfully");
				        } catch (Exception e) {
				            System.out.println("Failed to click on the submit button on earnPoint page: " + e.getMessage());
				            throw e;
				        }
					 
				 }
				 
				 public void enterGoToPassPoint(String goToPassPointUsed)
				 {
					 goToPassPoint.sendKeys(goToPassPointUsed);
					 
				 }
				 
				 public void ifTxnFieldEmptyThanGoToPassPointShouldBeEmpty(WebDriver driver) {
					 try {

					        // Get the border color of the element
					        String borderColor = goToPassPoint.getCssValue("border-color");
					        System.out.println("Border color of the 'Go To Pass Point' field is: " + borderColor);

					        // Expected border color in RGB format, replace with the actual expected value
					        String expectedBorderColor = "rgb(46, 56, 35)"; // Example: Red color

					     // Verify the border color using if-else
					        if (borderColor.equals(expectedBorderColor)) {
					            System.out.println("The 'Go to Pass Point' field is highlighted in red color.");
					        } else {
					            System.out.println("The field is NOT highlighted in red color.");
					            System.out.println("Expected border color: " + expectedBorderColor);
					            System.out.println("Actual border color: " + borderColor);
					        }

					        // Asserting to ensure the test fails if the color does not match
					        Assert.assertEquals(borderColor, expectedBorderColor, "The field border color does not match the expected value.Validation not working for empty transaction amount field");
					        
					    } catch (NoSuchElementException e) {
					        System.out.println("Field element was not found on the page.");
					        Assert.fail("Field element was not found on the page.");
					    } catch (Exception e) {
					        System.out.println("An unexpected error occurred: " + e.getMessage());
					        Assert.fail("An unexpected error occurred: " + e.getMessage());
					    }
					 
				 }
				 
				 public void enterTransactionAmount(double amount) {
				        
				        txnAmount.clear();
				        txnAmount.sendKeys(String.valueOf(amount));
				    }
				 
				
				 
				 public void verifySuccessMessageForValidTxnAmount(String expectedMessage)
				 {
					 
					 try {
							WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

					        // Wait for the error message element to be visible
					        WebElement successMsg = wait.until(ExpectedConditions.visibilityOf(successMessage));

					        // Get the text of the error message and trim it
					        String actualValidationMessage = successMsg.getText().trim();
					        System.out.println("Actual success message after Only transaction amount submitted :" +actualValidationMessage);
					        
					        
					        if (actualValidationMessage.contains(expectedMessage)) {
				                System.out.println("Success message is displayed correctly.");
				            } else {
				                System.out.println("Success message did not contain the expected text.");
				                Assert.fail("Success message did not contain the expected text.");
				            }
				        } catch (Exception e) {
				            System.out.println("An error occurred while verifying the success message: " + e.getMessage());
				            Assert.fail("An error occurred while verifying the success message: " + e.getMessage());
				        }
					        
					     
				 }
				 
				 
				 
				 public int getTaxAssumedAmount() {
					 
					 
				        String taxAssumedAmountText = taxAssumedAmount.getText().trim(); // Ensure to trim whitespace
				        // Remove any non-numeric characters (including currency symbols)
				        taxAssumedAmountText = taxAssumedAmountText.replaceAll("[^0-9]", "");

				     // Parse as double
				        double taxAssumedAmountDouble = Double.parseDouble(taxAssumedAmountText);

				        // Round to the nearest integer
				        return (int) Math.round(taxAssumedAmountDouble);
				   
				    }
				 
				 
				 public int calculateExpectedTaxAssumedAmount(int transactionAmount) {
				        // Calculate the expected tax assumed amount
					 double result = transactionAmount - (transactionAmount / 1.1);
				        return (int) Math.round(result);
				    }
				 
				 
				 public void enterGoToPassPointsUsed(double amount) {
				
				        goToPassPoint.sendKeys(String.valueOf(amount));
					 
				 }
				 
				 
				 public void verifySuccessMessageIfRedeemingPoints(String expectedMessage)
				 {
					 
					 try {
							WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

					        // Wait for the error message element to be visible
					        WebElement successMsg = wait.until(ExpectedConditions.visibilityOf(successRedeemMessage));

					        // Get the text of the error message and trim it
					        String actualValidationMessage = successMsg.getText().trim();
					        System.out.println("Actual success message after using transaction amount and Go to Pass point used values submitted :" +actualValidationMessage);
					        
					        
					        if (actualValidationMessage.contains(expectedMessage)) {
				                System.out.println("Success message is displayed correctly.");
				            } else {
				                System.out.println("Success message did not contain the expected text.");
				                Assert.fail("Success message did not contain the expected text.");
				            }
				        } catch (Exception e) {
				            System.out.println("An error occurred while verifying the success message: " + e.getMessage());
				            Assert.fail("An error occurred while verifying the success message: " + e.getMessage());
				        }
					        
					     
				 }
				 
				 
				 
				 public int eligibleForEarningPointsValue() {
					
				 String eligibleEarningPointText = eligibleEarningPoint.getText().trim(); // Ensure to trim whitespace
				        // Remove any non-numeric characters (including currency symbols)
					 eligibleEarningPointText = eligibleEarningPointText.replaceAll("[^0-9]", "");
					 

				     // Parse as double
				        double eligibleEarningPointDouble = Double.parseDouble(eligibleEarningPointText);

				        // Round to the nearest integer
				        return (int) Math.round(eligibleEarningPointDouble);
					 
					 
				 }
				 
				 
				 public int calculateEligibleEarningPoint(int transactionAmount, int taxAssumedAmount, int goToPassPointUsed) {
				        // Calculate the expected tax assumed amount: Transaction Amount - Tax Assumed Amount - Go To Pass Points Used
					 
					 double result = transactionAmount - taxAssumedAmount - goToPassPointUsed;
				        return (int) Math.round(result);
				    }
				 
				 
				 
				 public int calculatePointsFromTransaction(int transactionAmount) {
				        // Calculate points based on the rule: 1 point per 100 yen
				        return transactionAmount / 100;
				    }
				 
				 public void clickOnContinueSuccessButton()

				 {
					 successContinueButton.click();
					 
					 
				 }
				 
				 public boolean isThresholdMessageDisplayed(String expectedMessage) {
				        return thresoldSuccessMessage.isDisplayed() && thresoldSuccessMessage.getText().equals(expectedMessage);
				    }
				 
				 
				 public void verifyValidationMessageIfGoToPassIsGreaterThanTxnAmt(String expectedMessage)
				 {
					 
					 try {
							WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

					        // Wait for the error message element to be visible
					        WebElement successMsg = wait.until(ExpectedConditions.visibilityOf(goToPassValueMoreThanTxnAmount));

					        // Get the text of the error message and trim it
					        String actualValidationMessage = successMsg.getText().trim();
					        System.out.println("Actual validation message :" +actualValidationMessage);
					        
					        
					        if (actualValidationMessage.contains(expectedMessage)) {
				                System.out.println("Validation message is displayed correctly.");
				            } else {
				                System.out.println("Validation message did not contain the expected text.");
				                Assert.fail("Validation message did not contain the expected text.");
				            }
				        } catch (Exception e) {
				            System.out.println("An error occurred while verifying the Validation message: " + e.getMessage());
				            Assert.fail("An error occurred while verifying the Validation message: " + e.getMessage());
				        }
					        
					     
				 }
				 
				 
				 public void verifyValidationMessageIfGoToPassIsGreaterThanAvailablePoints(String expectedMessage)
				 {
					 
					 try {
							WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

					        // Wait for the error message element to be visible
					        WebElement successMsg = wait.until(ExpectedConditions.visibilityOf(goToPassValueMoreThanAvailablePoints));

					        // Get the text of the error message and trim it
					        String actualValidationMessage = successMsg.getText().trim();
					        System.out.println("Actual validation message if Go To Pass Value More Than Available Points :" +actualValidationMessage);
					        
					        
					        if (actualValidationMessage.contains(expectedMessage)) {
				                System.out.println("Validation message if Go To Pass Value More Than Available Points is displayed correctly.");
				            } else {
				                System.out.println("Validation message did not contain the expected text.");
				                Assert.fail("Validation message did not contain the expected text.");
				            }
				        } catch (Exception e) {
				            System.out.println("An error occurred while verifying the Validation message: " + e.getMessage());
				            Assert.fail("An error occurred while verifying the Validation message: " + e.getMessage());
				        }
					        
					     
				 }
				 
				
	}
				 

