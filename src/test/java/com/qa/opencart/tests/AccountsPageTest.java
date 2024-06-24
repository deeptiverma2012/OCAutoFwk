package com.qa.opencart.tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.appconstants.AppConstants;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.SearchResultPage;

public class AccountsPageTest extends BaseTest{

	
	@BeforeClass
	public void accSetUp()
	{
		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void AccountPageTitleTest()
	{
		String title = accPage.getAccountPageTitle();
		Assert.assertEquals(title,AppConstants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void AccountPageURLTest()
	{
		String url = accPage.getAccountsPageURL();
		Assert.assertTrue(url.contains(AppConstants.ACCOUNT_PAGE_URL_FRACTION));
	}
	@Test
	public void AisLogOutLinkExistsTest()
	{
		 Assert.assertTrue(accPage.isLogOutLinkExists());
	}
	@Test
	public void SearchFieldExistTest()
	{
		Assert.assertTrue(accPage.isSearchFieldExists());
	}
	
	@Test
	public void AccountsPageHeadersCountTest()
	{
		List<String> accPageHeadersList = accPage.getAccountHeaders();
		System.out.println("Accounts Page Header");
		Assert.assertEquals(accPageHeadersList.size(), AppConstants.ACCOUNT_PAGE_HEADERS_COUNT);
	}
	
	@Test
	public void AccountsPageHeadersTest()
	{
		List<String> actualHeadersList= accPage.getAccountHeaders();
		Assert.assertEquals(actualHeadersList, AppConstants.ACCOUNT_PAGE_HEADERS);
	}
	
	@Test
	public void SearchTest()
	{
		 searchresultpage = accPage.searchProduct("Macbook");
		 productdetailspage = searchresultpage.selectProduct("MacBook Pro");
		 String actualHeader = productdetailspage.getProductHeader();
		 Assert.assertEquals(actualHeader,"MacBook Pro");
			 
	}
}
