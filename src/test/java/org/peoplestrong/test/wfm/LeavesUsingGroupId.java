package org.peoplestrong.test.wfm;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.peoplestrong.altworklife.core.BaseClass;
import org.peoplestrong.altworklife.core.PropertyFileHandler;
import org.peoplestrong.altworklife.page.hris.LoginPage;
import org.peoplestrong.altworklife.utils.DBConnection;
import org.peoplestrong.altworklife.utils.Utils;
import org.peoplestrong.page.wfm.HomePageWFM;
import org.peoplestrong.page.wfm.MyAttendance;
import org.peoplestrong.page.wfm.MyTaskEmployee;
import org.peoplestrong.page.wfm.MyTasksManager;
import org.peoplestrong.page.wfm.NewLeavePage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class LeavesUsingGroupId {
	
	BaseClass base = new BaseClass();
	NewLeavePage newleave = new NewLeavePage();
	Utils utils = new Utils();
	LoginPage login = new LoginPage();
	HomePageWFM homepageWFM = new HomePageWFM();

	
	int OrganizationId= 269;
	int TenantID= 167;
	int LeaveTypeID=1992; //Sick Leave
	int LeaveGroupConfigID =522;
	int AltGroupId= 23253;
	int min =2 , max=3;
	ResultSet rs = null;
	String LeaveType="";
	String actualErrorMessg="";
	String expectedErrorMessg="";
	
  @Test(enabled=false)
  public void updateDatabasewithMinMaxLeaves() {
	  
	  DBConnection.getConnection();
	DBConnection.executeQuery("update TmLeaveGroupConfig set MinLeave="+min+ ",MaxLeave= "+max+" "
			+ "where LeaveGroupConfigID ="+LeaveGroupConfigID+" and OrganizationID="+OrganizationId+" "
					+ "and TenantID="+TenantID+"and AltGroupID="+AltGroupId+";");  
  }
  
  @Test(enabled=true)
  public void applyLeaveLessThanMin() throws SQLException, InterruptedException {
	  DBConnection.getConnection();
	  rs = DBConnection.getQueryResult("select * from TmLeaveType where OrganizationID=269 "
	  		+ "and TenantID=167 and LeaveTypeID=1992");
	  rs.next();	
  		LeaveType = rs.getString("LeaveTypeCode").trim();	        	
 	 System.out.println("Apply "+LeaveType+" for employee less than minimum i.e. "+min);
	 
 	expectedErrorMessg = "You cannot avail less than "+min+".0 days of "+LeaveType+" consecutively.";
 	login.open_site("ctt-ixigo").login_as(PropertyFileHandler.getInstance().getValue("ixigoEmployeeUsername"),
			PropertyFileHandler.getInstance().getValue("ixigoEmployeePassword"));
 	homepageWFM.clickNewLeaveLabel();
 	newleave.selectLeaveType(LeaveType);
 	newleave.selectLeaveReason("Feeling Unwell");
 	newleave.selectStartDate(30, 9, "2018");
 	newleave.selectEndDate(30, 9, "2018");
 	newleave.LeaveAnyOtherReason("Not Well");
 	newleave.clickSubmitButton();
 	actualErrorMessg= newleave.LeaveLessThanMinErrorMesg();
 	Assert.assertEquals(expectedErrorMessg, actualErrorMessg);
 	System.out.println("Test Completed for less than Minimum");
     login.logout();
	  
  }
  
  @Test(enabled=true)
  public void applyLeaveMoreThanMax() throws SQLException, InterruptedException {
	  DBConnection.getConnection();
	  rs = DBConnection.getQueryResult("select * from TmLeaveType where OrganizationID=269 "
	  		+ "and TenantID=167 and LeaveTypeID=1992");
	  rs.next();	
  		LeaveType = rs.getString("LeaveTypeCode").trim();	        	
 	 System.out.println("Apply "+LeaveType+" for employee more than maximum i.e. "+max);
	 
 	expectedErrorMessg = "You cannot avail more than "+max+".0 days of "+LeaveType+" consecutively.";
 	login.open_site("ctt-ixigo").login_as(PropertyFileHandler.getInstance().getValue("ixigoEmployeeUsername"),
			PropertyFileHandler.getInstance().getValue("ixigoEmployeePassword"));
 	homepageWFM.clickNewLeaveLabel();
 	newleave.selectLeaveType(LeaveType);
 	newleave.selectLeaveReason("Feeling Unwell");
 	newleave.selectStartDate(30, 9, "2018");
 	newleave.selectEndDate(10, 10, "2018");
 	newleave.LeaveAnyOtherReason("Not Well");
 	newleave.clickSubmitButton();
 	actualErrorMessg= newleave.LeaveMoreThanMinErrorMesg();
 	Assert.assertEquals(expectedErrorMessg, actualErrorMessg);
 	System.out.println("Test Completed for less than Minimum");
     login.logout();
	  
  }
  
  
  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }
}
