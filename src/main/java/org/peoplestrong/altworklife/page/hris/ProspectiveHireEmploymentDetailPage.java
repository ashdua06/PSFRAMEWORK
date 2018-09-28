package org.peoplestrong.altworklife.page.hris;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.peoplestrong.altworklife.core.BaseClass;

public class ProspectiveHireEmploymentDetailPage extends BaseClass {
	// Labels
	String companyNameLabel = "Company Name:";
	String employeeCodeLabel = "Employee Code:";
	String titleLabel = "Title:";
	String projectNameLabel = "Project Name:";
	String roleLabel = "Role:";
	String locationLabel = "Location:";
	String salaryLabel = "Salary:";
	String empTypeDropdownLabel="Employment Type:";
	String fullPartTimeLabel = "Full Part Time:";
	String empPFAccountNumberLabel = "PF ACCOUNT:";
	
	//Locators
	By loadingIcon = By
			.cssSelector("div[class*='ui-hidden-container loader_block'][aria-live='polite']");
	By fresherCheckbox = By
			.cssSelector("span[class='FL'] div[class*='chkbox'] span");
	By saveButton = By.xpath("//span[text()='Save']");
	By cancelButton = By.xpath("//span[text()='Cancel']");
	By errorMessageForm = By.cssSelector("form[id='messagesForm']");
	By messageForm = By.cssSelector("form[id='messagesForm']");
	By startDateCalendarIcon = By.xpath("//label[text()='Start Date:']/ancestor::dl[1]/dd[1]//button");
	By endDateCalendarIcon = By.xpath("//label[text()='End Date:']/ancestor::dl[1]/dd[1]//button");
	// Pattern Substitute at run time
	String prospectiveHireEmpDetailFieldPatetrn = "//label[text()='subs_pattern']/ancestor::dt[1]/following::dd[1]//input[1]";
	String companyNamePattern = "ul li[data-item-label='subs_pattern']";
	String dropDownValuePattern = "ul li[data-label='subs_pattern']";
	String dropDownPattern = "//label[text()='subs_pattern']/ancestor::dt[1]/following::dd[1]//div[contains(@class,'selectonemenu-trigger')]";
	
	WebDriverWait wait = new WebDriverWait(driver, 90);

	public ProspectiveHireEmploymentDetailPage me() {
		wait_for_page_load();
		return this;
	}

	// wait for loading icon to disappear from page before scripts take action
	// on UI
	public void wait_for_page_load() {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
	}

	// Set company Name of the Employemnt Detail page of Prospective Hire page
	public ProspectiveHireEmploymentDetailPage setCompanyName(String Name) {
		element(
				By.xpath(prospectiveHireEmpDetailFieldPatetrn.replace(
						"subs_pattern", companyNameLabel))).sendKeys(Name);
				wait_for_page_load();
				element(By.cssSelector(companyNamePattern.replace("subs_pattern", Name))).click();
		return me();
	}

	// Set Employee code of the Employemnt Detail page of Prospective Hire page
	public ProspectiveHireEmploymentDetailPage setEmpCode(String value) {
		element(
				By.xpath(prospectiveHireEmpDetailFieldPatetrn.replace(
						"subs_pattern", employeeCodeLabel))).clear();
		element(
				By.xpath(prospectiveHireEmpDetailFieldPatetrn.replace(
						"subs_pattern", employeeCodeLabel))).sendKeys(value);
		return me();
	}

	// Set title of the Employment Detail page of Prospective Hire page
	public ProspectiveHireEmploymentDetailPage setTitle(String value) {
		element(
				By.xpath(prospectiveHireEmpDetailFieldPatetrn.replace(
						"subs_pattern", titleLabel))).sendKeys(value);
		return me();
	}

	// Set project name of the Employemnt Detail page of Prospective Hire page
	public ProspectiveHireEmploymentDetailPage setProjectName(String value) {
		element(
				By.xpath(prospectiveHireEmpDetailFieldPatetrn.replace(
						"subs_pattern", projectNameLabel))).sendKeys(value);
		return me();
	}

	// Set role name of the Employemnt Detail page of Prospective Hire page
	public ProspectiveHireEmploymentDetailPage setRole(String value) {
		element(
				By.xpath(prospectiveHireEmpDetailFieldPatetrn.replace(
						"subs_pattern", roleLabel))).sendKeys(value);
		return me();
	}

	// Set location name of the Employemnt Detail page of Prospective Hire page
	public ProspectiveHireEmploymentDetailPage setLocation(String value) {
		element(
				By.xpath(prospectiveHireEmpDetailFieldPatetrn.replace(
						"subs_pattern", locationLabel))).sendKeys(value);
		return me();
	}

	// Set salary name of the Employment Detail page of Prospective Hire page
	public ProspectiveHireEmploymentDetailPage setSalary(String value) {
		element(
				By.xpath(prospectiveHireEmpDetailFieldPatetrn.replace(
						"subs_pattern", salaryLabel))).sendKeys(value);
		return me();
	}

	// check fresher checkbox
	public ProspectiveHireEmploymentDetailPage checkFresherCheckbox() {
		element(fresherCheckbox).click();
		return me();
	}

	// select Start Date
	public ProspectiveHireEmploymentDetailPage selectEmpDetailStartDate(
			int Date, int MnthNum, String Year) {
		click(startDateCalendarIcon);
		
		selectDateFromCalendar(Date, MnthNum, Year);
		return me();
	}

	// select End Date
	public ProspectiveHireEmploymentDetailPage selectEmpDetailEndDate(int Date,
			int MnthNum, String Year) {
		click(endDateCalendarIcon);
		selectDateFromCalendar(Date, MnthNum, Year);
		return me();
	}
	// set PF Account number
	public ProspectiveHireEmploymentDetailPage setEmpPFAccountNumber(String pfAccountNum){
		element(
				By.xpath(empPFAccountNumberLabel.replace(
						"subs_pattern", salaryLabel))).sendKeys(pfAccountNum);
		return me();
	}
		
	// select value from dropdown
	public ProspectiveHireEmploymentDetailPage selectEmpType(String emp_type){
		click(By.xpath(dropDownPattern.replace("subs_pattern", empTypeDropdownLabel)));
		click(By.cssSelector(dropDownValuePattern.replace("subs_pattern", emp_type)));
		return me();
	}

	// select value from dropdown
		public ProspectiveHireEmploymentDetailPage selectFullPartTimeValue(String value){
			click(By.xpath(dropDownPattern.replace("subs_pattern", fullPartTimeLabel)));
			click(By.cssSelector(dropDownValuePattern.replace("subs_pattern", value)));
			return me();
		}

	// click save button
		public ProspectiveHireEmploymentDetailPage clickSaveButton(){
			click(saveButton);
			return me();
		}
		// click cancel button
		public ProspectiveHireEmploymentDetailPage clickCancelButton(){
			click(cancelButton);
			return me();
		}
		
		// get error message from UI
		public String getErrorMessage(){
			return element(errorMessageForm).getText();
		}
	public String getUIMessage(){
		return element(messageForm).getText();
		
	}
}
