package org.peoplestrong.altworklife.page.hris;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.peoplestrong.altworklife.core.BaseClass;
import org.peoplestrong.altworklife.core.PropertyFileHandler;

public class LoginPage extends BaseClass {

	By usernameField = By.cssSelector("input[id='loginForm:username12']");
	By passwordField = By.cssSelector("input[id='loginForm:password']");
	By loginButton = By.xpath("//span[text()='Login']");
	By moreIcon = By.xpath("//span[text()='more']");
	By logoutIcon = By.xpath("//li//span[text()='power_settings_new']");
	By loginAgainLink=By.xpath("//a[text()='Login Again']");	  
	By moreIconOb=By.xpath("//a[@id='username']");
	By logoutOb=By.xpath("//li[@class='logout']/a");
	WebDriverWait wait=new WebDriverWait(driver, 90);
	By loadingIcon= By.cssSelector("div[class*='ui-hidden-container loader_block'][aria-live='polite']");
	
	public  LoginPage me() {
		wait_for_page_load();
			return this;
	}
	public void wait_for_page_load() {				
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
	}
	
	
	public LoginPage open_site(String env) {
		String url = PropertyFileHandler.getInstance().getValue(env.toLowerCase());
		navigateToUrl(url);
		return me();
	}
	
	
	public LoginPage open_ob_portal(String env) {
		//System.out.println("test");
		//String url = PropertyFileHandler.getInstance().getValue(env.toLowerCase());
		String url = PropertyFileHandler.getInstance().getValue((env+"-ob").toLowerCase());
		navigateToUrl(url);
		return me();
	}
	
	public LoginPage login_as(String userName , String password) {
		enter_username(userName);
		enter_password(password);
		click_login_btn();
		return me();
	}
	
	public LoginPage logout() {
		if(driver.findElement(moreIcon).isDisplayed() ){
			driver.manage().deleteAllCookies();
			click(moreIcon);
			click(logoutIcon);
			if(element(loginAgainLink).isDisplayed()){
				click(loginAgainLink);
			}
			//a[text()='Login Again']
		}
		return me();
	}
	
	public LoginPage enter_username(String userName) {
		waitInSeconds(5);
		enterText(usernameField, userName);
		return me();
	}
	  
	public LoginPage enter_password(String password) {
		enterText(passwordField, password);
		return me();
	}
	  
	public LoginPage click_login_btn() {
		click(loginButton);
		return me();
	}
	
	public LoginPage logout_ob() {
		mouseHover(moreIconOb);
		click(logoutOb);
		return me();
	}
}
