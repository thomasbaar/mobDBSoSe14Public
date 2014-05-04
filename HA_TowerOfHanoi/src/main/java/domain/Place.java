package domain;

/**
 * Represents a place with a name and a tower on it.
 * 
 * @author baar
 *
 */
public class Place {
	
	// immutable attributes
	private final String name;
	private final Tower tower;
	
	public Place(String name, Tower tower){
		this.name = name;
		this.tower = tower;
	}
	
	public Tower getTower(){
		return tower;
	}

	public String getName(){
		return name;
	}


}
