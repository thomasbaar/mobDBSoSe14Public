package domain;

/**
 * Represents a ring. Multiple rings form a tower.
 * @author baar
 *
 */
public class Ring {

	private final int size;

	public Ring(int size) {
		if (size < 1){
			throw new IllegalArgumentException("A ring must have a positive size");
		}
		this.size = size;
	}

	public boolean isSmallerThan(Ring r){
		return this.size < r.size;
	}
	
	public int getSize(){
		return size;
	}
	
}