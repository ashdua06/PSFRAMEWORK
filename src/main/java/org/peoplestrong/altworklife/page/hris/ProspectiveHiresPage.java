package org.peoplestrong.altworklife.page.hris;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.peoplestrong.altworklife.core.BaseClass;
import org.peoplestrong.altworklife.core.Log;

public class ProspectiveHiresPage extends BaseClass{

	By addProspectiveHireBtn=By.xpath("//span[contains(@class,'ui-icon-plus')]");
	By expectedDojCalendar=By.xpath("//span[contains(text(),'EXPECTED DOJ')]/..//button/span[1]");
	By todayDate = By.xpath("//a[@class='ui-state-default ui-state-highlight']");
	By anyDateInCalendar=By.xpath("//td[@data-handler='selectDay']//a[contains(text(),'20')]");
	By dobCalendar=By.xpath("//label[contains(text(),'Date Of Birth:')]/../../dd//button/span[1]");
	By selectYear=By.xpath("//select[@class='ui-datepicker-year']");//1990	
	By submitBtn=By.xpath("//span[contains(text(),'Submit')]");
	By msgAfterSubmit=By.xpath("//div[@id='message']//span[@class='ui-messages-info-summary']");
	//Prospective hire has been successfully created. Now you can update additional details.
	//initiateEmployeeWithEmail(email)
	By onBoardingMsg=By.xpath("//form[@id='messageForm']//span[@class='ui-messages-info-summary']");
	By onBoardingSuccessMsg=By.xpath("//span[@class='ui-messages-info-summary']");
	//Onboarding started successfully
	//viewOnboardingStatusBtn(email)
	By viewOnboardingStausLnk=By.xpath("//div[contains(@id,'onboardingStatus:btn:')][contains(@style,'left')]//*[text()='View onboarding details']");
	By editOnboardingEmployee=By.xpath("//div[contains(@id,'onboardingStatus:btn:')][contains(@style,'left')]//*[text()='Edit']");
	By moveToJoiningState=By.xpath("//span[contains(text(),'Move To Joining Stage')]");
	By yesOnAlert=By.xpath("//form[@id='warningFormForNotCompletedTask']//span[text()='Yes']");
	By stageChangedMsg=By.xpath("//form[@id='messageForm']//span[@class='ui-messages-info-summary']");
	//Status updated successfully. Prospective Hire moved to next stage.
	By joinedBtn=By.xpath("//span[contains(text(),'Joined')]");
	By onboardingCompleteBtn=By.xpath("//span[contains(text(),'Onboarding Completed')]");
	By errorMsgs=By.xpath("//div[@id='message']//ul");
	By errorOnInitiate=By.xpath("//form[@id='messageForm']//span[@class='ui-messages-error-summary']");
	By disabilityChkBox=By.xpath("//span[@id='disabilityInfo']//span[contains(@class,'chkbox')]");
	By contactDetailsTab=By.xpath("//a[contains(text(),'Contact Details')]");
	By empDetailsTab=By.xpath("//a[contains(text(),'Employment Details')]");
	By educationDetailsTab=By.xpath("//a[contains(text(),'Education Details')]");
	By saveBtn=By.xpath("//span[contains(text(),'Save')]");
	By addBtn=By.xpath("//span[text()='Add']");
	By uiErrorMessage = By.cssSelector("div[class='ui-messages ui-widget']");
	By msgAfterSave=By.xpath("//div[contains(@id,'message')]//span[@class='ui-messages-info-summary']");
	By addMoreBtn=By.xpath("//a[text()='Add More']");
	By approvalError=By.xpath("//span[@class='ui-messages-error-detail']");
	By acceptBtn=By.xpath("//span[contains(text(),'Accept')]");
	//
	//String id = find("span[id='hrManagerGroup'] dl dd span[class='ToolTip'] div[id]")
	
	public  ProspectiveHiresPage me() {	
		wait_for_page_load();
		return this;
	}
	WebDriverWait wait=new WebDriverWait(driver, 90);
	By loadingIcon= By.cssSelector("div[class*='ui-hidden-container loader_block'][aria-live='polite']");
	public void wait_for_page_load() {				
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
	}
	
	public By inputFieldsWithName(String fieldName) {
		Log.info("create multiple xpaths using this method");
		return By.xpath("//label[contains(text(),'"+fieldName+"')]/../following-sibling::dd//input");
	}
	
	public By selectOneFieldsWithName(String fieldName) {
		//WebElement dropdown
		waitInSeconds(1);
		return By.xpath("//label[contains(text(),'"+fieldName+"')]/../following-sibling::dd//label[text()='Select One']");
	}
	
	public By selectValueFromDropDown(String fieldName,String value) {
		waitInSeconds(3);
		String id=element( By.xpath("//label[contains(text(),'"+fieldName+"')]/../following-sibling::dd//label[text()='Select One']")).getAttribute("id").replace("label", "panel");
		return By.xpath("//div[@id='"+id+"']//li[contains(text(),'"+value+"')]");
	}
	
	public By initiateEmployeeWithEmail(String email) {
		return By.xpath("//span[contains(text(),'"+email+"')]/../../td[last()]//a");
	}
	
	public By viewOnboardingStatusBtn(String email) {
		return By.xpath("//span[contains(text(),'"+email+"')]/../../td[2]//button");
	}
	
	public By tabHeading(String tab) {
		return By.xpath("//a[contains(text(),'"+tab+"')]");
	}
	
	public ProspectiveHiresPage click_on_add_prospective_hire_btn() {
		waitInSeconds(8);
		click(addProspectiveHireBtn);
		waitInSeconds(8);
		return me();
	}
	
	public ProspectiveHiresPage click_specified_tab(String tabHeader ) {
		click(tabHeading(tabHeader));
		return me();
	}
	
	public ProspectiveHiresPage enter_value_in_specified_field(String field,String value) {
		enterText(inputFieldsWithName(field), value);
		return me();
	}
		
	public ProspectiveHiresPage open_dob_calendar() {
		click(dobCalendar);
		return me();
	}
	
	public ProspectiveHiresPage select_year() {
		selectByValue(selectYear, "1990");
		Log.info("value selected" +"1990");
		return me();
	}
	
	public ProspectiveHiresPage click_any_date() {
		click(anyDateInCalendar);
		return me();
	}
	//td[@data-handler='selectDay']//a[contains(text(),'20')]
	public ProspectiveHiresPage open_expected_doj_calendar() {
		click(expectedDojCalendar);
		return me();
	}
	
	public ProspectiveHiresPage click_today_date() {
		click(todayDate);
		return me();
	}
	
	public ProspectiveHiresPage click_to_open_specified_dropdown(String field) {
		click(selectOneFieldsWithName(field));
		return me();
	}
	
	public ProspectiveHiresPage select_value_from_specified_dropdown(String field,String value) {
		By valueToSelect=selectValueFromDropDown(field,value);
		click(valueToSelect);
		waitInSeconds(1);
		return me();
	}
	
	
	public ProspectiveHiresPage select_blood_group_from_dropdown() {
		By genderValue=selectValueFromDropDown("Blood Group:","O+");
		click(genderValue);
		return me();
	}
	
	
	public ProspectiveHiresPage select_company_from_dropdown() {
		By companyValue=selectValueFromDropDown("Company:","PeopleStrong");
		click(companyValue);
		return me();
	}
		
	public ProspectiveHiresPage select_country_from_dropdown() {
		By countryValue=selectValueFromDropDown("Country:","India");
		click(countryValue);
		return me();
	}
	
	
	
	public ProspectiveHiresPage select_l1_manager_from_dropdown() {
		By l1ManagerValue=selectValueFromDropDown("L1 Manager:","Gurjeet");
		click(l1ManagerValue);
		return me();
	}
	
	public ProspectiveHiresPage select_hr_manager_from_dropdown() {
		By hrManagerValue=selectValueFromDropDown("HR Manager:","Amit Jain");
		click(hrManagerValue);
		return me();
	}
	
	
	public ProspectiveHiresPage select_sbu_from_dropdown() {
		By sbuValue=selectValueFromDropDown("SBU:","Technology");
		click(sbuValue);
		return me();
	}
	
	public ProspectiveHiresPage select_businessunit_from_dropdown() {
		By businessUnitValue=selectValueFromDropDown("Business Unit:","Alt");
		click(businessUnitValue);
		return me();
	}
	
	public ProspectiveHiresPage select_function_from_dropdown() {
		By functionValue=selectValueFromDropDown("Function:","Engineering");
		click(functionValue);
		return me();
	}
	
	public ProspectiveHiresPage select_subfunction_from_dropdown() {
		By subFunctionValue=selectValueFromDropDown("Sub-function:","QA");
		click(subFunctionValue);
		return me();
	}
	
	public ProspectiveHiresPage select_projectcode_from_dropdown() {
		By projectCodeValue=selectValueFromDropDown("Project Code:","Alt P");
		click(projectCodeValue);
		return me();
	}
	
	public ProspectiveHiresPage select_region_from_dropdown() {
		By regionValue=selectValueFromDropDown("Region:","North Region");
		click(regionValue);
		return me();
	}
	
	public ProspectiveHiresPage select_state_from_dropdown() {
		By stateValue=selectValueFromDropDown("State:","Haryana");
		click(stateValue);
		return me();
	}
	
	public ProspectiveHiresPage select_city_from_dropdown() {
		By cityValue=selectValueFromDropDown("City:","Gurgaon");
		click(cityValue);
		return me();
	}
	
	public ProspectiveHiresPage select_branch_from_dropdown() {
		By branchValue=selectValueFromDropDown("Branch:","Gurgaon Infocity");
		click(branchValue);
		return me();
	}
	
	public ProspectiveHiresPage click_on_submit_btn() {
		click(submitBtn);
		return me();
	}
	
	public ProspectiveHiresPage verify_submit_msg() {
		waitInSeconds(5);
		verifyElementTextContained(msgAfterSubmit, "Prospective hire has been successfully created. Now you can update additional details.");
		return me();
	}
	
	public ProspectiveHiresPage verify_submit_msg(String msg) {
		waitInSeconds(5);
		verifyElementTextContained(msgAfterSubmit,msg );
		return me();
	}
	
	public ProspectiveHiresPage click_initiate_btn(String email) {
		waitInSeconds(5);
		click(initiateEmployeeWithEmail(email));
		return me();
	}
	
	public ProspectiveHiresPage verify_msg() {
		waitInSeconds(15);
		verifyElementTextContained(onBoardingMsg, "Onboarding started successfully");
		return me();
	}
	
	public ProspectiveHiresPage open_onboarding_status(String email) {
		click(viewOnboardingStatusBtn(email));
		return me();
	}
	
	public ProspectiveHiresPage click_view_onboarding_status() {
		click(viewOnboardingStausLnk);
		return me();
	}
	public ProspectiveHiresPage click_edit_onboarding_employee() {
		click(editOnboardingEmployee);
		return me();
	}
	
	public ProspectiveHiresPage click_move_to_joining_state() {
		click(moveToJoiningState);
		return me();
	}
	
	public ProspectiveHiresPage click_yes_on_alert() {
		click(yesOnAlert);
		return me();
	}
	
	public ProspectiveHiresPage verify_state_change_msg() {
		verifyElementTextContained(stageChangedMsg, "Status updated successfully. Prospective Hire moved to next stage.");
		waitInSeconds(2);
		return me();
	}
	
	public ProspectiveHiresPage click_move_to_joined_state() {		
		click(joinedBtn);
		return me();
	}
	
	public ProspectiveHiresPage click_onboarding_complete_link() {		
		click(onboardingCompleteBtn);
		return me();
	}
	
	public ProspectiveHiresPage verify_error_msgs(String msg) {
		verifyElementTextContained(errorMsgs, msg);
	//	waitInSeconds(2);
		return me();
	}
	
	public ProspectiveHiresPage verify_error_msg_on_initiate() {
		verifyElementTextContained(errorOnInitiate,
				"Onboarding process could not be started. L1 Manager, HR Manager, Date Of Joining, Work Information, should not be null");
	//	waitInSeconds(2);
		return me();
	}
	
	public ProspectiveHiresPage click_disability_chk_box() {		
		click(disabilityChkBox);
		return me();
	}
	
	public ProspectiveHiresPage click_contact_details_tab() {		
		click(contactDetailsTab);
		return me();
	}
	
	public ProspectiveHiresPage click_emp_detail_tab() {		
		click(empDetailsTab);
		return me();
	}
	
	public ProspectiveHiresPage click_education_detail_tab() {		
		click(educationDetailsTab);
		return me();
	}
	
	public ProspectiveHiresPage click_save_btn() {		
		click(saveBtn);
		return me();
	}
	
	public ProspectiveHiresPage click_add_btn() {		
		click(addBtn);
		return me();
	}
	public ProspectiveHiresPage verify_msg(String msg) {
		waitInSeconds(5);
		verifyElementTextContained(msgAfterSave,msg );
		return me();
	}
	public ProspectiveHiresPage verify_error_msg(String msg) {
		waitInSeconds(5);
		verifyElementTextContained(approvalError,msg );
		return me();
	}
	
	
	// Get UI messages
	public String getUIErrorMsg(){
		return element(uiErrorMessage).getText();
	}
	
	public ProspectiveHiresPage click_add_more_btn() {
		click(addMoreBtn);
		return me();
	}

	public ProspectiveHiresPage click_accept_btn() {
		click(acceptBtn);
		return me();
	}
	
}
