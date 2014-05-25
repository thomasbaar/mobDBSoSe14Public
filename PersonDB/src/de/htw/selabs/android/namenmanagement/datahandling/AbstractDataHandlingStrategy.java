package de.htw.selabs.android.namenmanagement.datahandling;

import android.widget.ListAdapter;
import android.content.Context;

abstract class AbstractDataHandlingStrategy implements DataHandlingStrategy {

	protected String title;
	protected ListAdapter listAdapter;
	protected Context context;
	
	
	
	AbstractDataHandlingStrategy(Context context) {
		super();
		if (context == null){
			throw new IllegalArgumentException("argument context must not be null");
		}
		this.context = context;
	}

	abstract void initialize(); // to be called after object creation; see factory
	
	public String getTitle() {
		return title;
	}

	public ListAdapter getListAdapter() {
		return listAdapter;
	}


}
