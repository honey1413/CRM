package com.crm.qa.testcases;

	import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

	import com.crm.qa.base.TestBase;
	import com.crm.qa.pages.ContactsPage;
	import com.crm.qa.pages.HomePage;
	import com.crm.qa.pages.Loginpage;
	import com.crm.qa.util.TestUtil;

	public class ContactsPageTest extends TestBase
	{
		Loginpage loginpage;
		HomePage homePage;
		TestUtil testUtil;
		ContactsPage contactsPage;
			
		public ContactsPageTest ()
		{
			super();
		}
		
		@BeforeMethod
		public void setUp()throws InterruptedException
		{
			initalization();
			testUtil = new TestUtil();
			contactsPage = new ContactsPage();
			loginpage  = new Loginpage();
			homePage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
			testUtil.switchToFrame();
			//contactsPage =homePage.clickOnContactsLink();
		
		}
		
		@Test(priority=1)
		public void verifyContactsPageLable()
		{
			Assert.assertTrue(contactsPage.verifyContactsLable(),"contacts lable is missing on the page");
		}
		
		@Test(priority=2)
		public void selectContactsByName()
		{
			contactsPage.selectContactsByName("hari manam");
		}
		
		@Test(priority=3)
		public void selectMultipleContactsByName()
		{
			contactsPage.selectContactsByName("hari manam");
			contactsPage.selectContactsByName("tejab prathro");
		}
		@DataProvider
		public Object[][]getCRMTestData()throws IOException
		{
			Object data[][] = TestUtil.getTestData("Contacts");
			return data;
		}
		
		@Test(priority=4, dataProvider="getCRMTestData")
		public void validateCreateNewContact(String title, String firstName, String lastName, String nickName,String company)throws InterruptedException,IOException
		{
			homePage.clickOnNewContactLink();
			contactsPage.createNewContact(title, firstName, lastName, nickName, company);
			//ArrayList<String> a1 =new ArrayList<String>();
			//a1.add("hari manam");
			//contactsPage.SelectMultipleContact(a1);
			String tablexpath = "//form[@id='vContactsForm']//table[@class='datacard']/tbody";
			//TestUtil.verifyDataExists(tablexpath,"Mobile","9876535017");
			TestUtil.getTestData(TestUtil.Sheet_Name);
		}
		
		@AfterMethod
		public void tearDown()
		{
			driver.quit();
		}
		
		
	}


