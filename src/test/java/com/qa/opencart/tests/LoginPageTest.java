package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.appconstants.AppConstants;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("Epic#101: Design open cart login page")
@Story("US#101: Login page features")
@Feature("F50: Feature login page")
public class LoginPageTest extends BaseTest{

	@Description("Login Page Title Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=1)
	public void LoginPageTitleTest()
	{
		String actTitle= loginpage.getLoginPageTitle();
		Assert.assertEquals(actTitle,AppConstants.LOGIN_PAGE_TITLE);
		
	}
	@Test
	public void LoginPageURLTest()
	{
		String url = loginpage.getLoginPageURL();
		Assert.assertTrue(url.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Test
	public void LoginPageLogoTest()
	{
		Assert.assertTrue(loginpage.isLogoExist());
	}
	
	@Test
	public void LoginPageForgotPwdLinkTest()
	{
		Assert.assertTrue(loginpage.isForgotPwdLinkExist());
	}
	
	@Test(priority=1)
	public void LoginTest()
	{
			accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
			Assert.assertTrue(accPage.isLogOutLinkExists());
			
	}
	@Test
	public void FooterHeaderTest()
	{
		List<String> headersList = loginpage.getLoginPageFooterHeaders();
		softassert.assertTrue(headersList.contains("Customer Service"));
		softassert.assertTrue(headersList.contains("Extras"));
		softassert.assertTrue(headersList.contains("My Account"));
		softassert.assertAll();
	}
	
	@Test
	public void FooterSubHeadsTest()
	{
		List<String> subheadersList = loginpage.getLoginPageSubHeaders();
		softassert.assertTrue(subheadersList.contains("Privacy Policy"));
		softassert.assertTrue(subheadersList.contains("Returns"));
		softassert.assertTrue(subheadersList.contains("Gift Certificates"));
		softassert.assertTrue(subheadersList.contains("Order History"));
		softassert.assertAll();
	}
	
	}

