package ui;

import util.StringUtil;
import domain.Place;

/**
 * Printer on System.out
 * 
 * @author baar
 * 
 */
public class ConsolePrinter extends AbstractPrinter {

	private String output = "";

	public ConsolePrinter(int noOfRings, Place p1, Place p2, Place p3) {
		super(noOfRings, p1, p2, p3);
	}

	/**
	 * Line-oriented implementation of printing the three towers;
	 */
	@Override
	public void printState() {
		initializeOutput();

		printLineEnd();

		for (int i = noOfRings; i >= 1; i--) {
			printLevel(i, p1);
			printSpace();
			printLevel(i, p2);
			printSpace();
			printLevel(i, p3);
			printLineEnd();
		}

		printStateEnd();
		flushStateAsString();
	}

	private void initializeOutput() {
		output = "";
	}

	private void flushStateAsString() {
		System.out.println(output);
	}

	private void printStateEnd() {
		Place[] places = { p1, p2, p3 };

		for (int i = 0; i < places.length; i++) {
			printString(StringUtil.multiplyString(noOfRings, " "));
			printString(places[i].getName());
			printString(StringUtil.multiplyString(noOfRings, " "));
			if (i < places.length - 1) {
				printSpace();
			}
		}
		printString("\n\n\n");
	}

	private void printLineEnd() {
		printString("\n ");

	}

	private void printSpace() {
		printString("  |  ");

	}

	private void printString(String string) {
		output = output + string;
	}

	/**
	 * Prints (2(k-1) + 1)-many '*' on System.out where k is the ringsize of the
	 * current level. In addition, surrounding whitespaces are printed so that
	 * the '*'s become centered.
	 * 
	 * There is an Exception for k=0: In this case we print one ' ', in order to
	 * fill the gap between left and right;
	 * 
	 * @param level
	 * @param p
	 */
	private void printLevel(int level, Place p) {
		int ringsizeAtCurrentLevel = p.getTower().getRingsizeAtLevel(level);
		int middle = 2 * ringsizeAtCurrentLevel - 1;
		int left = (middle < 0) ? noOfRings
				: ((2 * noOfRings + 1) - middle) / 2; // (oddNumber -
		// oddNumber)/2 yields a
		// correct integer
		int right = left;
		printString(StringUtil.multiplyString(left, " "));
		if (middle < 0) {
			printString(" ");
		} else {
			printString(StringUtil.multiplyString(middle, "*"));
		}
		printString(StringUtil.multiplyString(right, " "));
	}

}
