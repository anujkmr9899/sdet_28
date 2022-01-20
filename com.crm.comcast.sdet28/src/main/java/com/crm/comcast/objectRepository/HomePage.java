package com.crm.comcast.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUitility.WebDriverUtility;

/**
 * Homepage pom design
 * 
 * @author Anuj Prajapati
 *
 */

public class HomePage extends WebDriverUtility {

	// initialization of elements
	WebDriver driver;

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	// declaration of elements
	@FindBy(linkText = "Organizations")
	private WebElement organizationLink;

	@FindBy(linkText = "Contacts")
	private WebElement contactslink;

	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement logoutImg;

	@FindBy(linkText = "Sign Out")
	private WebElement signOutLink;

	// getters methods
	public WebElement getOrganizationLink() {
		return organizationLink;
	}

	public WebElement getContactslink() {
		return contactslink;
	}

	public WebElement getLogoutImg() {
		return logoutImg;
	}

	public WebElement getSignOutLink() {
		
		return signOutLink;
	}

	// business logics
	/**
	 * This method will click on organization link
	 */
	public void clickOrganizationLink() {
		organizationLink.click();
	}
	/**
	 * This method will performs logout action
	 */
	public void logout() {
		mouseOver(driver, logoutImg);
		signOutLink.click();
		
		
	}

}
