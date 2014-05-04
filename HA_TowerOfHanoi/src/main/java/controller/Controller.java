package controller;

import ui.ConsolePrinter;
import ui.Printer;
import algo.MoveStrategy;
import algo.MoveStrategyFactory;
import algo.MoveStrategyKind;
import domain.Place;
import domain.Ring;
import domain.Tower;

public class Controller {

	private int noOfRings;

	private Place p1;
	private Place p2;
	private Place p3;

	private MoveStrategy strategy;
	protected Printer printer; // made protected for testing

	/**
	 * Initializes the start state with three towers and <code>noOfRings</code>
	 * -many rings on the first tower. Also creates the printer.
	 * 
	 * @param noOfRings
	 */
	public void setUp(int noOfRings) {
		this.noOfRings = noOfRings;
		initialize();
		createPrinter();
	}

	public void setAlgorithm(MoveStrategyKind kind) {
		strategy = MoveStrategyFactory.create(kind, printer);
	}

	/**
	 * Starts the move-Algorithm. Assumes that algorithm has been already chosen before.
	 */
	public void start() {
		if (strategy == null) {
			throw new IllegalStateException("strategy not set");
		}
		printer.printState(); // shows the start state
		strategy.move(p1, p2, p3, noOfRings);
	}

	private void initialize() {
		Tower t1 = new Tower();
		Tower t2 = new Tower();
		Tower t3 = new Tower();

		// fill first tower starting with the largest ring
		for (int i = noOfRings; i >= 1; i--) {
			t1.push(new Ring(i));
		}

		p1 = new Place("A", t1);
		p2 = new Place("B", t2);
		p3 = new Place("C", t3);
	}

	private void createPrinter() {
		printer = new ConsolePrinter(noOfRings, p1, p2, p3);
	}

}
