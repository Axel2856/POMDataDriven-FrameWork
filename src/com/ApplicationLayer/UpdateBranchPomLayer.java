package com.ApplicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UpdateBranchPomLayer {
WebDriver driver;
//override Webdriver object
public UpdateBranchPomLayer(WebDriver driver3)
	{
		this.driver=driver3;
	}
//Repository creation
@FindBy(xpath="//tr//tr//tr//tr//tr[2]//td[1]//a[1]//img[1]")
WebElement uclickbranches;
@FindBy(xpath="//tr//tr[2]//td[7]//a[1]//img[1]")
WebElement ueditpic;
@FindBy(xpath="//input[@id='txtbnameU']")
WebElement ubranchname;
@FindBy(xpath="//input[@id='txtadd1u']")
WebElement uaddress1;
@FindBy(xpath="//input[@id='btnupdate']")
WebElement update;
//Method Creation
public String verifyUpdateBranch(String ubrname,String uadd1)
	{
		String res=null;
		this.uclickbranches.click();
		this.ueditpic.click();
		this.ubranchname.clear();
		this.ubranchname.sendKeys(ubrname);
		this.uaddress1.clear();
		this.uaddress1.sendKeys(uadd1);
		this.update.click();
		String msg1=driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		if(msg1.contains("updated Sucessfully"))
		{
			System.out.println("Branch Updated Successful");
			res="Pass";
		}
		else
		{
			System.out.println("Branch Updation Failed");
			res="Fail";
		}
		return res;
	}
}
