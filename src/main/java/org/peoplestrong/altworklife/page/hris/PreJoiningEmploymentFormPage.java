package org.peoplestrong.altworklife.page.hris;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.peoplestrong.altworklife.core.BaseClass;

public class PreJoiningEmploymentFormPage extends BaseClass {
	By employmentDetailsTab = By.xpath("//a[text()='Employment Details']");
	By empDetailAddMoreLink = By.xpath("//a[text()='Add More']");
	By basicDetailsSubmitMsg=By.xpath("//span[@class='ui-messages-info-summary']");//Basic Details saved successfully
	By saveBtn=By.xpath("//span[contains(text(),'Save')]");
	By loadingIcon = By
			.cssSelector("div[class*='ui-hidden-container loader_block'][aria-live='polite']");
	By uiErrorMessage = By.cssSelector("div[class='ui-messages ui-widget']");
	By startDateCalendarIcon = By.xpath("//label[text()='Start Date']/ancestor::dl[1]/dd[1]//button");
	By endDateCalendarIcon = By.xpath("//label[text()='End Date']/ancestor::dl[1]/dd[1]//button");
	By dialogConfirmDeleteMessage = By.cssSelector("span[class='ui-confirm-dialog-message']");
	By dialogYesButton = By.xpath("//span[text()='Yes']");
	By epmDetailsNumOfRows =  By.cssSelector("div[id*='employmentForm'] table tbody tr");
	// String to update as locator
	String companyNamePattern = "ul li[data-item-label='subs_pattern']";
	String epmDetailsRowPattern = "div[id*='employmentForm'] table tbody tr:nth-of-type(subs_pattern)";
	String docPath = "C:\\Users\\rahul.gulati\\Downloads\\DOU.pdf";
	String rowEditIcon = "div[id*='employmentForm'] table tbody tr:nth-of-type(subs_pattern)"
			+ " td div[aria-label='Toggle Row']";
	String rowDeleteIcon = "div[id*='employmentForm'] table tbody tr:nth-of-type(subs_pattern) td div a div[class*='trash']";
	String onBoardingEmpDetailFieldPatetrn = "//label[text()='subs_pattern']/ancestor::dt[1]/following::dd[1]//input[1]";

	/**
	 * Below methods are for verification/operation on employment details tab of
	 * on boarding portal
	 * 
	 **/

	public PreJoiningEmploymentFormPage me() {
		wait_for_page_load();
		return this;
	}

	WebDriverWait wait = new WebDriverWait(driver, 90);

	public void wait_for_page_load() {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
	}

	// click on edit icon against the emp detail row
	public PreJoiningEmploymentFormPage clickEditIcon(int rowNum) {
		element(
				By.cssSelector(rowEditIcon.replace("subs_pattern",
						String.valueOf(rowNum)))).click();
		waitInSeconds(2);
		return me();
	}

	// click on delete icon for a row
	//@ rowNum - pass row num which need to be delete (1 for first row)
	public PreJoiningEmploymentFormPage clickDeleteRowIcon(int rowNum) {
		element(
				By.cssSelector(rowDeleteIcon.replace("subs_pattern",
						String.valueOf(rowNum)))).click();
		return me();
	}
	
	// get Number of Rows from EMployment details
	public int getNumberOfEmpDetailRows(){
		return driver.findElements(epmDetailsNumOfRows).size();
	}
	
	// return the row from emp detail page.
	// Pass - @rowNum as integer to get the row data (1 for first row, 2 for
	// second row
	public String getEmpRowDetails(int rowNum) {
		return element(
				By.cssSelector(epmDetailsRowPattern.replace("subs_pattern",
						String.valueOf(rowNum)))).getText();
	}

	// Set project Name
	public PreJoiningEmploymentFormPage setprojectName(String name) {
		element(
				By.xpath(onBoardingEmpDetailFieldPatetrn.replace(
						"subs_pattern", "Project Name"))).clear();
		element(
				By.xpath(onBoardingEmpDetailFieldPatetrn.replace(
						"subs_pattern", "Project Name"))).sendKeys(name);
		waitInSeconds(2);
		return me();
	}
	
	// Set project Name
		public PreJoiningEmploymentFormPage setPFAccount(String name) {
			element(
					By.xpath(onBoardingEmpDetailFieldPatetrn.replace(
							"subs_pattern", "PF ACCOUNT"))).clear();
			element(
					By.xpath(onBoardingEmpDetailFieldPatetrn.replace(
							"subs_pattern", "PF ACCOUNT"))).sendKeys(name);
			click(By.xpath("//h1[text()='Employment Details']"));
			return me();
		}
	
	/**
	 *  Dialog methods on Emp details 
	 * @return
	 */
	
	// get UI dialog message from prompt window
		public String getDialogMessage() {
			return element(dialogConfirmDeleteMessage).getText();
		}
	// click on yes button to confirm delete operation
		public PreJoiningEmploymentFormPage clickDialogYesButton(){
			click(dialogYesButton);
			return me();
		}
	/**
	* Set details on employent detail page
	* @fieldName - Exact field name in which value need to be set(ex- Company Name, Title: )
	* @Value - value which need to be set in field
	**/
		public PreJoiningEmploymentFormPage setEmploymentDetailsOnBoarding(String fieldName,String Value) {
			element(
					By.xpath(onBoardingEmpDetailFieldPatetrn.replace(
							"subs_pattern",fieldName))).clear();
			element(
					By.xpath(onBoardingEmpDetailFieldPatetrn.replace(
							"subs_pattern", fieldName))).sendKeys(Value);
			wait_for_page_load();
			element(By.cssSelector(companyNamePattern.replace("subs_pattern", Value))).click();
				
			return me();
		}
		
		public PreJoiningEmploymentFormPage setEmploymentDetailsOnBoardingWithoutAutoSuggest(String fieldName,String Value) {
			element(
					By.xpath(onBoardingEmpDetailFieldPatetrn.replace(
							"subs_pattern",fieldName))).clear();
			element(
					By.xpath(onBoardingEmpDetailFieldPatetrn.replace(
							"subs_pattern", fieldName))).sendKeys(Value);
			return me();
		}

		// select Start Date
		public PreJoiningEmploymentFormPage selectEmpDetailStartDate(
				int Date, int MnthNum, String Year) {
			click(startDateCalendarIcon);
			
			selectDateFromCalendar(Date, MnthNum, Year);
			return me();
		}

		// select End Date
		public PreJoiningEmploymentFormPage selectEmpDetailEndDate(int Date,
				int MnthNum, String Year) {
			click(endDateCalendarIcon);
			selectDateFromCalendar(Date, MnthNum, Year);
			return me();
		}
	// Get UI messages available on UI(Emp details page)
	public String getUIMessage() {
		return element(basicDetailsSubmitMsg).getText();
	}
	public String getUIErrorMsg(){
		return element(uiErrorMessage).getText();
	}
	public PreJoiningEmploymentFormPage clickAddMoreLink() {
		click(empDetailAddMoreLink);
		return me();
	}
	
	public PreJoiningEmploymentFormPage click_save_button() {
		waitInSeconds(2);
		click(saveBtn);
		waitInSeconds(2);
		return me();
	}
}
