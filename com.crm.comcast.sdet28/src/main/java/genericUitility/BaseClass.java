package genericUitility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.crm.comcast.objectRepository.HomePage;
import com.crm.comcast.objectRepository.LoginPage;

/**
 * This is a base class which contains all the configuration annotation
 * 
 * @author Anuj Kumar
 *
 */
public class BaseClass {
	public WebDriver driver; // pass the globally
	public static WebDriver sdriver;
	public FileUtility fUtil = new FileUtility();
	public WebDriverUtility wUtil = new WebDriverUtility();
	public JavaUtility jutil = new JavaUtility();
	public ExcelUtility eUtil = new ExcelUtility();
	
	
	@BeforeSuite(groups = {"smoke", "regression"})
	public void configBs() {
		// connection to db
		System.out.println("=====connect to database======");
	}
	
    //@Parameters("browserName")
	@BeforeClass(groups = {"smoke", "regression"})
	 public void configBc() throws Throwable {
		// launching the browser
		String browserName = fUtil.getPropertyFileData("browser");
		String url = fUtil.getPropertyFileData("url");
		System.out.println(browserName);
		
		if (browserName.equals("chrome")) {
			System.setProperty(IpathConstants.CHROME_KEY,IpathConstants.CHROME_PATH);
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			System.setProperty(IpathConstants.FIREFOX_KEY, IpathConstants.FIREFOX_PATH);
			driver = new FirefoxDriver();
		} else {
			System.out.println("Browser not available");
		}
		sdriver = driver;
		driver.manage().window().maximize();
		driver.get(url);
		wUtil.waitForPageLoad(driver);
	}

	@BeforeMethod(groups = {"smoke", "regression"})
	public void configbm() throws Throwable {
		String username = fUtil.getPropertyFileData("username");
		String password = fUtil.getPropertyFileData("password");
		// Login into Application
		LoginPage login = new LoginPage(driver);
		login.loginToApplication(username, password);
	}

	@AfterMethod(groups = {"smoke", "regression"})
	public void configAm() {
		HomePage homepage = new HomePage(driver);
		homepage.logout();
	}

	@AfterClass(groups = {"smoke", "regression"})
	public void configAc() {
		// closing or quit the browser
		driver.quit();
	}

	@AfterSuite(groups = {"smoke", "regression"})
	public void configAs() {
		// closing the database connection
		System.out.println("====Database connection is closed");
	}

}
