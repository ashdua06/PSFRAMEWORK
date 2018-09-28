package org.peoplestrong.page.wfm;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.peoplestrong.altworklife.core.BaseClass;
import org.testng.Assert;

public class HomePageWFM {
	BaseClass base = new BaseClass();
	By leaveLabel = By.xpath("(//*[text()='Leave'])[2]");
	By loadingIcon =By.cssSelector("div[class*='ui-hidden-container loader_block'][aria-live='polite']");
	By NewLeaveclk = By.xpath("(//a[text()='New Leave'])[2]");
	By EmployeeMyTasks = By.xpath("//span[text()='Tasks']");
	By EmployeePendingReqsClck = By.xpath("//div[contains(text(),'pending') and contains(text(),'requests')]");
	
	
	public  HomePageWFM me() {
		wait_for_page_load();
			return this;
	}
	WebDriverWait wait=new WebDriverWait(base.driver, 90);
	public void wait_for_page_load() {				
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
	}

	public HomePageWFM clickNewLeaveLabel() {
		wait_for_page_load();
	 base.mouseHover(leaveLabel);
	 base.click(NewLeaveclk);
		return me();
	}
	
	public HomePageWFM checkPendingTasksEmployee() {
		wait_for_page_load();
		base.verify(ExpectedConditions.visibilityOfElementLocated(EmployeeMyTasks));
		base.waitInSeconds(2);
		base.click(EmployeeMyTasks);
		base.click(EmployeePendingReqsClck);
		return me();
	}
}
