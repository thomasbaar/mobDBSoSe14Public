package de.htw.selabs.android.namenmanagement.datahandling;

import android.database.Cursor;
import android.widget.SimpleCursorAdapter;
import de.htw.selabs.android.namenmanagement.R;
import de.htw.selabs.android.namenmanagement.database.PersonDBFacade;
import de.htw.selabs.android.namenmanagement.database.PersonTable;
import de.htw.selabs.android.namenmanagement.domain.Person;

// only visible in the package
class DBWithCursorAdapterStrategy extends AbstractDataHandlingStrategy {
	
	private static String TITLE = "Person List with Database and CursorAdapter";

	private PersonDBFacade facade;

	
	DBWithCursorAdapterStrategy(ContextAndDBFacadeProvider provider) {
		super(provider.getContext());
		facade = provider.getFacade();
		if (facade==null){
			throw new IllegalArgumentException("facade must not be null");
		}
		
		title = TITLE;
	}

	protected void initialize() {
		Cursor cursor = facade.fetchAllPersons();

		//
		// Creating listAdapter
		//
		String[] columns = new String[] { PersonTable.COL_LASTNAME,
				PersonTable.COL_EMAIL };

		// the XML defined views which the data will be bound to
		int[] to = new int[] { R.id.row, R.id.rowEmail };

		// create the adapter using the cursor pointing to the desired data
		// as well as the layout information
		// TODO: Can one also map the concatenation of two columns (PersonTable.COL_LASTNAME + PersonTable.COL_FIRSTNAME) to the same layout location (R.id.row) ???
		// 
		listAdapter = new SimpleCursorAdapter(context,
				R.layout.personlist_item, cursor, columns, to, 0);
	}

	// TODO: this code looks duplicated from other strategies interacting with the database 
	public void addPerson(Person person) {
		facade.addPerson(person);
		
		synchronizeData();
	}

	public void deleteAllPersons() {
		facade.deleteAllPersons();
		
		synchronizeData();
	}

	public void synchronizeData() {
		// inform the adapter on (potential) data change
		((SimpleCursorAdapter) listAdapter).swapCursor(facade.fetchAllPersons());
	}


}
