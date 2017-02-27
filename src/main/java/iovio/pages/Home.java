package iovio.pages;

import org.junit.Assert;
import org.openqa.selenium.By;

import iovio.driver.IDriver;
import util.MagicDriver;

public class Home {

	public static final By JENKINS_WELCOME_MESSAGE = By.xpath("//*[text()='Welcome to Qualibrate, Jenkins.']");
	public static final By ADMINISTRATION = By.xpath("//*[@class='MainCss-homeGeneralMenuItemSubMenu']");
	public static final By COMPANY_SETTINGS = By.xpath("//*[@class='MainCss-companySettingsBtn']");

	/**
	 * Performs an assert equals between a given string and a text obtained from a selector
	 * @param text The expected text
	 * @param selector The selector from which the actual text is obtained
	 */
	public static void validateSectionTitle(String text, By selector) {
		Assert.assertEquals(text, IDriver.getText(selector));
	}

	public static void waitForLoginComplete() {
		MagicDriver.waitForElement(JENKINS_WELCOME_MESSAGE);
		Assert.assertTrue("Home page was not loaded in due time (60 s)", IDriver.isPresent(JENKINS_WELCOME_MESSAGE));
	}

}
