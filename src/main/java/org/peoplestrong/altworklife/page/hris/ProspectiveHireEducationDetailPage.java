package org.peoplestrong.altworklife.page.hris;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.peoplestrong.altworklife.core.BaseClass;

public class ProspectiveHireEducationDetailPage extends BaseClass {

	// Labels
	String educationLevelLabel = "Education Level";
	String courseTypeLabel = "Course Type";
	String instituteNameLabel = "Institute Name/Location";
	String educationBoardName = "Board/University";
	String educationScoreTypeLabel = "Score type";
	String educationPercentageGradeLabel = "Percentage/Final Grade";
	// UI Locators
	By saveBtn = By.xpath("//span[contains(text(),'Save')]");
	By errorMessageForm = By.cssSelector("form[id='messagesForm']");
	By messageForm = By.cssSelector("form[id='messagesForm']");
	By loadingIcon = By
			.cssSelector("div[class*='ui-hidden-container loader_block'][aria-live='polite']");
	By educationDegreeListDropdown = By
			.cssSelector("input[id='degreeList_input']");
	By specializationListDropdown = By
			.cssSelector("input[id='specializationList_input']");
	By minorSpecializationListDropdown = By
			.cssSelector("input[id='minorSpecializationList_input']");
	By educationEndDateYearDropdpown = By.cssSelector("div[id='endDateyear']");
	By educationEndDateMonthDropdpown = By
			.cssSelector("div[id='endDatemonth']");
	By educationStartDateYearDropdown = By
			.cssSelector("label[id='year_label']");
	By educationStartDateMonthDropdown = By
			.cssSelector("label[id='month_label']");

	// String to hold the pattern values which can be substituted at run time
	String dropdownListValuePattern = "ul li[data-item-label='subs_pattern']";
	String dropDownValuePattern = "ul li[data-label='subs_pattern']";
	String endDateMonthDropdownPattern = "div[id*='endDatemonth'] ul li[data-label='subs_pattern']";
	String endDateYearDropdownPattern = "div[id*='endDateyear'] ul li[data-label='subs_pattern']";
	String dropDownPattern = "//label[contains(text(),'subs_pattern')]/ancestor::dt[1]/following::dd[1]//div[contains(@class,'selectonemenu-trigger')]";
	String educationDetailInputFieldPatetrn = "//label[contains(text(),'subs_pattern')]/ancestor::dt[1]/following::dd[1]//input[1]";

	// Methods
	WebDriverWait wait = new WebDriverWait(driver, 90);

	public ProspectiveHireEducationDetailPage me() {
		wait_for_page_load();
		return this;
	}

	// wait for loading icon to disappear from page before scripts take action
	// on UI
	public void wait_for_page_load() {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
	}

	// select value from dropdown
	public ProspectiveHireEducationDetailPage selectEducationLevel(String value) {
		selectValueFromDropdown(By.xpath(dropDownPattern.replace(
				"subs_pattern", educationLevelLabel)), value);
		return me();
	}

	// select course type value from dropdown
	public ProspectiveHireEducationDetailPage selectCourseTypeLevel(String value) {
		selectValueFromDropdown(By.xpath(dropDownPattern.replace(
				"subs_pattern", courseTypeLabel)), value);
		return me();
	}

	public ProspectiveHireEducationDetailPage selectScoreTypeValue(String value) {
		selectValueFromDropdown(By.xpath(dropDownPattern.replace(
				"subs_pattern", educationScoreTypeLabel)), value);
		return me();
	}

	public ProspectiveHireEducationDetailPage selectEndDate(String month,
			String year) {
		click(educationEndDateMonthDropdpown);
		click(By.cssSelector(endDateMonthDropdownPattern
				.replace("subs_pattern", month)));
		
		click(educationEndDateYearDropdpown);
		click(By.cssSelector(endDateYearDropdownPattern
				.replace("subs_pattern", year)));
		/*
		selectValueFromDropdown(educationEndDateMonthDropdpown, month);
		selectValueFromDropdown(educationEndDateYearDropdpown, year);*/
		return me();
	}

	public ProspectiveHireEducationDetailPage selectStartDate(String month,
			String year) {
		selectValueFromDropdown(educationStartDateMonthDropdown, month);
		selectValueFromDropdown(educationStartDateYearDropdown, year);
		return me();
	}

	// Select dropdown from filtered list value
	public ProspectiveHireEducationDetailPage selectDegreeValue(String value) {
		selectValueFromDropDownFilterList(educationDegreeListDropdown, value);
		return me();
	}

	public ProspectiveHireEducationDetailPage selectSpecializationValue(
			String value) {
		selectValueFromDropDownFilterList(specializationListDropdown, value);
		return me();
	}

	public ProspectiveHireEducationDetailPage selectMinorSpecializationValue(
			String value) {
		selectValueFromDropDownFilterList(minorSpecializationListDropdown,
				value);
		return me();
	}

	public ProspectiveHireEducationDetailPage selectInstituteNameValue(
			String value) {
		selectValueFromDropDownFilterList(
				By.xpath(educationDetailInputFieldPatetrn.replace(
						"subs_pattern", instituteNameLabel)), value);
		return me();
	}

	public ProspectiveHireEducationDetailPage clickSaveButton() {
		click(saveBtn);
		return me();
	}
	
	public ProspectiveHireEducationDetailPage setBoardUniv(String value) {
		element(
				By.xpath(educationDetailInputFieldPatetrn.replace(
						"subs_pattern", educationBoardName))).sendKeys(value);
		return me();
	}

	public ProspectiveHireEducationDetailPage setPercentGradeValue(String value) {
		element(
				By.xpath(educationDetailInputFieldPatetrn.replace(
						"subs_pattern", educationPercentageGradeLabel)))
				.clear();
		element(
				By.xpath(educationDetailInputFieldPatetrn.replace(
						"subs_pattern", educationPercentageGradeLabel)))
				.sendKeys(value);
		return me();
	}

	// get error message from UI
	public String getErrorMessage() {
		return element(errorMessageForm).getText();
	}

	public String getUIMessage() {
		return element(messageForm).getText();

	}


	// Wrapper to select value from a drodown where user need to click on
	// dropwdown trigger
	// and select value from dropdpwn list
	public void selectValueFromDropdown(By loc, String value) {
		click(loc);
		click(By.cssSelector(dropDownValuePattern
				.replace("subs_pattern", value)));
	}

	// Wrapper to select value from a drodown where user need to enter a value
	// and select value from dropdpwn filtered list
	public void selectValueFromDropDownFilterList(By loc, String value) {
		element(loc).clear();
		element(loc).sendKeys(value);
		wait_for_page_load();
		click(By.cssSelector(dropdownListValuePattern.replace("subs_pattern",
				value)));
	}
}
