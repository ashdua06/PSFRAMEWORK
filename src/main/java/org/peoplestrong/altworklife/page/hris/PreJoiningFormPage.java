package org.peoplestrong.altworklife.page.hris;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.peoplestrong.altworklife.core.BaseClass;
import org.peoplestrong.altworklife.core.Log;

public class PreJoiningFormPage extends BaseClass {
	
	By aadharCard=inputFieldsWithName("Aadhar Card:");
	By panNumber=inputFieldsWithName("PAN Number:");
	By basicDetailsSubmitMsg=By.xpath("//span[@class='ui-messages-info-summary']");//Basic Details saved successfully
	By contactDetailsTab=By.xpath("//a[contains(text(),'Contact Details')]");
	By emergencyContactAdd=By.xpath("//*[contains(text(),'Emergency Contact')]/following-sibling::a");
	By contactName=inputFieldsWithName("Contact Name:");
	By mobileNumber=inputFieldsWithName("Mobile Number");
	By saveBtn=By.xpath("//span[contains(text(),'Save')]");
	By contactRelationship=inputFieldsWithName("Contact Relationship");	
	By saveEmergencyContact=By.xpath("//form[@id='addEmergencyContact']//*[text()='Save']");
	By addedContact=By.xpath("//form[@id='emergencyContactList']//tbody/tr/td[1]");//first name verification
	By fresherCheckBox=By.xpath("//h1[contains(text(),'Fresher')]/..//span");
	By employeementDetails=By.xpath("//a[contains(text(),'Employment Details')]");
	By documentDetail=By.xpath("//a[contains(text(),'Document Details')]");
	By tenthMarkSheet=uploadFieldOfDocument("10th Marksheet");
	By twelthMarkSheet=uploadFieldOfDocument("12th Marksheet");
	By idProof=uploadFieldOfDocument("ID Proof");
	By addressProof=uploadFieldOfDocument("Address Proof");
	By finalizeBtn=By.xpath("//a[@id='rightArrow']/../..//a[text()='Finalize']");
	By declarationCheckBox=By.xpath("//div[contains(@id,'declaration')]//span");
	By submitBtn=By.xpath("//a[text()='Submit']");
	By confirmSubmit=By.xpath("//div[contains(@class,'dialog-content')]//*[text()='Yes']");
	By startPreJoining=By.xpath("//label[contains(text(),'Pre joining form')]/../../td[3]/a");
	By editAddress=By.xpath("//form[@id='addressList']//tbody/tr/td[last()]/div[1]");
	By deleteAddress=By.xpath("//form[@id='addressList']//tbody/tr/td[last()]//div[contains(@class,'trash')]");
	By emergencyEditAddress=By.xpath("//form[@id='emergencyContactList']//tbody/tr//td[last()]/div[1]");
	By emergencyDeleteAddress=By.xpath("//form[@id='emergencyContactList']//tbody/tr//td[last()]//div[contains(@class,'trash')]");
	By editDependantDetail=By.xpath("//form[@id='dependentsForm']//tbody/tr[1]/td[last()]/div[1]");
	By deleteDependentInNomination=By.xpath("//form[@id='dependentsForm']//tbody/tr[2]/td[last()]//div[contains(@class,'trash')]");
	By editNominationDetail=By.xpath("//form[@id='nominationForm']//tbody/tr[1]/td[last()]/div[1]");
	By deleteNomination=By.xpath("//form[@id='nominationForm']//tbody/tr/td[last()]//div[contains(@class,'trash')]");
	By editBankDetail=By.xpath("//form[@id='bankDetailForm']//tbody/tr[1]/td[last()]/div[1]");
	By deleteBankDetail=By.xpath("//form[@id='bankDetailForm']//tbody/tr/td[last()]//div[contains(@class,'trash')]");
	By editIdDetail=By.xpath("//form[@id='idList']//tbody/tr[1]/td[last()]/div[1]");
	By deleteIdDetail=By.xpath("//form[@id='idList']//tbody/tr/td[last()]//div[contains(@class,'trash')]");
	By yesOnConfirmPopUp=By.xpath("//span[contains(text(),'Yes')]");
	By employmentDetailsTab = By.xpath("//a[text()='Employment Details']");
	By educationsDetailsTab =  By.xpath("//a[text()='Education Details']");
	By addMoreLink = By.xpath("//a[text()='Add More']");
	By uiErrorMessage = By.cssSelector("div[class='ui-messages ui-widget']");
	By rightArrow=By.xpath("//a[@id='rightArrow']/span");
	// String to update as locator 
	String epmDetailsRowPattern= "div[id*='employmentForm'] table tbody tr:nth-of-type(subs_pattern)";
	String docPath="C:\\Users\\rahul.gulati\\Downloads\\DOU.pdf";
	String rowEditIcon = "div[id*='employmentForm'] table tbody tr:nth-of-type(subs_pattern)"+" td div[aria-label='Toggle Row']";
	String onBoardingEmpDetailFieldPatetrn = "//label[text()='subs_pattern']/ancestor::dt[1]/following::dd[1]//input[1]";
	
	public  PreJoiningFormPage me() {		
		wait_for_page_load();
		return this;
	}
	WebDriverWait wait=new WebDriverWait(driver, 90);
	By loadingIcon= By.cssSelector("div[class*='ui-hidden-container loader_block'][aria-live='polite']");
	public void wait_for_page_load() {				
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
	}
	public By inputFieldsWithName(String fieldName) {
		return By.xpath("//label[contains(text(),'"+fieldName+"')]/../following-sibling::dd//input");
	}
	
	public By uploadFieldOfDocument(String documentName) {
		return By.xpath("//label[contains(text(),'"+documentName+"')]/../../td[4]/a");
	}
	
	public By selected_value_dropdown(String fieldName) {
		return By.xpath("//label[contains(text(),'"+fieldName+"')]/../following-sibling::dd//label");
	}
	
	public By address_with_fields(String contactType , String address,String pinCode,String phoneNumber) {
		return By.xpath("//form[@id='addressList']//tbody/tr/td//label[contains(text(),'"+contactType+"')]/../../..//td[contains(text(),'"+address+"')]/..//td[contains(text(),'"+pinCode+"')]/..//td[contains(text(),'"+phoneNumber+"')]");
	}
	
	public By emergency_address_with_fields(String contactName , String contactRelation,String mobile,String contactAddress) {
		return By.xpath("//form[@id='emergencyContactList']//tbody/tr//td[contains(text(),'"+contactName+"')]/..//td[contains(text(),'"+contactRelation+"')]/..//td[contains(text(),'"+mobile+"')]/..//td[contains(text(),'"+contactAddress+"')]");
	}
	
	public By dependant_details_with_fields(String Name , String Relation,String IsDependent,String healthPolicy) {
		return By.xpath("//form[@id='dependentsForm']//tbody/tr//td[contains(text(),'"+Name+"')]/..//td[contains(text(),'"+Relation+"')]/..//td[contains(text(),'"+IsDependent+"')]/..//td[contains(text(),'"+healthPolicy+"')]");
	}
	
	public By nomination_details_with_fields(String dependant,String percentage,String nomineeType) {
		return By.xpath("//form[@id='nominationForm']//tbody/tr//td[contains(text(),'"+dependant+"')]/..//td[contains(text(),'"+percentage+"')]/..//td[contains(text(),'"+nomineeType+"')]");
	}
	
	public By bank_details_with_fields(String bankName,String accNumber,String accType,String salaryAcc) {
		return By.xpath("//form[@id='bankDetailForm']//tbody/tr//td[contains(text(),'"+bankName+"')]/..//td[contains(text(),'"+accNumber+"')]/..//td[contains(text(),'"+accType+"')]/..//td[contains(text(),'"+salaryAcc+"')]");
	}
	
	public By id_details_with_fields(String idType,String idNumber,String issuePlace) {
		return By.xpath("//form[@id='idList']//tbody/tr//td[contains(text(),'"+idType+"')]/..//td[contains(text(),'"+idNumber+"')]/..//td[contains(text(),'"+issuePlace+"')]");
	}
	
	public By tabHeading(String tab) {
		return By.xpath("//a[contains(text(),'"+tab+"')]");
	}
	
	//pre joining form start btn on pe joining page
	public PreJoiningFormPage click_start_pre_joining() {
		waitInSeconds(2);
		click(startPreJoining);
		waitInSeconds(5);
		return me();
	}	
	
	public PreJoiningFormPage click_specified_tab(String tabHeader ) {
		Log.info("opening tab name:- "  +tabHeader);
		click(tabHeading(tabHeader));
		return me();
	}
	
	public PreJoiningFormPage enter_aadhar_number(String value) {
		Log.info("enter aadhar value 2231"+value);
		enterText(aadharCard, value);//2231+random number 8 digit
		return me();
	}
	
	public PreJoiningFormPage enter_pan_number(String value) {
		Log.info("enter pan number :--" +value);
		enterText(panNumber, value);
		return me();
	}
	
	public PreJoiningFormPage click_save_basic_details() {
		waitInSeconds(2);
		click(saveBtn);		
		return me();
	}
	
	public PreJoiningFormPage verify_basic_details_saved_succesfully() {
		verifyElementText(basicDetailsSubmitMsg, "Basic Details saved successfully");
		return me();
	}
	
	public PreJoiningFormPage click_contact_details_tab() {
		click(contactDetailsTab);
		return me();
	}
	
	public PreJoiningFormPage click_add_emergency_contact() {
		click(emergencyContactAdd);
		waitInSeconds(4);
		return me();
	}
	
	public PreJoiningFormPage enter_contact_name(String value) {
		enterText(contactName, value);
		return me();
	}
	
	public PreJoiningFormPage enter_mobile_number(String value) {
		enterText(mobileNumber, value);
		return me();
	}
	
	public PreJoiningFormPage enter_contact_relationship(String value) {
		enterText(contactRelationship, value);
		return me();
	}
	
	public PreJoiningFormPage click_emergency_save_btn() {
		click(saveEmergencyContact);
		waitInSeconds(2);
		return me();
	}
	
	public PreJoiningFormPage click_employeement_details() {
		click(employeementDetails);
		return me();
	}
	
	public PreJoiningFormPage select_fresher() {
		click(fresherCheckBox);
		return me();
	}
	
	public PreJoiningFormPage click_document_details() {
		click(documentDetail);
		return me();
	}
	
	public PreJoiningFormPage upload_10th_doc() {
		waitInSeconds(2);
		click(tenthMarkSheet);
		upload_doc();
		return me();
	}
	
	public PreJoiningFormPage upload_12th_doc() {
		click(twelthMarkSheet);
		upload_doc();
		return me();
	}
	
	public PreJoiningFormPage upload_id_doc() {
		click(idProof);
		upload_doc();
		return me();
	}
	
	public PreJoiningFormPage upload_address_doc() {
		click(addressProof);
		upload_doc();
		return me();
	}
	
	public PreJoiningFormPage upload_doc() {
		uploadFile(docPath);
		return me();
	}
	
	public PreJoiningFormPage click_finalize_btn() {
		waitInSeconds(10);
		click(finalizeBtn);
		return me();
	}
	
	public PreJoiningFormPage click_declaration_chk_box() {
		click(declarationCheckBox);
		return me();
	}
	
	public PreJoiningFormPage click_submit_btn() {
		click(submitBtn);
		return me();
	}
	
	public PreJoiningFormPage click_cnfrm_submit() {
		click(confirmSubmit);
		return me();
	}
	
	//label[contains(text(),'Citizenship:')]/../..//select//option[@selected='selected']
	public PreJoiningFormPage verify_selected_value_in_dropdown(String dropdown, String value) {
		verifyElementText(selected_value_dropdown(dropdown), value);	
		return me();
	}
	
	public PreJoiningFormPage verify_value_in_input_field(String field,String expectedValue) {
		verifyElementAttribute(inputFieldsWithName(field), "value" , expectedValue);	
		return me();
	}
	
	public PreJoiningFormPage verify_address_present(String contactType,String address,String pinCode,String phoneNumber) {
		verify(ExpectedConditions.visibilityOfElementLocated(address_with_fields(contactType, address, pinCode, phoneNumber)));	
		return me();
	}
	
	public PreJoiningFormPage verify_emergency_address_present(String contactName,String contactRelation,String mobile,String contactAddress) {
		verify(ExpectedConditions.visibilityOfElementLocated(emergency_address_with_fields(contactName, contactRelation, mobile, contactAddress)));	
		return me();
	}
	
	public PreJoiningFormPage click_edit_address_btn() {
		click(editAddress);
		return me();
	}
	
	public PreJoiningFormPage click_delete_address_btn() {
		click(deleteAddress);
		return me();
	}
	
	public PreJoiningFormPage click_edit_emergency_address_btn() {
		click(emergencyEditAddress);
		return me();
	}
	
	public PreJoiningFormPage click_delete_emergency_address_btn() {
		click(emergencyDeleteAddress);
		return me();
	}
	
	//edit first record
	public PreJoiningFormPage click_edit_dependent_detail_btn() {
		click(editDependantDetail);
		return me();
	}
	
	//delete second record
	public PreJoiningFormPage click_delete_dependent_in_nomination_btn() {
		click(deleteDependentInNomination);
		return me();
	}
	
	//edit nomination first record
	public PreJoiningFormPage click_edit_nomination_detail_btn() {
		click(editNominationDetail);
		return me();
	}
	
	//delete first record
	public PreJoiningFormPage click_delete_nomination_btn() {
		click(deleteNomination);
		return me();
	}
	
	//edit first record
	public PreJoiningFormPage click_edit_bank_detail_btn() {
		click(editBankDetail);
		return me();
	}
	
	//delete first record
	public PreJoiningFormPage click_delete_bank_detail_btn() {
		click(deleteBankDetail);
		return me();
	}
	
	//edit first record
	public PreJoiningFormPage click_edit_id_detail_btn() {
		click(editIdDetail);
		return me();
	}
	
	//delete first record
	public PreJoiningFormPage click_delete_id_detail_btn() {
		click(deleteIdDetail);
		return me();
	}
	
	//yes btn on confirm delete popup
	public PreJoiningFormPage click_yes_on_popup() {
		click(yesOnConfirmPopUp);
		waitInSeconds(3);
		return me();
	}
	
	//verify msg appear after we perform some task
	public PreJoiningFormPage verify_msg(String msg) {
		waitInSeconds(2);
		verifyElementTextContained(basicDetailsSubmitMsg,msg );
		return me();
	}
	
	public PreJoiningFormPage clickEmpDetailTab(){
		click(employmentDetailsTab);
		return me();
	}
	
	public PreJoiningFormPage clickEducatiDetailTab(){
		click(educationsDetailsTab);
		return me();
	}
	
	//verify visibililty of a particular record
	public PreJoiningFormPage enter_value_in_specified_field(String field,String value) {
		enterText(inputFieldsWithName(field), value);
		return me();
	}

	//verify visibililty of a particular record
	public PreJoiningFormPage verify_dependant_detail_present(String Name,String Relation,String isDependent,String healthPolicy) {
		verify(ExpectedConditions.visibilityOfElementLocated(dependant_details_with_fields(Name, Relation, isDependent, healthPolicy)));	
		return me();
	}
	
	//verify visibililty of a particular record
	public PreJoiningFormPage verify_nomination_detail_present(String dependant,String percentage,String nomineeType) {
		verify(ExpectedConditions.visibilityOfElementLocated(nomination_details_with_fields(dependant, percentage, nomineeType)));	
		return me();
	}
	
	//verify visibililty of a particular record
	public PreJoiningFormPage verify_bank_detail_present(String bankName,String accNumber,String accType,String salaryAcc) {
		verify(ExpectedConditions.visibilityOfElementLocated(bank_details_with_fields(bankName, accNumber, accType, salaryAcc)));	
		return me();
	}
	
	public PreJoiningFormPage verify_id_detail_present(String idType,String idNumber,String issuePlace) {
		verify(ExpectedConditions.visibilityOfElementLocated(id_details_with_fields(idType, idNumber, issuePlace)));	
		return me();
	}
	
	/**
	 * Below methods are for verification/operation on employment details tab of on boarding portal
	 * 
	**/
	// click on edit icon against the emp detail row
	public PreJoiningFormPage clickEditIcon(int rowNum){
		element(By.cssSelector(rowEditIcon.replace("subs_pattern", String.valueOf(rowNum)))).click();
		return me();
	}
	
	public PreJoiningFormPage click_right_arrow(){
		click(rightArrow);
		return me();
	}
	
	
	//return the row from emp detail page.
	// Pass - @rowNum as integer to get the row data (1 for first row, 2 for second row
	public String getEmpRowDetails(int rowNum){
		return element(By.cssSelector(epmDetailsRowPattern.replace("subs_pattern", String.valueOf(rowNum)))).getText();
	}
	
	// Set project Name
	public PreJoiningFormPage setprojectName(String name){
		element(By.xpath(onBoardingEmpDetailFieldPatetrn.replace("subs_pattern", "Project Name"))).clear();
		element(By.xpath(onBoardingEmpDetailFieldPatetrn.replace("subs_pattern", "Project Name"))).sendKeys(name);
		return me();
	}

	// Get UI messages available on UI(Emp details page)
	public String getUIMessage(){
		return element(basicDetailsSubmitMsg).getText();
	}

	public PreJoiningFormPage clickAddMoreLink(){
		click(addMoreLink);
		return me();
	}

	// UI Error message
	public String getUIErrorMsg(){
		return element(uiErrorMessage).getText();
	}
}

