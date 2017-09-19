package iovio.driver;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.Sleeper;

public class IDriver {

	public static RemoteWebDriver driver = new FirefoxDriver();
	public static int timeout = 60;
	public static Actions actions = new Actions(driver);

	public IDriver(FirefoxDriver firefoxDriver) {
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
		driver = new FirefoxDriver(capabilities);
	}

	/**
	 * Sets the timeout for page load & implicit waits.
	 * @param timeout in Seconds.
	 */
	public static void setTimeout(int timeout) {
		IDriver.timeout = timeout;
		driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	/**
	 * Get the details of the platform, derived from the capabilities.
	 * @return A string with the platform name, major version, minor version and
	 *         family name.
	 */
	public static String getPlatformInfo() {
		String platformInfo;
		Capabilities caps = driver.getCapabilities();
		platformInfo = "name: " + caps.getPlatform().name() + "; ";
		platformInfo += "Major Version: " + caps.getPlatform().getMajorVersion() + "; ";
		platformInfo += "Minor version: " + caps.getPlatform().getMinorVersion() + "; ";
		platformInfo += "Family name: " + caps.getPlatform().family().name();
		return platformInfo;
	}

	/**
	 * Get the name of the browser, derived from the capabilities.
	 * @return the name of the browser.
	 */
	public static String getBrowser() {
		return driver.getCapabilities().getBrowserName();
	}

	/**
	 * Get the version of the browser, derived from the capabilities.
	 * @return the version name as a String.
	 */
	public static String getVersion() {
		return driver.getCapabilities().getVersion();
	}

	/**
	 * Get the screenshot in the type of parameter target.
	 * @param target the output type e.g. OutputType.BYTES.
	 * @param <X> Output type
	 * @return The screenshot content in format of parameter target.
	 */
	public static <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
		return IDriver.driver.getScreenshotAs(target);
	}

	/**
	 * Method to verify if an element is displayed.
	 * 
	 * @param selector the selector (e.g. By.id or By.xpath)
	 * @return true or false whether the element is displayed
	 */
	public static boolean isDisplayed(By selector) {
		return driver.findElement(selector).isDisplayed();
	}

	/**
	 * Method to verify if an element is present. Suitable of checks in tests.
	 * Doesn't throw error when element is absent.
	 * 
	 * @param selector By selector to identify an element
	 * @return true or false
	 */
	public static boolean isPresent(By selector) {
		try {
			driver.findElement(selector);
			return true;
		} catch (NoSuchElementException nsex) {
			return false;
		}
	}

	/**
	 * Method to verify if an element is present. Suitable of checks in tests.
	 * Doesn't throw error when element is absent.
	 * 
	 * @param selector By selector to identify an element
	 * @param timeout The number of seconds to wait for an element to be present.
	 * @return true or false
	 */
	public static boolean isPresent(By selector, int timeout) {
		int previousTimeout = IDriver.timeout;
		setTimeout(timeout);
		try {
			driver.findElement(selector);
			return true;
		} catch (NoSuchElementException nsex) {
			return false;
		} finally {
			setTimeout(previousTimeout);
		}
	}

	/**
	 * Forward of RemoteWebDriver.getCurrentUrl()
	 *
	 * @return The URL that is present.
	 * @see RemoteWebDriver#getCurrentUrl()
	 */
	public static String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	/**
	 * Send keys to a webelement identified by selenium By selector. 
	 * Used by selenium sendKeys
	 * 
	 * @param selector the selenium By object as selector
	 * @param value string of characters to send to webelement
	 */
	public static void type(By selector, String value) {
		WebElement we = driver.findElement(selector);
		we.sendKeys(Keys.DELETE);
		we.sendKeys(value);
	}

	/**
	 * Forward of the {@link RemoteWebDriver#get(String)} method.
	 *
	 * @param url The url to navigate to.
	 */
	public static void get(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}

	/**
	 * Waits for an Element (identified by selector By) to be displayed and then
	 * selects the visible option of that element.
	 *
	 * @param selector The selector to identify the Element.
	 * @param value The value of the item to select.
	 */
	public static void select(By selector, String value) {
		new Select(driver.findElement(selector)).selectByVisibleText(value);
	}

	/**
	 * Clicks on a checkbox when check is true and the checkbox is unchecked, or
	 * when check is false and the checkbox is checked. e.g. to make sure a
	 * checkbox result in checked or unchecked state. To always perform a
	 * check/uncheck action, use {@link #click(By)} method instead.
	 *
	 * @param selector The By selector of the checkbox. E.g. By.id or By.xpath
	 * @param check Checks whether or not the checkbox should be checked.
	 */
	public static void checkCheckBox(By selector, boolean check) {
		WebElement checkbox = driver.findElement(selector);
		if ((!checkbox.isSelected() & check) | (checkbox.isSelected() & !check)) {
			checkbox.click();
		}
	}

	/**
	 * Waits for the Element to be clickable
	 * @param selector The selector to identify the element
	 */
	public static void waitForElementToBeClickable(final By selector) {
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(selector));
	}

	/**
	 * Finds the element (identified by the selector By) to be displayed and
	 * then clicks on it.
	 * @param selector The selector of the element to perform the click upon.
	 */
	public static void click(By selector) {
		driver.findElement(selector).click();
	}

	/**
	 * Finds the element (identified by the selector By) to be displayed and
	 * then clicks on it via the actions class.
	 * @param selector The selector of the element to perform the click upon.
	 */
	public static void forceClick(By selector) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("var evt = document.createEvent('MouseEvents');"
				+ "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
				+ "arguments[0].dispatchEvent(evt);", findElement(selector));
	}

	/**
	 * Double clicks a WebElement via Actions class
	 * @throws Exception 
	 */
	public static void doubleClick(By selector) throws Exception {
		WebElement element = driver.findElement(selector);
		actions.moveToElement(element).doubleClick().perform();
	}

	/**
	 * Searches for an element and click on it, if NoSuchElementException is
	 * thrown, return false, otherwise return true
	 * @param selector The selector to identify the element
	 * @return True if the click was successful, false if NoSuchElementException is thrown
	 */
	public static boolean clickIfPresent(By selector) {
		try {
			driver.findElement(selector).click();
			return true;
		} catch (NoSuchElementException nsex) {
			return false;
		}
	}

	/**
	 * Searches for an element and click on it, if NoSuchElementException is
	 * thrown, return false, otherwise return true
	 * 
	 * @param selector The selector to identify the element
	 * @param timeout The number of seconds for timeout, set only for this action
	 * @return true if the click was successful, false if NoSuchElementException is thrown
	 */
	public static boolean clickIfPresent(By selector, int timeout) {
		int previousTimeout = IDriver.timeout;
		setTimeout(timeout);
		try {
			driver.findElement(selector).click();
			return true;
		} catch (NoSuchElementException nsex) {
			return false;
		} finally {
			setTimeout(previousTimeout);
		}
	}

	/**
	 * Method to find elements, but respond faster because of the timeout is set
	 * to 1 second firstly, then it is set back to 30 seconds.
	 *
	 * @param selector The selector of class {@link By}
	 * @return The elements that are found.
	 */
	public static List<WebElement> findElementQuickly(By selector) {
		int previousTimeout = IDriver.timeout;
		setTimeout(1);
		try {
			return driver.findElements(selector);
		} finally {
			setTimeout(previousTimeout);
		}
	}

	/**
	 * Forward of the {@link RemoteWebDriver#findElements(By)} method.
	 *
	 * @param selector The selector to use to find the Elements with.
	 * @return The elements that are found.
	 */
	public static List<WebElement> findElements(By selector) {
		return driver.findElements(selector);
	}

	/**
	 * Gets the text/value of a WebElement identified by the selector
	 *
	 * @param selector The By selector (e.g. xpath, id, cssSelector)
	 * @return The text of the WebElement
	 */
	public static String getText(By selector) {
		return driver.findElement(selector).getText();
	}

	/**
	 * Submits e.g. a form, identified by the By selector.
	 * 
	 * @param selector the selector as By object (e.g. xpath, id, cssSelector)
	 */
	public static void submit(By selector) {
		driver.findElement(selector).submit();
	}

	/**
	 * Waits for the Element (identified by xpath) to displayed and then gets
	 * the attribute src.
	 *
	 * @param selector The selenium By selector of the element.
	 * @return The path or url that was present in the src attribute in the
	 *         String format.
	 */
	public static String getSrcUrl(By selector) {
		return driver.findElement(selector).getAttribute("src");
	}

	/**
	 * Gets the url and changes it if it changes to the expected url value
	 * within 10 seconds.
	 *
	 * @param selector The selector of the Element to get the url from.
	 * @param expected The expected url value.
	 * @return The url that is expected of the url that is present after 10
	 *         seconds.
	 * @throws Exception 
	 */
	public static String getSrcUrlToBePresent(By selector, String expected) throws Exception {
		LocalDateTime date = LocalDateTime.now();
		String actual = getSrcUrl(selector);
		while (!expected.equals(actual)) {
			if (LocalDateTime.now().isAfter(date.plusSeconds(10))) {
				break;
			}
			Sleeper.sleep(1000);
			actual = getSrcUrl(selector);
		}
		return actual;
	}

	/**
	 * Waits for the Element to be displayed, then identifies the Element by By
	 * selector, then gets the value of cssProperty.
	 *
	 * @param selector The selector of the Element to search the css property for.
	 * @param cssProperty The css property name.
	 * @return The value of the css property.
	 */
	public static String getCssValue(By selector, String cssProperty) {
		return driver.findElement(selector).getCssValue(cssProperty);
	}

	/**
	 * Gets the value of a cssProperty of a WebElement.
	 *
	 * @param element The WebElement of which the css value will be returned
	 * @param cssProperty The css property name.
	 * @return The value of the css property.
	 */
	public static String getCssValue(WebElement element, String cssProperty) {
		return element.getCssValue(cssProperty);
	}

	/**
	 * Waits for the Element is displayed (identification via By selector), then
	 * returns the attributes value (identification via attribute name).
	 *
	 * @param selector The By selector to identify the Element. E.g. By.id() or
	 *            By.xpath()
	 * @param attribute The name of the attribute that you want the value of.
	 * @return the attributes value.
	 */
	public static String getAttributeValue(By selector, String attribute) {
		return driver.findElement(selector).getAttribute(attribute);
	}

	/**
	 * Forward of switchTo().frame() method.
	 *
	 * @param selector The By selector to identify the Element.
	 * @see RemoteWebDriver#switchTo()
	 */

	public static void switchToIframe(By selector) {
		driver.switchTo().frame(driver.findElement(selector));
	}

	/**
	 * Forward of switchTo().defaultContent() method.
	 *
	 * @see RemoteWebDriver#switchTo()
	 */
	public static void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	/**
	 * Forward of {@link RemoteWebDriver#executeScript(String, Object...)}
	 *
	 * @param command The command to execute.
	 * @param params The parameters to give to the command.
	 * @return Result.
	 */
	public static Object executeScript(String command, Map<String, Object> params) {
		return driver.executeScript(command, params);
	}

	/**
	 * Forward of {@link RemoteWebDriver#execute} without parameters.
	 * @param command The command to execute.
	 * @return Result.
	 */
	public static Object executeScript(String command) {
		return driver.executeScript(command);
	}

	/**
	 * Forward of {@link RemoteWebDriver#quit()}.
	 */
	public static void quit() {
		driver.quit();
		killGeckoDriver();
		System.out.println("...::IDriver Terminated Successfully::...");
	}

	/**
	 * Forward of {@link RemoteWebDriver#close()}.
	 */
	public static void close() {
		driver.close();
	}

	/**
	 * RemoteWebDriver manage method to access manage options.
	 * @return webdriver options.
	 */
	public static WebDriver.Options manage() {
		return driver.manage();
	}

	/**
	 * Forward of the {@link RemoteWebDriver#findElement(By)} method.
	 *
	 * @param selector The selector to use to find the Element with.
	 * @return The element that is found.
	 */
	public static WebElement findElement(By selector) {
		return driver.findElement(selector);
	}

	/**
	 * Moves the mouse cursor to the location defined by the Element refered by the parameter.
	 * 
	 * @param selector The selector used to identify the Element we are performing the action on.
	 */
	public static void mouseOver(By selector) {
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(selector);
		action.moveToElement(we).build().perform();
	}

	public static void forceDisplayBlockAndClick(By parentSelector, By childSelector) {
		WebElement element = driver.findElement(parentSelector);
		driver.executeScript(
				"arguments[0].setAttribute('style', 'display: block; position: absolute; z-index: 5; visibility: visible; left: 727px;')",
				element);
		click(childSelector);
		driver.executeScript(
				"arguments[0].setAttribute('style', 'display: none; position: absolute; z-index: 5; visibility: visible; left: 727px;')",
				element);
	}

	/**
	 * Presses 'ENTER' / 'RETURN' key
	 * @param selector given WebElement to perform the action on
	 */
	public static void pressEnter(By selector) {
		driver.findElement(selector).sendKeys(Keys.RETURN);
	}

	public static void killGeckoDriver() {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
			System.out.println("...::GeckoDriver Terminated Successfully::...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}