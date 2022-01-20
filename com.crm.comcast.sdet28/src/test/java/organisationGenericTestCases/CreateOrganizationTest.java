package organisationGenericTestCases;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();

		// create organization
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		driver.findElement(By.xpath("(//img[@alt='Create Organization...'])")).click();
		driver.findElement(By.name("accountname")).sendKeys(organizationName);
		driver.findElement(By.name("button")).click();

		// organization name verification
		String actualorganizationName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (actualorganizationName.contains(organizationName)) {
			System.out.println("Organization is created");
		} else {
			System.out.println("organization is not created");

		}
		// logout action
		WebElement profileImg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mouseOver(driver, profileImg);
		driver.findElement(By.linkText("Sign Out")).click();

		driver.quit();

	}

}
