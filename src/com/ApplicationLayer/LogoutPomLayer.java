package com.ApplicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPomLayer {
WebDriver driver;
//override Webdriver object
public LogoutPomLayer(WebDriver driver4)
	{
		this.driver=driver4;
	}
//Repository creation
@FindBy(xpath="//td[3]//a[1]//img[1]")
WebElement logout;
//Method Creation 
public void verifyLogout()
	{		
		this.logout.click();
		System.out.println("Logout Successful");			
	}
}
