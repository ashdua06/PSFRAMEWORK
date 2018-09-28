package org.peoplestrong.altworklife.page.hris;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.peoplestrong.altworklife.core.BaseClass;

public class PreJoiningEducationFormPage extends BaseClass {

	// Labels

	// Locators
	By educationFormAddMoreLink = By.xpath("//a[text()='Add More']");
	By saveBtn = By.xpath("//span[contains(text(),'Save')]");
	By educationDegreeListDropdown = By
			.cssSelector("input[id*='degreeList_input']");
	By loadingIcon = By
			.cssSelector("div[class*='ui-hidden-container loader_block'][aria-live='polite']");
	By educationFormNumberOfRows = By
			.cssSelector("div[id*=':educationList'] table tbody tr");
	By dialogConfirmDeleteMessage = By.cssSelector("span[class='ui-confirm-dialog-message']");
	By dialogYesButton = By.cssSelector("button[class*='ui-confirmdialog-yes']");
	// String to be substituted at run time to create a locator
	String educationFormRowValuePattern = "div[id*=':educationList'] table tbody tr:nth-of-type(subs_pattern)";
	String educationRowEditPattern = "div[id*=':educationList'] table tbody tr:nth-of-type(subs_pattern) td div[aria-label='Toggle Row']";
	String educationRowDeletePattern = "div[id*=':educationList'] table tbody tr:nth-of-type(subs_pattern) td a div[class*='trash']";

	// Methods
	public PreJoiningEducationFormPage me() {
		wait_for_page_load();
		return this;
	}

	WebDriverWait wait = new WebDriverWait(driver, 90);

	public void wait_for_page_load() {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
	}

	/**
	 * return row of education table
	 * 
	 * @rownum(int)- Pass rownum as to get that row details(Ex- 1 or 3 to get
	 *               first row or third row detail successfully)
	 */
	public String getEducationRowDetail(int rowNum) {
		return element(
				By.cssSelector(educationFormRowValuePattern.replace(
						"subs_pattern", String.valueOf(rowNum)))).getText();
	}

	public PreJoiningEducationFormPage setEducationDegreeNull() {
		click(educationDegreeListDropdown);
		element(educationDegreeListDropdown).clear();
		return me();
	}

	// Click edit icon as per rownum passed
	public PreJoiningEducationFormPage clickEditIcon(int rowNum) {
		element(
				By.cssSelector(educationRowEditPattern.replace("subs_pattern",
						String.valueOf(rowNum)))).click();
		return me();
	}

	// Click Delete Icon as per rownum Passed
	public PreJoiningEducationFormPage clickDeleteIcon(int rowNum) {
		element(
				By.cssSelector(educationRowDeletePattern.replace(
						"subs_pattern", String.valueOf(rowNum)))).click();
		return me();
	}

	public PreJoiningEducationFormPage clickSaveButton() {
		click(saveBtn);
		return me();
	}

	public int getNumberOfRows() {
		return getElementCount(educationFormNumberOfRows);
	}

	// get UI dialog message from prompt window
	public String getDialogMessage() {
		return element(dialogConfirmDeleteMessage).getText();
	}

	// click on yes button to confirm delete operation
	public PreJoiningEducationFormPage clickDialogYesButton() {
		waitInSeconds(1);
		click(dialogYesButton);
		return me();
	}
}
