package org.peoplestrong.test.hris;

import org.peoplestrong.altworklife.core.Log;
import org.peoplestrong.altworklife.core.PropertyFileHandler;
import org.peoplestrong.altworklife.page.hris.HomePage;
import org.peoplestrong.altworklife.page.hris.LoginPage;
import org.peoplestrong.altworklife.page.hris.OnboardingStatusPage;
import org.peoplestrong.altworklife.page.hris.ProspectiveHiresPage;
import org.peoplestrong.altworklife.utils.Utils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RejectProspectiveHire {

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

	
	@BeforeClass
	public void initialization() {
		//System.out.println("rrrrr");
		panCardNo="BMEPG"+Long.toString(Utils.GenerateRandomNumber(4))+"H";
		firstName=Utils.GenerateRandomString(5);
		lastName=Utils.GenerateRandomString(5);
		mobileNumber="9"+Long.toString(Utils.GenerateRandomNumber(9));
		emergencyNumber="9"+Long.toString(Utils.GenerateRandomNumber(9));
		email=Utils.GenerateRandomString(6)+"@mailinator.com";
		emergencyemail=Utils.GenerateRandomString(6)+"@test.com";
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
	}
	
	@Test(priority=10)
	public void verifyBasicDetailErrorMsgAndCreatingProspectiveHireWithMandatoryFields() {
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
			.enter_value_in_specified_field("Notice Period:","30")
			.click_to_open_specified_dropdown("Gender:")
			.select_value_from_specified_dropdown("Gender:", "Male")
			//.select_gender_from_dropdown()
			.click_to_open_specified_dropdown("Grade:")
			.select_value_from_specified_dropdown("Grade:", "3")
			//.select_grade_from_dropdown()
			.click_to_open_specified_dropdown("Designation:")
			.select_value_from_specified_dropdown("Designation:", "Testing Engineer")
			//.select_designation_from_dropdown()			
			.click_to_open_specified_dropdown("Employment Type:")
			.select_value_from_specified_dropdown("Employment Type:", "Probation")
			//.select_employee_type_from_dropdown()
			.click_to_open_specified_dropdown("Work Center:")
			.select_value_from_specified_dropdown("Work Center:", "Gurgaon")
			//.select_work_center_from_dropdown()
			.click_on_submit_btn()
			.verify_submit_msg();
	}
	
	@Test(priority=20)
	public void verifyErrorMsgOnInitiateAndAddDetails() {
		
		new HomePage()
			.mouse_over_onboarding()
			.click_propspective_hire();
		
		new ProspectiveHiresPage()
			.click_initiate_btn(email)
			.verify_error_msg_on_initiate()			
			.open_onboarding_status(email)
			.click_edit_onboarding_employee()
			////position information //////////
			.open_expected_doj_calendar()
			.click_today_date()
			.click_to_open_specified_dropdown("L1 Manager:")
			.select_l1_manager_from_dropdown()
			.click_to_open_specified_dropdown("HR Manager:")
			.select_hr_manager_from_dropdown()
			.click_to_open_specified_dropdown("Confirmation Status:")
			.select_value_from_specified_dropdown("Confirmation Status:", "On Probation")
			.click_to_open_specified_dropdown("Business Designation:")
			.select_value_from_specified_dropdown("Business Designation:", "HR Associate")
			////company/////////////
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
			////country/////////////
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
			.verify_submit_msg("Details have been successfully updated.");
			
	}
	
	
	@Test(priority=90)
	public void initiateAndGotoOnboardingPortal() {	
		new HomePage()
			.mouse_over_onboarding()
			.click_propspective_hire();
		
		new ProspectiveHiresPage()
			.click_initiate_btn(email)
			.verify_msg();
		
		
		new ProspectiveHiresPage()
			.open_onboarding_status(email)
			.click_view_onboarding_status()
			.verify_error_msg("Approval Pending");
		
		new LoginPage()
			.logout()
			.login_as(PropertyFileHandler.getInstance().getValue("hrManagerUserName"),
				PropertyFileHandler.getInstance().getValue("hrManagerPassword"));
		
		new HomePage()
			.mouse_over_onboarding()
			.click_onboarding_status();
		
		new OnboardingStatusPage()
			.verify_text_and_open_stage(firstName,"Prospective Hire Approval")
			.enter_comment("Rejected")
			.click_reject_btn()
			.click_ok_btn()	;
		new ProspectiveHiresPage()
			.verify_msg("Status updated successfully");
		
		new LoginPage()
			.logout()
			.login_as(PropertyFileHandler.getInstance().getValue("hrUserName"),
				PropertyFileHandler.getInstance().getValue("hrPassword"));
		new HomePage()
			.mouse_over_onboarding()
			.click_propspective_hire();
		
	}
	
}
