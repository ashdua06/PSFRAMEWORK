package org.peoplestrong.altworklife.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.peoplestrong.altworklife.core.BaseClass;

public class Utils {

	private static XSSFSheet ExcelWSheet;	 
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	
	public static long GenerateRandomNumber(int charLength) {
        return (charLength < 1 ? 0 : new Random()
                .nextInt((9 * (int) Math.pow(10, charLength - 1)) - 1)
                + (int) Math.pow(10, charLength - 1));
    }
	
	public static String GenerateRandomString(int charLength) {
		
		char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < charLength; i++)
		    sb.append(chars[rnd.nextInt(chars.length)]);

		return sb.toString();
	}
	
	// Get sheet row num and column num
	public int getSheetRowNum(String fileName,String sheetName) throws IOException{
		
		FileInputStream fs = new FileInputStream(BaseClass.CURRENT_DIR+"/ExcelData/"+fileName);
		ExcelWBook = new XSSFWorkbook(fs);
		ExcelWSheet = ExcelWBook.getSheet(sheetName);
		return ExcelWSheet.getPhysicalNumberOfRows();
	}
	
public int getSheetColNum(String fileName,String sheetName) throws IOException{
		
		FileInputStream fs = new FileInputStream(BaseClass.CURRENT_DIR+"/ExcelData/"+fileName);
		ExcelWBook = new XSSFWorkbook(fs);
		ExcelWSheet = ExcelWBook.getSheet(sheetName);
		return ExcelWSheet.getRow(0).getPhysicalNumberOfCells();
	}
	// Get the sheet data in 2 dimensional array from a sheet
		// Pass file path as argument and sheet Name from which data need to be retrieved.
		public String[][] getSheetData(String fileName,String sheetName) throws IOException{
			
			FileInputStream fs = new FileInputStream(BaseClass.CURRENT_DIR+"/ExcelData/"+fileName);
			ExcelWBook = new XSSFWorkbook(fs);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			DataFormatter df = new DataFormatter();
			int rownum = ExcelWSheet.getPhysicalNumberOfRows();
			int colnum = ExcelWSheet.getRow(0).getPhysicalNumberOfCells();
			String[][] sheetDataObject = new String[rownum][colnum];
			for(int i=0 ;i<rownum;i++){
				for (int j=0;j<colnum;j++){
					
					System.out.println("row: "+rownum + "col: "+colnum);
					//GET CELL
		            XSSFCell cell1 = ExcelWSheet.getRow(i).getCell(j);  
		            //String value = df.formatCellValue(cell1);
					sheetDataObject[i][j]= df.formatCellValue(cell1);
					System.out.println("Values: rownum: "+rownum + " col: "+colnum + " value: "+sheetDataObject[i][j]);
				}
			}
			return sheetDataObject;
		}
		
		// Get month Name as per month(int) passed to it.
		// Pass month Number as an argument and if needed the Month name in short form(Jan, Feb etc) , Pass fullName=false
		// For Jan- pass 0 , For Dec, pass 11.
		public String getMonthName(int month,boolean fullName){
		    String[] monthFullNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		    String name = monthFullNames[month];
		    if(!fullName)
		    		name=name.substring(0, 3);
		    return name;
		}
		
		// Get month number as per month name(string) passed to it.
			// Pass Month Name(Ex- Aug, Jan,Sep) as argument.
			public int getMonthNum(String monthName){
				int monthNum=-1;
			    String[] monthFullNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
			    	for(int i=0;i<monthFullNames.length;i++){
			    		if(monthFullNames[i].equalsIgnoreCase(monthName) ){
			    			monthNum=i;
			    			break;
			    		}
			    	}
			    return monthNum;
			}
		
		// Add days in current Date  , If current date is 25, adding 10 will make it as 5 of next month
		// Return Date would be in Mon Date, Year Format(Jul 13,2018)
		public String addDays(int days){
			Calendar cal = Calendar.getInstance();
			System.out.println(cal.getTime());
			cal.add(Calendar.DAY_OF_MONTH, days);
			
			System.out.println(cal.getTime() );
			System.out.println(cal.get(Calendar.DAY_OF_MONTH));
			System.out.println(cal.get(Calendar.MONTH));
			System.out.println(getMonthName(cal.get(Calendar.MONTH),false));
			System.out.println(cal.get(Calendar.YEAR));
			String newDate = getMonthName(cal.get(Calendar.MONTH),false) +" "+cal.get(Calendar.DAY_OF_MONTH)+", "+cal.get(Calendar.YEAR);
			return newDate;
		}
		
		// Add month to current month and retrun the short name of that month
		// Ex- current month is 5(May).
		// numOfMonth=4 , It will return (5+4=9) Sep as output
		public int addMonth(int numOfMonth){
			Calendar cal = Calendar.getInstance();
			System.out.println("CURRENT MONTH"+ Calendar.MONTH);
			cal.add(Calendar.MONTH, numOfMonth);
			System.out.println("###"+cal.get(Calendar.MONTH));
			return cal.get(Calendar.MONTH);
		}
		//
		public void logDebugging(String logs){
			if(true)
				System.out.println(logs);
		}
		
		// Get the of the week as per the number passed.
			// Monday as 0, Sunday as 6
			public String getWeekDay(int week_num){
				HashMap<Integer, String> week_map = new HashMap<Integer, String>();
					week_map.put(0,"Monday");
					week_map.put(1,"tuesday");
					week_map.put(2,"wednesday");
					week_map.put(3,"thursday");
					week_map.put(4,"friday");
					week_map.put(5,"saturday");
					week_map.put(6,"sunday");
				return week_map.get(week_num);
			}
		
	
	
	


}
