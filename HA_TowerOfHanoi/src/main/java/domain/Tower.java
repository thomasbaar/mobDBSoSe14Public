package domain;
import java.util.List;
import java.util.ArrayList;


/**
 * A <code>Tower</code> is a container for a list of <code>Ring</code>s. 
 * It is the responsibility of the <code>Tower</code> to ensure, that the contained <code>Ring</code>s
 * are ordered by size, the highest ring being the smallest.
 * 
 * The class <code>Tower</code> provides typical Stack-methods in order to manipulate its content.
 * 
 * @author baar
 *
 */
public class Tower {

	private List<Ring> rings = new ArrayList<Ring>();

	/**
	 * 
	 * @return - last (smallest) ring or NULL if empty.
	 */
	private Ring getHighestRing() {
		if (rings.isEmpty())
			return null;
		return rings.get(rings.size()-1);
	}

	/**
	 * Attempts to push argument <code>r</code>  on <code>this</code>. Returns, whether push() was
	 * successful.
	 * 
	 * @param r
	 * @return
	 */
	public boolean push(Ring r) {
		if (r == null) {
			throw new IllegalArgumentException("argument must not be null");
		}
		Ring lastRing = getHighestRing();
		if (lastRing == null) {
			rings.add(r);
			return true;
		}

		// lastRing not null
		if (r.isSmallerThan(lastRing)) {
			rings.add(r);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the highest ring element and deletes it from the list. Returns <code>null</code> if
	 * list is empty.
	 * 
	 * @return
	 */
	public Ring pop() {
		Ring lastRing = getHighestRing();
		if (lastRing != null) {
			rings.remove(lastRing);
		}
		return lastRing;
	}

	/**
	 * 
	 * @return This size of the ring at level. The level number starts with 1. 
	 */
	public int getRingsizeAtLevel(int level) {
		if (level < 1){
			throw new IllegalArgumentException("level must be positive");
		}
		
		if (rings.size() < level){
			return 0;
		}
		
		return rings.get(level - 1).getSize();
	}

	
	/**
	 * TODO: this is a facility method just for Backtracking-Strategy; It somehow pollutes this class here :-(
	 * @return
	 */
	public int getStateEncoding() {
		int result = 0;
		for (Ring ring : rings) {
			result = result + (int) Math.pow(2, ring.getSize());
		}
		return result;
	}

	

}
