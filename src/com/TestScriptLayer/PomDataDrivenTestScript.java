package com.TestScriptLayer;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ApplicationLayer.BranchCreatePomLayer;
import com.ApplicationLayer.LoginPomLayer;
import com.ApplicationLayer.LogoutPomLayer;
import com.ApplicationLayer.UpdateBranchPomLayer;

public class PomDataDrivenTestScript {
	WebDriver driver;
	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","E:\\Selenium_Evengbatch\\POMDataDriven_Framework\\Drivers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://primusbank.qedgetech.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		LoginPomLayer lpl=PageFactory.initElements(driver,LoginPomLayer.class);
		String loginresult=lpl.verifyLogin("Admin","Admin");
		Reporter.log("Login case:="+loginresult);
	}
	@Test(description="Branch Creation Test",priority=0)
	public void branchCreation()
	{
		BranchCreatePomLayer bcpl=PageFactory.initElements(driver,BranchCreatePomLayer.class);
		String branchcreateresult=bcpl.verifyBranchCreation("Selenium", "Hyderabad", "Gachiboli", "darmaroad",
				"satyalane", "52348", "INDIA", "Delhi", "Delhi");
		Reporter.log("BranchCreation case:="+branchcreateresult);
	}
	@Test(description="Update Branch Test",priority=1)
	public void updateBranch()
	{
		UpdateBranchPomLayer ubpl=PageFactory.initElements(driver, UpdateBranchPomLayer.class);
		String updatebranchresult=ubpl.verifyUpdateBranch("Automation", "Telengana");
		Reporter.log("UpdateBranch Case:="+updatebranchresult);
	}
	@AfterMethod
	public void tearDown()
	{
		LogoutPomLayer logout=PageFactory.initElements(driver, LogoutPomLayer.class);
		logout.verifyLogout();		
		driver.close();
	}

}
