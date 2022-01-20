package organizationTestCasesTestng;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.crm.comcast.objectRepository.CreateOrganizationPage;
import com.crm.comcast.objectRepository.HomePage;
import com.crm.comcast.objectRepository.LoginPage;
import com.crm.comcast.objectRepository.OrganizationInformationPage;
import com.crm.comcast.objectRepository.OrganizationPage;

import genericUitility.BaseClass;
import genericUitility.ExcelUtility;
import genericUitility.FileUtility;
import genericUitility.IpathConstants;
import genericUitility.JavaUtility;
import genericUitility.WebDriverUtility;

/**
 * This 
 * @author Anuj Kumar
 *
 */

public class CreateOrganizationWithMembersOfTest extends BaseClass {
	@Test
	public  void CreateOrganizationWithMembersOf() throws Throwable {
		
		// generate random number
		int randomNumber = jutil.getRandomNumber();

		// get data from excel sheet
		String organizationName = eUtil.getStringCellData("sheet1", 1, 2);
		String MemberOfName = eUtil.getStringCellData("sheet1", 7, 2);
		String ParentBrowser = eUtil.getStringCellData("sheet1", 8, 3);
		String ChildBrowser = eUtil.getStringCellData("sheet1", 9, 3);
		organizationName = organizationName + randomNumber;

		// Clicking on the Organization Link
		HomePage homePage = new HomePage(driver);
		homePage.clickOrganizationLink();

		// Create New Organization
		OrganizationPage orgpage = new OrganizationPage(driver);
		orgpage.clickCreateOrgImg();
		CreateOrganizationPage createOrgPage = new CreateOrganizationPage(driver);
		createOrgPage.createOrganization(organizationName);

		// Select the member of organization name
		createOrgPage.createMember();
		createOrgPage.switchToWindow(driver, ChildBrowser, ParentBrowser, MemberOfName);
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

	}

}
