package de.htw.selabs.android.namenmanagement.datahandling;

import android.database.Cursor;
import android.util.Log;
import de.htw.selabs.android.namenmanagement.database.PersonDBFacade;
import de.htw.selabs.android.namenmanagement.database.PersonTable;
import de.htw.selabs.android.namenmanagement.domain.Person;

// only visible in the package

//this class inherits from WithoutDBStrategy due to the many similarities
class DBWithPersonItemAdapterStrategy extends AbstractPersonItemAdapterStrategy {

	private static String TITLE = "Person List with Database and PersonItemAdapter";

	private PersonDBFacade facade;

	DBWithPersonItemAdapterStrategy(ContextAndDBFacadeProvider provider) {
		super(provider);
		this.facade = provider.getFacade();
		if (facade == null) {
			throw new IllegalArgumentException("facade must not be null");
		}
		title = TITLE;
	}

	@Override
	protected void initializePersons() {
		initializeWithPersonsFromDatabase();
	}

	protected void initializeWithPersonsFromDatabase() {
		// initialize again persons with empty list
		persons.removeAll(persons);
		Cursor cursor = facade.fetchAllPersons();

		// initialize persons
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			int index_fn = cursor.getColumnIndex(PersonTable.COL_FIRSTNAME);
			int index_ln = cursor.getColumnIndex(PersonTable.COL_LASTNAME);
			int index_em = cursor.getColumnIndex(PersonTable.COL_EMAIL);

			while (true) {
				String firstName = cursor.getString(index_fn);
				String lastName = cursor.getString(index_ln);
				String email = cursor.getString(index_em);

				try {
					Person person = new Person(firstName, lastName, email);
					persons.add(person);

				} catch (Exception exc) {
					Log.w(this.getClass() + "",
							"Could not create Person for params read from DB: "
									+ firstName + ", " + lastName + ", "
									+ email);
				}
				if (cursor.isLast()) {
					break;
				} else {
					cursor.moveToNext();
				}
			}
		}
	}

	public void addPerson(Person person) {
		if (person==null){
			Log.w(this.getClass() + "", "Unexpected argument null in addPerson(). Ignored.");
			return;
		}
		
		facade.addPerson(person);

		persons.add(person);
		informAdapter();
	}

	public void deleteAllPersons() {
		facade.deleteAllPersons();
		
		persons.removeAll(persons);
		informAdapter();
	}

	public void synchronizeData() {
		initializeWithPersonsFromDatabase();
		
		informAdapter();
	}

}
