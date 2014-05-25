package de.htw.selabs.android.namenmanagement.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Standard OpenHelper-class.
 * 
 * Visibility is restricted to package.
 * @author baar
 *
 */
class PersonDBOpenHelper extends SQLiteOpenHelper {

	PersonDBOpenHelper(Context context){
		super(context, PersonDBFacade.DATABASE_FILE_NAME, null, PersonDBFacade.DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// put here the creation commands for each table
		PersonTable.onCreate(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// put here the update commands for each table
		PersonTable.onUpgrade(db, oldVersion, newVersion);
	}

}
