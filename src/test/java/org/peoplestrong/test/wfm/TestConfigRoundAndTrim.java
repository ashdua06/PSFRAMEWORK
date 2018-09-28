package org.peoplestrong.test.wfm;

import java.sql.SQLException;

import org.peoplestrong.altworklife.core.PropertyFileHandler;
import org.peoplestrong.altworklife.page.hris.LoginPage;
import org.peoplestrong.altworklife.utils.DBConnection;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestConfigRoundAndTrim {

	@BeforeTest
	public void login() throws ClassNotFoundException, SQLException,
			InterruptedException {
		System.out.println(this.getClass().getName());
		DBConnection.getConnection();
		new LoginPage().open_site("ctt-vivo").login_as(
				PropertyFileHandler.getInstance().getValue(
						"vivoEmployeeUsername"),
				PropertyFileHandler.getInstance().getValue("vivoEmpPassword"));
	}

	@AfterTest
	public void afterTest() {
		System.out.println(this.getClass().getName());
		System.out.println("after test");
		new LoginPage().logout();
	}

	@BeforeSuite
	public void beforeSuite() throws ClassNotFoundException, SQLException {
		System.out.println("before suite");
		DBConnection.getConnection();
	}

	@AfterSuite
	public void afterSuite() throws SQLException {
		System.out.println("in after suite");
		new LoginPage().closeBrowser();
		DBConnection.closeConnection();
	}

}
