package util;

import java.util.Scanner;

import org.junit.Assert;
import org.openqa.selenium.By;

import iovio.driver.IDriver;

public class TextValidator {

	/**
	 * A line-by-line parser that validates the data on each row
	 * @param selector The selector which will identify the element containing the rows of text
	 * @param expectedData The expected data to be found on each row
	 * @throws Exception The class Exception
	 */

	public void lineByLineTextValidator(By selector, String expectedData) throws Exception {
		String actualData = IDriver.getText(selector);
		Scanner scanner = new Scanner(actualData);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			Assert.assertTrue(line.equals(expectedData));
		}
		scanner.close();
	}
}
