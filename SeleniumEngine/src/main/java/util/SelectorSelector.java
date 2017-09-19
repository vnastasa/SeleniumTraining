package util;

import org.openqa.selenium.By;

public class SelectorSelector {

	/**
	 * Obtains the value of a selector using reflection
	 * @param prefix The common part of the selector's name
	 * @param toConcatenate The extra part added to the selector's name in order to differentiate them
	 * @param className The class in which the selector value is declared
	 * @return Will return a By type object
	 * @throws Exception The class Exception
	 */

	public static By getNameSpecificSelector(String prefix, String toConcatenate,
			@SuppressWarnings("rawtypes") Class className) throws Exception {

		String selectorConstructor = prefix.concat(toConcatenate);
		By selector = (By) className.getField(String.valueOf(selectorConstructor)).get(null);
		return selector;
	}
}
