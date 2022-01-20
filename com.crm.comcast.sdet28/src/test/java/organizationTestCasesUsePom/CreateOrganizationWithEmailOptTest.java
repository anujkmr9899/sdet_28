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

public class CreateOrganizationWithEmailOptTest {
	public static void main(String[] args) throws Throwable {
		// create object for utilities
		FileUtility fUtil = new FileUtility();
		JavaUtility jUtil = new JavaUtility();
		ExcelUtility eUtil = new ExcelUtility();
		WebDriverUtility wUtil = new WebDriverUtility();

		// get the data from property file
		String url = fUtil.getPropertyFileData("url");
		String browserName = fUtil.getPropertyFileData("browser");
		String username = fUtil.getPropertyFileData("username");
		String password = fUtil.getPropertyFileData("password");

		// generate random number
		int randomNumber = jUtil.getRandomNumber();

		// get data from excel sheet
		String organizationName = eUtil.getStringCellData("sheet1", 1, 2);
		organizationName = organizationName + randomNumber;

		// how to use browser value and launch the browser
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

		// Navigating to application
		driver.get(url);

		// Login to application
		LoginPage login = new LoginPage(driver);
		login.loginToApplication(username, password);

		//Clicking on the Organization Link
		HomePage homePage = new HomePage(driver);
		homePage.clickOrganizationLink();

		// click to create organization
		OrganizationPage orgpage = new OrganizationPage(driver);
		orgpage.clickCreateOrgImg();

		// Create New Organization with EmailOpt
		CreateOrganizationPage createOrgPage1 = new CreateOrganizationPage(driver);
		createOrgPage1.createOrganization(organizationName);
		createOrgPage1.clickEmailopt();
		createOrgPage1.saveButton();
		
		// Organization Information page should be Displayed
		OrganizationInformationPage orgInfoPage = new OrganizationInformationPage(driver);
		String actualOrgName = orgInfoPage.getOrgInformationText();
		
		//Verification of Organization Information
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
