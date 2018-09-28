package org.peoplestrong.altworklife.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

	 private static Logger Log = LogManager.getLogger();
	
	 public static void info(String srg) {
		 Log.info(srg);		 
	 }	 

	 public static void debug(String srg) {
		 Log.debug(srg);		 
	 }
	 
	 public static void warn(String srg) {
		 Log.warn(srg);		 
	 }
	 
	 public static void error(String srg) {
		 Log.error(srg);		 
	 }
	 
	 public static void fatal(String srg) {
		 Log.fatal(srg);		 
	 }
}
