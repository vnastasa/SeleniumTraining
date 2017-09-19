package util;

/**
 * This class is able to stop the current Thread for a certain time using
 * Thread.sleep().
 */
public class Sleeper {

	/**
	 * This method will sleep the active thread while treating Exceptions internally
	 * @param milli The duration of sleep in milliseconds
	 */

	public static void sleep(int milli) {
		try {
			Thread.sleep(milli);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}