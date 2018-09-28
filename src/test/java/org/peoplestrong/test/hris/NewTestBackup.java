	package org.peoplestrong.test.hris;

import java.sql.SQLException;

import org.peoplestrong.altworklife.core.Log;
import org.peoplestrong.altworklife.core.PropertyFileHandler;
import org.peoplestrong.altworklife.page.hris.DependentIDNominationBankTabDetailPage;
import org.peoplestrong.altworklife.page.hris.HomePage;
import org.peoplestrong.altworklife.page.hris.LoginPage;
import org.peoplestrong.altworklife.page.hris.PreJoiningEducationFormPage;
import org.peoplestrong.altworklife.page.hris.PreJoiningEmploymentFormPage;
import org.peoplestrong.altworklife.page.hris.PreJoiningFormPage;
import org.peoplestrong.altworklife.page.hris.ProspectiveHireEducationDetailPage;
//import org.peoplestrong.altworklife.page.hris.ProspectiveHireDependentIDNominationBankDetailPage;
import org.peoplestrong.altworklife.page.hris.ProspectiveHireEmploymentDetailPage;
import org.peoplestrong.altworklife.page.hris.ProspectiveHiresPage;
import org.peoplestrong.altworklife.utils.DBConnection;
import org.peoplestrong.altworklife.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class NewTestBackup {

	String panCardNo;
	String mobileNumber;
	String emergencyNumber;
	String emergencyemail;
	String aadharNo;
	String firstName;
	String lastName;
	String email;
	String employeeCode;
	String officialEmail;
	String pfno;
	String uanNumber;
	String voterID;
	String pranNumber;
	String ssn;
	String esic;

	String dependantName1 = "Father";
	String dependantName2 = "Mother";
	String accountNumber;
	String DLNumber;

	// UI Messages on Employment Detail Section:
	String companyNameRequiredMessage = "Company Name information is required";
	String titleNameRequiredMessage = "Title information is required";
	String empTypeRequiredMessage = "Employment Type information is required";
	String startDateRequiredMessage = "Start Date information is required";
	String endDateRequiredMessage = "End Date information is required";
	String empDetailsSavedSuccessfully = "Employment Details saved successfully";
	String empDetailsDeleteSuccessfully = "Details deleted successfully";
	String educationLevelRequiredMsg = "Education Level information is required";
	String educationDegreeInforequiredMsg = "Degree information is required";
	String educationSpecializationInfoRequiredMsg = "Specialization information is required";
	String educationScoreTypeRequiredMsg = "Please select a score type";
	String educationPercentageRequiredMsg = "Percentage/Final Grade information is required";
	String educationDetailSaveSuccessfully = "Education Details saved successfully";
	String educationPercentageNumericMsg = "Percentage/Final Grade should be numeric";

	// UI message validations on Onboarding Portal
	String companyNameRequiredMessageOB = "Company Name required";
	String titleNameRequiredMessageOB = "Title required";
	String startDateRequiredMessageOB = "Start Date is required";
	String endDateRequiredMessageOB = "End Date is required";

	String educationLevelRequiredMsgOB = "Education Level is required";
	String educationDegreeInforequiredMsgOB = "Degree is required";
	String educationSpecializationInfoRequiredMsgOB = "Specialization is required";
	String educationScoreTypeRequiredMsgOB = "Please select a score type";
	String educationPercentageRequiredMsgOB = "Percentage/Final Grade is required";
	String educationBoardUnivRequiredMsgOB = "Board/University is required";
	String educationDetailSaveSuccessfullyOB = "Education Details saved successfully";
	String educationPercentageNumericMsgOB = "Percentage/Final Grade should be numeric";

	// String expected result strings
	String companyRediffCom = "Rediffcom";
	String empCode = "82340";
	String empTitle = "Test Engg.";
	String empRole = "QA Test";
	String empProject = "Tele Com";
	String empUpdatedProjectName = "Tele Com1";
	String empLocation = "INDIA";
	String empSalary = "100000.00";
	String empType = "Regular";
	String empPFAccountNumber = "123456789";
	String empTimeType = "Full Time";
	String empPFAccountNum = "1238970234";
	String expectedRowOnBoardingPortal = companyRediffCom + " " + empCode + " "
			+ empTitle + " " + "01/Feb/2017 01/Feb/2018" + " " + empProject
			+ " " + empRole + " " + empLocation + " " + empSalary + " "
			+ empType + " " + empPFAccountNumber;
	String expectedUpdatedRowOnBoardingPortal = companyRediffCom + " "
			+ empCode + " " + empTitle + " " + "01/Feb/2017 01/Feb/2018" + " "
			+ empUpdatedProjectName + " " + empRole + " " + empLocation + " "
			+ empSalary + " " + empType + " " + empPFAccountNumber;

	// Education details
	String educationLevelPostGraduate = "Post Graduate";
	String educationDegreeBTech = "B.Tech";
	String educationComputerSpecialization = "Elec. & Comp Engg";
	String educationMinorSpecialization = "TEST";
	String educationCourseType = "Full time";
	String educationInsttName = "GIT,Jaipur";
	String educationBoardUniv = " RAJ. UNIV";
	String educationSelectScoreType = "Percentage";
	String educationPercentageValue = "77";
	String educationStartDateMonth = "Aug";
	String educationEndDateMonth = "Oct";
	String year2016 = "2016";
	String year2017 = "2017";

	String expectedRowOnBoardingEducationDetail = educationDegreeBTech + " "
			+ educationInsttName + " " + educationComputerSpecialization + " "
			+ educationMinorSpecialization + " " + educationStartDateMonth
			+ "-" + year2016 + " " + educationEndDateMonth + "-" + year2017
			+ " " + educationPercentageValue;

	@BeforeClass
	public void initialization() {
		panCardNo = "BMEPG" + Long.toString(Utils.GenerateRandomNumber(4))
				+ "H";
		firstName = Utils.GenerateRandomString(5);
		lastName = Utils.GenerateRandomString(5);
		mobileNumber = "9" + Long.toString(Utils.GenerateRandomNumber(9));
		emergencyNumber = "9" + Long.toString(Utils.GenerateRandomNumber(9));
		email = Utils.GenerateRandomString(6) + "@test.com";
		emergencyemail = Utils.GenerateRandomString(6) + "@test.com";
		aadharNo = "2228" + Long.toString(Utils.GenerateRandomNumber(8));
		employeeCode = "2" + Long.toString(Utils.GenerateRandomNumber(6));
		pfno = "2" + Long.toString(Utils.GenerateRandomNumber(4));
		officialEmail = Utils.GenerateRandomString(6) + "@peoplestrong.com";
		uanNumber = "2222" + Long.toString(Utils.GenerateRandomNumber(8));
		voterID = "BME" + Long.toString(Utils.GenerateRandomNumber(7));
		pranNumber = "2222" + Long.toString(Utils.GenerateRandomNumber(8));
		ssn = Long.toString(Utils.GenerateRandomNumber(9));
		esic = Long.toString(Utils.GenerateRandomNumber(10));
		accountNumber = Long.toString(Utils.GenerateRandomNumber(10));
		DLNumber = Long.toString(Utils.GenerateRandomNumber(6));
		System.out.println(email);
	}

	@Test(priority = 0)
	public void goToOnBoardingPage() {
		Log.info("In login method");

		new LoginPage().open_site("ctt").login_as(
				PropertyFileHandler.getInstance().getValue("hrUserName"),
				PropertyFileHandler.getInstance().getValue("hrPassword"));
		new HomePage().mouse_over_onboarding().click_propspective_hire();

		new ProspectiveHiresPage().click_on_add_prospective_hire_btn()
				.click_on_submit_btn()
				.verify_error_msgs("First Name is required")
				.verify_error_msgs("Last Name is required")
				.verify_error_msgs("Father Name is required")
				.verify_error_msgs("Employment Type is required")
				.verify_error_msgs("Date Of Birth is required")
				.verify_error_msgs("Gender information is required")
				.verify_error_msgs("Personal Email information is required")
				.verify_error_msgs("Mobile is required")
				.verify_error_msgs("PAN Number information is required")
				.verify_error_msgs("Grade information is required")
				.verify_error_msgs("Designation information is required")
				.verify_error_msgs("Notice Period information is required")
				.verify_error_msgs("Work Center information is required")
				.enter_value_in_specified_field("First Name:", firstName)
				.enter_value_in_specified_field("Last Name:", lastName)
				.enter_value_in_specified_field("Father Name:", "father")
				.enter_value_in_specified_field("Mobile:", mobileNumber)
				.enter_value_in_specified_field("Personal Email:", email)
				.enter_value_in_specified_field("PAN Number:", panCardNo)
				.open_dob_calendar().select_year().click_any_date()
				.enter_value_in_specified_field("Notice Period:", "30")
				.click_to_open_specified_dropdown("Gender:")
				/*.select_gender_from_dropdown()
				.click_to_open_specified_dropdown("Grade:")
				.select_grade_from_dropdown()
				.click_to_open_specified_dropdown("Designation:")
				.select_designation_from_dropdown()
				.click_to_open_specified_dropdown("Employment Type:")
				.select_employee_type_from_dropdown()
				.click_to_open_specified_dropdown("Work Center:")
				.select_work_center_from_dropdown()*/
				.click_on_submit_btn()
				.verify_submit_msg();
	}

	@Test(priority = 1)
	public void test2() {

		new HomePage().mouse_over_onboarding().click_propspective_hire();

		new ProspectiveHiresPage()
				.click_initiate_btn(email)
				.verify_error_msg_on_initiate()
				.open_onboarding_status(email)
				.click_edit_onboarding_employee()
				// //position information //////////
				.open_expected_doj_calendar()
				.click_today_date()
				.click_to_open_specified_dropdown("L1 Manager:")
				.select_l1_manager_from_dropdown()
				.click_to_open_specified_dropdown("HR Manager:")
				.select_hr_manager_from_dropdown()
				// //company/////////////
				.click_to_open_specified_dropdown("Company:")
				.select_company_from_dropdown()
				.click_to_open_specified_dropdown("SBU:")
				.select_sbu_from_dropdown()
				.click_to_open_specified_dropdown("Business Unit:")
				.select_businessunit_from_dropdown()
				.click_to_open_specified_dropdown("Function:")
				.select_function_from_dropdown()
				.click_to_open_specified_dropdown("Sub-function:")
				.select_subfunction_from_dropdown()
				.click_to_open_specified_dropdown("Project Code:")
				.select_projectcode_from_dropdown()
				// //country/////////////
				.click_to_open_specified_dropdown("Country:")
				.select_country_from_dropdown()
				.click_to_open_specified_dropdown("Region:")
				.select_region_from_dropdown()
				.click_to_open_specified_dropdown("State:")
				.select_state_from_dropdown()
				.click_to_open_specified_dropdown("City:")
				.select_city_from_dropdown()
				.click_to_open_specified_dropdown("Branch:")
				.select_branch_from_dropdown().click_on_submit_btn()
				.verify_submit_msg("Details have been successfully updated.");

	}

	@Test(priority = 2)
	public void test3() {
		// //personal information////
		new ProspectiveHiresPage()
				.click_to_open_specified_dropdown("Title:")
				.select_value_from_specified_dropdown("Title:", "Mr.")
				.click_to_open_specified_dropdown("Country Of Birth:")
				.select_value_from_specified_dropdown("Country Of Birth:",
						"India")
				.click_to_open_specified_dropdown("Marital Status:")
				.select_value_from_specified_dropdown("Marital Status:",
						"Single")
				.enter_value_in_specified_field("Middle Name:", "middle")
				.enter_value_in_specified_field("Place Of Birth:", "haryana")
				.click_on_submit_btn()
				.verify_submit_msg("Details have been successfully updated.")
				// //wrong ID Information///////////
				.enter_value_in_specified_field("PF Account Number:", "21234")
				.enter_value_in_specified_field("ESI Number (If Applicable):",
						"665544")
				.enter_value_in_specified_field("Voter ID Card:", "8877666")
				.enter_value_in_specified_field("UAN Number:", "1232323231")
				.enter_value_in_specified_field("Aadhar Card:", "4343434343")
				.enter_value_in_specified_field("SSN:", "776655")
				.enter_value_in_specified_field("PRAN Number:", "55444554455")
				.click_on_submit_btn()
				.verify_error_msgs("Data Invalid. UAN Must be 12 digit number.")
				.verify_error_msgs(
						"Data Invalid. ESIC Must be 10 digit number in correct format.")
				.verify_error_msgs(
						"Data invalid. PRAN Number must be a 12 digit number.")
				.verify_error_msgs(
						"Data Invalid. Adhar Must be 12 digit number in correct format. first 2 digit should not contain 0 or 1 and last 11 digit should be any number from 0-9")
				.verify_error_msgs("Data Invalid. SSN must be 9 digit number.")
				.verify_error_msgs(
						"Data Invalid. Voter Id must be a 10 digit number in correct format. first 3 digit should be alphabet and last seven should be number. eg. XXX1234567.");

	}

	@Test(priority = 3)
	public void test4() {
		// //personal information////
		new ProspectiveHiresPage()
				// ////right information//////////
				.enter_value_in_specified_field("PF Account Number:", pfno)
				.enter_value_in_specified_field("ESI Number (If Applicable):",
						esic)
				.enter_value_in_specified_field("Voter ID Card:", voterID)
				.enter_value_in_specified_field("UAN Number:", uanNumber)
				.enter_value_in_specified_field("Aadhar Card:", aadharNo)
				.enter_value_in_specified_field("SSN:", ssn)
				.enter_value_in_specified_field("PRAN Number:", pranNumber)
				.click_on_submit_btn()
				.verify_submit_msg("Details have been successfully updated.")
				// //////////////Ethinicity information/////////////////////////
				// .click_to_open_specified_dropdown("Religion:")
				// .select_value_from_specified_dropdown("Religion:", "")
				.click_to_open_specified_dropdown("Citizenship:")
				.select_value_from_specified_dropdown("Citizenship:", "Indian")
				.click_to_open_specified_dropdown("MOTHER TONGUE:")
				.select_value_from_specified_dropdown("MOTHER TONGUE:", "Hindi")
				.click_to_open_specified_dropdown("Nationality:")
				.select_value_from_specified_dropdown("Nationality:", "Indian")
				.enter_value_in_specified_field("Ethnicity:", "Hindu")
				.click_on_submit_btn()
				.verify_submit_msg("Details have been successfully updated.")
				// /////disability information////////
				.click_disability_chk_box()
				.click_to_open_specified_dropdown("Type Of Disability:")
				.select_value_from_specified_dropdown("Type Of Disability:",
						"Sitting Jobs Only")
				.enter_value_in_specified_field("Percentage Of Disability:",
						"10")
				.enter_value_in_specified_field("Official agency:", "None")
				.enter_value_in_specified_field("Reason Of Disability:",
						"Accident")
				.enter_value_in_specified_field(
						"Reference number of issuing authority:", "8787878787")
				.click_on_submit_btn()
				.verify_submit_msg("Details have been successfully updated.")
				// ///health information////////////
				.click_to_open_specified_dropdown("Blood Group:")
				.select_value_from_specified_dropdown("Blood Group:", "O+")
				.enter_value_in_specified_field("Weight:", "70")
				.enter_value_in_specified_field("Height:", "6")
				.enter_value_in_specified_field("Right Eye:", "6/6")
				.enter_value_in_specified_field("Major Illness:", "NO")
				.enter_value_in_specified_field("Left Eye:", "6/6")
				.click_on_submit_btn()
				.verify_submit_msg("Details have been successfully updated.");

	}

	@Test(priority = 4)
	public void test5() {
		// //personal information////
		new ProspectiveHiresPage()
				.click_contact_details_tab()
				.click_to_open_specified_dropdown("Contact Type:")
				.select_value_from_specified_dropdown("Contact Type:",
						"Official")
				.click_to_open_specified_dropdown("Country:")
				.select_value_from_specified_dropdown("Country:", "India")
				.click_to_open_specified_dropdown("State:")
				.select_value_from_specified_dropdown("State:", "Haryana")
				.click_to_open_specified_dropdown("City:")
				.select_value_from_specified_dropdown("City:", "Gurgaon")
				.click_to_open_specified_dropdown("Pin Code:")
				.select_value_from_specified_dropdown("Pin Code:",
						"Gurgaon-122001")
				.click_to_open_specified_dropdown("When Available:")
				.select_value_from_specified_dropdown("When Available:",
						"Weekdays")
				.enter_value_in_specified_field("Address Line 1:",
						"address line 1")
				.enter_value_in_specified_field("Address Line 2:", "line 2")
				.enter_value_in_specified_field("Phone:", mobileNumber)
				.click_save_btn()
				.verify_msg("Record added successfully.")
				.enter_value_in_specified_field("Contact Name:", "Brother")
				.enter_value_in_specified_field("Mobile Number:",
						emergencyNumber)
				.enter_value_in_specified_field("Contact Relationship:",
						"Brother")
				.enter_value_in_specified_field("Contact Address:",
						"emergency address")
				.enter_value_in_specified_field("Email Id:", emergencyemail)
				.click_add_btn().verify_msg("Record added successfully.");
	}

	@Test(priority = 5)
	public void addEducationDetails() {
		SoftAssert softAssertionEducationDetail = new SoftAssert();
		String errorMsg, successMsg = null;
		// //Education information////
		new ProspectiveHiresPage().click_education_detail_tab();
		errorMsg = new ProspectiveHireEducationDetailPage().clickSaveButton()
				.getErrorMessage();
		softAssertionEducationDetail.assertTrue(
				errorMsg.contains(educationLevelRequiredMsg),
				"Expected Correct required message. Got: " + errorMsg);
		softAssertionEducationDetail.assertTrue(
				errorMsg.contains(educationDegreeInforequiredMsg),
				"Expected Correct required message. Got: " + errorMsg);
		softAssertionEducationDetail.assertTrue(
				errorMsg.contains(educationSpecializationInfoRequiredMsg),
				"Expected Correct required message. Got: " + errorMsg);
		softAssertionEducationDetail.assertTrue(
				errorMsg.contains(educationScoreTypeRequiredMsg),
				"Expected Correct required message. Got: " + errorMsg);

		errorMsg = new ProspectiveHireEducationDetailPage()
				.selectEducationLevel(educationLevelPostGraduate)
				.selectDegreeValue(educationDegreeBTech)
				.selectSpecializationValue(educationComputerSpecialization)
				.selectMinorSpecializationValue(educationMinorSpecialization)
				.selectCourseTypeLevel(educationCourseType)
				.selectInstituteNameValue(educationInsttName)
				.setBoardUniv(educationBoardUniv)
				.selectScoreTypeValue(educationSelectScoreType)
				.selectStartDate(educationStartDateMonth, year2016)
				.selectEndDate(educationEndDateMonth, year2017)
				.clickSaveButton().getErrorMessage();
		softAssertionEducationDetail.assertTrue(
				errorMsg.contains(educationPercentageRequiredMsg),
				"Expected oercentage required message. Got: " + errorMsg);
		errorMsg = new ProspectiveHireEducationDetailPage()
				.setPercentGradeValue("TEST").clickSaveButton()
				.getErrorMessage();
		softAssertionEducationDetail.assertTrue(
				errorMsg.contains(educationPercentageNumericMsg),
				"Expected oercentage required message.. Got: " + errorMsg);

		successMsg = new ProspectiveHireEducationDetailPage()
				.setPercentGradeValue(educationPercentageValue)
				.clickSaveButton().getUIMessage();
		softAssertionEducationDetail.assertTrue(
				successMsg.contains(educationDetailSaveSuccessfully),
				"Expected oercentage required message. Got: " + successMsg);

		softAssertionEducationDetail.assertAll();

	}

	@Test(priority = 6)
	public void addEmploymentDetails() {

		SoftAssert softAssertionEmpDetail = new SoftAssert();
		String errorMsg, successMsg = null;
		// //Employment information////
		new ProspectiveHiresPage().click_emp_detail_tab();
		errorMsg = new ProspectiveHireEmploymentDetailPage().clickSaveButton()
				.getErrorMessage();
		softAssertionEmpDetail.assertTrue(
				errorMsg.contains(companyNameRequiredMessage),
				"Expected Company required message");
		softAssertionEmpDetail.assertTrue(
				errorMsg.contains(titleNameRequiredMessage),
				"Expected Company required message");
		softAssertionEmpDetail.assertTrue(
				errorMsg.contains(empTypeRequiredMessage),
				"Expected Company required message");
		softAssertionEmpDetail.assertTrue(
				errorMsg.contains(startDateRequiredMessage),
				"Expected Company required message");
		softAssertionEmpDetail.assertTrue(
				errorMsg.contains(endDateRequiredMessage),
				"Expected Company required message");

		successMsg = new ProspectiveHireEmploymentDetailPage()
				.setCompanyName(companyRediffCom).setEmpCode(empCode)
				.setTitle(empTitle).setRole(empRole).setProjectName(empProject)
				.setLocation(empLocation).setSalary(empSalary)
				.selectEmpDetailStartDate(01, 01, "2017")
				.selectEmpDetailEndDate(01, 01, "2018").selectEmpType(empType)
				.selectFullPartTimeValue(empTimeType)
				.setEmpPFAccountNumber(empPFAccountNumber).clickSaveButton()
				.getUIMessage();
		softAssertionEmpDetail.assertTrue(
				successMsg.contains(empDetailsSavedSuccessfully),
				"Expected employment detail should be saved successfuully");

		softAssertionEmpDetail.assertAll();

	}

	@Test(priority = 7)
	public void initiateProspectiveHireTest() {
		new HomePage().mouse_over_onboarding().click_propspective_hire();

		new ProspectiveHiresPage().click_initiate_btn(email).verify_msg();

		new LoginPage().logout().open_ob_portal("ctt")
				.login_as(email, firstName + "_demo");

		new PreJoiningFormPage()
				.click_start_pre_joining()
				.verify_selected_value_in_dropdown("Title:", "Mr.")
				.verify_selected_value_in_dropdown("Gender:", "Male")
				.verify_selected_value_in_dropdown("Country Of Birth:", "India")
				.verify_selected_value_in_dropdown("Marital Status:", "Single")
				.verify_selected_value_in_dropdown("Citizenship:", "Indian")
				.verify_selected_value_in_dropdown("MOTHER TONGUE:", "Hindi")
				.verify_selected_value_in_dropdown("Nationality:", "Indian")
				.verify_selected_value_in_dropdown("Type Of Disability:",
						"Sitting Jobs Only")
				.verify_selected_value_in_dropdown("Blood Group:", "O+")

				.verify_value_in_input_field("First Name:", firstName)
				.verify_value_in_input_field("Middle Name:", "middle")

				.verify_value_in_input_field("Last Name:", lastName)
				.verify_value_in_input_field("Father Name:", "father")
				.verify_value_in_input_field("Place Of Birth:", "haryana")
				.verify_value_in_input_field("Mobile:", mobileNumber)
				.verify_value_in_input_field("Personal Email:", email)
				.verify_value_in_input_field("PAN Number:", panCardNo)
				.verify_value_in_input_field("UAN Number:", uanNumber)
				.verify_value_in_input_field("PF Account Number:", pfno)

				.verify_value_in_input_field("Aadhar Card:", aadharNo)
				.verify_value_in_input_field("ESI Number (If Applicable):",
						esic)
				.verify_value_in_input_field("SSN:", ssn)
				.verify_value_in_input_field("Voter ID Card:", voterID)
				.verify_value_in_input_field("PRAN Number:", pranNumber)
				.verify_value_in_input_field("Percentage Of Disability:",
						"10.0")
				.verify_value_in_input_field("Official agency:", "None")
				.verify_value_in_input_field("Reason Of Disability:",
						"Accident")

				.verify_value_in_input_field(
						"Reference number of issuing authority:", "8787878787")
				.verify_value_in_input_field("Weight:", "70.0")
				.verify_value_in_input_field("Right Eye:", "6/6")
				.verify_value_in_input_field("Height:", "6")
				.verify_value_in_input_field("Left Eye:", "6/6")
				.verify_value_in_input_field("Major Illness:", "NO")
				.click_contact_details_tab();

	}

	@Test(priority = 8)
	public void verifyEmploymentDetailsOnBoardingPage() {
		SoftAssert softAssertionEmpDetail = new SoftAssert();

		String rowResult, saveMsg, errorMsg, deleteMsg;
		new PreJoiningFormPage().click_start_pre_joining().clickEmpDetailTab();
		rowResult = new PreJoiningEmploymentFormPage().getEmpRowDetails(1);
		Assert.assertTrue(
				(expectedRowOnBoardingPortal.trim()).contains(rowResult.trim()),
				"Expected Result to be: " + expectedRowOnBoardingPortal
						+ " .Got: " + rowResult);

		saveMsg = new PreJoiningEmploymentFormPage().clickEditIcon(1)
				.setprojectName(empUpdatedProjectName).click_save_button()
				.getUIMessage();

		Assert.assertEquals(saveMsg, empDetailsSavedSuccessfully,
				"Expected Result to be: " + empDetailsSavedSuccessfully
						+ ".Got: " + saveMsg);
		Assert.assertTrue(
				new PreJoiningEmploymentFormPage().getEmpRowDetails(1)
						.contains(expectedUpdatedRowOnBoardingPortal),
				"Expected Result to be: " + expectedUpdatedRowOnBoardingPortal
						+ ".Got: " + rowResult);

		// Add new employment detail from on boarding
		errorMsg = new PreJoiningEmploymentFormPage().clickAddMoreLink()
				.click_save_button().getUIErrorMsg();

		softAssertionEmpDetail.assertTrue(
				errorMsg.contains(companyNameRequiredMessageOB),
				"Expected Company required message");
		softAssertionEmpDetail.assertTrue(
				errorMsg.contains(titleNameRequiredMessageOB),
				"Expected Company required message");
		softAssertionEmpDetail.assertTrue(
				errorMsg.contains(startDateRequiredMessageOB),
				"Expected Company required message");
		softAssertionEmpDetail.assertTrue(
				errorMsg.contains(endDateRequiredMessageOB),
				"Expected Company required message");

		// Add details
		saveMsg = new PreJoiningEmploymentFormPage()
				.setEmploymentDetailsOnBoarding("Company Name",
						companyRediffCom)
				.setEmploymentDetailsOnBoarding("Employee Code:", empCode)
				.setEmploymentDetailsOnBoarding("Title", empTitle)
				.setprojectName(empProject)
				.setEmploymentDetailsOnBoarding("Role", empRole)
				.setEmploymentDetailsOnBoarding("Location:", empLocation)
				.setEmploymentDetailsOnBoarding("Salary", empSalary)
				.selectEmpDetailStartDate(01, 01, "2017")
				.selectEmpDetailEndDate(01, 01, "2018")
				.setEmploymentDetailsOnBoarding("PF ACCOUNT: ",
						empPFAccountNumber).click_save_button().getUIMessage();
		Assert.assertEquals(saveMsg, empDetailsSavedSuccessfully,
				"Expected Result to be: " + empDetailsSavedSuccessfully
						+ ".Got: " + saveMsg);

		deleteMsg = new PreJoiningEmploymentFormPage().clickDeleteRowIcon(2)
				.clickDialogYesButton().getUIMessage();
		Assert.assertEquals(deleteMsg, empDetailsDeleteSuccessfully,
				"Expected Row to be deleted successfully.");
		Assert.assertEquals(
				new PreJoiningEmploymentFormPage().getNumberOfEmpDetailRows(),
				1, "Expected Number of rows to be ");
	}

	@Test(priority = 9)
	public void verifyEducationDetailsOnBoardingPage() {
		SoftAssert softAssertionEducationDetail = new SoftAssert();
		new PreJoiningFormPage().click_start_pre_joining()
				.clickEducatiDetailTab();
		String rowResult, saveMsg, errorMsg, deleteMsg, successMsg;
		errorMsg = new PreJoiningEmploymentFormPage().clickAddMoreLink()
				.click_save_button().getUIErrorMsg();
		// Assertion for Mandatory fields
		softAssertionEducationDetail.assertTrue(
				errorMsg.contains(educationDegreeInforequiredMsgOB),
				"Expected Degree Info required message. " + errorMsg);
		softAssertionEducationDetail.assertTrue(
				errorMsg.contains(educationDegreeInforequiredMsgOB),
				"Expected Degree Name required message");
		softAssertionEducationDetail.assertTrue(
				errorMsg.contains(educationSpecializationInfoRequiredMsgOB),
				"Expected Specialization required message");
		softAssertionEducationDetail.assertTrue(
				errorMsg.contains(educationBoardUnivRequiredMsgOB),
				"Expected Education Board/Univ required message");
		softAssertionEducationDetail.assertTrue(
				errorMsg.contains(educationScoreTypeRequiredMsgOB),
				"Expected Score Type required message");

		new ProspectiveHireEducationDetailPage()
				.selectEducationLevel(educationLevelPostGraduate)
				.selectDegreeValue(educationDegreeBTech)
				.selectSpecializationValue(educationComputerSpecialization)
				.selectMinorSpecializationValue(educationMinorSpecialization)
				.selectCourseTypeLevel(educationCourseType)
				.selectInstituteNameValue(educationInsttName)
				.setBoardUniv(educationBoardUniv)
				.selectScoreTypeValue(educationSelectScoreType)
				.selectStartDate(educationStartDateMonth, year2016)
				.selectEndDate(educationEndDateMonth, year2017)
				.clickSaveButton();
		errorMsg = new PreJoiningEmploymentFormPage().getUIErrorMsg();
		softAssertionEducationDetail.assertTrue(
				errorMsg.contains(educationPercentageRequiredMsgOB),
				"Expected Percentage required message. Got: " + errorMsg);
		new ProspectiveHireEducationDetailPage().setPercentGradeValue("TEST")
				.clickSaveButton();
		errorMsg = new PreJoiningFormPage().getUIErrorMsg();
		softAssertionEducationDetail.assertTrue(
				errorMsg.contains(educationPercentageNumericMsgOB),
				"Expected Percentage to be Numericrequired message.. Got: "
						+ errorMsg);

		new ProspectiveHireEducationDetailPage().setPercentGradeValue(
				educationPercentageValue).clickSaveButton();

		successMsg = new PreJoiningFormPage().getUIMessage();
		softAssertionEducationDetail.assertTrue(
				successMsg.contains(educationDetailSaveSuccessfully),
				"Expected oercentage required message. Got: " + successMsg);

		// //Education information////
		rowResult = new PreJoiningEducationFormPage().getEducationRowDetail(1);
		// Assert Rows
		Assert.assertTrue((expectedRowOnBoardingEducationDetail.trim())
				.contains(rowResult.trim()), "Expected Result to be: "
				+ expectedRowOnBoardingEducationDetail + " .Got: " + rowResult);

		new PreJoiningEducationFormPage().clickEditIcon(1)
				.setEducationDegreeNull().clickSaveButton();
		errorMsg = new PreJoiningFormPage().getUIErrorMsg();
		// Assert Rows
		softAssertionEducationDetail.assertTrue((errorMsg.trim())
				.contains(educationDegreeInforequiredMsgOB.trim()),
				"Expected Result to be: " + educationDegreeInforequiredMsgOB
						+ " .Got: " + errorMsg);

		new PreJoiningEducationFormPage().clickDeleteIcon(2)
				.clickDialogYesButton();
		successMsg = new PreJoiningFormPage().getUIMessage();
		softAssertionEducationDetail.assertTrue(
				successMsg.contains(empDetailsDeleteSuccessfully), "Expected "
						+ empDetailsDeleteSuccessfully + "message. Got: "
						+ successMsg);

		// Assert All Verifications
		softAssertionEducationDetail.assertAll();
		Assert.assertEquals(
				new PreJoiningEducationFormPage().getNumberOfRows(), 1,
				"Expected only one row after delete");

	}

	@Test(priority = 10)
	public void testDependantDetails() {
		// //personal information////
		new ProspectiveHiresPage().click_specified_tab("Dependent Details");
		new DependentIDNominationBankTabDetailPage()
				.click_save_btn()
				.verify_error_msg("Name information is required")
				.verify_error_msg("Relationship information is required")
				.enter_value_in_specified_field("Name:", dependantName1)
				.click_to_open_specified_dropdown("Gender:")
				.select_value_from_specified_dropdown("Gender:", "Male")
				.click_to_open_specified_dropdown("Relationship:")
				.select_value_from_specified_dropdown("Relationship:", "Father")
				.click_to_open_specified_dropdown("Marital Status:")
				.select_value_from_specified_dropdown("Marital Status:",
						"Married")
				.click_to_open_specified_dropdown("Blood Group:")
				.select_value_from_specified_dropdown("Blood Group:", "O+")
				.click_to_open_specified_dropdown("Employment Type:")
				.select_value_from_specified_dropdown("Employment Type:",
						"Retired")
				.enter_value_in_specified_field("Address:", "address line 1")
				.enter_value_in_specified_field("Occupation:", "Retired")
				.click_save_btn()
				.verify_msg("Dependent Details saved successfully")
				.click_add_more_btn()
				.enter_value_in_specified_field("Name:", dependantName2)
				.click_to_open_specified_dropdown("Gender:")
				.select_value_from_specified_dropdown("Gender:", "Female")
				.click_to_open_specified_dropdown("Relationship:")
				.select_value_from_specified_dropdown("Relationship:", "Mother")
				.click_to_open_specified_dropdown("Marital Status:")
				.select_value_from_specified_dropdown("Marital Status:",
						"Married")
				.click_to_open_specified_dropdown("Blood Group:")
				.select_value_from_specified_dropdown("Blood Group:", "O+")
				.click_to_open_specified_dropdown("Employment Type:")
				.select_value_from_specified_dropdown("Employment Type:",
						"Retired")
				.enter_value_in_specified_field("Address:", "address line 1")
				.enter_value_in_specified_field("Occupation:", "Retired")
				.click_chkbox_of_specified_field("Is Dependent:")
				.click_save_btn()
				.verify_msg("Dependent Details saved successfully");

		new ProspectiveHiresPage().click_specified_tab("NOMINATION");
		new DependentIDNominationBankTabDetailPage()
				.click_save_btn()
				.verify_error_msg("DEPENDANT information is required")
				.verify_error_msg("PERCENTAGE information is required")
				.verify_error_msg("NOMINEE TYPE information is required")
				// .enter_value_in_specified_field("Account Holder Name:" ,
				// "Bapu")
				.click_to_open_specified_dropdown("DEPENDANT:")
				.select_value_from_specified_dropdown("DEPENDANT:",
						dependantName2)
				.click_to_open_specified_dropdown("NOMINEE TYPE:")
				.select_value_from_specified_dropdown("NOMINEE TYPE:", "PF")
				.enter_value_in_specified_field("PERCENTAGE:", "100")
				.click_save_btn().verify_msg("NOMINATION saved successfully");

		new ProspectiveHiresPage().click_specified_tab("Bank Details");
		new DependentIDNominationBankTabDetailPage()
				.click_save_btn()
				.verify_error_msg("Name Of Bank information is required")
				.verify_error_msg("Account Number information is required")
				.enter_value_in_specified_field("Account Holder Name:",
						firstName)
				.click_to_open_specified_dropdown("Name Of Bank:")
				.select_value_from_specified_dropdown("Name Of Bank:",
						"Yes Bank")
				.click_to_open_specified_dropdown("Account Type:")
				.select_value_from_specified_dropdown("Account Type:", "Saving")
				.click_to_open_specified_dropdown("Payment Mode:")
				.select_value_from_specified_dropdown("Payment Mode:", "Cheque")
				.enter_value_in_specified_field("Branch Name:",
						"address line 1")
				.enter_value_in_specified_field("Account Number:",
						accountNumber)
				.enter_value_in_specified_field("Ifsc Code:", "AXIS00098GH")
				.click_save_btn().verify_msg("Bank Details saved successfully");

		new ProspectiveHiresPage().click_specified_tab("ID Details");
		new DependentIDNominationBankTabDetailPage()
				.click_save_btn()
				.verify_error_msg("ID Type information is required")
				.verify_error_msg("Number information is required")
				.enter_value_in_specified_field("Name On Document:", firstName)
				.click_to_open_specified_dropdown("ID Type:")
				.select_value_from_specified_dropdown("ID Type:",
						"Driving License")
				.enter_value_in_specified_field("Issue Place:", "Gurugram")
				.enter_value_in_specified_field("Number:", DLNumber)
				// .enter_value_in_specified_field("Ifsc Code:" , "AXIS00098G")
				.click_save_btn().verify_msg("Details saved successfully");
	}

	@Test(priority = 11)
	public void test7() {
		new HomePage().mouse_over_onboarding().click_propspective_hire();

		new ProspectiveHiresPage().click_initiate_btn(email).verify_msg();

		new LoginPage().logout().open_ob_portal("ctt")
				.login_as(email, firstName + "_demo");

		new PreJoiningFormPage()
				.click_start_pre_joining()
				.verify_selected_value_in_dropdown("Title:", "Mr.")
				.verify_selected_value_in_dropdown("Gender:", "Male")
				.verify_selected_value_in_dropdown("Country Of Birth:", "India")
				.verify_selected_value_in_dropdown("Marital Status:", "Single")
				.verify_selected_value_in_dropdown("Citizenship:", "Indian")
				.verify_selected_value_in_dropdown("MOTHER TONGUE:", "Hindi")
				.verify_selected_value_in_dropdown("Nationality:", "Indian")
				.verify_selected_value_in_dropdown("Type Of Disability:",
						"Sitting Jobs Only")
				.verify_selected_value_in_dropdown("Blood Group:", "O+")

				.verify_value_in_input_field("First Name:", firstName)
				.verify_value_in_input_field("Middle Name:", "middle")

				.verify_value_in_input_field("Last Name:", lastName)
				.verify_value_in_input_field("Father Name:", "father")
				.verify_value_in_input_field("Place Of Birth:", "haryana")
				.verify_value_in_input_field("Mobile:", mobileNumber)
				.verify_value_in_input_field("Personal Email:", email)
				.verify_value_in_input_field("PAN Number:", panCardNo)
				.verify_value_in_input_field("UAN Number:", uanNumber)
				.verify_value_in_input_field("PF Account Number:", pfno)

				.verify_value_in_input_field("Aadhar Card:", aadharNo)
				.verify_value_in_input_field("ESI Number (If Applicable):",
						esic)
				.verify_value_in_input_field("SSN:", ssn)
				.verify_value_in_input_field("Voter ID Card:", voterID)
				.verify_value_in_input_field("PRAN Number:", pranNumber)
				.verify_value_in_input_field("Percentage Of Disability:",
						"10.0")
				.verify_value_in_input_field("Official agency:", "None")
				.verify_value_in_input_field("Reason Of Disability:",
						"Accident")

				.verify_value_in_input_field(
						"Reference number of issuing authority:", "8787878787")
				.verify_value_in_input_field("Weight:", "70.0")
				.verify_value_in_input_field("Right Eye:", "6/6")
				.verify_value_in_input_field("Height:", "6")
				.verify_value_in_input_field("Left Eye:", "6/6")
				.verify_value_in_input_field("Major Illness:", "NO")
				.click_contact_details_tab();
	}

	@Test(priority = 8)
	public void test10() {
		new PreJoiningFormPage()
				.click_contact_details_tab()
				.verify_address_present("Official", "address line 1",
						"Gurgaon-122001", mobileNumber)
				.verify_emergency_address_present("Brother", "Brother",
						emergencyNumber, "emergency address")
				.click_edit_address_btn()
				.enter_value_in_specified_field("Address Line 1:", "edited")
				.click_save_basic_details()
				.verify_address_present("Official", "edited", "Gurgaon-122001",
						mobileNumber)
				.click_delete_address_btn()
				.click_yes_on_popup()
				.click_edit_emergency_address_btn()
				.enter_value_in_specified_field("Contact Name:", "EditBrother")
				.click_save_basic_details()
				.verify_emergency_address_present("EditBrother", "Brother",
						emergencyNumber, "emergency address")
				.click_delete_emergency_address_btn().click_yes_on_popup();

		new PreJoiningFormPage()
				.click_specified_tab("Dependent Details")
				.verify_dependant_detail_present(dependantName1, "Father",
						"No", "No")
				.verify_dependant_detail_present(dependantName2, "Mother",
						"Yes", "No");

		new PreJoiningFormPage().click_specified_tab("NOMINATION")
				.verify_nomination_detail_present(dependantName2, "100", "PF");

		new PreJoiningFormPage().click_specified_tab("Bank Details")
				.verify_bank_detail_present("Yes Bank", accountNumber,
						"Saving", "Yes")// bankDetailForm
		;

		new PreJoiningFormPage().click_specified_tab("ID Details")
				.verify_id_detail_present("Driving License", DLNumber,
						"Gurugram")// bankDetailForm
		;

	}

	@AfterClass
	public void deleteUser() {
		
			DBConnection.getConnection();
			DBConnection
					.executeQuery("delete from HrOnboardingApproverChecklist where EmployeeJoiningFormID = (select EmployeeJoiningFormID from dbo.HrEmployeeJoiningForm where EmailID='"
							+ email + "')");
			DBConnection
					.executeQuery("delete  from HrOnboardingDocumentMap where EmployeeJoiningFormID = (select EmployeeJoiningFormID from dbo.HrEmployeeJoiningForm where EmailID='"
							+ email + "')");
			DBConnection
					.executeQuery("delete from HrOnboardingEmployeeChecklist where EmployeeJoiningFormID = (select EmployeeJoiningFormID from dbo.HrEmployeeJoiningForm where EmailID='"
							+ email + "')");
			DBConnection
					.executeQuery("delete from HrEmployeeJoiningFormHistory where EmployeeJoiningFormID = (select EmployeeJoiningFormID from dbo.HrEmployeeJoiningForm where EmailID='"
							+ email + "')");
			DBConnection
					.executeQuery("delete  from dbo.HrEmployeeJoiningForm where EmailID='"
							+ email + "'");

			DBConnection.closeConnection();

	}

}
