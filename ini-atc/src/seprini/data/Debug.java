package seprini.data;

/**
 * Convienience class for debugging
 * 
 * @author Crembo
 * 
 */
public class Debug {

	/**
	 * Prints debug message to console (println) <br>
	 * Takes Config.DEBUG_TEXT into account
	 * 
	 * @param ob
	 */
	public static void msg(Object ob) {
		if (Config.DEBUG_TEXT)
			System.out.println(ob);
	}

	public static void error(Object ob) {
		if (Config.DEBUG_TEXT)
			System.out.println("Error: " + ob);
	}
}
