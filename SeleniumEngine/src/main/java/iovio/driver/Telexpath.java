package iovio.driver;

public class Telexpath {

	private final String name;
	private final String id;
	private final String type;
	private final String tagName;
	private final String className;
	private final String innerText;
	private final String innerHTML;
	private final String href;
	private final String src;
	private final String title;
	private final String value;
	private final String index;

	private Telexpath(TelexpathBuilder builder) {
		this.name = builder.name;
		this.id = builder.id;
		this.type = builder.type;
		this.tagName = builder.tagName;
		this.className = builder.className;
		this.innerText = builder.innerText;
		this.innerHTML = builder.innerHTML;
		this.href = builder.href;
		this.src = builder.src;
		this.title = builder.title;
		this.value = builder.value;
		this.index = builder.index;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getTagName() {
		return tagName;
	}

	public String getClassName() {
		return className;
	}

	public String getInnerText() {
		return innerText;
	}

	public String getInnerHTML() {
		return innerHTML;
	}

	public String getHref() {
		return href;
	}

	public String getSrc() {
		return src;
	}

	public String getTitle() {
		return title;
	}

	public String getValue() {
		return value;
	}

	public String getIndex() {
		return index;
	}

	public static class TelexpathBuilder {

		private String name;
		private String id;
		private String type;
		private String tagName;
		private String className;
		private String innerText;
		private String innerHTML;
		private String href;
		private String src;
		private String title;
		private String value;
		private String index;

		public TelexpathBuilder withName(String name) {
			this.name = name;
			return this;
		}

		public TelexpathBuilder withId(String id) {
			this.id = id;
			return this;
		}

		public TelexpathBuilder withType(String type) {
			this.type = type;
			return this;
		}

		public TelexpathBuilder withTagName(String tagName) {
			this.tagName = tagName;
			return this;
		}

		public TelexpathBuilder withClassName(String className) {
			this.className = className;
			return this;
		}

		public TelexpathBuilder withInnerText(String innerText) {
			this.innerText = innerText;
			return this;
		}

		public TelexpathBuilder withInnerHTML(String innerHTML) {
			this.innerHTML = innerHTML;
			return this;
		}

		public TelexpathBuilder withHref(String href) {
			this.href = href;
			return this;
		}

		public TelexpathBuilder withSrc(String src) {
			this.src = src;
			return this;
		}

		public TelexpathBuilder withTitle(String title) {
			this.title = title;
			return this;
		}

		public TelexpathBuilder withValue(String value) {
			this.value = value;
			return this;
		}

		public TelexpathBuilder withIndex(String index) {
			this.index = index;
			return this;
		}

		public Telexpath build() {
			return new Telexpath(this);
		}
	}

}
