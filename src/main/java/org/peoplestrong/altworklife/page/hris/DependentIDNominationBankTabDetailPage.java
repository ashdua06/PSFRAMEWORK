package org.peoplestrong.altworklife.page.hris;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.peoplestrong.altworklife.core.BaseClass;
import org.peoplestrong.altworklife.core.Log;

public class DependentIDNominationBankTabDetailPage extends BaseClass {
	
	//UI Locators
	By errorMsg=By.xpath("//form[@id='messagesForm']//ul");
	By msgAfterSave=By.xpath("//div[contains(@id,'message')]//span[@class='ui-messages-info-summary']");
	By saveBtn=By.xpath("//span[contains(text(),'Save')]");
	By addMoreBtn=By.xpath("//a[text()='Add More']");
	By anyDateInCalendar=By.xpath("//td[@data-handler='selectDay']//a[contains(text(),'20')]");
	By dobCalendar=By.xpath("//label[contains(text(),'DOB')]/../../dd//button/span[1]");
	By selectYear=By.xpath("//select[@class='ui-datepicker-year']");//1990
	By loadingIcon= By.cssSelector("div[class*='ui-hidden-container loader_block'][aria-live='polite']");
	By IssueDateCalendar=By.xpath("//label[contains(text(),'Issue Date:')]/../../dd//button/span[1]");
	By expiryDateCalendar=By.xpath("//label[contains(text(),'Expiry Date:')]/../../dd//button/span[1]");
	WebDriverWait wait=new WebDriverWait(driver, 90);
	
	//returns reference of current page
	public  DependentIDNominationBankTabDetailPage me() {	
		wait_for_page_load();
		return this;
	}
	//wait for page load	
	public void wait_for_page_load() {				
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
	}
	
	public By inputFieldsWithName(String fieldName) {
		Log.info("Locator of field with label :- " +fieldName);
		return By.xpath("//label[contains(text(),'"+fieldName+"')]/../following-sibling::dd//input");
	}
	
	public By selectOneFieldsWithName(String fieldName) {
		//WebElement dropdown
		waitInSeconds(1);
		Log.info("Open dropdown  of field with label :- " +fieldName);
		return By.xpath("//label[contains(text(),'"+fieldName+"')]/../following-sibling::dd//label[text()='Select One']");
	}
	
	public By selectValueFromDropDown(String fieldName,String value) {
		waitInSeconds(3);
		Log.info("select value from field "+fieldName+"and value :-  " +value);
		String id=element( By.xpath("//label[contains(text(),'"+fieldName+"')]/../following-sibling::dd//label[text()='Select One']")).getAttribute("id").replace("label", "panel");
		return By.xpath("//div[@id='"+id+"']//li[contains(text(),'"+value+"')]");
	}
	
	public By checkBoxWithName(String fieldName) {
		Log.info("returns reference of checkbox with label:--"  + fieldName);
		return By.xpath("//label[contains(text(),'"+fieldName+"')]/../following-sibling::dd//span[contains(@class,'chkbox')]");
	}
	
	public DependentIDNominationBankTabDetailPage click_save_btn() {		
		click(saveBtn);
		return me();
	}
	public DependentIDNominationBankTabDetailPage enter_value_in_specified_field(String field,String value) {
		enterText(inputFieldsWithName(field), value);
		return me();
	}
	
	public DependentIDNominationBankTabDetailPage click_to_open_specified_dropdown(String field) {
		click(selectOneFieldsWithName(field));
		return me();
	}
	
	public DependentIDNominationBankTabDetailPage select_value_from_specified_dropdown(String field,String value) {
		By valueToSelect=selectValueFromDropDown(field,value);
		click(valueToSelect);
		return me();
	}
	
	public DependentIDNominationBankTabDetailPage click_chkbox_of_specified_field(String field) {
		click(checkBoxWithName(field));
		return me();
	}
	
	public DependentIDNominationBankTabDetailPage verify_error_msg(String msg) {
		waitInSeconds(2);
		verifyElementTextContained(errorMsg,msg );
		return me();
	}
	
	public DependentIDNominationBankTabDetailPage verify_msg(String msg) {
		waitInSeconds(2);
		verifyElementTextContained(msgAfterSave,msg );
		return me();
	}
	
	public DependentIDNominationBankTabDetailPage click_add_more_btn() {
		click(addMoreBtn);
		return me();
	}
	
	public DependentIDNominationBankTabDetailPage select_dob_calendar() {
		click(dobCalendar);
		select_year("1970");
		click_any_date();
		return me();
	}
	
	public DependentIDNominationBankTabDetailPage select_issue_date() {
		click(IssueDateCalendar);
		select_year("1999");
		click_any_date();
		return me();
	}
	
	public DependentIDNominationBankTabDetailPage select_expiry_date() {
		click(expiryDateCalendar);
		select_year("2019");
		click_any_date();
		return me();
	}
	public DependentIDNominationBankTabDetailPage select_year(String year) {
		selectByValue(selectYear, year);
		Log.info("value selected" +year);
		return me();
	}
	
	public DependentIDNominationBankTabDetailPage click_any_date() {
		click(anyDateInCalendar);
		return me();
	}
	
}
