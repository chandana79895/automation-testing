package zapcg.Capillary.Base;
 
import java.time.Duration;
import java.util.logging.LogManager;
import org.testng.ITestResult;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import zapcg.Cappilary.utils.ConfigReader;
import zapcg.Cappilary.utils.DeviceData;
 
import org.apache.logging.log4j.Logger;
 
 
public class BaseTest {
	public static WebDriver driver;
    protected String baseUrl;
    protected Dimension dimension;
    protected String deviceName; 
    private static final Logger logger=org.apache.logging.log4j.LogManager.getLogger(BaseTest.class.getName());
    //private static final Logger logger1 = LogManager.LogManager.getLogger(BaseTest.class.getName());
 
    
    @BeforeSuite
    public void beforeSuite() {
        logger.info("Starting Test Suite");
    }
 
    @AfterSuite
    public void afterSuite() {
        logger.info("Test Suite Completed");
    }
 
    @BeforeClass
    @Parameters({"browser", "deviceName"})
    public void beforeClass(String browser, String deviceName) {
        logger.info("Starting Test Class");
        setUp(browser, deviceName);
        initialization(browser);
    }
    @AfterClass
    public void afterClass() {
        logger.info("Test Class Completed");
        if (driver != null) {
            driver.quit();
        }
    }
    @BeforeMethod
 
    public void setUp(String browser, String deviceName) {
        ConfigReader configReader = new ConfigReader();
        baseUrl = configReader.getProperty("url");
        dimension = DeviceData.getDimension(deviceName);
        this.deviceName = deviceName;
    }
 
    public void initialization(String browser) {
        try {
            if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", "C:\\ProgramData\\chocolatey\\lib\\chromedriver\\tools\\chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                options.setExperimentalOption("detach", true);
                driver = new ChromeDriver(options);
            } else if (browser.equalsIgnoreCase("firefox")) {
                System.setProperty("webdriver.gecko.driver", "path/to/geckodriver");
                driver = new FirefoxDriver();
            }
 
            if (dimension != null) {
                driver.manage().window().setSize(dimension);
            }
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        } catch (Exception e) {
            logger.error("Error initializing WebDriver: ", e);
            if (driver != null) {
                driver.quit();
            }
        }
    }
 
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.error("Test Failed: " + result.getName());
            logger.error("Error: ", result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.info("Test Passed: " + result.getName());
        }
    }

 
    

 
 
}