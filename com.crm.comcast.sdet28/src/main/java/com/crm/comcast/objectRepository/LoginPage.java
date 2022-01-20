package com.crm.comcast.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * 
 * @author Anuj Prajapati
 *
 */

public class LoginPage {

	// element initialization
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// element identification or declaration
	@FindBy(name = "user_name")
	private WebElement usernameTextField;

	@FindBy(name = "user_password")
	private WebElement passwordTextField;

	@FindBy(id = "submitButton")
	private WebElement loginButton;

	// getters methods
	public WebElement getUsernameTextField() {
		return usernameTextField;
	}

	public WebElement getPasswordTextField() {
		return passwordTextField;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}
	//business logic for logic action
	public void loginToApplication(String username,String password) {
		usernameTextField.sendKeys(username);
		passwordTextField.sendKeys(password);
		loginButton.click();
		;
		
	}
	

	

}