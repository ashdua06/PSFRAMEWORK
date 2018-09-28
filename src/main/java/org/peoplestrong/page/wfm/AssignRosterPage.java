package org.peoplestrong.page.wfm;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.peoplestrong.altworklife.core.BaseClass;
import org.peoplestrong.altworklife.page.hris.LoginPage;

public class AssignRosterPage extends BaseClass {

	BaseClass base = new BaseClass();
	/** Locators of the New Roaster Page
	 * 
	 */
		By roaterPlusIcon= By.cssSelector("button[title='Create Roster']");
		By assignRosterButton = By.xpath("//div[@id='actionPanel']/button/span[text()='Assign Roster']");
		By rosterStartDateCalIcon = By.cssSelector("span[id*=':startDate'] button");
		By rosterEndDateCalIcon = By.cssSelector("span[id*=':endDate'] button");
		By rosterSelectDropDownIcon = By.cssSelector("label[id='addEmpDetailsForm:friday_label']");
		By rosterAssignButton = By.xpath("//span[text()='Assign']");
		By rosterMonthDropdown = By.cssSelector("select[data-handler='selectMonth']");
		By rosterYearDropDown = By.cssSelector("select[data-handler='selectYear']");
		By loadingIcon= By.cssSelector("div[class*='ui-hidden-container loader_block'][aria-live='polite']");
		
		//String locators which need to be updated dynamically at run time
		String assignRosterCheckBox = "//td[contains(text(),'subs_pattern')]//ancestor::tr[1]/td[1]/div";
		String rosterDropDownValuePattern = "li[data-label='subs_pattern']";
		String rosterStartDateDay = "//td[@data-handler='selectDay']/a[text()='subs_pattern']";
		String rosterDeleteLinkPattern = "//td[text()='subs_pattern']/ancestor::tr[1]/td/a[text()='delete']";
		
		public  AssignRosterPage me() {
			wait_for_page_load();
				return this;
		}
		WebDriverWait wait=new WebDriverWait(driver, 90);
		public void wait_for_page_load() {				
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		}
		// Assign Roaster to Employee . Pass employee code as parameter.
		public AssignRosterPage checkRosterCheckbox(int emp_code){
			base.click(By.xpath(assignRosterCheckBox.replace("subs_pattern", Integer.toString(emp_code) )));
			return me();
		}
		
		public AssignRosterPage clickAssignRosterButton(){
			base.click(assignRosterButton);
			return me();
		}
		
	
		
		public AssignRosterPage clickAssignButton(){
			base.click(rosterAssignButton);
			return me();
		}

		public AssignRosterPage setRosterStartDate(int Date,int MonthNum,String Year){
			base.click(rosterStartDateCalIcon);
			Select calendarMonth = new Select(driver.findElement(rosterMonthDropdown));
			Select calendarYear = new Select(driver.findElement(rosterYearDropDown));
			calendarYear.selectByValue(Year);
			calendarMonth.selectByIndex(MonthNum);
			base.click(By.xpath("//a[text()='"+Date+"']"));
			return me();
		}
		
		public AssignRosterPage setRosterEndDate(int Date,int MonthNum,String Year){
			base.click(rosterEndDateCalIcon);
			Select calendarMonth = new Select(driver.findElement(rosterMonthDropdown));
			Select calendarYear = new Select(driver.findElement(rosterYearDropDown));
			calendarYear.selectByValue(Year);
			calendarMonth.selectByIndex(MonthNum);
			base.click(By.xpath("//a[text()='"+Date+"']"));
			return me();
		}
		
		public AssignRosterPage selectRosterValue(String value){
			base.click(rosterSelectDropDownIcon);
			base.click(By.cssSelector(rosterDropDownValuePattern.replace("subs_pattern", value)));
			return me();
		}
		
		public AssignRosterPage deleteAssignedRoster(String assignedRosterName){
			click(By.xpath(rosterDeleteLinkPattern.replace("subs_pattern", assignedRosterName)));
			return me();
		}
		
}
