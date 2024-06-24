package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.appconstants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {

	// private references
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//private locators/
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By emailId = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By passwd = By.id("input-password");
	private By confirmpasswd = By.id("input-confirm");
	private By subscribeYes = By.xpath("(//input[@name='newsletter'])[1]");
	private By subscribeNo = By.xpath("(//input[@name='newsletter'])[2]");
	private By agreeCheckbox = By.xpath("//input[@type='checkbox']");
	private By continueBtn = By.cssSelector("input.btn.btn-primary");
	private By sucessMsg = By.xpath("//div[@id='content']/h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	
	//public constructor
	public RegisterPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public boolean createUser(String firstname, String lastname,String email, String telephone, String password, String subscribe )
	{
		eleUtil.doSendKeys(this.firstName, firstname);
		eleUtil.doActionsSendKeys(this.lastName, lastname);
		eleUtil.doActionsSendKeys(this.emailId, email);
		eleUtil.doActionsSendKeys(this.telephone, telephone);
		eleUtil.doActionsSendKeys(this.passwd, password);
		eleUtil.doActionsSendKeys(this.confirmpasswd, password);
		if(subscribe.equalsIgnoreCase("yes")) {
		eleUtil.doActionsSendKeys(this.subscribeYes, subscribe);
		}
		else
		{
			eleUtil.doActionsSendKeys(this.subscribeNo, subscribe);
		}
		eleUtil.doClick(agreeCheckbox);
		eleUtil.doClick(continueBtn);
		
		String successMessage = eleUtil.waitForVisibilityOfElement(sucessMsg, AppConstants.MEDIUM_DEFAULT_TIME).getText();
		if(successMessage.contains(AppConstants.REGISTER_SUCCESS_MESSAGE))
		{
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink );
			return true;
		}
		else
		{
			return false;
		}
	}
	

}