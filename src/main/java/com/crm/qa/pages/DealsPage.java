package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class DealsPage extends TestBase {
	
	@FindBy(xpath = "//td[contains(text(),'Deals')]")
	WebElement dealsLable;
	
	@FindBy(id = "title")
	WebElement Title;
	
	@FindBy(name="client_lookup")
	WebElement Company;
	
	@FindBy(id = "amount")
	WebElement Amount;
	
	@FindBy(xpath = "//body/table/tbody/tr/td/table/tbody/tr/td/fieldset[@class='fieldset']/form[@id='prospectForm']/table/tbody/tr/td/input[1]")
	WebElement SaveBtn;
	
	public DealsPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyContactsLable()
	{
		return dealsLable.isDisplayed();
	}
	
	public void createNewDeals(String title, String com, String amt)throws InterruptedException
	{
		Title.sendKeys(title);
		Company.sendKeys(com);
		Amount.sendKeys(amt);
		SaveBtn.click();
	}

	
}
