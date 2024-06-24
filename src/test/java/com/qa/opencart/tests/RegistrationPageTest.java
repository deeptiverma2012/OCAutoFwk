package com.qa.opencart.tests;

import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.appconstants.AppConstants;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void regSetUp()
	{
		registerpage= loginpage.navigateToRegisterPage();
	}
	
	public String getRandomEmailId()
	{
		return "testautomation"+System.currentTimeMillis()+"@gmail.com";
		
		//return "testautomation"+UUID.randomUUID()+"@gmail.com";
		
	}
	
//	@DataProvider
//	public Object[][] getregistrationData()
//	{
//		return new Object[][] {
//			{"Tommy","Adams","9999999999", "Selenium@123", "Yes"}
////			{"Simmi","Adams","9999999999", "Selenium@123", "No"},
////			{"Jack","Henry","9999999999", "Selenium@123", "No"}
//		};				
//	}
	
	@DataProvider
	public Object[][] getUserRegExcelTestData()
	{
		
	 Object regData[][]=ExcelUtil.getTestData(AppConstants.EXCEL_TEST_REGISTER_DATA_SHEET);
	 return regData;
	}
	
	@Test(dataProvider = "getUserRegExcelTestData")
	public void CreateUserTest(String fName, String lName, String phone, String password, String subscribe)
	{
		boolean isRegDone= registerpage.createUser(fName, lName, getRandomEmailId(), phone, password, subscribe);
	    Assert.assertTrue(isRegDone);
	}
}
