package iovio.driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class V10 {

	public static List<String> gather(String url, By tag) {

		List<WebElement> el = IDriver.findElements(tag);
		List<String> xpath = new ArrayList<String>();
		try {
			for (WebElement e : el) {
				WebDriverWait wait = new WebDriverWait((WebDriver) IDriver.driver, 0);
				wait.until(ExpectedConditions.visibilityOf(e));
				Telexpath xpathObject = new Telexpath.TelexpathBuilder().withClassName(e.getAttribute("className"))
						.withHref(e.getAttribute("href")).withId(e.getAttribute("id"))
						.withIndex(e.getAttribute("index")).withInnerText(e.getAttribute(e.getText()))
						.withName(e.getAttribute("name")).withSrc(e.getAttribute("src")).withTagName(e.getTagName())
						.withTitle(e.getAttribute("title")).withType(e.getAttribute("type"))
						.withValue(e.getAttribute("value")).build();
				if (XpathComposer.composed(xpathObject) != null && !XpathComposer.composed(xpathObject).isEmpty()) {
					xpath.add(XpathComposer.composed(xpathObject));
				}
			}
		} catch (Exception e) {
		}
		return xpath;
	}

	public static List<String> tags = Arrays.asList("!DOCTYPE", "a", "abbr", "address", "area", "article", "aside",
			"audio", "b", "base", "bdi", "bdo", "blockquote", "body", "br", "button", "canvas", "caption", "cite",
			"code", "col", "colgroup", "command", "datalist", "dd", "del", "details", "dfn", "div", "dl", "dt", "em",
			"embed", "fieldset", "figcaption", "figure", "footer", "form", "h1", "h2", "h3", "h4", "h5", "h6", "head",
			"header", "hgroup", "hr", "html", "i", "iframe", "img", "input", "ins");
}
