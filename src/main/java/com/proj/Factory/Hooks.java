package com.proj.Factory;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.proj.utils.ReadPropertyFile;

public class Hooks {
	
	protected WebDriver driver;
	DriverFactory df = new DriverFactory();
	protected Properties conProp = ReadPropertyFile.getConfigProperties();
	protected Properties testDataProp = ReadPropertyFile.getTestDataProperties();

	
	@BeforeMethod
	public void setUp() {
		driver = df.initWebDriver(conProp.getProperty("chromeBrowser"));
		driver.get(conProp.getProperty("url"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}


}
