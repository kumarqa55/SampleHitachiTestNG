package com.proj.pageobject;

import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CommonFunctionality extends Base_Page {


	/**
	 * Click Functionality using Xpath
	 */
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
	

	/**
	 * Click Functionality using Webelement
	 */
	public void clickFunction(WebElement element) {

		try {
			explicitWait(element);
			longWait.until(ExpectedConditions.elementToBeClickable(element)).click();
		} catch (StaleElementReferenceException ser) {
			longWait.until(ExpectedConditions.elementToBeClickable(element)).click();
		} catch (NoSuchElementException nse) {
			System.out.println("Element Not Found : " + element + " | Error- " + nse);
		}
	}

	public String getTextFunction(WebElement element) {
		explicitWait(element);
		String text = longWait.until(ExpectedConditions.visibilityOf(element)).getText();
		return text;

	}
	
	public String getWebelementText(String xpath) {
		String text = null;
		try {
			explicitWait(xpath);
			text = longWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).getText();
		} catch (NoSuchElementException nse) {
			System.out.println("Element Not Found : " + xpath + " | Error - " + nse);
		} catch (StaleElementReferenceException sre) {
			getWebelementText(xpath);
		}
		return text;

	}
	
	public void windowHandle() {
		String parent = driver.getWindowHandle();
		Set<String> childWindow = driver.getWindowHandles();
		for(String window : childWindow) {
			if(!window.equals(parent)) {
				driver.switchTo().window(window);
			}
			driver.switchTo().window(parent);
		}
	}
	
	

}
