package org.peoplestrong.test.wfm;

import java.sql.SQLException;

import org.peoplestrong.altworklife.core.PropertyFileHandler;
import org.peoplestrong.altworklife.page.hris.LoginPage;
import org.peoplestrong.altworklife.utils.DBConnection;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestConfigRoster {
 
  @BeforeClass
  public void beforeClass() {
	  System.out.println("in before class");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("in after class");
  }

  @BeforeTest
  public void beforeTest() throws ClassNotFoundException, SQLException, InterruptedException {
	  System.out.println("before test");
	 new LoginPage().open_site("ctt")
	  .login_as(PropertyFileHandler.getInstance().getValue("hrManagerUserName"),
			  PropertyFileHandler.getInstance().getValue("hrManagerPassword"));
	 // Create DB connection and execute query
	 DBConnection.getConnection();
	  DBConnection.executeQuery("update tmLeavetype set WeekOff_Exclude=0 where tenantid=10 and LeaveTypeCode in ('PL','CL');");
	 
	}
  
  @BeforeMethod
  public void beforeMethod(){
	  System.out.println("before method");
  }
  
  @AfterMethod
  public void AfterMethod(){
	  System.out.println("after method");
  }
  @AfterTest
  public void afterTest() {
	  System.out.println("after test");
	  new LoginPage().logout();
  }

  @BeforeSuite
  public void beforeSuite() throws ClassNotFoundException, SQLException {
	  System.out.println("before suite");
	  DBConnection.getConnection();
	  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("in after suite");
	  new LoginPage().closeBrowser();
  }

}
