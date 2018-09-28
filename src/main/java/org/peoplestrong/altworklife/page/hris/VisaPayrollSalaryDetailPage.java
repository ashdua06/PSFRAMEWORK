package org.peoplestrong.altworklife.page.hris;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.peoplestrong.altworklife.core.BaseClass;
import org.peoplestrong.altworklife.core.Log;

public class VisaPayrollSalaryDetailPage extends BaseClass {

	By loadingIcon= By.cssSelector("div[class*='ui-hidden-container loader_block'][aria-live='polite']");
	By visaIssueDate=By.xpath("//label[contains(text(),'VISA ISSUE DATE')]/../../dd//button/span[1]");
	By visaExpiryDate=By.xpath("//label[contains(text(),'VISA EXPIRY DATE')]/../../dd//button/span[1]");
	By anyDateInCalendar=By.xpath("//td[@data-handler='selectDay']//a[contains(text(),'20')]");
	//By dobCalendar=By.xpath("//label[contains(text(),'DOB')]/../../dd//button/span[1]");
	By selectYear=By.xpath("//select[@class='ui-datepicker-year']");//1990
	By errorMsg=By.xpath("//form[@id='messagesForm']//ul");
	By saveBtn=By.xpath("//span[contains(text(),'Save')]");
	
	WebDriverWait wait=new WebDriverWait(driver, 90);
	
	//returns reference of current page
	public  VisaPayrollSalaryDetailPage me() {	
		wait_for_page_load();
		return this;
	}
	//wait for page load	
	public void wait_for_page_load() {				
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
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
	
	public VisaPayrollSalaryDetailPage click_save_btn() {		
		click(saveBtn);
		return me();
	}
	
	public VisaPayrollSalaryDetailPage click_to_open_specified_dropdown(String field) {
		click(selectOneFieldsWithName(field));
		return me();
	}
	
	public VisaPayrollSalaryDetailPage select_value_from_specified_dropdown(String field,String value) {
		By valueToSelect=selectValueFromDropDown(field,value);
		click(valueToSelect);
		return me();
	}
	
	public VisaPayrollSalaryDetailPage select_visa_issue_date() {
		click(visaIssueDate);
		select_year("2017");
		click_any_date();
		return me();
	}
	public VisaPayrollSalaryDetailPage select_visa_expiry_date() {
		click(visaExpiryDate);
		select_year("2019");
		click_any_date();
		return me();
	}
	
	public VisaPayrollSalaryDetailPage select_year(String year) {
		selectByValue(selectYear, year);
		Log.info("value selected" +year);
		return me();
	}
	
	public VisaPayrollSalaryDetailPage click_any_date() {
		click(anyDateInCalendar);
		return me();
	}

	public VisaPayrollSalaryDetailPage verify_error_msg(String msg) {
		waitInSeconds(2);
		verifyElementTextContained(errorMsg,msg );
		return me();
	}
	
}
