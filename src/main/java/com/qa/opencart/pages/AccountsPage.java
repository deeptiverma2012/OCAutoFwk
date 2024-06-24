package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import com.qa.opencart.appconstants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	//private Webdriver & ElementUtil reference
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//private locators
	
	private By logoutBtn = By.linkText("Logout");
	private	By searchField = By.xpath("//input[@name='search']");
	private By accHeaders = By.xpath("//div[@id='content']/h2");
	private By searchIcon = By.cssSelector("button.btn.btn-default.btn-lg");
	
	
	
	public AccountsPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	public String getAccountPageTitle()
	{
		
		String title = eleUtil.waitForTitleIs(AppConstants.ACCOUNT_PAGE_TITLE,AppConstants.MEDIUM_DEFAULT_TIME);
		System.out.println("Accounts page title is:"+ title);
		return title;
	}
	public String getAccountsPageURL()
	{
		String url = eleUtil.waitForUrl(AppConstants.ACCOUNT_PAGE_URL, AppConstants.MEDIUM_DEFAULT_TIME);
		System.out.println("Accounts page url is"+url);
		return url;
	}
	
	public boolean isSearchFieldExists()
	{
		return eleUtil.waitForVisibilityOfElement(searchField, AppConstants.SHORT_DEFAULT_TIME).isDisplayed();
	}
	
	public boolean isLogOutLinkExists()
	{
		return eleUtil.waitForVisibilityOfElement(logoutBtn, AppConstants.SHORT_DEFAULT_TIME).isDisplayed();
	}
	
	public void logout()
	{
		if(isLogOutLinkExists())
		{
			eleUtil.doClick(logoutBtn);
		}
	}
	
	public List<String> getAccountHeaders()
	{
		List<WebElement> elements = eleUtil.waitForPresenceOfAllElement(accHeaders, AppConstants.SHORT_DEFAULT_TIME);
		List<String> headersValue = new ArrayList<String>();
		
		for(WebElement e: elements)
		{
			String text = e.getText();
			headersValue.add(text);
			
		}
		
		return headersValue;
	}
	
	
	public  SearchResultPage searchProduct(String SearchItem)
	{	eleUtil.waitForVisibilityOfElement(searchField,AppConstants.MEDIUM_DEFAULT_TIME ).clear();
		eleUtil.waitForVisibilityOfElement(searchField,AppConstants.MEDIUM_DEFAULT_TIME ).sendKeys(SearchItem);
		eleUtil.doClick(searchIcon);
		return new SearchResultPage(driver);
	}
	
	}
