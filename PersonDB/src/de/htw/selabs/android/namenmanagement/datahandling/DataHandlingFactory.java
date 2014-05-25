package de.htw.selabs.android.namenmanagement.datahandling;

import de.htw.selabs.android.namenmanagement.database.PersonDBFacade;
import android.content.Context;

public class DataHandlingFactory implements ContextAndDBFacadeProvider{

	private Context context;
	private PersonDBFacade facade; // might be null

	public DataHandlingFactory(Context context, PersonDBFacade facade) {
		super();
		if (context == null) {
			throw new IllegalArgumentException("context must not be null");
		}
		this.context = context;
		this.facade = facade;
	}

	/**
	 * Precondition: facade-object must have been set.
	 * 
	 * @return
	 */
	public DataHandlingStrategy createDBWithCursorAdapterStrategy() {
		AbstractDataHandlingStrategy instance = new DBWithCursorAdapterStrategy(this);
		return postCreation(instance);
	}

	public DataHandlingStrategy createWithoutDBStrategy() {
		AbstractDataHandlingStrategy instance = new LocalDataWithPersonItemAdapterStrategy(this);
		return postCreation(instance);
	}

	public DataHandlingStrategy createDBWithPersonItemAdapterStrategy() {
		AbstractDataHandlingStrategy instance = new DBWithPersonItemAdapterStrategy(this);
		return postCreation(instance);
	}
	
	private DataHandlingStrategy postCreation(AbstractDataHandlingStrategy instance){
		instance.initialize();
		return instance;
	}

	// visibility rather package, but since method implements
	// a method in interface, we are enforced to make it public :-(
	public Context getContext() {
		return context;
	}

	// visibility rather package, but since method implements
	// a method in interface, we are enforced to make it public :-(
	public PersonDBFacade getFacade() {
		return facade;
	}

}
