/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 *  * Adapted for teaching by T.Baar
 */

package de.htwberlin.selabs.cnotepad;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Displays a list of notes. Will display notes from the {@link Uri} provided in
 * the incoming Intent if there is one, otherwise it defaults to displaying the
 * contents of the {@link CNotePadProvider}.
 * 
 * NOTE: Notice that the provider operations in this Activity are taking place
 * on the UI thread. This is not a good practice. It is only done here to make
 * the code more readable. A real application should use the
 * {@link android.content.AsyncQueryHandler} or {@link android.os.AsyncTask}
 * object to perform operations asynchronously on a separate thread.
 */
public class CNotesList extends Activity implements
		AdapterView.OnItemClickListener {

	// For logging and debugging
	private static final String TAG = "CNotesList";

	/**
	 * The columns needed by the cursor adapter
	 */
	private static final String[] PROJECTION = new String[] {
			CNotePad.CNotes._ID, // 0
			CNotePad.CNotes.COLUMN_NAME_TITLE, // 1
	};


	/**
	 * onCreate is called when Android starts this Activity from scratch.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// take the layout as described in the xml-file
		setContentView(R.layout.noteslist);

		// The user does not need to hold down the key to use menu shortcuts.
		setDefaultKeyMode(DEFAULT_KEYS_SHORTCUT);

		/*
		 * If no data is given in the Intent that started this Activity, then
		 * this Activity was started when the intent filter matched a MAIN
		 * action. We should use the default provider URI.
		 */
		// Gets the intent that started this Activity.
		Intent intent = getIntent();

		// If there is no data associated with the Intent, sets the data to the
		// default URI, which
		// accesses a list of notes.
		if (intent.getData() == null) {
			intent.setData(CNotePadProvider.CONTENT_URI);
		}

		ListView listview = (ListView) findViewById(R.id.listall);

		/*
		 * Sets the callback for context menu activation for the ListView. The
		 * listener is set to be this Activity. The effect is that context menus
		 * are enabled for items in the ListView, and the context menu is
		 * handled by a method in NotesList.
		 */
//		listview.setOnCreateContextMenuListener(this);

		/*
		 * Performs a managed query. The Activity handles closing and requerying
		 * the cursor when needed.
		 * 
		 * Please see the introductory note about performing provider operations
		 * on the UI thread.
		 */
		Cursor cursor = managedQuery(getIntent().getData(), // Use the default
															// content URI for
															// the provider.
				PROJECTION, // Return the note ID and title for each note.
				null, // No where clause, return all records.
				null, // No where clause, therefore no where column values.
				CNotePad.CNotes.DEFAULT_SORT_ORDER // Use the default sort
													// order.
		);

		/*
		 * The following two arrays create a "map" between columns in the cursor
		 * and view IDs for items in the ListView. Each element in the
		 * dataColumns array represents a column name; each element in the
		 * viewID array represents the ID of a View. The SimpleCursorAdapter
		 * maps them in ascending order to determine where each column value
		 * will appear in the ListView.
		 */

		// The names of the cursor columns to display in the view, initialized
		// to the title column
		String[] dataColumns = { CNotePad.CNotes.COLUMN_NAME_TITLE };

		// The view IDs that will display the cursor columns, initialized to the
		// TextView in
		// noteslist_item.xml
		int[] viewIDs = { android.R.id.text1 };

		// Creates the backing adapter for the ListView.
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, // The
																	// Context
																	// for the
																	// ListView
				R.layout.noteslist_item, // Points to the XML for a list item
				cursor, // The cursor to get items from
				dataColumns, viewIDs);

		// Sets the adapter to be the cursor adapter that was just created.
		listview.setAdapter(adapter);

		// set the listener when the user clicks on item in the list
		listview.setOnItemClickListener(this);

		// bound the click on the button on a concrete method
		Button newButton = (Button) findViewById(R.id.newbtn);
		newButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_INSERT, getIntent()
						.getData()));
			}
		});

	}

	/**
	 * Called when the user clicks the device's Menu button the first time for
	 * this Activity. Android passes in a Menu object that is populated with
	 * items.
	 * 
	 * Sets up a menu that provides the Insert option.
	 * 
	 * @param menu
	 *            A Menu object, to which menu items should be added.
	 * @return True, always. The menu should be displayed.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate menu from XML resource
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.list_options_menu, menu);

		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * This method is called when the user selects an option from the menu, but
	 * no item in the list is selected. If the option was INSERT, then a new
	 * Intent is sent out with action ACTION_INSERT. The data from the incoming
	 * Intent is put into the new Intent. In effect, this triggers the
	 * NoteEditor activity in the NotePad application.
	 * 
	 * If the item was not INSERT, then most likely it was an alternative option
	 * from another application. The parent method is called to process the
	 * item.
	 * 
	 * @param item
	 *            The menu item that was selected by the user
	 * @return True, if the INSERT menu item was selected; otherwise, the result
	 *         of calling the parent method.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_add:
			/*
			 * Launches a new Activity using an Intent. The intent filter for
			 * the Activity has to have action ACTION_INSERT. No category is
			 * set, so DEFAULT is assumed. In effect, this starts the NoteEditor
			 * Activity in NotePad.
			 */
			startActivity(new Intent(Intent.ACTION_INSERT, getIntent()
					.getData()));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}


	/**
	 * This method is called when the user clicks a note in the displayed list.
	 * 
	 * This method handles incoming actions of either PICK (get data from the
	 * provider) or GET_CONTENT (get or create data). If the incoming action is
	 * EDIT, this method sends a new Intent to start NoteEditor.
	 * 
	 * @param l
	 *            The ListView that contains the clicked item
	 * @param v
	 *            The View of the individual item
	 * @param position
	 *            The position of v in the displayed list
	 * @param id
	 *            The row ID of the clicked item
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// Constructs a new URI from the incoming URI and the row ID
		Uri uri = ContentUris.withAppendedId(getIntent().getData(), id);

		startActivity(new Intent(Intent.ACTION_EDIT, uri));
	}
}
