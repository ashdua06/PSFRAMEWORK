package org.peoplestrong.page.wfm;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.peoplestrong.altworklife.core.BaseClass;

public class MyTaskEmployee {
	BaseClass base = new BaseClass();

	By loadingIcon =By.cssSelector("div[class*='ui-hidden-container loader_block'][aria-live='polite']");
	By LeaveRejectedLink = By.xpath("//td[4]/a[text()='Leave Rejected']");
	By WithdrawClick = By.xpath("//span[text()='Withdraw']");
	
	public MyTaskEmployee me() {
		wait_for_page_load();
		return this;
	}
	WebDriverWait wait=new WebDriverWait(base.driver, 90);
	public void wait_for_page_load() {				
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
	}
	
	public MyTaskEmployee withdrawRejectedLeave() {
		base.click(LeaveRejectedLink);
		base.click(WithdrawClick);
		return me();
	}
	
}
