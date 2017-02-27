package util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import iovio.driver.IDriver;

public class MagicDriver {

	/**
	 * Waits for an element for 10 seconds  while checking every 10 milliseconds 
	 * that the element has reached a clickable state
	 * 
	 * @param selector The selector the WebElement the method is waiting for
	 */
	public static void waitForElement(By selector) {
		try {
			WebDriverWait wait = new WebDriverWait((WebDriver) IDriver.driver, 60);
			wait.ignoring(StaleElementReferenceException.class, NoSuchElementException.class)
					.pollingEvery(10, TimeUnit.MILLISECONDS).until(ExpectedConditions.elementToBeClickable(selector));
			wait.until(ExpectedConditions.numberOfElementsToBe(selector, 1));
		} catch (Exception e) {
			System.out.println("Something went wrong... " + e.getMessage());
		}
	}
	
	
	/**
	 * Waits for an element for 10 seconds  while checking every 10 milliseconds 
	 * that the element has reached an invisible state
	 * 
	 * @param selector The selector the WebElement the method is waiting for
	 */
	public static void waitForElementInvisibility(By selector) {
		try {
			WebDriverWait wait = new WebDriverWait((WebDriver) IDriver.driver, 60);
			wait.ignoring(StaleElementReferenceException.class, NoSuchElementException.class)
					.pollingEvery(10, TimeUnit.MILLISECONDS).until(ExpectedConditions.invisibilityOfElementLocated(selector));
		} catch (Exception e) {
			System.out.println("Something went wrong... " + e.getMessage());
		}
	}

	/**
	 * Checks every 10 milliseconds that the given element
	 * has reached a clickable state and then clicks it
	 * 
	 * @param selector The By type object that points to the element we are waiting to click on
	 */
	public static void click(By selector) {
		waitForElement(selector);
		IDriver.click(selector);
	}
	
	/**
	 * Double clicks a WebElement via Actions class while waiting for the element to be clickable
	 */
	public static void doubleClick(By selector) {
		Actions a = new Actions(IDriver.driver);
		waitForElement(selector);
		a.doubleClick(IDriver.driver.findElement(selector)).perform();
	}

	/**
	 * Checks every 10 milliseconds for the element in question
	 * to reach a clickable state and then will type the given text
	 * 
	 * @param selector The By type object that points to the element we are waiting to type in
	 * @param text The text we want to type
	 */
	public static void type(By selector, String text) {
		waitForElement(selector);
		IDriver.type(selector, text);
	}
}
