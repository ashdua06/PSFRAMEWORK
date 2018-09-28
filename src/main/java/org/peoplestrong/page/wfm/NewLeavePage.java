package org.peoplestrong.page.wfm;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.peoplestrong.altworklife.core.BaseClass;

public class NewLeavePage extends BaseClass {
	
	

	By leaveTypeDropdown = By.cssSelector("label[id*=':leaveReasondropDown2']");
	By leaveReasonDropdown = By.cssSelector("label[id*=':leaveReasondropDown1']");
	By leaveStartDateCalendarIcon = By.cssSelector("span[id$=':startdate'] button");
	By leaveEndDateCalendarIcon = By.cssSelector("span[id$=':enddate'] button");
	By leaveDayValue = By.cssSelector("tbody[id='newLeaveRequestForm:locholidaysDetail_data'] tr td:nth-of-type(2)");
	By loadingIcon= By.cssSelector("div[class*='ui-hidden-container loader_block'][aria-live='polite']");
	By leaveSubmitButton = By.xpath("//span[text()='Submit']");
	By leavePageMessage = By.cssSelector("div[id*=':errorMessages']");
	By leaveSuccessMessage = By.cssSelector("div[id='messagesHide']");
	By leaveRejectMessage = By.cssSelector("span[class='ui-messages-info-summary']");
	By leaveLink  = By.cssSelector("tbody[id='leaveListFormid:tableId_data'] tr:nth-of-type(1) td a");
	By leaveL1ApprovalLink= By.partialLinkText("L1 Approval");
	By leaveApprovalComments = By.cssSelector("textarea[id='bulkTaskForm:remarksMsg']");
	By leaveRejectButton = By.xpath("//span[text()='Reject']");
	By leaveConfirmPopUpYesButton = By.xpath("//div[contains(@id,'popUpRejectForm')]//span[text()='Yes']");
	By CompOffSelectDate = By.xpath("//a[text()='Select Date']");
	By CompOffSelect = By.xpath("//div[contains(@class,'ui-radiobutton-box')]/span");
	By CompOffDateSubmit = By.xpath("//span[text()='submit']");
	By LeaveAnyOtherReason = By.xpath("//textarea[@id='newLeaveRequestForm:commentsTxtId']");
	By compOffAvailedTillDate = By.xpath("//table[@id='newLeaveRequestForm:leaveQuotaDetail']//tbody//tr//td[contains(text(),'CompOff')]//following-sibling::td[4]");
	By ApplyLeaveLessThanMin = By.xpath("(//span[contains(text(),'You cannot avail less than')])[1]");
	By ApplyLeaveMoreThanMax = By.xpath("(//span[contains(text(),'You cannot avail more than')])[1]");
	
	// pattern values
	String leaveDropdownPattern = "ul li[data-label='subs_pattern']";
	String  leaveApproveCheckboxPattern = "//td[contains(text(),'subs_pattern')]/ancestor::tr[1]/td/div//span[contains(@class,'chkbox')]";
	
	
	
	public  NewLeavePage me() {
		wait_for_page_load();
			return this;
	}
	
	WebDriverWait wait=new WebDriverWait(driver, 90);
	public void wait_for_page_load() {				
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
	}
	
	// Select Leave from leave type dropdown
	// @leaveType - type of leave  which need to be selected.
	public NewLeavePage selectLeaveType(String leaveType){
		click(leaveTypeDropdown);
		String leave_value = leaveDropdownPattern.replace("subs_pattern",leaveType);
		click(By.cssSelector(leave_value));
		return me();
	}
	
	// Select Leave from leave type dropdown
	// @leaveReason - type of leave  which need to be selected.
	public NewLeavePage selectLeaveReason(String leaveReason){
		click(leaveReasonDropdown);
		String Reason = leaveDropdownPattern.replace("subs_pattern",leaveReason);
		click(By.cssSelector(Reason));
		return me();
	}
	
	// Select Start Date from leave
	public NewLeavePage selectStartDate(int Date,int month,String Year){
		click(leaveStartDateCalendarIcon);
		selectDateFromCalendar( Date, month, Year);
		
		return me();
	}
	
	// Select end Date from leave
		public NewLeavePage selectEndDate(int Date,int month,String Year){
			click(leaveEndDateCalendarIcon);
			selectDateFromCalendar( Date, month, Year);
			return me();
		}
	// select leave duration
		public NewLeavePage selectLeaveDuration(int leaveRowNum,String leaveDuration){
			int row=leaveRowNum-1;
			String elementLoc= "div[id='newLeaveRequestForm:locholidaysDetail:"+row+":leavedropDown2']";
			click(By.cssSelector(elementLoc));
			if(leaveDuration == "Half Day")
				elementLoc = "div[id='newLeaveRequestForm:locholidaysDetail:"+row+":leavedropDown2_panel'] li[data-label='Half Day']";
			else
				elementLoc = "div[id='newLeaveRequestForm:locholidaysDetail:"+row+":leavedropDown2_panel'] li[data-label='Full Day']";
			click(By.cssSelector(elementLoc));
			return me();
		}
	// return leave duration value.
		// it is weekly off or simple day when leave can be applied
		public String getLeaveDayValue(){
			return driver.findElement(leaveDayValue).getText();
		}
		
		// click on submit button to apply leave
		public NewLeavePage clickSubmitButton(){
			click(leaveSubmitButton);
			return me();
		}
		
		// get leave applied message
		public String getLeaveAppliedMsg(){
			return element(leavePageMessage).getText();
		}
		
		public String getLeaveApplySucecssMsg(){
			return element(leaveSuccessMessage).getText();
		}
		
		// click on first leave in Leave page
		public NewLeavePage clickL1ApprovalLink(){
			element(leaveL1ApprovalLink).click();
			return me();
		}
		
		public NewLeavePage checkLeaveApproveCheckbox( String empName){
			element(By.xpath(leaveApproveCheckboxPattern.replace("subs_pattern",empName))).click();
			return me();
		}
		
		public NewLeavePage setLeaveApproveComments(String remarks){
			element(leaveApprovalComments).sendKeys(remarks);
			return me();
		}
		
		public NewLeavePage clickRejectButton(){
			click(leaveRejectButton);
			return me();
		}
		
		public NewLeavePage clickPopUpYesButton(){
			click(leaveConfirmPopUpYesButton);
			return me();
		}
	
		public String getLeaveRejectMessage(){
			return element(leaveRejectMessage).getText();
		}
		
		public NewLeavePage selectCompOffDate() {
			click(CompOffSelectDate);
			click(CompOffSelect);
			//click(CompOffDateSubmit);
			return me();
		}
		
		public NewLeavePage LeaveAnyOtherReason(String Leavereason) {
			
			enterText(LeaveAnyOtherReason, Leavereason);
			return me();
		}
		
		public String compOffAvaialedTillDate() {
			return (getElementText(compOffAvailedTillDate));
		}
		
		public String LeaveLessThanMinErrorMesg() {
			verify(ExpectedConditions.visibilityOfElementLocated(ApplyLeaveLessThanMin));
			return element(ApplyLeaveLessThanMin).getText();	
		}
		
		public String LeaveMoreThanMinErrorMesg() {
			verify(ExpectedConditions.visibilityOfElementLocated(ApplyLeaveMoreThanMax));
			return element(ApplyLeaveMoreThanMax).getText();	
		}
		
}
