package organizationTestCasesUsePom;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.crm.comcast.objectRepository.CreateOrganizationPage;
import com.crm.comcast.objectRepository.HomePage;
import com.crm.comcast.objectRepository.LoginPage;
import com.crm.comcast.objectRepository.OrganizationInformationPage;
import com.crm.comcast.objectRepository.OrganizationPage;

import genericUitility.ExcelUtility;
import genericUitility.FileUtility;
import genericUitility.IpathConstants;
import genericUitility.JavaUtility;
import genericUitility.WebDriverUtility;

/**
 * 
 * @author Anuj Prajapati
 *
 */

public class CreateOrganizationTest {
	public static void main(String[] args) throws Throwable {
		// create object for utilities
		FileUtility futil = new FileUtility();
		JavaUtility jutil = new JavaUtility();
		ExcelUtility eUtil = new ExcelUtility();
		WebDriverUtility wUtil = new WebDriverUtility();

		// get the data from property file
		String url = futil.getPropertyFileData("url");
		String browserName = futil.getPropertyFileData("browser");
		String username = futil.getPropertyFileData("username");
		String password = futil.getPropertyFileData("password");

		// generate random number
		int randomNumber = jutil.getRandomNumber();

		// get data from excel sheet
		String organizationName = eUtil.getStringCellData("sheet1", 1, 2);
		organizationName = organizationName + randomNumber;

		// Launch the browser
		WebDriver driver = null;
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty(IpathConstants.CHROME_KEY, IpathConstants.CHROME_PATH);
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			System.setProperty(IpathConstants.FIREFOX_KEY, IpathConstants.FIREFOX_PATH);

		} else {
			System.out.println("browser is not supported");
		}
		driver.manage().window().maximize();
		wUtil.waitForPageLoad(driver);

		// Navigating the Application URL
		driver.get(url);

		// login to application
		LoginPage login = new LoginPage(driver);
		login.loginToApplication(username, password);

		// Clicking on the Organization Link
		HomePage homePage = new HomePage(driver);
		homePage.clickOrganizationLink();

		// Create New Organization
		OrganizationPage orgpage = new OrganizationPage(driver);
		orgpage.clickCreateOrgImg();
		CreateOrganizationPage createOrgPage = new CreateOrganizationPage(driver);
		createOrgPage.createOrganization(organizationName);
		createOrgPage.saveButton();

		// Organization Information page should be Displayed
		OrganizationInformationPage orgInfoPage = new OrganizationInformationPage(driver);
		String actualOrgName = orgInfoPage.getOrgInformationText();

		// Verification of Organization Information
		String actualorganizationName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (actualorganizationName.contains(organizationName)) {
			System.out.println("Organization is created");
		} else {
			System.out.println("organization is not created");
		}
		
		// logout action
		homePage.logout();

		// quit the browser
		driver.quit();

	}

}
