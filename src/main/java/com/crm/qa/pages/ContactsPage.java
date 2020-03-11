package com.crm.qa.pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase{
	
	@FindBy(xpath = "//td[contains(text(),'Contacts')]") 
	WebElement contactsLable;
	
	@FindBy(id ="first_name")
	WebElement firstName;
	
	@FindBy(id ="surname")
	WebElement surName;
	
	@FindBy(name ="nickname")
	WebElement nickName;
	
	@FindBy(name = "client_lookup")
	WebElement company;
	
	@FindBy(xpath="//body/table/tbody/tr/td/table/tbody/tr/td/fieldset[@class='fieldset']/form[@id='contactForm']/table/tbody/tr/td/input[2]")
	WebElement saveBtn;
	
	
	public ContactsPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyContactsLable()
	{
		return contactsLable.isDisplayed();
	}
	
	public void selectContactsByName(String name)
	{
		driver.findElement(By.xpath("//form[@id='vContactsForm']//tr[5]//td[2]\r\n")).click();
	}
	
	public void createNewContact(String title, String fname, String lname, String nname, String com)throws InterruptedException
	{
		Select select = new Select(driver.findElement(By.name("title")));
		select.selectByVisibleText(title);
		firstName.sendKeys(fname);
		surName.sendKeys(lname);
		nickName.sendKeys(nname);
		company.sendKeys(com);
		saveBtn.click();
	}
	
	public void SelectMultipleContact(ArrayList<String>a1)
	{
		for(String contactName : a1)
		{
			driver.findElement(By.xpath("//a[contains(text(),'"+contactName+"')]/parent::td//preceding-sibling::td//input[@name='contact_id']")).click();
		}                               
	}                                    


}
