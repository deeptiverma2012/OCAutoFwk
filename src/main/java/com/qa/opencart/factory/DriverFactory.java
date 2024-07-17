package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsmanager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public WebDriver initDriver(Properties prop)
	{
		String browserName = prop.getProperty("browser");
		System.out.println("browser name is:"+browserName);
		
		optionsmanager = new OptionsManager(prop);
		switch(browserName.toLowerCase().trim())
		{
		case "chrome":
			if(Boolean.parseBoolean(prop.getProperty("remote")))
			{
				initRemoteDriver(browserName);
			}
			
			else {
			driver = new ChromeDriver(optionsmanager.getChromeOptions());
			tlDriver.set(driver);
			}
			break;
			
		case "edge":
			if(Boolean.parseBoolean(prop.getProperty("remote")))
			{
				initRemoteDriver(browserName);
			}
		else{
			driver = new EdgeDriver(optionsmanager.getEdgeOptions());
			tlDriver.set(driver);
		}
			break;
			
		case "firefox":
			if(Boolean.parseBoolean(prop.getProperty("remote")))
			{
				initRemoteDriver(browserName);
			}
			else{
				driver = new FirefoxDriver(optionsmanager.getFirefoxOptions());
				tlDriver.set(driver);
			}
			break;
			
		case "safari":
			driver = new SafariDriver();
			tlDriver.set(driver);
			break;
			
			default:
				System.out.println("Please pass a valid browser name:"+browserName);
				throw new FrameworkException("INVALID BROWSER");
				
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	private void initRemoteDriver(String browserName) {
		System.out.println("Running tests on grid with browser"+browserName);
		
		try
		{
			switch(browserName.toLowerCase().trim())
		{
		case "chrome":
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),optionsmanager.getChromeOptions()));
			break;
		
		case "firefox":
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),optionsmanager.getFirefoxOptions()));
			break;
		
		case "edge":
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),optionsmanager.getEdgeOptions()));
			break;
			
		default:
			System.out.println("Wrong Browser Info...Cannnot run on grid remote machine....");
			break;
				
	}
		}
		catch(MalformedURLException e)
		{
			
		}
		
	}

	public static WebDriver getDriver()
	{
		return tlDriver.get();
	}
	public Properties initProp()
	{
		FileInputStream ip =null;
		 prop = new Properties();
		 
		String envName = System.getProperty("env");
		 try 
		 {
			if(envName == null) {
				System.out.println("Your environment is null...hence running tests on default environment");
			ip = new  FileInputStream("./src/test/resources/config/config.qa.properties");
			
		}
			else
			{
				switch(envName.toLowerCase().trim())
				{
				case "qa":
					ip = new  FileInputStream("./src/test/resources/config/config.qa.properties");
					break;
				case "stage":
					ip = new  FileInputStream("./src/test/resources/config/config.stage.properties");
					break;
				case "uat":
					ip = new  FileInputStream("./src/test/resources/config/config.uat.properties");
					break;
				case "prod":
					ip = new  FileInputStream("./src/test/resources/config/config.properties");
					break;
				default:
					System.out.println("Please pass a valid environment"+envName);
					throw new FrameworkException("Not a Valid Environment");
				}
			}
		 }
			 
		 catch (FileNotFoundException e) {
					
					e.printStackTrace();
				}
			
			try {
				prop.load(ip);
			}
		 
		 
		 catch (IOException e) {
			e.printStackTrace();
		}
		 return prop;
	}
	
	public static String getScreenshot(String methodName)
	{
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		
		String path = System.getProperty("user.dir")+"/screenshot/"+methodName+"_"+System.currentTimeMillis()+".png";
		
		File destination = new File(path);
		
		try {
			  FileHandler.copy(srcFile,destination);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return path;
	}
}
