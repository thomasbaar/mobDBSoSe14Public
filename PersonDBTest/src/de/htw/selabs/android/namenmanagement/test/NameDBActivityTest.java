package de.htw.selabs.android.namenmanagement.test;

import de.htw.selabs.android.namenmanagement.PersonDBActivity;
import de.htw.selabs.android.namenmanagement.PersonListFragment;
import de.htw.selabs.android.namenmanagement.R;
import android.app.FragmentManager;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class NameDBActivityTest extends ActivityInstrumentationTestCase2<PersonDBActivity> {

	
	private PersonDBActivity out;
	
	private ListView personListView;
	private EditText lastNameEditText;
	private Button saveButton;
	private Button clearButton;
	private Button deleteButton;
	private Button nextButton;
	
	public NameDBActivityTest() {
		super(PersonDBActivity.class);
	}


	   @Override
	    protected void setUp() throws Exception {

	        /*
	         * prepare to send key events to the app under test by turning off touch mode.
	         * Must be done before the first call to getActivity()
	         */

	        setActivityInitialTouchMode(false);

	        /*
	         * Start the app under test by starting its main activity. The test runner already knows
	         * which activity this is from the call to the super constructor, as mentioned
	         * previously. The tests can now use instrumentation to directly access the main
	         * activity through mActivity.
	         */
	        out = getActivity();

	        FragmentManager fm = out.getFragmentManager();
			PersonListFragment personListFragment = (PersonListFragment) fm
					.findFragmentById(R.id.PersonListFragment);
			personListView = personListFragment.getListView();
			lastNameEditText = (EditText) out.findViewById(R.id.firstnameEditText);
			
			saveButton = (Button) out.findViewById(R.id.buttonSave);
			clearButton = (Button) out.findViewById(R.id.buttonCancel);
			deleteButton = (Button) out.findViewById(R.id.buttonDeleteAll);
			nextButton = (Button) out.findViewById(R.id.buttonNext);
	
	   }
	
	
	    
	    // without that annotation, an failure occurs
	    @UiThreadTest
	    public void testListViewAfterSaveButtonPressed() {
	    	int preCnt = personListView.getCount();
	    	saveButton.performClick(); // because nothing was inserted, the counter has not increased
	        assertTrue("Number of items should remain the same", personListView.getCount()==preCnt);
	    }

	    // without that annotation, an failure occurs
	    @UiThreadTest
	    public void testListViewAfterInsertAndSaveButtonPressed() {
	    	int preCnt = personListView.getCount();
	    	
	    	lastNameEditText.setText("Meier");
	    	saveButton.performClick(); 
	        assertTrue("Number of items should increase by 1", personListView.getCount()==preCnt+1);
	    }

	    @UiThreadTest
	    public void testListViewAfterInsertAndClearButtonPressed() {
	    	int preCnt = personListView.getCount();
	    	
	    	lastNameEditText.setText("Meier");
	    	clearButton.performClick(); 
	        assertTrue("Number of items should remain the same", personListView.getCount()==preCnt);
	    }

	    @UiThreadTest
	    public void testListViewAfterNextButtonPressed() {
	    	int preCnt = personListView.getCount();
	    	nextButton.performClick(); 
	        assertTrue("Number of items should remain the same", personListView.getCount()==preCnt);
	    }

	    @UiThreadTest
	    public void testListViewAfterInsertSaveClearButtonPressed() {
	    	
	    	lastNameEditText.setText("Meier");
	    	saveButton.performClick(); 
	    	deleteButton.performClick(); 
	        assertTrue("Number of items should be 0", personListView.getCount()==0);
	    }



}
