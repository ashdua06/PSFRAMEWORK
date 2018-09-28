package org.peoplestrong.altworklife.page.hris;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.peoplestrong.altworklife.core.BaseClass;
import org.peoplestrong.altworklife.core.PropertyFileHandler;

public class HomePage extends BaseClass{
	
	String test =PropertyFileHandler.getInstance().getValue("title");
	By onboarding=By.xpath("//*[contains(@class,'desktopmenu-only')]//*[text()='Onboarding']");
	By prospectiveHire=By.xpath("//*[contains(@class,'desktopmenu-only')]//*[text()='Prospective Hires']");
	By viewMoreOnboardingCard=By.xpath("//app-onboarding//a");
	By onBoardingStatus=By.xpath("//*[contains(@class,'desktopmenu-only')]//a[text()='Onboarding Status']");
	//String test =PropertyFileHandler.getInstance().getValue("title");
	By homePageMoreIcon = By.cssSelector("a[title='More']");
	By homePageAttendanceRosterSideIcon = By.xpath("//ul[@class='list-reset bar-navigation desktopmenu-only']//a[@title='Attendance']/following::div[1]//ul/li/a[text()='Configuration']//ancestor::li[1]/div[1]//li[2]/a");
	By homePageAssignRosterPage= By.xpath("//ul[@class='list-reset bar-navigation desktopmenu-only']//a[@title='Attendance']/following::div[1]//ul/li/a[text()='Configuration']//ancestor::li[1]/div[1]//li[3]/a");
	By homePageLeaveApprovalFlipButton = By.xpath("//span[@class='icon_sheet leave_icon']/ancestor::div[4]//a[text()='flip for details']");
	By homePageLeaveApprovalViewAllButton= By.xpath("//span[@class='icon_sheet leave_icon']/ancestor::div[4]/following-sibling::div[1]//a[text()='View All']");
	// String to substitute the pattern at run time
	String homePageMenuValuePattern = "//*[contains(@class,'desktopmenu-only')]//*[text()='subs_pattern']";
	String homePageMenuPattern= "//ul[@class='list-reset bar-navigation desktopmenu-only']//a[@title='subs_pattern']";

	
	public  HomePage me() {
		wait_for_page_load();
		return this;
	}
	WebDriverWait wait=new WebDriverWait(driver, 90);

	By loadingIcon= By.cssSelector("div[class*='ui-hidden-container loader_block'][aria-live='polite']");
	public void wait_for_page_load() {				
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
	}
	
	public HomePage mouse_over_onboarding() {
		mouseHover(onboarding);
		return me();
	}
	
	public HomePage click_propspective_hire() {
		waitInSeconds(5);
		click(prospectiveHire);
		return me();
	}
	
	public HomePage click_onboarding_status() {
		waitInSeconds(5);
		click(onBoardingStatus);
		return me();
	}
	
	public HomePage clickMoreIcon(){
		wait_for_page_load();
		int count = driver.findElements(homePageMoreIcon).size();
		if(count != 0)
			click(homePageMoreIcon);
		return me();
	}
	
	public HomePage clickHomePage(){
		click(By.xpath(homePageMenuValuePattern.replaceAll("subs_pattern", "home")));
		return me();
	}
	public HomePage clickMenuValue(String menuOptionValue){
		clickMoreIcon();
		String menuName = homePageMenuValuePattern.replace("subs_pattern",menuOptionValue );
		click(By.xpath(menuName));
		return me();
	}
	public HomePage clickSubTab(String tabValue,String subTabValue){
		String option = homePageMenuPattern.replace("subs_pattern", tabValue);
		click(By.xpath(option));
		option = option+("/following::div[1]//ul/li/a[text()='subs_pattern']").replace("subs_pattern", subTabValue);
		click(By.xpath(option));
		return me();
	}
	
	public HomePage clickConfigurationRosterTab(){
		clickSubTab("Attendance","Configuration");
		click(homePageAttendanceRosterSideIcon);
		return me();
	}
	
	public HomePage clickAssignRosterByEmpTab(){
		clickSubTab("Attendance","Configuration");
		click(homePageAssignRosterPage);
		return me();
	}
	
	// Leave approval Card
	public HomePage clickLeaveApprovalViewAllButton(){
		click(homePageLeaveApprovalFlipButton);
		waitInSeconds(2);
		click(homePageLeaveApprovalViewAllButton);
		return me();
	}
}
