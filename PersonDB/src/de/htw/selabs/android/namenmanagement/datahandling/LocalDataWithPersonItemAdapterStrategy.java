package de.htw.selabs.android.namenmanagement.datahandling;

import android.util.Log;
import de.htw.selabs.android.namenmanagement.domain.Person;

// only visible in the package

class LocalDataWithPersonItemAdapterStrategy extends AbstractPersonItemAdapterStrategy {

	private static String TITLE = "Person List without Database and PersonItemAdapter";


	LocalDataWithPersonItemAdapterStrategy(ContextAndDBFacadeProvider provider) {
		super(provider);

		title = TITLE;
	}

	@Override
	protected void initializePersons() {
		persons.add(new Person("Klaus", "Müller", null));
		persons.add(new Person("Lucy", "Lachmann", "lucy@mailme.de"));
	}


	public void addPerson(Person person) {
		if (person==null){
			Log.w(this.getClass() + "", "Unexpected argument null in addPerson(). Ignored.");
			return;
		}
		
		persons.add(person);
		informAdapter();
	}

	public void deleteAllPersons() {
		persons.removeAll(persons);
		informAdapter();
	}

	public void synchronizeData() {
		// do nothing
	}

}
