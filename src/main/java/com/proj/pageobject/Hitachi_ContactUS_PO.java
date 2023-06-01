package com.proj.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class Hitachi_ContactUS_PO extends Base_Page {

	CommonFunctionality cf = new CommonFunctionality();

	// Locators
	@FindBy(xpath = "//a[@class='btn btn-primary' and text()='Contact us']")
	protected WebElement contactUS;

	private String irvine_LinkText = "//a[contains(text(),'Irvine')]";
	private String us_LinkText = "//a[contains(text(),'United States')]";
	
	public String getIrvine_LinkText() {
		return irvine_LinkText;
	}

	public void setIrvine_LinkText(String irvine_LinkText) {
		this.irvine_LinkText = irvine_LinkText;
	}
	
	public String getUs_LinkText() {
		return us_LinkText;
	}

	public void setUs_LinkText(String us_LinkText) {
		this.us_LinkText = us_LinkText;
	}
	

	/*
	 * public void user_launches_the_hitachi_application() {
	 * driver.get("https://global.hitachi-solutions.com/"); }
	 */

	public void user_click_on_contact_us_button() {
		cf.clickFunction(contactUS);

	}

	public void user_should_able_to_navigate_to_the_contact_us_page_and_verify_PageTitle(String expTitle) {
		String actTitle = driver.getTitle();
		Assert.assertEquals(expTitle, actTitle);
		System.out.println("The Title is : " + actTitle);
	}

	public void user_verifies_Text_the_contact_us_page(String expValue, String xpath) {

		String actValue = cf.getWebelementText(xpath);
		Assert.assertTrue(actValue.contains(expValue));
		System.out.println("The Link_Text Contains : " + expValue);

	}

	

	

}
