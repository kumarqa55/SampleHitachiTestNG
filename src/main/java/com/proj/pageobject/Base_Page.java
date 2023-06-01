package com.proj.pageobject;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.proj.Factory.DriverFactory;
import com.proj.utils.ReadPropertyFile;

public class Base_Page {
	public WebDriver driver;
	public WebDriverWait longWait;
	public WebDriverWait mediumWait;
	public WebDriverWait shortWait;
	public Properties testDataProp;

	public Base_Page() {
		driver = DriverFactory.getDeviceDriver();
		PageFactory.initElements(driver, this);

		longWait = new WebDriverWait(driver, Duration.ofSeconds(60));
		mediumWait = new WebDriverWait(driver, Duration.ofSeconds(40));
		shortWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		testDataProp = ReadPropertyFile.getTestDataProperties();

	}

	public void explicitWait(WebElement element) {
		longWait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void explicitWait(String xpath) {
		longWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	public void sendKeys(WebElement element, String sendText) {
		try {
			explicitWait(element);
			shortWait.until(ExpectedConditions.visibilityOf(element)).clear();
			element.sendKeys(sendText);
		} catch (StaleElementReferenceException stale) {
			driver.navigate().refresh();
			explicitWait(element);
			shortWait.until(ExpectedConditions.visibilityOf(element)).clear();
			element.sendKeys(sendText);
		}
	}

	public void sendKeys(String xpath, String sendText) {
		try {
			explicitWait(xpath);
			shortWait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).clear();
			shortWait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).sendKeys(sendText);

		} catch (StaleElementReferenceException stale) {
			driver.navigate().refresh();
			sendKeys(xpath, sendText);
		}
	}

	public void clickFunction(String xpath) {
		try {
			explicitWait(xpath);
			longWait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
		} catch (NoSuchElementException nse) {
			System.out.println("Element not found :" + xpath + "| Error - " + nse);
		} catch (StaleElementReferenceException stale) {
			driver.navigate().refresh();
			clickFunction(xpath);
		}
	}

	public void selectRadio_ChekBox(String xpath) {
		boolean isSelect = longWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).isEnabled();
		if (isSelect) {
			isSelect = shortWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).isSelected();
			if (isSelect)
				System.out.println("The CheckBox or Radio Button is Already Selected");
			else
				clickFunction(xpath);
		} else {
			System.out.println("The CheckBox or Radio Button is in Disabled State");
			driver.navigate().refresh();
			selectRadio_ChekBox(xpath);
		}
	}

}
