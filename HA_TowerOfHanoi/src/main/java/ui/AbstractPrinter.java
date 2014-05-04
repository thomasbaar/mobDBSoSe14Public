package ui;

import domain.Place;

public abstract class AbstractPrinter implements Printer {

	
	protected final int noOfRings;
	protected final Place p1;
	protected final Place p2;
	protected final Place p3;

	public AbstractPrinter(int noOfRings, Place p1, Place p2, Place p3) {
		this.noOfRings = noOfRings;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}
	
	

}
