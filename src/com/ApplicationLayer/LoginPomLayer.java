package com.ApplicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPomLayer {
WebDriver driver;
//override Webdriver object
public LoginPomLayer(WebDriver driver1)
	{
		this.driver=driver1;
	}
//Repository creation
@FindBy(xpath="//input[@id='txtuId']")
WebElement username;
@FindBy(xpath="//input[@id='txtPword']")
WebElement password;
@FindBy(xpath="//input[@id='login']")
WebElement login;
//Method Creation
public String verifyLogin(String uname,String pass)
	{
		String res=null;
		this.username.sendKeys(uname);
		this.password.sendKeys(pass);
		login.click();
		if(driver.getCurrentUrl().contains("adminflow"))
		{
			System.out.println("Login Successful");
			res="Pass";
		}
		else
		{
			System.out.println("Login Failed");
			res="Fail";
		}
		return res;
	}
}
