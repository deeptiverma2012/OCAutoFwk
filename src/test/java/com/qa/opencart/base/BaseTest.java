package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductDetailsPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultPage;

public class BaseTest {

	protected WebDriver driver;
	DriverFactory df;
    protected LoginPage loginpage;
    protected AccountsPage accPage;
	protected Properties prop;
	protected SearchResultPage searchresultpage; 
	protected ProductDetailsPage productdetailspage;
	protected SoftAssert softassert;
	protected RegisterPage registerpage;
	
	
	

    @Parameters({"browser"})
    @BeforeTest
	public void setUp(String browserName)
	{
    	
		df = new DriverFactory();
		prop = df.initProp();
		if(browserName!=null)
		{
			prop.setProperty("browser", browserName);
		}
		driver = df.initDriver(prop);
		
		
		loginpage = new LoginPage(driver);
		softassert = new SoftAssert();
		
		
	}
    
    @AfterTest
	public void tearDown()
	{
    	driver.quit();
	}
}
