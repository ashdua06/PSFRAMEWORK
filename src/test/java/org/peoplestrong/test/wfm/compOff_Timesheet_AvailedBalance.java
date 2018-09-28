package org.peoplestrong.test.wfm;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.peoplestrong.altworklife.core.BaseClass;
import org.peoplestrong.altworklife.core.PropertyFileHandler;
import org.peoplestrong.altworklife.page.hris.LoginPage;
import org.peoplestrong.altworklife.utils.Utils;
import org.peoplestrong.page.wfm.HomePageWFM;
import org.peoplestrong.page.wfm.MyAttendance;
import org.peoplestrong.page.wfm.MyTaskEmployee;
import org.peoplestrong.page.wfm.MyTasksManager;
import org.peoplestrong.page.wfm.NewLeavePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;


public class compOff_Timesheet_AvailedBalance {
	double compAvailedBeforeApplyingCompOff= 0;
	double compAvailedAfterApplyingCompOff=0;
	
	BaseClass base = new BaseClass();

	NewLeavePage newleave = new NewLeavePage();
	Utils utils = new Utils();
	MyAttendance regularize = new MyAttendance();
	LoginPage login = new LoginPage();
	MyTasksManager managertasks = new MyTasksManager();
	HomePageWFM homepageWFM = new HomePageWFM();
	MyTaskEmployee employeetasks = new MyTaskEmployee();
	
	@Test(enabled=false)
	//Timesheet regularization for compoff
	public void WeekOffTimesheetRegularize() throws Exception {
		  //Regularization request sent by employee for compoff
		 login.open_site("ctt-vivo").login_as(
					PropertyFileHandler.getInstance().getValue("vivoEmployeeUsername1"),
					PropertyFileHandler.getInstance().getValue("vivoEmployeePassword1"));
		  regularize.clickMyAttendance();
		  regularize.selectWeeklyOff();
		  System.out.println("Weekly off selected");
		  regularize.regularizeWOAttendance();
		  System.out.println("Attendance Regularize reques sent by employee");
		  login.logout();
		  
		  //Regularization request approved by manager for compoff
		  login.open_site("ctt-vivo").login_as(PropertyFileHandler.getInstance().getValue("vivoL1ManagerUsername"),
					PropertyFileHandler.getInstance().getValue("vivol1ManagerPassword"));
		  managertasks.managerPendingTasks();
		  managertasks.openPendingRequestEmployee("Ganesh", "Ubale");
		  managertasks.rejectRegularizeRequestOfEmployee();
		  login.logout();
	}
	
	
  @Test(enabled=true,priority=0)
  //Comp-Off applied by Employee
  public void applyCompoffLeaveEmployee() throws InterruptedException {
	  login.open_site("ctt-vivo").login_as(PropertyFileHandler.getInstance().getValue("vivoEmployeeUsername1"),
				PropertyFileHandler.getInstance().getValue("vivoEmployeePassword1"));
	  homepageWFM.clickNewLeaveLabel();
	  compAvailedBeforeApplyingCompOff = Double.parseDouble(newleave.compOffAvaialedTillDate());
	  newleave.selectLeaveType("CompOff");
	  newleave.selectLeaveReason("Comp-Off");
	  newleave.selectStartDate(28, 8, "2018");
	  newleave.selectEndDate(28, 8, "2018");
	  newleave.selectCompOffDate();
	  newleave.LeaveAnyOtherReason("Compoff applied");
	  newleave.clickSubmitButton();
	  login.logout();
  }
  
  //Depends on applyCompoffLeaveEmployee Test
  //Validate Availed till updated correctly after applying comp off
//Check if the availed comp-off leaves has been incremented by 1 after applying compoff
  @Test(enabled=true,priority=1)
  public void AvaileddateUpdateCompOffApply() {
	  login.open_site("ctt-vivo").login_as(PropertyFileHandler.getInstance().getValue("vivoEmployeeUsername1"),
				PropertyFileHandler.getInstance().getValue("vivoEmployeePassword1"));
	  homepageWFM.clickNewLeaveLabel();
	  compAvailedAfterApplyingCompOff = Double.parseDouble(newleave.compOffAvaialedTillDate());
	  Assert.assertEquals(compAvailedAfterApplyingCompOff, compAvailedBeforeApplyingCompOff+1);
	  System.out.println("CompOff AvailedTilldate on New Leave Page before applying Comp off is "+compAvailedBeforeApplyingCompOff);
	  System.out.println("CompOff AvailedTilldate on New Leave Page after applying Comp off is "+compAvailedAfterApplyingCompOff);
	  
	  login.logout();
  }
	  
	
  
	  @Test(enabled=true,priority=4)
	  //Validate Availed till updated correctly after rejecting comp off by Manager
	//Check if the availed comp-off leaves has been decremented by 1 after applying compoff
	  public void AvaileddateUpdateCompOffReject() {
		  login.open_site("ctt-vivo").login_as(PropertyFileHandler.getInstance().getValue("vivoEmployeeUsername1"),
					PropertyFileHandler.getInstance().getValue("vivoEmployeePassword1"));
		  homepageWFM.clickNewLeaveLabel();
		  compAvailedAfterApplyingCompOff = Double.parseDouble(newleave.compOffAvaialedTillDate());
		  Assert.assertEquals(compAvailedAfterApplyingCompOff, compAvailedBeforeApplyingCompOff);
		  System.out.println("CompOff AvailedTilldate on New Leave Page is updated to  "+compAvailedBeforeApplyingCompOff+" after rejecting by Manager");
		  login.logout();
  }
	  
  
  @Test(enabled=true,priority=2)
  //Comp-Off Leave rejected by Manager
  public void CompOffLeaveRejectedByManager() throws Exception {
	  login.open_site("ctt-vivo").login_as(PropertyFileHandler.getInstance().getValue("vivoL1ManagerUsername"),
				PropertyFileHandler.getInstance().getValue("vivol1ManagerPassword"));
	  managertasks.managerPendingTasks();
	  managertasks.openPendingRequestEmployee("Ganesh", "Ubale");
	  managertasks.rejectCompOffRequestOfEmployee();
	  login.logout(); 
  }
  
  @Test(enabled=true,priority=3)
  // Comp Off request withdraw by employee after rejection from Manager
  public void CompOffWithdrawByEmployee() {
	  login.open_site("ctt-vivo").login_as(PropertyFileHandler.getInstance().getValue("vivoEmployeeUsername1"),
				PropertyFileHandler.getInstance().getValue("vivoEmployeePassword1"));
	  homepageWFM.checkPendingTasksEmployee();
	  employeetasks.withdrawRejectedLeave();
	  login.logout();
  }
  
  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
  }

  @BeforeTest
  public void beforeTest() throws Exception {
	 
	  
	  
  }

  @AfterTest
  public void afterTest() {
  }

}
