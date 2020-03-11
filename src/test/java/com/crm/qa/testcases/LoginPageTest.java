package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.Loginpage;

public class LoginPageTest extends TestBase{
	Loginpage loginpage;
	HomePage homePage;
	public LoginPageTest()
	{
		super();
	}
	
		

	@BeforeMethod
	public void setUp() 
	{
		initalization();
		loginpage  = new Loginpage();
	}
	
	@Test(priority=1)
	public void validateLoginPageTitle()
	{
		String title = loginpage.validateLoginPageTitle();
		Assert.assertEquals(title,"CRMPRO - CRM software for customer relationship management, sales, and support.");
	}
	
	@Test(priority=2)
	public void validateCRMImage()
	{
		boolean flag =  loginpage.validateCRMImage();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=3)
	public void loginTest()
	{
		homePage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
