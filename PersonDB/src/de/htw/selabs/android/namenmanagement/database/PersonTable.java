package de.htw.selabs.android.namenmanagement.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Represents a database table. Has information about the table, e.g. its name and the 
 * name of the columns.
 * @author baar
 *
 */

public class PersonTable {

	static final String TABLE_NAME = "person"; 
	// columns of database table
	static final String COL_ROWID = "_id"; // never change, Android relies on it!!!
	public static final String COL_FIRSTNAME = "firstname"; 
	public static final String COL_LASTNAME = "lastname"; 
	public static final String COL_EMAIL = "email"; 
	
	// database creation SQL statement
	private static final String SQL_DATABASE_CREATE = "create table " + TABLE_NAME + " (" 
	   + COL_ROWID + " integer primary key autoincrement, "
	   + COL_FIRSTNAME + " text not null, "
	   + COL_LASTNAME + " text not null, "
	   + COL_EMAIL + " text "
	   + ");";
	
	private static final String SQL_DATABASE_INITDATA = "insert into " + TABLE_NAME + " (" 
			   + COL_FIRSTNAME + ", "
			   + COL_LASTNAME + ", "
			   + COL_EMAIL + ""
			   + ")"
			   + " values " 
			   + "('thomas', 'baar', 'thomas.baar@htw-berlin.de'), "
			   + "('peter', 'puschmann', NULL) "
			   + ";";
			
	// database creation SQL statement
	//TODO: Does not work when Table was renamed 
	private static final String SQL_DATABASE_DROP = "DROP table IF EXISTS " + TABLE_NAME; 

	
	static void onCreate(SQLiteDatabase db){
		Log.d(PersonTable.class.getName(), "Create Tables with SQL-code: " + SQL_DATABASE_CREATE);
		db.execSQL(SQL_DATABASE_CREATE);
		
		Log.d(PersonTable.class.getName(), "Init Tables with SQL-code: " + SQL_DATABASE_INITDATA);
		db.execSQL(SQL_DATABASE_INITDATA);
	}
	
	static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		Log.d(PersonTable.class.getName(), "Upgrading database from version " 
				+ oldVersion + " to "
				+ newVersion + ", which will might be used to migrate the db schema. Here, we reinitialize the database");
		db.execSQL(SQL_DATABASE_DROP);
		onCreate(db);
	}
	
	
}
