package com.crm.comcast.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUitility.WebDriverUtility;

/**
 * Create organization pom class
 * 
 * @author Anuj Prajapati
 *
 */

public class CreateOrganizationPage extends WebDriverUtility {
	WebDriver driver;

	public CreateOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "accountname") // write organization name
	private WebElement organizationTextField;

	@FindBy(xpath = "//img[@title='Select']") // select plus button
	private WebElement clickPlusButton;

	@FindBy(xpath = "//input[@type='text']") // write organization name in memberOf name
	private WebElement memberOfOrgTextField;

	@FindBy(name = "search") // click on search button
	private WebElement searchTextField;

	@FindBy(xpath = "//a[@href='javascript:window.close();']") // click org name in child browser window
	private WebElement clickorgname;

	@FindBy(xpath = "//input[@src='themes/images/clear_field.gif']") // clear the member text field
	private WebElement cleartheMemberOfNameTextField;

	@FindBy(name = "emailoptout") // click check box emailOptOut
	private WebElement clickEmailopt;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']") // click the save button
	private WebElement saveButton;

	// getters method

	public WebElement getOrganizationTextField() {
		return organizationTextField;
	}

	public WebElement getMemberOfOrgTextField() {
		return memberOfOrgTextField;
	}

	public WebElement getSearchTextField() {
		return searchTextField;
	}

	public WebElement getClickorgname() {
		return clickorgname;
	}

	public WebElement getCleartheMemberOfNameTextField() {
		return cleartheMemberOfNameTextField;
	}

	public WebElement getClickEmailopt() {
		return clickEmailopt;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	//Business Logics
	/**
	 * This method will create an organization
	 * 
	 * @param organizationName
	 */
	public void createOrganization(String organizationName) {
		organizationTextField.sendKeys(organizationName);
	}

	/**
	 * This method click plus button to create memberOf
	 */
	public void createMember() {
		clickPlusButton.click();
	}

	/**
	 * This method will switch to Parent browser to child browser and search member
	 * Of name and swtich to parent browser
	 * 
	 * @param driver
	 * @param childBrowser
	 * @param parentBrowser
	 * @param membername
	 */
	public void switchToWindow(WebDriver driver, String childBrowser, String parentBrowser, String membername) {
		switchWindow(driver, childBrowser);
		memberOfOrgTextField.sendKeys(membername);
		searchTextField.click();
		clickorgname.click();
		driver.switchTo().alert().accept();
		switchWindow(driver, parentBrowser);
	}

	/**
	 * This method will clear the text field member of name
	 */
	public void cleartheMemberOfNameTextField() {
		cleartheMemberOfNameTextField.click();
	}

	/**
	 * This method will click the EmailOpt
	 */
	public void clickEmailopt() {
		clickEmailopt.click();
	}

	/**
	 * This method will click the save button
	 */
	public void saveButton() {
		saveButton.click();
	}
}
