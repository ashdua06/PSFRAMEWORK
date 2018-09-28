package org.peoplestrong.altworklife.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.peoplestrong.altworklife.core.Log;

public class DBConnection {
	
	public static Connection con;	
	
	public static  void getConnection() {			
		try {
			String userName = "Apuser";
			String password = "Apuser@789";
			String url = "jdbc:jtds:sqlserver://10.225.241.184:11433/TalentPact";
			con = DriverManager.getConnection(url, userName, password);				
		}
		catch(SQLException e) {
			Log.info(e.getMessage());
		}
		
	}
		
	public static void executeQuery(String query) {
		try {
			if(con != null)	{
				Statement s = con.createStatement();
				int numOfRowsAffected = s.executeUpdate(query);
				Log.info("Query Updated Number of Rows: "+ numOfRowsAffected);
				Thread.sleep(2000);				
			}
		} catch (SQLException | InterruptedException e) {
			Log.info(e.getMessage());
		}
		
	
	}
	
	public static ResultSet getQueryResult(String query)  {
		ResultSet m_ResultSet = null ;
		if(con != null)	{
			try {
			Statement s = con.createStatement();
			m_ResultSet = s.executeQuery(query);
			}
			catch(Exception e ) {
				if(e.getMessage().contains("SQL")){
					Log.info(e.getMessage());
					try {
						con.close();
						getConnection();
						Statement s = con.createStatement();
						m_ResultSet = s.executeQuery(query);
					} catch (SQLException e1) {						
						Log.info(e.getMessage());
					}
						
				}
			}
			
		}
		return m_ResultSet;
	}
	
	//close connection
	public static void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

