package algo;

import domain.Place;

public interface MoveStrategy {

	/**
	 * Moves <code>noOfRings</code>-many Rings from <code>from</code>-place to <code>to</code>-place.
	 * @param from
	 * @param to
	 * @param middle
	 * @param noOfRings
	 */
	void move(Place from, Place to, Place middle, int noOfRings);
	
}
