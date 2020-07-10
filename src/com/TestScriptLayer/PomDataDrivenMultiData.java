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
import com.XmlUtilityReader.XmlDataReader;

public class PomDataDrivenMultiData {
	String inputpath="E:\\Selenium_Evengbatch\\POMDataDriven_Framework\\src\\com\\XmlFiles\\PrimusExcel.xlsx";
	String outputpath="E:\\Selenium_Evengbatch\\POMDataDriven_Framework\\OutputFiles\\MultiDataReport.xlsx";
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
	public void branchCreation()throws Throwable
	{
		String branchcreateresult=null;
		BranchCreatePomLayer bcpl=PageFactory.initElements(driver,BranchCreatePomLayer.class);
		XmlDataReader reader=new XmlDataReader(inputpath);
		int rc1=reader.rowCount("AddBranch");
		for(int i=1;i<=rc1;i++)
		{
			String branchname=reader.getCellData("AddBranch", i, 0);
			String address1=reader.getCellData("AddBranch", i, 1);
			String address2=reader.getCellData("AddBranch", i, 2);
			String address3=reader.getCellData("AddBranch", i, 3);
			String area=reader.getCellData("AddBranch", i, 4);
			String zipcode=reader.getCellData("AddBranch", i, 5);
			String country=reader.getCellData("AddBranch", i, 6);
			String state=reader.getCellData("AddBranch", i, 7);
			String city=reader.getCellData("AddBranch", i, 8);
			branchcreateresult=bcpl.verifyBranchCreation(branchname, address1, address2, address3,
					area, zipcode, country, state, city);
			reader.setCellData("AddBranch", i,9, branchcreateresult, outputpath);			
		}	
		//used this here to check the status column of branch creation print pass or not
		/*String updatebranchresult=null;
		UpdateBranchPomLayer ubpl=PageFactory.initElements(driver, UpdateBranchPomLayer.class);
		//XmlDataReader reader=new XmlDataReader(inputpath);
		int rc2=reader.rowCount("UpdateBranch");
		for(int j=1;j<=rc2;j++)
		{
		String ubname=reader.getCellData("UpdateBranch", j, 0);
		String uadd1=reader.getCellData("UpdateBranch", j, 1);
		updatebranchresult=ubpl.verifyUpdateBranch(ubname, uadd1);
		reader.setCellData("UpdateBranch", j, 2, updatebranchresult, outputpath);
		}		*/
	}
	@Test(description="Update Branch Test",priority=1)
	public void updateBranch()throws Throwable
	{
		String updatebranchresult=null;
		UpdateBranchPomLayer ubpl=PageFactory.initElements(driver, UpdateBranchPomLayer.class);
		XmlDataReader reader=new XmlDataReader(inputpath);
		int rc2=reader.rowCount("UpdateBranch");
		for(int j=1;j<=rc2;j++)
		{
		String ubname=reader.getCellData("UpdateBranch", j, 0);
		String uadd1=reader.getCellData("UpdateBranch", j, 1);
		updatebranchresult=ubpl.verifyUpdateBranch(ubname, uadd1);
		reader.setCellData("UpdateBranch", j, 2, updatebranchresult, outputpath);
		}		
	}
	@AfterMethod
	public void tearDown()
	{
		LogoutPomLayer logout=PageFactory.initElements(driver, LogoutPomLayer.class);
		logout.verifyLogout();		
		driver.close();
	}
}
