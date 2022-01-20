package organizationTestCasesTestng;

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
 * 
 * @author Anuj Kumar
 *
 */

public class CreateOrganizationWithMembersOfandClearTest extends BaseClass{
@Test
	public void CreateOrganizationWithMembersOfandClear() throws Throwable {
		
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

		// Click on OrganizationPage image
		OrganizationPage orgpage = new OrganizationPage(driver);
		orgpage.clickCreateOrgImg();
		
		// Write Organization name TextField
		CreateOrganizationPage createOrgPage = new CreateOrganizationPage(driver);
		createOrgPage.createOrganization(organizationName);

		// Create and write member of organization name and clear 
		createOrgPage.createMember();
		createOrgPage.switchToWindow(driver, ChildBrowser, ParentBrowser, MemberOfName);
		createOrgPage.cleartheMemberOfNameTextField();
		createOrgPage.saveButton();
		
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
	
	}

}
