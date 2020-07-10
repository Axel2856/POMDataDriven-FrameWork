package com.ApplicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class BranchCreatePomLayer {
WebDriver driver;
//override Webdriver object
public BranchCreatePomLayer(WebDriver driver2)
	{
		this.driver=driver2;
	}
//Repository creation
@FindBy(xpath="//tr//tr//tr//tr//tr[2]//td[1]//a[1]//img[1]")
WebElement clickbranches;
@FindBy(xpath="//input[@id='BtnNewBR']")
WebElement newbranch;
@FindBy(xpath="//input[@id='txtbName']")
WebElement branchname;
@FindBy(xpath="//input[@id='txtAdd1']")
WebElement address1;
@FindBy(xpath="//input[@id='Txtadd2']")
WebElement address2;
@FindBy(xpath="//input[@id='txtadd3']")
WebElement address3;
@FindBy(xpath="//input[@id='txtArea']")
WebElement area;
@FindBy(xpath="//input[@id='txtZip']")
WebElement zipcode;
@FindBy(xpath="//select[@id='lst_counrtyU']")
WebElement country;
@FindBy(xpath="//select[@id='lst_stateI']")
WebElement state;
@FindBy(xpath="//select[@id='lst_cityI']")
WebElement city;
@FindBy(xpath="//input[@id='btn_insert']")
WebElement submit;
//Method Creation
public String verifyBranchCreation(String bname,String add1,String add2,String add3,String areadata,String zcode,String countrydata,
		String statedata,String citydata)
	{
		String res=null;
		this.clickbranches.click();
		this.newbranch.click();
		this.branchname.sendKeys(bname);
		this.address1.sendKeys(add1);
		this.address2.sendKeys(add2);
		this.address3.sendKeys(add3);
		this.area.sendKeys(areadata);
		this.zipcode.sendKeys(zcode);
		new Select(	this.country).selectByVisibleText(countrydata);
		new Select(	this.state).selectByVisibleText(statedata);
		new Select(	this.city).selectByVisibleText(citydata);		
		this.submit.click();
		String msg=driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		if(msg.contains("created Sucessfully"))
		{
			System.out.println("BranchCreation Successful");
			res="Pass";
		}
		else
		{
			System.out.println("BranchCreation Failed");
			res="Fail";
		}
		return res;
	}
}
