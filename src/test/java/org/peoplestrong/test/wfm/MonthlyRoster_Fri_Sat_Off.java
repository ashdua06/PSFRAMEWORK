package org.peoplestrong.test.wfm;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.peoplestrong.altworklife.core.BaseClass;
import org.peoplestrong.altworklife.core.Log;
import org.peoplestrong.altworklife.core.PropertyFileHandler;
import org.peoplestrong.altworklife.page.hris.HomePage;
import org.peoplestrong.altworklife.page.hris.LoginPage;
import org.peoplestrong.altworklife.utils.DBConnection;
import org.peoplestrong.altworklife.utils.Utils;
import org.peoplestrong.page.wfm.AssignRosterPage;
import org.peoplestrong.page.wfm.NewLeavePage;
import org.peoplestrong.page.wfm.RosterPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.asserts.Assertion;

/**
 * Below Test Case will check for the 
 * 
 * 
 **/
public class MonthlyRoster_Fri_Sat_Off {
  LoginPage login = new LoginPage();
  RosterPage roster = new RosterPage();
  Utils util= new Utils();
  AssignRosterPage asgn_roster = new AssignRosterPage();
  HomePage home = new HomePage();
  BaseClass base = new BaseClass();
  NewLeavePage newLeave = new NewLeavePage();
  String FileName = "RosterData.xlsx";
  
  // 
  String rosterName_Fri_Sat_off="FRI_SAT_OFF";
  Object[][] data = new Object[5][7];
  List<String> weekOffDates = new ArrayList<String>();
  List<String> workingDayDates = new ArrayList<String>();
  
  int nextMonth = util.addMonth(1);
  int next2NextMonth = util.addMonth(2);
   
  @Test(priority=1,enabled=false)
  public void createAndAssignRoster_Fri_Sat_Off() throws InterruptedException, IOException, SQLException {
	  DBConnection.executeQuery("update tmLeavetype set WeekOff_Exclude=0 where tenantid=10 and LeaveTypeCode in ('PL','CL');");
	  
	  List<String> weekOffDates = new ArrayList<String>();
	  Calendar leaveCal = Calendar.getInstance();
	  // Open roaster page
	  data = util.getSheetData(FileName,"Fri_Sat_Off_Roster");
	  System.out.println("MONTH NAME: "+ nextMonth + " next 2 next: "+next2NextMonth);
	  home.clickMenuValue("Attendance");
	   home.clickConfigurationRosterTab();
	  roster.click_roaster_plus_icon()
	  		.set_roaster_name(rosterName_Fri_Sat_off)
	  		.select_roaster_type("Monthly Cycle")
	  		.setRoasterWeekData(data)
	  		.click_save_button();
	  home.clickAssignRosterByEmpTab();
	  asgn_roster.checkRosterCheckbox(22574)
	  			.clickAssignRosterButton()
	  			.setRosterStartDate(16,nextMonth,"2018")
	  			.setRosterEndDate(15,next2NextMonth,"2018")
	  			.selectRosterValue(rosterName_Fri_Sat_off)
	  			.clickAssignButton();	  
  }
  
  @Test(priority=2,enabled=false)
  public void VerifyShiftAndLeaves_Roster_Week_Fri_Sat_Off(){
	  // logout and login as employee
	  login.logout()
	  		.login_as(PropertyFileHandler.getInstance().getValue("hrRosterAssignUserName"),
	  			  PropertyFileHandler.getInstance().getValue("hrRosterAssignPassword"));
	  home.clickMenuValue("Attendance")
	  		.clickSubTab("Attendance","My Roster");
	  roster.setRoaserShiftStartDate(16,nextMonth,"2018")
	  		.setRoaserShiftEndDate(15,next2NextMonth,"2018")
	  		.clickRefreshButton();
	  //Assert shift is calculated successfully.
	  Assert.assertEquals(true, roster.assertRosterData(16,data));
	  weekOffDates = roster.getWeekOffRosterDates();
	  workingDayDates	= roster.getWorkingDayRosterDates();
	  String weekOffDateValue;
	  
	  home.clickMenuValue("Leave")
	  		.clickSubTab("Leave", "New Leave");
	// Getting Iterator
	Iterator<String> weekOffIterator = weekOffDates.iterator();
	while(weekOffIterator.hasNext()){
		weekOffDateValue = weekOffIterator.next();
		System.out.println("Date for week off: "+weekOffDateValue);
		String[] dateValue = weekOffDateValue.split(" ");
		newLeave.selectLeaveType("CL")
		 		.selectLeaveReason("Others")
		 		.selectStartDate(Integer.valueOf(dateValue[0]) , util.getMonthNum(dateValue[1]), dateValue[2]);
		System.out.println("for "+dateValue[0] + util.getMonthNum(dateValue[1])+ dateValue[2]+ newLeave.getLeaveDayValue());		
	    Assert.assertTrue((newLeave.getLeaveDayValue()).contains("Weekly Off")  , "Expected that user not able to apply leave on weekly off day as per roster");
	} 
  }
  
  @Test(priority=3)
  public void LeaveApplyAndApprove(){
	  login.logout()
		.login_as(PropertyFileHandler.getInstance().getValue("hrRosterAssignUserName"),
			  PropertyFileHandler.getInstance().getValue("hrRosterAssignPassword"));

	  String leaveApplyDate1,leaveApplyDate2,leaveApplyDate3,leaveApplyDate4;
	  home.clickMenuValue("Leave")
		.clickSubTab("Leave", "New Leave");
	  String dayShiftDateValue;
// Getting Iterator
	Iterator<String> weekOffIterator = weekOffDates.iterator();
	// Apply 4 leaves and check workflow
	int numOfLeavesApplied=0;
	while(weekOffIterator.hasNext()){
		dayShiftDateValue = weekOffIterator.next();
		System.out.println("Date for week off: "+dayShiftDateValue);
		String[] dateValue = dayShiftDateValue.split(" ");
		newLeave.selectLeaveType("CL")
		 		.selectLeaveReason("Others")
		 		.selectStartDate(Integer.valueOf(dateValue[0]) , util.getMonthNum(dateValue[1]), dateValue[2]);
		System.out.println("for "+dateValue[0] + util.getMonthNum(dateValue[1])+ dateValue[2]+ newLeave.getLeaveDayValue());		
	  Assert.assertFalse((newLeave.getLeaveDayValue()).contains("Weekly Off")  , "Expected that user  able to apply leave on day shift as per roster");
	  newLeave.clickSubmitButton();
	    Assert.assertTrue((newLeave.getLeaveAppliedMsg()).contains("Request Submitted Successfully.") , "Expected that user  able to apply leave on shift day as per shift roster");   
	    numOfLeavesApplied++;
	    if(numOfLeavesApplied==4)
	    		break;
	} 
}

  
  @Test(priority=4)
  public void delete_Roster_Week_Fri_Sat_Off(){
	  login.logout()
	  	.login_as(PropertyFileHandler.getInstance().getValue("hrManagerUserName"),
			  PropertyFileHandler.getInstance().getValue("hrManagerPassword"));
	  home.clickMenuValue("Attendance")
	  	.clickAssignRosterByEmpTab();
	  asgn_roster.checkRosterCheckbox(22574)
	  			.clickAssignRosterButton()
	  			.deleteAssignedRoster(rosterName_Fri_Sat_off);
	  home.clickMenuValue("Attendance");
	  home.clickConfigurationRosterTab();
	  roster.checkRoster(rosterName_Fri_Sat_off)
	  		.clickDeleteRosterIcon();
	  Assert.assertEquals(roster.getRosterDeleteMsg(), "Roster Successfully Deleted.", "Roster should be deleted successfully. ");
 }
  
  /*
  @BeforeClass
  public void beforeClass() throws IOException, InterruptedException, SQLException, ClassNotFoundException {  
	  data = util.getSheetData(FileName,"Fri_Sat_Off_Roster");
	  login.open_site("ctt");
	  login.login_as(PropertyFileHandler.getInstance().getValue("hrManagerUserName"),
			  PropertyFileHandler.getInstance().getValue("hrManagerPassword"));
	  DBConnection.getConnection();
	  DBConnection.executeQuery("update tmLeavetype set WeekOff_Exclude=0 where tenantid=10 and LeaveTypeCode in ('PL','CL');");
	  Log.debug("Updated query");
	  home.clickMenuValue("Attendance");
	   home.clickConfigurationRosterTab();
	  roster.click_roaster_plus_icon()
	  		.set_roaster_name(rosterName_Fri_Sat_off)
	  		.select_roaster_type("Monthly Cycle")
	  		.setRoasterWeekData(data)
	  		.click_save_button();
  }

  @AfterClass
  public void afterClass() {
	  login.logout();
	  base.closeBrowser();
  }*/

}
