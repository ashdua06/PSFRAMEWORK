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

public class WFMLeaveTrimOneDayLeaveTest {
	LoginPage login = new LoginPage();
	RosterPage roster = new RosterPage();
	Utils util = new Utils();
	AssignRosterPage asgn_roster = new AssignRosterPage();
	HomePage home = new HomePage();
	BaseClass base = new BaseClass();
	NewLeavePage newLeave = new NewLeavePage();
	String empUserId = "1984892";
	String leaveBalanceValue = "SUB_PATTERN";
	String trimWorkBookName = "Round_Trim_Data.xlsx";
	String trim1DayLeaveWorkSheetName = "Trim_Sheet_1Day_Leave";
	String empNameAbhijeet = "Abhijit Katkar";
	String executeleaveUpdateQuery;
	ResultSet queryResultSet = null;
	String leaveBalanceNum = null;
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

	// Query to insert row in TmLeaveBalanceRules for TRIM with 0 decimal
	// values.
	String trim0Decimal = "insert Into   TmLeaveBalanceRules values ('TRIM','0',NULL,258,156,1,GETDATE(),1,GETDATE(),1,NULL)";

	// Query to insert row in TmLeaveBalanceRules for TRIM with 0 decimal
	// values.
	String trim1Decimal = "insert Into   TmLeaveBalanceRules values ('TRIM','1',NULL,258,156,1,GETDATE(),1,GETDATE(),1,NULL)";

	// Query to insert row in TmLeaveBalanceRules for TRIM with 0 decimal
	// values.
	String trim2Decimal = "insert Into   TmLeaveBalanceRules values ('TRIM','2',NULL,258,156,1,GETDATE(),1,GETDATE(),1,NULL)";

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
	public void leaveTrimOneDayLeaveWith0Decimal() throws SQLException,
			InterruptedException, IOException, ClassNotFoundException {
		System.out.println(this.getClass().getName());
		DBConnection.executeQuery(deleteLeaveRuleQuery);
		DBConnection.executeQuery(trim0Decimal);
		SoftAssert leaveTrim0DecimalValidate = new SoftAssert();
		int rowNum = util.getSheetRowNum(trimWorkBookName,
				trim1DayLeaveWorkSheetName);
		int colNum = util.getSheetColNum(trimWorkBookName,
				trim1DayLeaveWorkSheetName);
		String[][] data = new String[rowNum][colNum];
		// get data from sheet
		data = util.getSheetData("Round_Trim_Data.xlsx",
				trim1DayLeaveWorkSheetName);
		for (int i = 1; i < rowNum; i++) {
			DBConnection.executeQuery(leaveDeleteQuery);
			System.out.println("row: " + i + " data: " + data[i][0]);
			String res = data[i][0];
			executeleaveUpdateQuery = updateLeaveQuotaQUery.replace(
					leaveBalanceValue, String.valueOf(res));
			System.out.println("Query is: " + executeleaveUpdateQuery);
			DBConnection.executeQuery(executeleaveUpdateQuery);
			home.clickMenuValue("Leave")
				.clickSubTab("Leave", "New Leave");
			newLeave.selectLeaveType("Earned Leave")
					.selectLeaveReason("Others")
					.selectStartDate(13, 11, "2018");
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
						+ "expected:" + Float.valueOf(data[i][2]));
				// Assert.assertEquals(actual, expected);
				leaveTrim0DecimalValidate.assertEquals(leaveBalanceNum,
						data[i][2], "Expected:" + data[i][2] + "and got:"
								+ leaveBalanceNum + ".");
			}
			Assert.assertTrue((newLeave.getLeaveApplySucecssMsg())
					.contains("Request Submitted Successfully."),
					"Expected that user  able to apply leave on shift day as per shift roster");
			if (i == rowNum - 1) {
				leaveTrim0DecimalValidate.assertAll();
			}
		}

	
	}
	
	@Test(priority = 1)
	public void leaveTrimOneDayLeaveWith1Decimal() throws SQLException,
			InterruptedException, IOException, ClassNotFoundException {
		System.out.println(this.getClass().getName());
		DBConnection.executeQuery(deleteLeaveRuleQuery);
		DBConnection.executeQuery(trim1Decimal);
		SoftAssert leaveTrim1DecimalValidate = new SoftAssert();
		int rowNum = util.getSheetRowNum(trimWorkBookName,
				trim1DayLeaveWorkSheetName);
		int colNum = util.getSheetColNum(trimWorkBookName,
				trim1DayLeaveWorkSheetName);
		String[][] data = new String[rowNum][colNum];
		// get data from sheet
		data = util.getSheetData("Round_Trim_Data.xlsx",
				trim1DayLeaveWorkSheetName);
		for (int i = 1; i < rowNum; i++) {
			DBConnection.executeQuery(leaveDeleteQuery);
			System.out.println("row: " + i + " data: " + data[i][0]);
			String res = data[i][0];
			executeleaveUpdateQuery = updateLeaveQuotaQUery.replace(
					leaveBalanceValue, String.valueOf(res));
			System.out.println("Query is: " + executeleaveUpdateQuery);
			DBConnection.executeQuery(executeleaveUpdateQuery);
			home.clickMenuValue("Leave")
				.clickSubTab("Leave", "New Leave");
			newLeave.selectLeaveType("Earned Leave")
					.selectLeaveReason("Others")
					.selectStartDate(13, 11, "2018");
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
						+ "expected:" + Float.valueOf(data[i][3]));
				// Assert.assertEquals(actual, expected);
				leaveTrim1DecimalValidate.assertEquals(leaveBalanceNum,
						data[i][3], "Expected:" + data[i][3] + "and got:"
								+ leaveBalanceNum + ".");
			}
			Assert.assertTrue((newLeave.getLeaveApplySucecssMsg())
					.contains("Request Submitted Successfully."),
					"Expected that user  able to apply leave on shift day as per shift roster");
			if (i == rowNum - 1) {
				leaveTrim1DecimalValidate.assertAll();
			}
		}

	
	}
	
	@Test(priority = 2)
	public void leaveTrimOneDayLeaveWith2Decimal() throws SQLException,
			InterruptedException, IOException, ClassNotFoundException {
		System.out.println(this.getClass().getName());
		DBConnection.executeQuery(deleteLeaveRuleQuery);
		DBConnection.executeQuery(trim2Decimal);
		SoftAssert leaveTrim1DecimalValidate = new SoftAssert();
		int rowNum = util.getSheetRowNum(trimWorkBookName,
				trim1DayLeaveWorkSheetName);
		int colNum = util.getSheetColNum(trimWorkBookName,
				trim1DayLeaveWorkSheetName);
		String[][] data = new String[rowNum][colNum];
		// get data from sheet
		data = util.getSheetData("Round_Trim_Data.xlsx",
				trim1DayLeaveWorkSheetName);
		for (int i = 1; i < rowNum; i++) {
			DBConnection.executeQuery(leaveDeleteQuery);
			System.out.println("row: " + i + " data: " + data[i][0]);
			String res = data[i][0];
			executeleaveUpdateQuery = updateLeaveQuotaQUery.replace(
					leaveBalanceValue, String.valueOf(res));
			System.out.println("Query is: " + executeleaveUpdateQuery);
			DBConnection.executeQuery(executeleaveUpdateQuery);
			home.clickMenuValue("Leave")
				.clickSubTab("Leave", "New Leave");
			newLeave.selectLeaveType("Earned Leave")
					.selectLeaveReason("Others")
					.selectStartDate(13, 11, "2018");
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
						+ "expected:" + Float.valueOf(data[i][4]));
				// Assert.assertEquals(actual, expected);
				leaveTrim1DecimalValidate.assertEquals(leaveBalanceNum,
						data[i][4], "Expected:" + data[i][4] + "and got:"
								+ leaveBalanceNum + ".");
			}
			Assert.assertTrue((newLeave.getLeaveApplySucecssMsg())
					.contains("Request Submitted Successfully."),
					"Expected that user  able to apply leave on shift day as per shift roster");
			if (i == rowNum - 1) {
				leaveTrim1DecimalValidate.assertAll();
			}
		}

	
	}
}
