package org.peoplestrong.test.wfm;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import bsh.ParseException;

public class WFMLeaveRoundUpTest {
	LoginPage login = new LoginPage();
	RosterPage roster = new RosterPage();
	Utils util = new Utils();
	AssignRosterPage asgn_roster = new AssignRosterPage();
	HomePage home = new HomePage();
	BaseClass base = new BaseClass();
	NewLeavePage newLeave = new NewLeavePage();
	String leaveBalanceValue = "SUB_PATTERN";
	String caseIn01And50 = ">=.01 and <.50";
	String caseIn50And99 = ">.50 and<=.99";
	boolean leaveDecimalLessThan50 = false;
	boolean leaveDecimalGreaterThan50 = false;
	String executeleaveUpdateQuery;
	float leaveBalance;
	int numOfRowsEffected = 0;
	int newLeaveBalance = 0;
	String empUserId = "1984892";
	ResultSet queryResultSet = null;
	String leaveBalanceNum = null;
	String roundOffWorkBookName = "Round_Trim_Data.xlsx";
	String roundOffUp50WorkSheetName = "round_up_50";
	String roundOffUp25WorkSheetName = "round_up_25";
	String empNameAbhijeet = "Abhijit Katkar";
	String pageMessage = null;
	String leaveRejectedSuccessMsg = "Request Rejected Successfully.";

	/**
	 * SQL Queries
	 **/
	/**
	 * Query to delete existing leaves taken
	 */
	String leaveDeleteQuery = "delete from TmEmployeeLeaveDetail where LeaveID in (select LeaveID from TmEmployeeLeave where TenantID='156' and EmployeeID='1611931');"
			+ "delete  from TmEmployeeLeaveHistory where EmployeeLeaveID in (select LeaveID from TmEmployeeLeave where TenantID='156' and EmployeeID='1611931'); "
			+ "delete  from TmEmployeeLeave where TenantID='156' and EmployeeID='1611931'; "
			+ "delete  from TmEmpAttRegApprovalHistory where tenantid=156 and UserID in('1984892','1984826'); "
			+ "delete  from SysUserWorkflowCommHistory where TenantID='156' and workflowhistoryid in (select workflowhistoryid from SysUserWorkflowHistory where tenantID='156' and ActorID in ('1984892','1984826')); "
			+ "delete  from TmEmployeeLeaveDetailHistory where LeaveHistoryID in (select LeaveHistoryID from TmEmployeeLeaveHistory where UserID in ('1984892','1984826')); "
			+ "delete  from TmEmployeeLeaveHistory where UserID in ('1984892','1984826'); "
			+ "delete   from HrProfileInstanceDetailHistory where ProfileInstanceHistoryID in (select ProfileInstanceHistoryID from HrProfileInstanceHistory where ProfileInstanceID in (select ProfileInstanceID from HrProfileInstance where EmployeeID='1611931' and TenantID=156)); "
			+ "delete   from HrProfileInstanceHistory where ProfileInstanceID in (select ProfileInstanceID from HrProfileInstance where EmployeeID='1611931' and TenantID=156); "
			+ "delete  from HrProfileInstanceDetail where ProfileInstanceID in (select ProfileInstanceID from HrProfileInstance where EmployeeID='1611931' and TenantID=156); "
			+ "delete   from HrProfileInstance where EmployeeID='1611931' and TenantID=156; "
			+ "delete from HrTaskAssignmentHistory where SysUserWorkflowHistoryId in (select SysUserWorkflowHistoryId from SysUserWorkflowHistory where tenantID='156' and  ActorID in ('1984892','1984826')  and TaskCode like 'L0%')"
			+ "delete  from SysUserWorkflowHistory where tenantID='156' and  ActorID in ('1984892','1984826')  and TaskCode like 'L0%';";

	// Query to insert row in TmLeaveBalanceRules for ROund OFF using round up
	// with .50 values
	String roundUp50InsertQuery = "insert Into   TmLeaveBalanceRules values ('ROUND_OFF','ROUND_UP',0.5,258,156,1,GETDATE(),1,GETDATE(),1,NULL)";
	// Query to insert row in TmLeaveBalanceRules for ROund OFF using round up
	// with .25 values
	String roundUp25InsertQuery = "insert Into   TmLeaveBalanceRules values ('ROUND_OFF','ROUND_UP',0.25,258,156,1,GETDATE(),1,GETDATE(),1,NULL)";

	// Query to insert row in TmLeaveBalanceRules for ROund OFF using round down
	// with .50 values
	String roundDown50InsertQuery = "insert Into   TmLeaveBalanceRules values ('ROUND_OFF','ROUND_DOWN',0.5,19,10,1,GETDATE(),1,GETDATE(),1,NULL)";
	// Query to insert row in TmLeaveBalanceRules for ROund OFF using round down
	// with .25 values
	String roundDown25InsertQuery = "insert Into   TmLeaveBalanceRules values ('ROUND_OFF','ROUND_DOWN',0.25,19,10,1,GETDATE(),1,GETDATE(),1,NULL)";

	// Delete the row from tmLeaveBalanceRules
	String deleteLeaveRuleQuery = "delete from TmLeaveBalanceRules where  OrganizationID=258 and TenantID=156 ";
	String updateLeaveQuotaQUery = "update TmEmployeeLeaveQuota set CurrentBalance="
			+ leaveBalanceValue
			+ " where Active=1 and "
			+ "LeaveTypeID=940 and EmployeeId in (select EmployeeId  from hremployee where "
			+ "userid in (select UserId from sysuser where TenantID=156 and UserID='"
			+ empUserId + "'))";

	// Get Leave Balance from
	String getCurrentBalanceQuery = "select CurrentBalance from TmEmployeeLeaveQuota where Active=1 "
			+ "and LeaveTypeID=940 and EmployeeId in (select EmployeeId  from hremployee "
			+ "where userid in (select UserId from sysuser where TenantID=156 and UserID='"
			+ empUserId + "'))";

	

	@Test(priority = 0)
	public void leaveRoundUp50Test() throws Exception {
		System.out.println(this.getClass().getName());
		DBConnection.executeQuery(deleteLeaveRuleQuery);
		DBConnection.executeQuery(roundUp50InsertQuery);
		SoftAssert leaveRoundUpValidate = new SoftAssert();
		int rowNum = util.getSheetRowNum(roundOffWorkBookName,
				roundOffUp50WorkSheetName);
		int colNum = util.getSheetColNum(roundOffWorkBookName,
				roundOffUp50WorkSheetName);
		String[][] data = new String[rowNum][colNum];
		// get data from sheet
		data = util.getSheetData("Round_Trim_Data.xlsx", "round_up_50");
		for (int i = 1; i < rowNum; i++) {
			DBConnection.executeQuery(leaveDeleteQuery);
			leaveDecimalLessThan50 = false;
			leaveDecimalGreaterThan50 = false;
			System.out.println("row: " + i + " data: " + data[i][0]);
			String res = data[i][0];
			executeleaveUpdateQuery = updateLeaveQuotaQUery.replace(
					leaveBalanceValue, String.valueOf(res));
			System.out.println("Query is: " + executeleaveUpdateQuery);

			DBConnection.executeQuery(executeleaveUpdateQuery);
			home.clickMenuValue("Leave").clickSubTab("Leave", "New Leave");
			newLeave.selectLeaveType("Earned Leave")
					.selectLeaveReason("Others").selectStartDate(17, 9, "2018");
			// Assert that leave is applied successfully.
			Assert.assertFalse(
					(newLeave.getLeaveDayValue()).contains("Weekly Off"),
					"Expected that user  able to apply leave on day shift as per roster");
			newLeave.clickSubmitButton();
			queryResultSet = DBConnection
					.getQueryResult(getCurrentBalanceQuery);
			while (queryResultSet.next()) {
				leaveBalanceNum = queryResultSet.getString("CurrentBalance");
				System.out.println(queryResultSet.getString("CurrentBalance")
						+ "expected:" + Float.valueOf(data[i][1]));
				// Assert.assertEquals(actual, expected);
				leaveRoundUpValidate.assertEquals(leaveBalanceNum, data[i][1],
						"Expected:" + data[i][1] + "and got:" + leaveBalanceNum
								+ ".");
			}
			Assert.assertTrue((newLeave.getLeaveApplySucecssMsg())
					.contains("Request Submitted Successfully."),
					"Expected that user  able to apply leave on shift day as per shift roster");
			if (i == rowNum - 1) {
				leaveRoundUpValidate.assertAll();
			}

			/*
			 * login.logout() .login_as(
			 * PropertyFileHandler.getInstance().getValue(
			 * "vivoL1ManagerUsername"),
			 * PropertyFileHandler.getInstance().getValue(
			 * "vivol1ManagerPassword"));
			 * 
			 * 
			 * System.out.println("first completed"); Thread.sleep(10000);
			 */
		}
	
	}

	@Test(priority = 1)
	public void leaveRoundUp50_RejectTest() throws Exception {
		System.out.println(this.getClass().getName());
		DBConnection.executeQuery(deleteLeaveRuleQuery);
		DBConnection.executeQuery(roundUp50InsertQuery);
		SoftAssert leaveRoundUpReject = new SoftAssert();
		int rowNum = util.getSheetRowNum(roundOffWorkBookName,
				roundOffUp50WorkSheetName);
		int colNum = util.getSheetColNum(roundOffWorkBookName,
				roundOffUp50WorkSheetName);
		String[][] data = new String[rowNum][colNum];
		// get data from sheet
		data = util.getSheetData("Round_Trim_Data.xlsx", "round_up_50");
		for (int i = 1; i < rowNum; i++) {
			DBConnection.executeQuery(leaveDeleteQuery);
			leaveDecimalLessThan50 = false;
			leaveDecimalGreaterThan50 = false;
			System.out.println("row: " + i + " data: " + data[i][0]);
			String res = data[i][0];
			executeleaveUpdateQuery = updateLeaveQuotaQUery.replace(
					leaveBalanceValue, String.valueOf(res));
			System.out.println("Query is: " + executeleaveUpdateQuery);

			DBConnection.executeQuery(executeleaveUpdateQuery);
			home.clickMenuValue("Leave").clickSubTab("Leave", "New Leave");
			newLeave.selectLeaveType("Earned Leave")
					.selectLeaveReason("Others").selectStartDate(17, 9, "2018");
			// Assert that leave is applied successfully.
			Assert.assertFalse(
					(newLeave.getLeaveDayValue()).contains("Weekly Off"),
					"Expected that user  able to apply leave on day shift as per roster");
			newLeave.clickSubmitButton();

			Assert.assertTrue((newLeave.getLeaveApplySucecssMsg())
					.contains("Request Submitted Successfully."),
					"Expected that user  able to apply leave on shift day as per shift roster");

			login.logout().login_as(
					PropertyFileHandler.getInstance().getValue(
							"vivoL1ManagerUsername"),
					PropertyFileHandler.getInstance().getValue(
							"vivol1ManagerPassword"));

			home.clickLeaveApprovalViewAllButton();
			pageMessage = newLeave.checkLeaveApproveCheckbox(empNameAbhijeet)
					.setLeaveApproveComments("REJECT" + i).clickRejectButton()
					.clickPopUpYesButton().getLeaveRejectMessage();

			leaveRoundUpReject
					.assertEquals(pageMessage, leaveRejectedSuccessMsg,
							"Expected reject Message to be: "
									+ leaveRejectedSuccessMsg + " .got: "
									+ pageMessage);

			queryResultSet = DBConnection
					.getQueryResult(getCurrentBalanceQuery);
			while (queryResultSet.next()) {
				leaveBalanceNum = queryResultSet.getString("CurrentBalance");
				System.out.println(queryResultSet.getString("CurrentBalance")
						+ " expected:  " + Float.valueOf(data[i][2]));
				// Assert.assertEquals(actual, expected);
				leaveRoundUpReject.assertEquals(leaveBalanceNum, data[i][2],
						"Expected: " + data[i][2] + " and got:  "
								+ leaveBalanceNum);
			}

			login.logout().login_as(
					PropertyFileHandler.getInstance().getValue(
							"vivoEmployeeUsername"),
					PropertyFileHandler.getInstance().getValue(
							"vivoEmpPassword"));

		}
		leaveRoundUpReject.assertAll();
	
	}

	@Test(priority = 2)
	public void leaveRoundUp25Test() throws Exception {
		System.out.println(this.getClass().getName());
		SoftAssert leaveRoundUpValidate = new SoftAssert();
		SoftAssert leaveRoundUpReject = new SoftAssert();
		DBConnection.executeQuery(deleteLeaveRuleQuery);
		DBConnection.executeQuery(roundUp25InsertQuery);
		int rowNum = util.getSheetRowNum(roundOffWorkBookName,
				roundOffUp25WorkSheetName);
		int colNum = util.getSheetColNum(roundOffWorkBookName,
				roundOffUp25WorkSheetName);
		String[][] data = new String[rowNum][colNum];
		// get data from sheet
		data = util.getSheetData("Round_Trim_Data.xlsx", "round_up_25");
		for (int i = 1; i < rowNum; i++) {
			DBConnection.executeQuery(leaveDeleteQuery);
			leaveDecimalLessThan50 = false;
			leaveDecimalGreaterThan50 = false;
			System.out.println("row: " + i + " data: " + data[i][0]);
			String res = data[i][0];
			executeleaveUpdateQuery = updateLeaveQuotaQUery.replace(
					leaveBalanceValue, String.valueOf(res));
			System.out.println("Query is: " + executeleaveUpdateQuery);

			DBConnection.executeQuery(executeleaveUpdateQuery);
			home.clickMenuValue("Leave").clickSubTab("Leave", "New Leave");
			newLeave.selectLeaveType("Earned Leave")
					.selectLeaveReason("Others").selectStartDate(18, 9, "2018");
			// Assert that leave is applied successfully.
			Assert.assertFalse(
					(newLeave.getLeaveDayValue()).contains("Weekly Off"),
					"Expected that user  able to apply leave on day shift as per roster");
			newLeave.clickSubmitButton();
			queryResultSet = DBConnection
					.getQueryResult(getCurrentBalanceQuery);
			while (queryResultSet.next()) {
				leaveBalanceNum = queryResultSet.getString("CurrentBalance");
				System.out.println(queryResultSet.getString("CurrentBalance")
						+ "expected:" + Float.valueOf(data[i][1]));
				// Assert.assertEquals(actual, expected);
				leaveRoundUpValidate.assertEquals(leaveBalanceNum, data[i][1],
						"Expected:" + data[i][1] + "and got:" + leaveBalanceNum
								+ ".");
			}
			Assert.assertTrue((newLeave.getLeaveApplySucecssMsg())
					.contains("Request Submitted Successfully."),
					"Expected that user  able to apply leave on shift day as per shift roster");

			login.logout().login_as(
					PropertyFileHandler.getInstance().getValue(
							"vivoL1ManagerUsername"),
					PropertyFileHandler.getInstance().getValue(
							"vivol1ManagerPassword"));

			home.clickLeaveApprovalViewAllButton();
			pageMessage = newLeave.checkLeaveApproveCheckbox(empNameAbhijeet)
					.setLeaveApproveComments("REJECT" + i).clickRejectButton()
					.clickPopUpYesButton().getLeaveRejectMessage();

			leaveRoundUpReject
					.assertEquals(pageMessage, leaveRejectedSuccessMsg,
							"Expected reject Message to be: "
									+ leaveRejectedSuccessMsg + " .got: "
									+ pageMessage);

			queryResultSet = DBConnection
					.getQueryResult(getCurrentBalanceQuery);
			while (queryResultSet.next()) {
				leaveBalanceNum = queryResultSet.getString("CurrentBalance");
				System.out.println(queryResultSet.getString("CurrentBalance")
						+ " expected:  " + Float.valueOf(data[i][2]));
				leaveRoundUpReject.assertEquals(leaveBalanceNum, data[i][2],
						"Expected: " + data[i][2] + " and got:  "
								+ leaveBalanceNum);
			}

			login.logout().login_as(
					PropertyFileHandler.getInstance().getValue(
							"vivoEmployeeUsername"),
					PropertyFileHandler.getInstance().getValue(
							"vivoEmpPassword"));

			if (i == rowNum - 1) {
				leaveRoundUpValidate.assertAll();
				leaveRoundUpReject.assertAll();
			}

			/*
			 * login.logout() .login_as(
			 * PropertyFileHandler.getInstance().getValue(
			 * "vivoL1ManagerUsername"),
			 * PropertyFileHandler.getInstance().getValue(
			 * "vivol1ManagerPassword"));
			 * 
			 * 
			 * System.out.println("first completed"); Thread.sleep(10000);
			 */
		}
	
	}
}
