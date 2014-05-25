package de.htw.selabs.android.namenmanagement.datahandling;

import android.content.Context;
import de.htw.selabs.android.namenmanagement.database.PersonDBFacade;

// visibility only within the package
interface ContextAndDBFacadeProvider {
	Context getContext(); 
	PersonDBFacade getFacade();
}
