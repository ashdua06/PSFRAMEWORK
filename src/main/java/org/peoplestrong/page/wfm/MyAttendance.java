package org.peoplestrong.page.wfm;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.peoplestrong.altworklife.core.BaseClass;

public class MyAttendance {
	
	BaseClass base = new BaseClass();

	
	By attendancelabel = By.xpath("(//a[@title='Attendance'])[2]");
	By myAttendance = By.xpath("(//a[text()='My Attendance'])[2]");
	By loadingIcon =By.cssSelector("div[class*='ui-hidden-container loader_block'][aria-live='polite']");
	By myAttendanceLabel = By.xpath("//span[text()='My Attendance']");
	//By myAttendancetablerows = By.xpath("//tbody[@id='searchForm:attendanceDetail_data']/tr/td[1]");
	//By myAttendanceDynamicRow = By.xpath("//tbody[@id='searchForm:attendanceDetail_data']/tr[" + (i+1)+ "]/td[9]/label");
	By checkWOAttendanceRegularize = By.xpath("//tbody[@id='searchForm:attendanceDetail_data']//tr//td[last()]/label[contains(text(),'WO')]/../..//td[1]/div/div[2]/span");
	By regularizeButton = By.xpath("//button[@title='Regularize']/span");
	By regularizeInTime = By.xpath("//input[@id='addEmpDetailsForm:attendanceList:0:inTime_input']");
	By regularizeOutTime = By.xpath("//input[@id='addEmpDetailsForm:attendanceList:0:outTime_input']");
	By regularizeReasonDropDown = By.cssSelector("span.ui-icon-triangle-1-s");
	By regularizeReasonSelect = By.cssSelector("li[data-label='Others']");
	By regularizeEmpComments = By.xpath("//textarea[@id='addEmpDetailsForm:attendanceList:0:comment']");
	By regularizeSubmitbutton = By.xpath("//span[text()='Submit']");
	
	public MyAttendance me() {
		wait_for_page_load();
		return this;
	}
	WebDriverWait wait=new WebDriverWait(base.driver, 90);
	public void wait_for_page_load() {				
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
	}
	
	public MyAttendance clickMyAttendance() {
		wait_for_page_load();
		base.mouseHover(attendancelabel);
		base.click(myAttendance);
		base.verify(ExpectedConditions.visibilityOfElementLocated(myAttendanceLabel));
		System.out.println("My Attendance Page opened");
		return me();
		
	}
	
	//Select first weekly off from My Attendance table
	public MyAttendance selectWeeklyOff() {
		base.click(checkWOAttendanceRegularize);
		System.out.println("Weekly off attendance selected");
		return me();
	}
	
	//Submit regularize attendance for weekly off
	public MyAttendance regularizeWOAttendance() {
		base.click(regularizeButton);
		//Regularize InTime
		String InTime = base.element(regularizeInTime).getAttribute("value");
		String RegularizedInTime = InTime.replace("00:00", "09:00");
		base.enterText(regularizeInTime, RegularizedInTime);
		//RegularizeOutTime
		String OutTime = base.element(regularizeOutTime).getAttribute("value");
		String RegularizedOutTime = OutTime.replace("00:00", "18:00");
		base.enterText(regularizeOutTime, RegularizedOutTime);
		base.click(regularizeReasonDropDown);
		base.click(regularizeReasonSelect);
		base.enterText(regularizeEmpComments, "Overtime work");
		base.click(regularizeSubmitbutton);
		return me();
	}
	
	
	
	
}
