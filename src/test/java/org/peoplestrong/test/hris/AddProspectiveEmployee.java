package org.peoplestrong.test.hris;

import java.sql.SQLException;

import org.peoplestrong.altworklife.core.Log;
import org.peoplestrong.altworklife.core.PropertyFileHandler;
import org.peoplestrong.altworklife.page.hris.HomePage;
import org.peoplestrong.altworklife.page.hris.LoginPage;
import org.peoplestrong.altworklife.page.hris.OnboardingStatusPage;
import org.peoplestrong.altworklife.page.hris.PreJoiningFormPage;
import org.peoplestrong.altworklife.page.hris.ProspectiveHiresPage;
import org.peoplestrong.altworklife.utils.DBConnection;
import org.peoplestrong.altworklife.utils.Utils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddProspectiveEmployee {
	
	
	String panCardNo;
	String mobileNumber;
	String emergencyNumber;
	String aadharNo;
	String firstName;
	String lastName;
	String email;
	String employeeCode;
	String officialEmail;
	
	
	
	@BeforeClass
	public void initialization() {
		panCardNo="BMEPG"+Long.toString(Utils.GenerateRandomNumber(4))+"H";
		firstName=Utils.GenerateRandomString(5);
		lastName=Utils.GenerateRandomString(5);
		mobileNumber="9"+Long.toString(Utils.GenerateRandomNumber(9));
		emergencyNumber="9"+Long.toString(Utils.GenerateRandomNumber(9));
		email=Utils.GenerateRandomString(6)+"@test.com";
		aadharNo="2228"+Long.toString(Utils.GenerateRandomNumber(8));
		employeeCode="2"+Long.toString(Utils.GenerateRandomNumber(6));
		officialEmail=Utils.GenerateRandomString(6)+"@peoplestrong.com";
		System.out.println(email);
	}
	
	@Test(priority=0)
	public void goToOnBoardingPage() {
		Log.info("In login method");
		
		new LoginPage()
			.open_site("ctt")
			.login_as(PropertyFileHandler.getInstance().getValue("hrUserName"),
					PropertyFileHandler.getInstance().getValue("hrPassword"));
		new HomePage()
			.mouse_over_onboarding()
			.click_propspective_hire();
		
		
		new ProspectiveHiresPage()
			.click_on_add_prospective_hire_btn()
			.enter_value_in_specified_field("First Name:" , firstName)
			.enter_value_in_specified_field("Last Name:" , lastName)
			.enter_value_in_specified_field("Father Name:" , "father")
			.enter_value_in_specified_field("Mobile:" , mobileNumber)
			.enter_value_in_specified_field("Personal Email:" , email)
			.enter_value_in_specified_field("PAN Number:" , panCardNo)			
			.open_dob_calendar()
			.select_year()
			.click_any_date()
			.open_expected_doj_calendar()
			.click_today_date()
			.enter_value_in_specified_field("Notice Period:","30")
			.click_to_open_specified_dropdown("Gender:")
			/*.select_gender_from_dropdown()
			.click_to_open_specified_dropdown("Blood Group:")
			.select_blood_group_from_dropdown()
			.click_to_open_specified_dropdown("Grade:")
			.select_grade_from_dropdown()
			.click_to_open_specified_dropdown("Designation:")
			.select_designation_from_dropdown()
			.click_to_open_specified_dropdown("L1 Manager:")
			.select_l1_manager_from_dropdown()
			.click_to_open_specified_dropdown("HR Manager:")
			.select_hr_manager_from_dropdown()
			.click_to_open_specified_dropdown("Employment Type:")
			.select_employee_type_from_dropdown()
			.click_to_open_specified_dropdown("Work Center:")
			.select_work_center_from_dropdown()*/
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
			.click_to_open_specified_dropdown("Country:")
			.select_country_from_dropdown()
			.click_to_open_specified_dropdown("Region:")
			.select_region_from_dropdown()
			.click_to_open_specified_dropdown("State:")
			.select_state_from_dropdown()
			.click_to_open_specified_dropdown("City:")
			.select_city_from_dropdown()
			.click_to_open_specified_dropdown("Branch:")
			.select_branch_from_dropdown()			
			.click_on_submit_btn()
			.verify_submit_msg();
		}
		@Test(priority=1)
		public void test2() {
		
		new HomePage()
			.mouse_over_onboarding()
			.click_propspective_hire();
		
		new ProspectiveHiresPage()
			.click_initiate_btn(email)
			.verify_msg();
		
		new LoginPage()
			.logout()
			.open_ob_portal("ctt")
			.login_as(email, firstName+"_demo");
	
	new PreJoiningFormPage()
		.click_start_pre_joining()
		.enter_aadhar_number(aadharNo)
		//.enter_pan_number(panCardNo)
		.click_save_basic_details()
		.click_contact_details_tab()
		.click_add_emergency_contact()
		.enter_contact_name("brother")
		.enter_contact_relationship("brother")
		.enter_mobile_number(emergencyNumber)
		.click_emergency_save_btn()
		.click_employeement_details()
		.select_fresher()
		.click_document_details()
		.upload_10th_doc()
		//.waitInSeconds(seconds);
		.click_finalize_btn()
		.click_declaration_chk_box()
		.click_finalize_btn()
		.click_submit_btn()
		.click_cnfrm_submit();
	
	new LoginPage()
		.logout_ob()
		.open_site("ctt")
		.login_as(PropertyFileHandler.getInstance().getValue("hrUserName"),
				PropertyFileHandler.getInstance().getValue("hrPassword"));
		}
	@Test(priority=2)
	public void test3() {
		
	new HomePage()
		.mouse_over_onboarding()
		.click_propspective_hire();
	
	new ProspectiveHiresPage()
			.open_onboarding_status(email)
			.click_view_onboarding_status()
			.click_move_to_joining_state()
		
			.verify_state_change_msg()
			.click_move_to_joined_state()
			.click_yes_on_alert();

		new HomePage()
			.mouse_over_onboarding()
			.click_onboarding_status();
		
		new OnboardingStatusPage()
		.open_employee_in_onboarding(firstName)
			.enter_cmnt_in_specified_task("P-File Creation","Done")
			.click_chk_box_specified_task("P-File Creation")
			.enter_cmnt_in_specified_task("Permanent Identity Card Handover","Done")
			.click_chk_box_specified_task("Permanent Identity Card Handover")
			.enter_cmnt_in_specified_task("Bank Account Opening","Done")
			.click_chk_box_specified_task("Bank Account Opening")
			.enter_cmnt_in_specified_task("Temporary Identity Card Handover","Done")
			.click_chk_box_specified_task("Temporary Identity Card Handover")
			.enter_cmnt_in_specified_task("Appointment Letter","Done")
			.click_chk_box_specified_task("Appointment Letter")
			.enter_cmnt_in_specified_task("Biometric","Done")
			.click_chk_box_specified_task("Biometric")
			.enter_cmnt_in_specified_task("Offer Document received from Recruitment Team","Done")
			.click_chk_box_specified_task("Offer Document received from Recruitment Team")
			.enter_cmnt_in_specified_task("CTC Documents to be shared with finance","Done")
			.click_chk_box_specified_task("CTC Documents to be shared with finance")
			.enter_cmnt_in_specified_task("Employee handover","Done")
			.click_chk_box_specified_task("Employee handover")			
			.enter_cmnt_in_specified_task("Onboarding Survey","Done")
			.click_chk_box_specified_task("Onboarding Survey")
			.enter_cmnt_in_specified_task("Welcome & Feedback Call","Done")
			.click_chk_box_specified_task("Welcome & Feedback Call")
			.enter_cmnt_in_specified_task("Share PAN Card details with Finance team","Done")
			.click_chk_box_specified_task("Share PAN Card details with Finance team")
			.enter_cmnt_in_specified_task("ESIC Card generation","Done")
			.click_chk_box_specified_task("ESIC Card generation")
			.enter_cmnt_in_specified_task("Generate Employee Code","Done")
			.click_add_specified_task("Generate Employee Code")
			.enter_employee_code(employeeCode)
			.enter_employee_comment("Done")
			.click_submit_btn()
			.enter_cmnt_in_specified_task("Create official email","Done")
			.click_chk_box_specified_task("Create official email")
			;
	
	}
		@Test(priority=3)
		public void test4() {
	
		
	new LoginPage()
	.logout()
	//.open_site("ctt")
	.login_as(PropertyFileHandler.getInstance().getValue("adminUserName"),
			PropertyFileHandler.getInstance().getValue("adminPassword"));
	
	new HomePage()
	.mouse_over_onboarding()
	.click_onboarding_status();
	
		new OnboardingStatusPage()
		.open_employee_in_onboarding(firstName)
			.enter_cmnt_in_specified_task("Seat Allocation","Done")
			.click_chk_box_specified_task("Seat Allocation")
			.enter_cmnt_in_specified_task("Business Card allocation","Done")
			.click_chk_box_specified_task("Business Card allocation")
			.enter_cmnt_in_specified_task("Permanent ID card creation","Done")
			.click_chk_box_specified_task("Permanent ID card creation")
			.enter_cmnt_in_specified_task("Temp ID Card allocation","Done")
			.click_chk_box_specified_task("Temp ID Card allocation")
			.enter_cmnt_in_specified_task("Cab /Transport Confirmation","Done")
			.click_chk_box_specified_task("Cab /Transport Confirmation")
			.enter_cmnt_in_specified_task("Drawer Key allocation","Done")
			.click_chk_box_specified_task("Drawer Key allocation");
		
		new LoginPage()
		.logout()
		.login_as(PropertyFileHandler.getInstance().getValue("hrManagerUserName"),
				PropertyFileHandler.getInstance().getValue("hrManagerPassword"));
		
		new HomePage()
			.mouse_over_onboarding()
			.click_onboarding_status();
		
		new OnboardingStatusPage()
		.open_employee_in_onboarding(firstName)
			.enter_cmnt_in_specified_task("Induction Sheet","Done")
			.click_chk_box_specified_task("Induction Sheet")
			.enter_cmnt_in_specified_task("Indent forecast sheet","Done")
			.click_chk_box_specified_task("Indent forecast sheet")
			.click_add_specified_task("Buddy assignation")
			.select_first_buddy()
			.enter_employee_comment_buddy("done")
			.submit_buddy_form()
			;
		new LoginPage()
		.logout()
		.login_as(PropertyFileHandler.getInstance().getValue("l1UserName"),
				PropertyFileHandler.getInstance().getValue("l1Password"));
		
		new HomePage()
			.mouse_over_onboarding()
			.click_onboarding_status();
		
		new OnboardingStatusPage()
		.open_employee_in_onboarding(firstName)
			.enter_cmnt_in_specified_task("Access needed for client applications namely :","Done")
			.click_chk_box_specified_task("Access needed for client applications namely :")
			.enter_cmnt_in_specified_task("VPN access needed(Yes/No) :","Done")
			.click_chk_box_specified_task("VPN access needed(Yes/No) :")
			.enter_cmnt_in_specified_task("Access needed for Folders/Servers :","Done")
			.click_chk_box_specified_task("Access needed for Folders/Servers :");
		
		new LoginPage()
		.logout()
		.login_as(PropertyFileHandler.getInstance().getValue("itUserName"),
				PropertyFileHandler.getInstance().getValue("itPassword"));
		
		new HomePage()
			.mouse_over_onboarding()
			.click_onboarding_status();
		
		new OnboardingStatusPage()
		.open_employee_in_onboarding(firstName)
			.enter_cmnt_in_specified_task("Desktop/Laptop allocation","Done")
			.click_chk_box_specified_task("Desktop/Laptop allocation")
			.enter_cmnt_in_specified_task("Desk Phone allocation","Done")
			.click_chk_box_specified_task("Desk Phone allocation")
			.enter_cmnt_in_specified_task("Data Card allocation","Done")
			.click_chk_box_specified_task("Data Card allocation")
			.enter_cmnt_in_specified_task("Access Card allocation","Done")
			.click_chk_box_specified_task("Access Card allocation")
			.enter_cmnt_in_specified_task("Mobile Handset allocation","Done")
			.click_chk_box_specified_task("Mobile Handset allocation")
			.enter_cmnt_in_specified_task("Email ID Creation","Done")
			.click_add_specified_task("Email ID Creation")
			.enter_employee_email(officialEmail)
			.enter_employee_comment_email("Done")
			.click_submit_btn_email()
			//.click_chk_box_specified_task("Email ID Creation")
			.enter_cmnt_in_specified_task("SIM Card allocation", "Done")
			.click_chk_box_specified_task("SIM Card allocation");
		
		
		new LoginPage()
		.logout()
		.login_as(PropertyFileHandler.getInstance().getValue("hrUserName"),
				PropertyFileHandler.getInstance().getValue("hrPassword"));
		
		new HomePage()
			.mouse_over_onboarding()
			.click_propspective_hire();
		new ProspectiveHiresPage()
		.open_onboarding_status(email)
		.click_view_onboarding_status()
		.click_onboarding_complete_link();
		
	}
	@AfterClass
	public void deleteUser() throws SQLException, InterruptedException  {		
			DBConnection.getConnection();		
			DBConnection.executeQuery("delete from HrOnboardingApproverChecklist where EmployeeJoiningFormID = (select EmployeeJoiningFormID from dbo.HrEmployeeJoiningForm where EmailID='"+email+"')");
			DBConnection.executeQuery("delete  from HrOnboardingDocumentMap where EmployeeJoiningFormID = (select EmployeeJoiningFormID from dbo.HrEmployeeJoiningForm where EmailID='"+email+"')");
			DBConnection.executeQuery("delete from HrOnboardingEmployeeChecklist where EmployeeJoiningFormID = (select EmployeeJoiningFormID from dbo.HrEmployeeJoiningForm where EmailID='"+email+"')");
			DBConnection.executeQuery("delete from HrEmployeeJoiningFormHistory where EmployeeJoiningFormID = (select EmployeeJoiningFormID from dbo.HrEmployeeJoiningForm where EmailID='"+email+"')");
			DBConnection.executeQuery("delete  from dbo.HrEmployeeJoiningForm where EmailID='"+email+"'");
			DBConnection.closeConnection();
		
	}
	
}
