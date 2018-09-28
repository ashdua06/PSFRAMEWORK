package org.peoplestrong.altworklife.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverContext {
	private static DriverContext obj;
	private static WebDriver driver;
    // private constructor to force use of
    // getInstance() to create Singleton object
    private DriverContext() {}

    
    public static DriverContext getInstance()
    {
        if (obj==null)
            obj = new DriverContext();
        return obj;
    }
    
    public static WebDriver driver(){
    
    	 if (driver==null) {
    		String projectPath= System.getProperty("user.dir");
         	System.setProperty("webdriver.chrome.driver",projectPath+"/drivers/chromedriver.exe");
        	driver = new ChromeDriver();
        	
        }    	
    	return driver;
    } 
    
	


}
