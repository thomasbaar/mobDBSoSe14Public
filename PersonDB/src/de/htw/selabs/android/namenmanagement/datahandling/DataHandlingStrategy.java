package de.htw.selabs.android.namenmanagement.datahandling;

import de.htw.selabs.android.namenmanagement.domain.Person;
import android.widget.ListAdapter;

/**
 * Interface for different strategies to manage the handling of data.
 * 
 * @author baar
 * 
 */
public interface DataHandlingStrategy{

	String getTitle();

	ListAdapter getListAdapter();
	
	void synchronizeData();  // called when strategy is activated (come to the foreground)

	void addPerson(Person person);

	void deleteAllPersons();
	
}
