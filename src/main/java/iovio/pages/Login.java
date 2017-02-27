package iovio.pages;

import org.junit.Assert;
import org.openqa.selenium.By;

import iovio.driver.IDriver;

public class Login {

	public static final String PYTHIA_URL = "https://pythia.qualibrate.com:7443/";
	public static final String ATE_URL = "https://ate.qualibrate.com:7443/";
	public static final By USERNAME_FIELD = By.xpath("//*[(@class='username') and (@name='q_username')]");
	public static final By USERNAME_FIELD1 = By.name("q_username");

	public static final By PASSWORD_FIELD = By.xpath("//*[(@class='username') and (@name='q_password')]");
	public static final By LOGIN_BUTTON = By.xpath("//*[(@class='login') and (@type='button')]");

	/**
	 * Performs an assert equals between a given string and a text obtained from a selector
	 * @param text The expected text
	 * @param selector The selector from which the actual text is obtained
	 */
	public static void validateSectionTitle(String text, By selector) {
		Assert.assertEquals("Something went wrong, login did not succeed", text, IDriver.getText(selector));
	}

}
