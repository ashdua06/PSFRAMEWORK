package org.peoplestrong.test.wfm;



import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.peoplestrong.altworklife.core.BaseClass;
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


public class MonthlyRoster_2_4_WeekoffTest {
  LoginPage login = new LoginPage();
  RosterPage roster = new RosterPage();
  Utils util= new Utils();
  AssignRosterPage asgn_roster = new AssignRosterPage();
  HomePage home = new HomePage();
  BaseClass base = new BaseClass();
  NewLeavePage newLeave = new NewLeavePage();
  String FilePath = "RosterData.xlsx";
  
  String rosterNameAlternateWeekend_2_4_off="WEEK_2_4_OFF";
  List<String> weekOffDates = new ArrayList<String>();
  int nextMonth = util.addMonth(1);
  int next2NextMonth = util.addMonth(2);
  Object[][] data = new Object[5][7];
  
  @Test(priority=1)
  public void createAndAssignRoster_Week_2_4_Off() throws InterruptedException, IOException {
	  data = util.getSheetData(FilePath,"AlternateSaturday_24_Off");
	  Calendar leaveCal = Calendar.getInstance();
	  // Open roaster page

	  System.out.println("MONTH NAME: "+ nextMonth + " next 2 next: "+next2NextMonth);
	 	  
	  home.clickMenuValue("Attendance");
	   home.clickConfigurationRosterTab();
	  roster.click_roaster_plus_icon()
	  		.set_roaster_name(rosterNameAlternateWeekend_2_4_off)
	  		.select_roaster_type("Monthly Cycle")
	  		.setRoasterWeekData(data)
	  		.click_save_button();
	  home.clickAssignRosterByEmpTab();
	  asgn_roster.checkRosterCheckbox(22574)
	  			.clickAssignRosterButton()
	  			.setRosterStartDate(16,nextMonth,"2018")
	  			.setRosterEndDate(15,next2NextMonth,"2018")
	  			.selectRosterValue(rosterNameAlternateWeekend_2_4_off)
	  			.clickAssignButton();	    
  }
  
  @Test(priority=2)
  public void VerifyShiftAndLeaves_Roster_Week_2_4_Off(){
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
  public void delete_Roster_Week_2_4_Off(){
	  login.logout()
	  .login_as(PropertyFileHandler.getInstance().getValue("hrManagerUserName"),
			  PropertyFileHandler.getInstance().getValue("hrManagerPassword"));
	  home.clickMenuValue("Attendance")
	  	.clickAssignRosterByEmpTab();
	  asgn_roster.checkRosterCheckbox(22574)
	  			.clickAssignRosterButton()
	  			.deleteAssignedRoster(rosterNameAlternateWeekend_2_4_off);
	  home.clickMenuValue("Attendance");
	  home.clickConfigurationRosterTab();
	  roster.checkRoster(rosterNameAlternateWeekend_2_4_off)
	  		.clickDeleteRosterIcon();
	  Assert.assertEquals(roster.getRosterDeleteMsg(), "Roster Successfully Deleted.", "Roster should be deleted successfully. ");
 }
  
  /*
  @BeforeClass
  public void beforeClass() throws ClassNotFoundException, SQLException {
	  login.open_site("ctt")
	  .login_as(PropertyFileHandler.getInstance().getValue("hrManagerUserName"),
			  PropertyFileHandler.getInstance().getValue("hrManagerPassword"));
	  DBConnection.getConnection();
	  DBConnection.executeQuery("update tmLeavetype set WeekOff_Exclude=0 where tenantid=10 and LeaveTypeCode in ('PL','CL');");
	  try {
		Thread.sleep(10000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

  @AfterClass
  public void afterClass() {
	  login.logout();
	  base.closeBrowser();
  }
  */

}
