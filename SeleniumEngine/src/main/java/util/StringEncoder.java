package util;

public class StringEncoder {

	/**
	 * Encodes a String to UTF-8, while removing common found issue characters
	 * @param stringToEncode The string to be encoded
	 * @return Will return a String in UTF-8 enconding
	 * @throws Exception The class Exception
	 */

	public static String encodeToUTF8(String stringToEncode) throws Exception {
		byte[] x = stringToEncode.getBytes("UTF-8");
		return new String(x, "UTF-8").replace(" ", " ").replace("Ã", "").replace("Â", "").replace("â", "")
				.replace("\u0082", "").trim();
	}
}