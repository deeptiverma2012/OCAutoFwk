package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.appconstants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	//private webDriver
		private WebDriver driver;
		private ElementUtil eleUtil;
		
		//private locators
		
		private By logo = By.xpath("//img[@title='naveenopencart']");
		private By username = By.id("input-email");
		private By password = By.id("input-password");
		private By submitBtn = By.xpath("//input[@type='submit']");
		private By forgotPwd = By.linkText("Forgotten Password");
		private By footerHeaders= By.xpath("//div[@class='row']/div[@class='col-sm-3']/h5");
		private By footersubheader = By.xpath("//div[@class='row']/div[@class='col-sm-3']/h5/parent::div/ul/li/a");
		private By registerLink = By.linkText("Register");
		
		
		public LoginPage(WebDriver driver)
		{
			this.driver = driver;
			eleUtil = new ElementUtil(driver);
		}
		@Step("Getting logion page title")
		public String getLoginPageTitle()
		{
			
			String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, 5);
			System.out.println("Login page title is:"+ title);
			return title;
		}
		@Step("Getting login page url")
		public String getLoginPageURL()
		{
			String url = eleUtil.waitForUrlIs(AppConstants.LOGIN_PAGE_URL, 5);
			System.out.println("Login page url is"+url);
			return url;
		}
		@Step("Checking if logo exist")
		public boolean isLogoExist() {
			
			return eleUtil.waitForVisibilityOfElement(logo, AppConstants.SHORT_DEFAULT_TIME).isDisplayed();
			
		}
		@Step("Checking if forgot password link exist")
		public boolean isForgotPwdLinkExist()
		{
			return eleUtil.waitForVisibilityOfElement(forgotPwd, AppConstants.SHORT_DEFAULT_TIME).isDisplayed();
			
		}
		@Step("userName is{0} and passWord{1}")
		public AccountsPage doLogin(String userName, String passWord)
		{
			System.out.println("Credentials are:"+userName+" "+passWord);
			eleUtil.waitForVisibilityOfElement(username, AppConstants.SHORT_DEFAULT_TIME).sendKeys("deepti@opencart.com");
			eleUtil.doSendKeys(password, "Selenium@123");
			eleUtil.doClick(submitBtn);			
			return new AccountsPage(driver);
		}
		@Step("Getting login page footer links")
		public List<String> getLoginPageFooterHeaders()
		{
			List<WebElement> footerLoginHeaders=eleUtil.waitForPresenceOfAllElement(footerHeaders, AppConstants.MEDIUM_DEFAULT_TIME);
			List<String> listOfHeaders = new ArrayList<String>();
			for(WebElement e: footerLoginHeaders)
			{
				String headersText = e.getText();
				listOfHeaders.add(headersText);
			}
			return listOfHeaders;
		}
		@Step("Getting login page subheaders of the footer")
		public List<String> getLoginPageSubHeaders()
		{
			List<WebElement> subFooterList=eleUtil.waitForPresenceOfAllElement(footersubheader, AppConstants.MEDIUM_DEFAULT_TIME);
		    List<String> footerOptionsList = new ArrayList<String>();
			for(WebElement e: subFooterList)
			{
				String options = e.getText();
				footerOptionsList.add(options);
			}
			return footerOptionsList;
		}
		@Step("Navigate to register page")
		public RegisterPage navigateToRegisterPage()
		{
			eleUtil.waitForVisibilityOfElement(registerLink, AppConstants.SHORT_DEFAULT_TIME).click();
			return new RegisterPage(driver);
		}
}
