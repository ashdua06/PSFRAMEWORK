package org.peoplestrong.altworklife.core;

import java.io.IOException;
import java.util.Properties;

public class PropertyFileHandler {
	
	
	 private static PropertyFileHandler keyMasterInstance = null;
	    private Properties props = null;
	    private PropertyFileHandler() throws IOException {
	        props = new Properties();
	        try {
	            props.load(PropertyFileHandler.class.getClassLoader().getResourceAsStream("AltWorkLife.properties"));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    public static synchronized PropertyFileHandler getInstance() {
	        if (keyMasterInstance == null)
	            try {
	                keyMasterInstance = new PropertyFileHandler();
	            } catch (IOException e) {
	                System.out.println(e.toString());
	            }
	        return keyMasterInstance;
	    }
	    
	    public String getValue(String propKey) {
	         return this.props.getProperty(propKey);
	    }
	    
	    /*public String getValue(String propertyFileName,String propKey) {
	         return this.props.getProperty(propKey);
	    }*/



}
