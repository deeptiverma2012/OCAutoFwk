package com.qa.opencart.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void regSetUp()
	{
		registerpage = loginpage.navigateToRegisterPage();
	}
	
	@Test
	public void createUserTest()
	{
		boolean isregDone=registerpage.createUser("Tommy", "Yem", "tom@opencart.com", "999999999", "Selenium@123", "Yes");
	}
}
