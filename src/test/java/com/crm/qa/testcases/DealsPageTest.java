package com.crm.qa.testcases;

import java.awt.List;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.Loginpage;
import com.crm.qa.util.TestUtil;

public class DealsPageTest extends TestBase
{
	
		Loginpage loginpage;
		HomePage homePage;
		TestUtil testUtil;
		//ContactsPage contactsPage;
		DealsPage dealsPage;
		
		public DealsPageTest()
		{
			super();
		}
		
		@BeforeMethod
		public void setUp()throws InterruptedException
		{
			initalization();
			testUtil = new TestUtil();
			dealsPage = new DealsPage();
			loginpage  = new Loginpage();
			homePage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
			testUtil.switchToFrame();
			//contactsPage =homePage.clickOnContactsLink();
		
		}
		
		@DataProvider
		public Object[][]getCRMTestData()throws IOException
		{
			Object data[][] = TestUtil.getTestData("Deals");
			return data;
		}
		
		@Test(priority=4, dataProvider="getCRMTestData")
		public  void validateCreateNewDeals(String title, String com, String amt)throws InterruptedException,IOException
		{
			homePage.clikOnNewDealsLink();
			dealsPage.createNewDeals(title, com, amt);
			String talexpath = "//form[@id='vDealsForm']//table[@class='datacard']/tbody";
			TestUtil.getTestData(TestUtil.Sheet_Name);
		}
		
		@AfterMethod
		public void tearDown()
		{
			driver.quit();
		}
}