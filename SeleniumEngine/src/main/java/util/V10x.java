package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;

import iovio.driver.IDriver;
import iovio.driver.V10;

public class V10x {

	/**
	 * Running on Selenium 3.3.1 using geckodriver on Firefox 47.0.1 or later
	 * Requires geckodriver - https://github.com/mozilla/geckodriver/releases
	 * Geckodriver.exe path needs to be added to environment variables PATH for Windows execution
	 * Current webdriver implementation includes by default handling of geckodriver through marionette
	 * @param args
	 */

	public static void main(String[] args) {

		String url = "https://aws.amazon.com/";
		Map<String, Integer> _xpaths = new HashMap<String, Integer>();
		List<String> xpathsList = new ArrayList<String>();
		IDriver.get(url);

		for (String t : V10.tags) {
			xpathsList.addAll(V10.gather(url, By.tagName(t)));
		}
		for (String xpath : xpathsList) {
			if (_xpaths.containsKey(xpath)) {
				_xpaths.put(xpath, _xpaths.get(xpath) + 1);
			}
			_xpaths.putIfAbsent(xpath, 1);
		}
		for (String temp : _xpaths.keySet()) {
			int xtmp = _xpaths.get(temp);
			if (xtmp == 1) {
				System.out.println(temp + "[" + xtmp + "]");
			} else {
				System.out.println(temp + "[" + 1 + "]" + "[" + "'but actual index count was: " + xtmp + "'" + "]");
			}
		}
		IDriver.quit();
	}
}