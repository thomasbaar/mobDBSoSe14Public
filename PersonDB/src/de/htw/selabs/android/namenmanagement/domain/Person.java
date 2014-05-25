package de.htw.selabs.android.namenmanagement.domain;

/**
 * The corresponding Java class for the content stored in the database table
 * Person.
 * 
 * @author baar
 * 
 */
public class Person {

	// attributes (cannot be null, see constructor)
	private int dbId = -1; // -1 indicates that dbId is unknown yet
	private String firstName;
	private String lastName;
	private String eMail;

	public Person(String firstName, String lastName, String eMail) {
		super();
		this.firstName = (firstName==null?"":firstName);
		this.lastName = (lastName==null?"":lastName);
		this.eMail = (eMail==null?"":eMail);
	}

	
	public int getDbId() {
		return dbId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String geteMail() {
		return eMail;
	}


	public boolean isDbIdUnknown() {
		if (dbId == -1)
			return true;
		else
			return false;
	}
	
	  @Override
	  public String toString() {
	    return "(" + lastName + ", " + firstName + ", " + eMail + ") ";
	  }

}