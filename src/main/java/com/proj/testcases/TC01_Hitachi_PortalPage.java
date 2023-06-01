package com.proj.testcases;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.proj.Factory.Hooks;
import com.proj.pageobject.Hitachi_ContactUS_PO;
import com.proj.utils.ReadPropertyFile;
import com.proj.listeners.MyListeners;

@Listeners(MyListeners.class)
public class TC01_Hitachi_PortalPage extends Hooks {

	Hitachi_ContactUS_PO hitachi;

	@Test(priority = 0)
	public void hitachiApplicationVerfication() {
		String contactUs_Title = ReadPropertyFile.getTestDataProperties().getProperty("contactUS_PG_Title");
		String irvine_LinkText = ReadPropertyFile.getTestDataProperties().getProperty("irvine_LinkText");
		String us_LinkText = ReadPropertyFile.getTestDataProperties().getProperty("us_LinkText");

		hitachi = new Hitachi_ContactUS_PO();
		//hitachi.user_launches_the_hitachi_application();
		hitachi.user_click_on_contact_us_button();
		hitachi.user_should_able_to_navigate_to_the_contact_us_page_and_verify_PageTitle(contactUs_Title);
		hitachi.user_verifies_Text_the_contact_us_page(irvine_LinkText, hitachi.getIrvine_LinkText());
		hitachi.user_verifies_Text_the_contact_us_page(us_LinkText, hitachi.getUs_LinkText());

	}

}
