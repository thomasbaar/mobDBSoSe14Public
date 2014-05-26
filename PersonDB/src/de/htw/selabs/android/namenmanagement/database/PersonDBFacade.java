package de.htw.selabs.android.namenmanagement.database;

import de.htw.selabs.android.namenmanagement.domain.Person;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

/**
 * Facade class providing access to the database.
 * 
 * It contains also information about the database itself, e.g. its current version.
 * 
 * @author baar
 * 
 */

public class PersonDBFacade {
	
	// some bookkeeping information on the database
	static final String DATABASE_FILE_NAME = "persondb";
	static final int DATABASE_VERSION = 8;
	


	private Context context;
	private SQLiteDatabase db; // should be set whenever it is used
	private PersonDBOpenHelper dbHelper;

	public PersonDBFacade(Context context) {
		this.context = context;
		open();
	}

	private PersonDBFacade open() throws SQLException {
		dbHelper = new PersonDBOpenHelper(context);
//		db = getDatabase();
		return this;
	}

	public void close() {
		dbHelper.close();
	}

	/**
	 * Creates a new Person in the database.
	 * 
	 * Returns the new rowId if successful, otherwise -1
	 */
	public long addPerson(Person person) {
		ContentValues values = createContentValues(person);
		db = getDatabase();
		return db.insert(PersonTable.TABLE_NAME, null, values);
	}
	
	/**
	 * Delete all row in table Person
	 */
	public void deleteAllPersons() {
		db = getDatabase();
		db.delete(PersonTable.TABLE_NAME, null, null);
	}


	public Cursor fetchAllPersons() {
		db = getDatabase();
		Cursor mCursor = db.query(PersonTable.TABLE_NAME, new String[] {
				PersonTable.COL_ROWID, PersonTable.COL_FIRSTNAME,
				PersonTable.COL_LASTNAME, PersonTable.COL_EMAIL }, null, null,
				null, null, null);

		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	private SQLiteDatabase getDatabase() {
		try {
			return dbHelper.getWritableDatabase();
		} catch (SQLiteException exc) {
			Log.w(this.getClass() + "", "Cannot open database for writing");
			return dbHelper.getReadableDatabase();
		}
	}

	private ContentValues createContentValues(Person person) {
		ContentValues values = new ContentValues();
		values.put(PersonTable.COL_FIRSTNAME, person.getFirstName());
		values.put(PersonTable.COL_LASTNAME, person.getLastName());
		values.put(PersonTable.COL_EMAIL, person.geteMail());

		return values;
	}


}
