package iovio;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import iovio.driver.IDriver;
import iovio.pages.Home;
import iovio.pages.Login;

public class AteUpdateAdapter {

	private String username = "Jenkins";
	private String password = "Jenkins@q123";

	@Before
	//execute precondition

	@Test
	public void test() throws Exception {

		IDriver.get(Login.ATE_URL);
		IDriver.type(Login.USERNAME_FIELD, username);
		IDriver.type(Login.PASSWORD_FIELD, password);
		IDriver.click(Login.LOGIN_BUTTON);
		Home.waitForLoginComplete();
		IDriver.forceDisplayBlockAndClick(Home.ADMINISTRATION, Home.COMPANY_SETTINGS);
		//validate
	}

	/**
	 * After method to be executed once the actual test has ended
	 */
	@After
	public void cleanUp() {
		IDriver.quit();
	}
}
