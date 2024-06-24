package com.qa.opencart.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.appconstants.AppConstants;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ExcelUtil;

public class ProductResultTest extends BaseTest {

	@BeforeClass
	public void productDetailsSetUp()
	{
		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
//	
//	@DataProvider
//	public Object[][] getSearchData()
//	{
//		return new Object[][]	{
//			{"MacBook", "MacBook Pro",4},
//			{"MacBook", "MacBook Air",4},
//			{"iMac","iMac",3},
//			{"Samsung","Samsung SyncMaster 941BW",1}
//			};
//	}
	
	@DataProvider
	public Object[][] getProductSearchData()
	{
		Object searchData[][]=ExcelUtil.getTestData(AppConstants.EXCEL_TEST_PRODUCT_SEARCH_DATA);
		return searchData;
	}
	
	@Test(dataProvider= "getProductSearchData")
	public void productImageTest(String searchKey, String productName, String imageCount)
	{
	 searchresultpage = accPage.searchProduct(searchKey);
	 productdetailspage = searchresultpage.selectProduct(productName);
	 int actualproductCount = productdetailspage.getProductImageCount();
	 
	 Assert.assertEquals(String.valueOf(actualproductCount), imageCount);
	 
 }
	@DataProvider
	public Object[][] getProductData()
	{
		return new Object[][] {
			{"MacBook","MacBook Pro","Apple","Product 18","800","In Stock","$2,000.00","$2,000.00"}
		};
	}
	
	@Test(dataProvider="getProductData")
	public void GetProductMetaDataTest(String searchKey, String productName,String brand, String productCode,String rewardPoints, String availibility, String price, String extaxPrice )
	{
		searchresultpage = accPage.searchProduct(searchKey);
		productdetailspage  = searchresultpage.selectProduct(productName);
	 Map<String, String> prodDetailsMap =	productdetailspage.getProductDetails();
	 
	 //Assert.assertEquals(prodDetailsMap.get("Brand"),"Apple");
	 softassert.assertEquals(prodDetailsMap.get("Brand"), brand);
	 
	 //Assert.assertEquals(prodDetailsMap.get("Product Code"),"Product 18");
	 softassert.assertEquals(prodDetailsMap.get("Product Code"),productCode);
	// Assert.assertEquals(prodDetailsMap.get("Reward Points"),"800");
	 softassert.assertEquals(prodDetailsMap.get("Reward Points"),rewardPoints);
	// Assert.assertEquals(prodDetailsMap.get("Availability"),"In Stock");
	 softassert.assertEquals(prodDetailsMap.get("Availability"),availibility);
	 
	 //Assert.assertEquals(prodDetailsMap.get("Price"),"$2,000.00");	 
	 softassert.assertEquals(prodDetailsMap.get("Price"),price);	
	 //Assert.assertEquals(prodDetailsMap.get("ExTaxPrice"),"$2,000.001");
	 softassert.assertEquals(prodDetailsMap.get("ExTaxPrice"),extaxPrice);
	
	 softassert.assertAll();
 
		
		
	}
}
