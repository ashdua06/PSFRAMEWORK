package org.peoplestrong.altworklife.page.hris;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.peoplestrong.altworklife.core.BaseClass;

public class OnboardingStatusPage extends BaseClass{
	
	By enterEmployeeCode=By.xpath("//input[@id='employeeCode']");
	By commentEmployeeCode = By.xpath("//form[@id='generateEcnForm']//tr[2]//input");
	By submitBtn=By.xpath("//form[@id='generateEcnForm']//*[text()='Submit']");
	By enterEmail=By.xpath("//form[@id='generateEmailForm']//tr[1]//input");
	By enterComment=By.xpath("//form[@id='generateEmailForm']//tr[2]//input");
	By submitBtnEmail=By.xpath("//form[@id='generateEmailForm']//*[text()='Submit']");
	By selectFirstBuddy=By.xpath("//tbody[contains(@id,'buddyForm')]/tr[1]//span");
	By commentBuddy=By.xpath("//textarea[contains(@id,'buddyForm')]");
	By submitBuddyForm=By.xpath("//*[contains(@id,'buddyForm')]//*[text()='Submit']");
	By commentBox=By.xpath("//textarea[@id='comment']");
	By approveBtn=By.xpath("//span[contains(text(),'Approve')]");
	By okBtnOnConfirmPopUp=By.xpath("//span[contains(text(),'OK')]");
	By assignBtn=By.xpath("//span[text()='Assign']");
	By yesBtnOnConfirmPopUP=By.xpath("//span[contains(text(),'Yes')]");
	By selectDropDownOfferLetter=By.xpath("//label[text()='Select One']");
	By previewBtn=By.xpath("//span[contains(text(),'Preview')]");
	By generateBtn=By.xpath("//span[text()='Generate']");
	
	
	public By inputFieldsWithName(String task) {
		return By.xpath("//td[contains(text(),'"+task+"')]/..//textarea");
	}
	
	public By checkBoxTask(String task) {
		return By.xpath("//td[contains(text(),'"+task+"')]/..//td[last()]//span");
	}
	
	public By createTask(String task) {
		return By.xpath("//td[contains(text(),'"+task+"')]/..//td[last()]//a");
	}
	
	public By openEmployee(String firstName) {
		return By.xpath("//label[contains(text(),'"+firstName+"')]/../..//a");
	}
	//label[contains(text(),'tesstt tsest')]/../..//*[contains(text(),'Post')]
	public OnboardingStatusPage me() {
		wait_for_page_load();
		return this;
	}
	//WebDriverWait wait=new WebDriverWait(driver, 90);
	By loadingIcon= By.cssSelector("div[class*='ui-hidden-container loader_block'][aria-live='polite']");
	public void wait_for_page_load() {				
		//wait.until();
	verify(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
	}
	public OnboardingStatusPage enter_cmnt_in_specified_task(String task, String comment) {
		waitInSeconds(5);
		enterText(inputFieldsWithName(task), comment);
		return  me();
	}
	
	public OnboardingStatusPage click_chk_box_specified_task(String task) {
		click(checkBoxTask(task));
		//verify(ExpectedConditions.visibilityOfElementLocated(loadingIcon));
		waitInSeconds(15);
		return  me();
	}
	
	public OnboardingStatusPage click_add_specified_task(String task) {
		click(createTask(task));
		return  me();
	}
	
	public OnboardingStatusPage enter_employee_code(String  code) {
		enterText(enterEmployeeCode ,code);
		return  me();
	}
	
	public OnboardingStatusPage enter_employee_comment(String comment) {
		enterText(commentEmployeeCode ,comment);
		return  me();
	}
	
	public OnboardingStatusPage click_submit_btn() {
		click(submitBtn);
		return  me();
	}
	
	public OnboardingStatusPage enter_employee_email(String  email) {
		enterText(enterEmail ,email);
		return  me();
	}
	
	public OnboardingStatusPage enter_employee_comment_email(String comment) {
		enterText(enterComment ,comment);
		return  me();
	}
	
	public OnboardingStatusPage click_submit_btn_email() {
		click(submitBtnEmail);
		return  me();
	}
	
	public OnboardingStatusPage open_employee_in_onboarding(String firstName) {
		click(openEmployee(firstName));
		return  me();
	}
	
	public OnboardingStatusPage select_first_buddy() {
		click(selectFirstBuddy);
		return  me();
	}
	
	public OnboardingStatusPage enter_employee_comment_buddy(String comment) {
		enterText(commentBuddy ,comment);
		return  me();
	}
	
	public OnboardingStatusPage submit_buddy_form() {
		click(submitBuddyForm);
		waitInSeconds(8);
		return  me();
	}
	
	public OnboardingStatusPage verify_text_and_open_stage(String firstName,String stage) {
		verifyElementTextContained(openEmployee(firstName), stage);
		click(openEmployee(firstName));
		return  me();
	}
	
	public OnboardingStatusPage enter_comment(String comment1) {
		enterText(commentBox ,comment1);
		return  me();
	}
	
	public OnboardingStatusPage click_approve_btn() {
		click(approveBtn);
		return  me();
	}
	
	public OnboardingStatusPage click_ok_btn() {
		click(okBtnOnConfirmPopUp);
		return  me();
	}
	
	public OnboardingStatusPage click_assign_btn() {
		click(assignBtn);
		return  me();
	}
	
	public OnboardingStatusPage click_yes_btn() {
		click(yesBtnOnConfirmPopUP);
		return  me();
	}
	
	public OnboardingStatusPage open_select_letter_drop_down() {
		click(selectDropDownOfferLetter);
		return  me();
	}
	
	public OnboardingStatusPage select_offer_letter() {
		open_select_letter_drop_down();
		click(By.xpath("//li[text()='Address Proof']"));
		return  me();
	}
	
	public OnboardingStatusPage clilck_preview_btn() {
		click(previewBtn);
		return  me();
	}
	
	public OnboardingStatusPage gotoNewWindowVerifyUrlAndSwitchBack() {
		switchToSecondaryWindow();
		verifyUrl(".pdf");
		closeCurrentWindow();
		switchBackToPrimaryWindow();
		return  me();
	}
	
	public OnboardingStatusPage click_generate_btn() {
		click(generateBtn);
		return  me();
	}
	
	
}
