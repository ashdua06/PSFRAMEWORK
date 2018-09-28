package org.peoplestrong.page.wfm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.peoplestrong.altworklife.core.BaseClass;
import org.peoplestrong.altworklife.page.hris.LoginPage;
import org.peoplestrong.altworklife.utils.Utils;

public class RosterPage extends BaseClass {
	// Locators of the New Roaster Page
	By roasterPlusIcon= By.cssSelector("button[title='Create Roster']");
	By roasterNameField = By.cssSelector("input[id*=':roasterName']");
	By roasterTypeDropdown = By.cssSelector("div[id$=':roasterTypeID']");
	By roasterSaveButton = By.xpath("//span[text()='Save']");
	By roasterNumOfWeeks = By.cssSelector("tbody[id='tmshiftEdit:locholidaysDetail_data'] tr");
	By roasterShiftStartDateCalIcon = By.xpath("//label[text()='Start Date']/ancestor::tr[1]/td[2]/span/button");
	By roasterShiftEndDateCalIcon = By.xpath("//label[text()='Start Date']/ancestor::tr[1]/td[4]/span/button");
	By rosterMonthDropdown = By.cssSelector("select[data-handler='selectMonth']");
	By rosterYearDropDown = By.cssSelector("select[data-handler='selectYear']");
	By rosterRefreshButton = By.xpath("//span[text()='REFRESH']");
	By rosterMyRosterDetailTable=By.cssSelector("tbody[id='searchForm:shiftTable_data'] tr");
	By rosterDeleteRosterIcon= By.cssSelector("button[title='Delete Roster']");
	By rosterDeleteMessage = By.cssSelector("form[name='leaveFormid'] div[class='ui-messages ui-widget']");
	By rosterAssignMsg = By.cssSelector("div[id='errorMessage1']");
	By loadingIcon= By.cssSelector("div[class*='ui-hidden-container loader_block'][aria-live='polite']");
	
	// String of the locators which are generic in nature
	String roasterTypeDropDownValuePattern = "div[id*='tmshiftEdit:roasterTypeID']  ul li[data-label='subs_pattern']";
	String roasterCalendarShiftInfo = "tbody[id='tmshiftEdit:locholidaysDetail_data'] tr:nth-of-type(1) td:nth-of-type(2) div";
	String rosterDetailTableShiftPattern = "tbody[id='searchForm:shiftTable_data'] tr:nth-of-type(subs_pattern) td:nth-of-type(2)";
	String rosterDetailTableDateShiftPattern = "tbody[id='searchForm:shiftTable_data'] tr:nth-of-type(subs_pattern) td:nth-of-type(1)";
	String checkRosterCheckboxPattern = "//td/a[text()='subs_pattern']/ancestor::tr[1]/td[1]/div[contains(@class,'chkbox')]";
	
	
	Utils util = new Utils();
	BaseClass base = new BaseClass();
	
	
	// Return the instance of the class
	public  RosterPage me() {
		wait_for_page_load();
			return this;
	}
	WebDriverWait wait=new WebDriverWait(driver, 90);
	public void wait_for_page_load() {				
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
	}
	
	// Click on plus icon to create new roaster
	public RosterPage click_roaster_plus_icon() throws InterruptedException{
		driver.findElement(roasterPlusIcon).click();
		return me();
	}
	
	// Set Roaster Name
	public RosterPage set_roaster_name(String name){
		driver.findElement(roasterNameField).sendKeys(name);
		return me();
	}
	
	// select roaster value from the 
	public RosterPage select_roaster_type(String roaster_type) throws InterruptedException{
		driver.findElement(roasterTypeDropdown).click();
		String roaster_value = roasterTypeDropDownValuePattern.replace("subs_pattern", roaster_type);
		driver.findElement(By.cssSelector(roaster_value)).click();
		return me();
		
	}
	
	// click on Save button to save Roaster
	public RosterPage click_save_button(){
		driver.findElement(roasterSaveButton).click();
		return me();
	}
	
	// get number of weeks in roaster
	public int getWeekCount(){
		return  driver.findElements(roasterNumOfWeeks).size();
	}
	
	// Set all week days in roaster
	public RosterPage setRoasterWeekData(Object[][] rosterDataSet){
		int numOfWeeks = getWeekCount();
		int numOfDays = 7;
		int weekNum=0;
		String weekDay=null;
		for(int i=0;i<numOfWeeks;i++){		
			for(int j=0 ; j<numOfDays ; j++){
				driver.findElement(By.cssSelector("tbody[id='tmshiftEdit:locholidaysDetail_data'] tr:nth-of-type("+(i+1)+") td:nth-of-type("+(j+2)+") div")).click();
				weekNum= i;
				weekDay=util.getWeekDay(j);
				System.out.println("weeknum: "+ weekNum + " and weekDay: "+ weekDay);
				driver.findElement(By.cssSelector("div[id='tmshiftEdit:locholidaysDetail:"+weekNum+":"+weekDay+"_panel'] ul li[data-label='"+rosterDataSet[i][j]+"']")).click();
			}
		}
		return me();
	}
	
	// MY ROASTER PAGE
	
	// set roaster shift start date
	public RosterPage setRoaserShiftStartDate(int Date,int MonthNum,String Year){
		base.click(roasterShiftStartDateCalIcon);
		Select calendarMonth = new Select(driver.findElement(rosterMonthDropdown));
		Select calendarYear = new Select(driver.findElement(rosterYearDropDown));
		calendarYear.selectByValue(Year);
		calendarMonth.selectByIndex(MonthNum);
		base.click(By.xpath("//a[text()='"+Date+"']"));
		return me();
	}
	
	// set roaster shift end date
		public RosterPage setRoaserShiftEndDate(int Date,int MonthNum,String Year){
			base.click(roasterShiftEndDateCalIcon);
			Select calendarMonth = new Select(driver.findElement(rosterMonthDropdown));
			Select calendarYear = new Select(driver.findElement(rosterYearDropDown));
			calendarYear.selectByValue(Year);
			calendarMonth.selectByIndex(MonthNum);
			base.click(By.xpath("//a[text()='"+Date+"']"));
			return me();
		}
		
		public RosterPage clickRefreshButton(){
			base.click(rosterRefreshButton);
			return me();
		}
	
		public boolean assertRosterData(int nextMonthDate, Object[][] shiftData){
			int totalRows = driver.findElements(rosterMyRosterDetailTable).size();
			int dayNumInWeek;
			int weekNumInMonth;
			Object[][] rosterData = new Object[5][7];
			Calendar rosterCal = Calendar.getInstance();
			rosterCal.add(Calendar.MONTH, 1);
			rosterCal.set(Calendar.DAY_OF_MONTH,nextMonthDate);
			String shiftType ;
			String rosterShiftDetailValue;
			boolean valueMatched = true;
			for(int i=1;i <= totalRows;i++){
				dayNumInWeek = rosterCal.get(Calendar.DAY_OF_WEEK);
				weekNumInMonth= rosterCal.get(Calendar.WEEK_OF_MONTH);
				 rosterShiftDetailValue=rosterDetailTableShiftPattern.replace("subs_pattern", String.valueOf(i));
				 shiftType = driver.findElement(By.cssSelector(rosterShiftDetailValue)).getText();
				 if(shiftType.contains("Off"))
					 shiftType="WOFF";
				 if(dayNumInWeek==1 && weekNumInMonth!=1)
					 weekNumInMonth=weekNumInMonth-1;
				 if(dayNumInWeek==1)
					 dayNumInWeek=8;
				 System.out.println("TEST "+shiftData[weekNumInMonth-1][dayNumInWeek-2]);
				  if( !(shiftData[weekNumInMonth-1][dayNumInWeek-2].toString().equals(shiftType)) ){
					  valueMatched=false;
					  System.out.println("Loop:"+i+". DAY: "+dayNumInWeek + ". Week Num:"+weekNumInMonth + ". shift "+shiftType);
					  break;
				  }
				 rosterCal.add(Calendar.DAY_OF_MONTH , 1);
			}
			//System.out.println(driver.findElement(By.cssSelector("tbody[id='searchForm:shiftTable_data'] tr:nth-of-type(1)")).getText());
			return valueMatched;
		}
		
		
		public List<String> getWeekOffRosterDates(){
			int totalRows = driver.findElements(rosterMyRosterDetailTable).size();
			String rosterShiftDetailValue,shiftType,shiftDate;
			List<String> weekOffDates = new ArrayList<String>();

			for(int i=1;i <= totalRows;i++){
				 rosterShiftDetailValue=rosterDetailTableShiftPattern.replace("subs_pattern", String.valueOf(i));
				 shiftType = driver.findElement(By.cssSelector(rosterShiftDetailValue)).getText();	 
				 if(shiftType.contains("Off")){
					 shiftDate= driver.findElement(By.cssSelector(rosterDetailTableDateShiftPattern.replace("subs_pattern", String.valueOf(i)))).getText();
					 weekOffDates.add(shiftDate);
				 } 
			}
			return weekOffDates;
		}
		
		public List<String> getWorkingDayRosterDates(){
			int totalRows = driver.findElements(rosterMyRosterDetailTable).size();
			String rosterShiftDetailValue,shiftType,shiftDate;
			List<String> shiftDayDates = new ArrayList<String>();

			for(int i=1;i <= totalRows;i++){
				 rosterShiftDetailValue=rosterDetailTableShiftPattern.replace("subs_pattern", String.valueOf(i));
				 shiftType = driver.findElement(By.cssSelector(rosterShiftDetailValue)).getText();	 
				 if(shiftType.contains("DAY")){
					 shiftDate= driver.findElement(By.cssSelector(rosterDetailTableDateShiftPattern.replace("subs_pattern", String.valueOf(i)))).getText();
					 shiftDayDates.add(shiftDate);
				 } 
			}
			return shiftDayDates;
		}
		
		public RosterPage checkRoster(String rosterName){
			click(By.xpath(checkRosterCheckboxPattern.replace("subs_pattern", rosterName)));
			return me();
		}
		
		public RosterPage clickDeleteRosterIcon(){
			click(rosterDeleteRosterIcon);
			return me();
		}
		
		public String getRosterDeleteMsg(){
			return element(rosterDeleteMessage).getText();
		}
		
		public String getRosterAssignMsg(){
			return element(rosterAssignMsg).getText();
		}
}
	


