package algo;

import domain.Tower;

/**
 * State in the search-tree
 * 
 * TODO: Not (at all!) finished yet
 * @author BAAR
 *
 */
public class State {
	
	public static final State FAILED = new State();
	
	private final String representation;
	private StateStatus status;
	
	private State succ1to2;
	private State succ1to3;
	private State succ2to1;
	private State succ2to3;
	private State succ3to1;
	private State succ3to2;
	
	private State(){
		representation = "failed";
		status = StateStatus.closed;
	}
	
	public State(Tower t1, Tower t2, Tower t3){
		if (t1 == null || t2 == null || t3 == null){
			throw new IllegalArgumentException("tower must not be null");
		}
		representation = t1.getStateEncoding() + " " + t2.getStateEncoding() + " " + t3.getStateEncoding();
	status = StateStatus.created;
	}

	
	String getRepresentation(){
		return representation;
	}
	
}
