package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.appconstants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductDetailsPage {
	
	//private Webdriver & ElementUtil reference
			private WebDriver driver;
			private ElementUtil eleUtil;
			
			Map<String,String> prodMap = new TreeMap<String,String>();	
			
			
			//private By Locators
			private By productHeader = By.cssSelector("div#content h1")	;	
			private By productImages = By.xpath("//ul[@class='thumbnails']/li/a");
			private By productPrice = By.xpath("//ul[@class='list-unstyled']/li/h2");
			private By productMetaData = By.xpath("(//div[@class='col-sm-4']//ul[@class ='list-unstyled'])[1]/li");
			private By prodPriceDetails = By.xpath("(//div[@class='col-sm-4']//ul[@class ='list-unstyled'])[2]/li");
			//public constructor
			
		  public ProductDetailsPage(WebDriver driver) 
		  {
				this.driver = driver;
				eleUtil = new ElementUtil(driver);
				
		}
		  
		  
		  
		 public String getProductHeader()
		 {
			String actualProductHeader= eleUtil.getText(productHeader);
			System.out.println(actualProductHeader);
			return actualProductHeader;
			
		 }
		 
		 public int getProductImageCount()
		 {
			int productimagecount= eleUtil.waitForPresenceOfAllElement(productImages, AppConstants.LONG_DEFAULT_TIME).size();
			return productimagecount;
		 }
			
//		 public String getProductPrice() {
//			 
//			String actualPrice= eleUtil.getText(productPrice);
//			
//			return actualPrice;
//		 }

		 private void getProductMetaData()
		 {
			 List<WebElement> metaDataList = eleUtil.waitForVisibilityOfElements(productMetaData, AppConstants.MEDIUM_DEFAULT_TIME);
			 for(WebElement e: metaDataList)
			 {
				String metaData= e.getText();
				String metaKey =  metaData.split(":")[0].trim();
				String metaValue =  metaData.split(":")[1].trim();
				prodMap.put(metaKey, metaValue);
				
				
			 }
		 }
		 private void getProductPriceDetails()
		 {
			List<WebElement> priceList = eleUtil.waitForPresenceOfAllElement(prodPriceDetails, AppConstants.MEDIUM_DEFAULT_TIME);
			String Price = priceList.get(0).getText();				
			String prodExTaxPrice =	priceList.get(1).getText().split(":")[1].trim();
			
			prodMap.put("Price", Price);
			prodMap.put("ExTaxPrice",prodExTaxPrice);
			
		 }
		 
		 public Map<String, String> getProductDetails()
		 {
			 
			 prodMap.put("productName", getProductHeader());
			 getProductMetaData();
			 getProductPriceDetails();
			 
			 System.out.println(prodMap);
			 return prodMap;
			 
		 }
			
		 }
			
			

