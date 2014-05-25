package de.htw.selabs.android.namenmanagement.datahandling;

import java.util.ArrayList;
import java.util.List;

import de.htw.selabs.android.namenmanagement.PersonItemAdapter;
import de.htw.selabs.android.namenmanagement.R;
import de.htw.selabs.android.namenmanagement.domain.Person;

/**
 * Version, in which Persons are stored in a local list.
 * 
 * In this class, the persons are only stored locally and not in a database.
 * 
 * 
 * @author baar
 * 
 */
//only visible in the package
abstract class AbstractPersonItemAdapterStrategy extends AbstractDataHandlingStrategy {

	protected List<Person> persons = new ArrayList<Person>();

	AbstractPersonItemAdapterStrategy(ContextAndDBFacadeProvider provider) {
		super(provider.getContext());
	}

	@Override
	void initialize() {
		
		initializePersons();

		// create the adapter
		int resID = R.layout.personlist_item;
		listAdapter = new PersonItemAdapter(context, resID, persons);
	}

	abstract protected void initializePersons();
	
	protected void informAdapter() {
		((PersonItemAdapter) listAdapter).notifyDataSetChanged();
	}


}
