package org.peoplestrong.page.wfm;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.peoplestrong.altworklife.core.BaseClass;
import org.testng.Assert;

public class MyTasksManager {
	String Fname="",Lname="";
	BaseClass base = new BaseClass();

	By loadingIcon =By.cssSelector("div[class*='ui-hidden-container loader_block'][aria-live='polite']");
	By managerTasksclck = By.xpath("//span[contains(@class,'mytasks')]//following-sibling::span");
	By managerPendingTasksClck = By.cssSelector("div.task-list-title");
	By approveRegularizeRequest = By.xpath("//span[text()='Approve']");
	By rejectRegularRequest = By.xpath("//span[text()='Reject']");
	By approverComments = By.xpath("//textarea[@id='searchForm:attendanceDetail:0:commentApprover']");
	By regularizeReqRejectConfrmation = By.xpath("(//span[text()='Request Rejected Successfully.'])[1]");
	By CompOffManagerComments = By.xpath("//textarea[@id='leaveRequestForm:manComm']");
	By CompOffRejectByManager = By.xpath("//span[text()='Reject']");
	By CompOffApproveByManager = By.xpath("//span[text()='Approve']");
	By CompOffReqRejectConfrmation = By.xpath("(//span[text()='Request Rejected Successfully.'])[1]");
	
	public MyTasksManager me() {
		wait_for_page_load();
		return this;
	}
	WebDriverWait wait=new WebDriverWait(base.driver, 90);
	public void wait_for_page_load() {				
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
	}
	
	
	
	public MyTasksManager managerPendingTasks() throws Exception {
		wait_for_page_load();
		base.verify(ExpectedConditions.visibilityOfElementLocated(managerTasksclck));
		base.waitInSeconds(2);
		base.click(managerTasksclck);
		base.click(managerPendingTasksClck);
		return me();
	}
	
	public MyTasksManager approveRegularizeRequestOfEmployee(){
		base.enterText(approverComments, "Ok");
		base.click(approveRegularizeRequest);
		return me();
	}
	
	public MyTasksManager openPendingRequestEmployee(String Fname,String Lname) {
		By L1Approvalclck = By.xpath("//tbody[@id='searchForm:myTaskList_data']//tr//td[3][contains(text(),'"+Lname+"') and contains(text(),'"+Fname+"')]//following-sibling::td[1]/a");
		base.click(L1Approvalclck);
		return me();
	}
	
	public MyTasksManager rejectRegularizeRequestOfEmployee(){
		base.enterText(approverComments, "Rejected");
		base.click(rejectRegularRequest);
		base.verify(ExpectedConditions.visibilityOfElementLocated(regularizeReqRejectConfrmation));
		return me();
	}
	
	public MyTasksManager rejectCompOffRequestOfEmployee(){
		base.enterText(CompOffManagerComments, "Comp-Off Approved");
		base.click(CompOffRejectByManager);
		base.verify(ExpectedConditions.visibilityOfElementLocated(CompOffReqRejectConfrmation));
		return me();
	}
	
}
