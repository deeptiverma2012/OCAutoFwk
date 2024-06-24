package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.appconstants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {
	
	//private Webdriver & ElementUtil reference
		private WebDriver driver;
		private ElementUtil eleUtil;
		
		//private locators
		

		
		//public constructor
		
		public SearchResultPage(WebDriver driver)
		{
			this.driver= driver;
			eleUtil = new ElementUtil(driver);
		}
		
		//public methods
		public ProductDetailsPage selectProduct(String productName)
		{
			
			By productNameLocator = By.linkText(productName);
			eleUtil.waitForVisibilityOfElement(productNameLocator, AppConstants.MEDIUM_DEFAULT_TIME).click();
				
			return new ProductDetailsPage(driver);
		}
}
