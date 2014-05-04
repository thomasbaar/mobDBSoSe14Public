package util;

public class StringUtil {

	/**
	 * Returns the concatenation of <code>no</code> many <code>orig</code> strings.
	 * If <code>no</code> is below 1, the empty string is returned.
	 */
	
	public static String multiplyString(int no, String orig){
		String result = "";
		for (int i = 0; i < no; i++) {
			result = result + orig;
		}
		return result;
	}
	
}
