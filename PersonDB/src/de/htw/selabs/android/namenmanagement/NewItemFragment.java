package de.htw.selabs.android.namenmanagement;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import de.htw.selabs.android.namenmanagement.domain.Person;

public class NewItemFragment extends Fragment {

	private OnNewPersonAddedListener onNewPersonAddedListener;

	public interface OnNewPersonAddedListener {
		public void onNewPersonAdded(Person newPerson);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.new_item_fragment, container,
				false);

		final EditText firstnameEditText = (EditText) view
				.findViewById(R.id.firstnameEditText);

		final EditText lastnameEditText = (EditText) view
				.findViewById(R.id.lastnameEditText);

		final EditText emailEditText = (EditText) view
				.findViewById(R.id.emailEditText);

		final List<EditText> allEditTexts = new ArrayList<EditText>();
		allEditTexts.add(firstnameEditText);
		allEditTexts.add(lastnameEditText);
		allEditTexts.add(emailEditText);

		final Button buttonSave = (Button) view.findViewById(R.id.buttonSave);

		final Button buttonCancel = (Button) view
				.findViewById(R.id.buttonCancel);

		buttonSave.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				String firstName = firstnameEditText.getText().toString();
				String lastName = lastnameEditText.getText().toString();
				String email = emailEditText.getText().toString();

				// TODO: Validation of mandatory attributes of Person here ???
				if (!(firstName.isEmpty() && lastName.isEmpty() && email
						.isEmpty())) {
					onNewPersonAddedListener.onNewPersonAdded(new Person(
							firstName, lastName, email));
				}

				clearText(allEditTexts);
			}
		});

		buttonCancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				clearText(allEditTexts);
			}
		});

		return view;
	}

	private void clearText(List<EditText> editTextList) {
		for (EditText editText : editTextList) {
			editText.setText("");
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		try {
			onNewPersonAddedListener = (OnNewPersonAddedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnNewItemAddedListener");
		}
	}

}