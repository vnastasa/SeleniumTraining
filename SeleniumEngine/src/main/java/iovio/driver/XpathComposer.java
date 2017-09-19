package iovio.driver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XpathComposer {

	public static String composed(Telexpath obj) {

		String and = "and";
		String name = "(@name=" + "'" + obj.getName() + "')";
		String id = "(@id=" + "'" + obj.getId() + "')";
		String type = "(@type=" + "'" + obj.getType() + "')";
		String tagName = "//" + obj.getTagName() + "[";
		String className = "(@class=" + "'" + obj.getClassName() + "')or(@className=" + "'" + obj.getClassName() + "')";
		String innerText = "(@innerText=" + "'" + obj.getInnerText() + "')";
		String innerHTML = "(@innerHTML=" + "'" + obj.getInnerHTML() + "')";
		String href = "(@href=" + "'" + obj.getHref() + "')";
		String src = "(@src=" + "'" + obj.getSrc() + "')";
		String title = "(@title=" + "'" + obj.getTitle() + "')";
		String value = "(@value=" + "'" + obj.getValue() + "')";
		String index = "(@index=" + "'" + obj.getIndex() + "')";

		String complete = tagName;

		if (obj.getName() != null && obj.getName().length() != 0) {
			complete = complete.concat(name + and);
		}

		if (obj.getId() != null && obj.getId().length() != 0) {
			complete = complete.concat(id + and);
		}

		if (obj.getType() != null && obj.getType().length() != 0) {
			complete = complete.concat(type + and);
		}

		if (obj.getClassName() != null && obj.getClassName().length() != 0) {
			complete = complete.concat(className + and);
		}

		if (obj.getInnerText() != null && obj.getInnerText().length() != 0) {
			complete = complete.concat(innerText + and);
		}

		if (obj.getInnerHTML() != null && obj.getInnerHTML().length() != 0) {
			complete = complete.concat(innerHTML + and);
		}

		if (obj.getHref() != null && obj.getHref().length() != 0) {
			complete = complete.concat(href + and);
		}

		if (obj.getSrc() != null && obj.getSrc().length() != 0) {
			complete = complete.concat(src + and);
		}

		if (obj.getTitle() != null && obj.getTitle().length() != 0) {
			complete = complete.concat(title + and);
		}

		if (obj.getValue() != null && obj.getValue().length() != 0) {
			complete = complete.concat(value + and);
		}

		if (obj.getIndex() != null && obj.getIndex().length() != 0) {
			complete = complete.concat(index + and);
		}
		if (complete.endsWith(and)) {
			int suffixStart = complete.length() - 3;
			int expressionStart = 0;
			complete = complete.subSequence(expressionStart, suffixStart).toString();
		}

		complete = complete.concat("]");

		Pattern pattern = Pattern.compile("\\[\\]");
		Matcher matcher = pattern.matcher(complete);

		if (matcher.find()) {
			complete = null;

		}
		return complete;
	}

}
