package de.htw.selabs.android.namenmanagement;

import java.util.ArrayList;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.TextView;
import de.htw.selabs.android.namenmanagement.database.PersonDBFacade;
import de.htw.selabs.android.namenmanagement.datahandling.DataHandlingFactory;
import de.htw.selabs.android.namenmanagement.datahandling.DataHandlingStrategy;
import de.htw.selabs.android.namenmanagement.domain.Person;

public class PersonDBActivity extends Activity implements
		NewItemFragment.OnNewPersonAddedListener, MainButtonsFragment.OnMainButtonsPressedListener {
	
	private DataHandlingStrategy currentStrategy;
	private ArrayList<DataHandlingStrategy> allStrategies =  new ArrayList<DataHandlingStrategy>();
	
	private PersonListFragment personListFragment;
	private TextView titleTextView;
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Inflate your view
		setContentView(R.layout.main);
		
		titleTextView = (TextView) findViewById(R.id.titleTextView);

		createStrategies();


		
		// Get references to the Fragments
		FragmentManager fm = getFragmentManager();
		personListFragment = (PersonListFragment) fm
				.findFragmentById(R.id.PersonListFragment);

		onMoveStrategyPost();
	}

	protected void createStrategies() {
		
		DataHandlingFactory factory = new DataHandlingFactory(this, new PersonDBFacade(this));
		allStrategies.add(factory.createDBWithCursorAdapterStrategy());
		allStrategies.add(factory.createDBWithPersonItemAdapterStrategy());
		allStrategies.add(factory.createWithoutDBStrategy());
		
		currentStrategy = allStrategies.get(0);
	}

	public void onNewPersonAdded(Person person) {
		currentStrategy.addPerson(person);
	}

	public void onPrevPressed() {
		int index = getIndexOfCurrentStrategy();
		
		if (index == 0){
			currentStrategy = allStrategies.get(allStrategies.size() - 1);
		} else {
			currentStrategy = allStrategies.get(index - 1);
		}
		onMoveStrategyPost();
	}

	public void onDeleteAllPressed() {
		currentStrategy.deleteAllPersons();
	}

	public void onNextPressed() {
		int index = getIndexOfCurrentStrategy();
		
		if (index == allStrategies.size() - 1){
			currentStrategy = allStrategies.get(0);
		} else {
			currentStrategy = allStrategies.get(index + 1);
		}
		onMoveStrategyPost();
	}

	private int getIndexOfCurrentStrategy() {
		int index = allStrategies.indexOf(currentStrategy);
		
		if (index < 0 || index > allStrategies.size() - 1){
			throw new IllegalStateException("Got unexpected index " + index + " for a list of size " + allStrategies.size());
		}
		return index;
	}
	
	private void onMoveStrategyPost(){
		// Bind the array adapter to the ListView.
		personListFragment.setListAdapter(currentStrategy.getListAdapter());
		// set title text
		titleTextView.setText(currentStrategy.getTitle());

		currentStrategy.synchronizeData();
	}

}
