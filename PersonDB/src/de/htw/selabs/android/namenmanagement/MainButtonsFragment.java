package de.htw.selabs.android.namenmanagement;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MainButtonsFragment extends Fragment {

	private OnMainButtonsPressedListener onMainButtonsPressedListener;

	public interface OnMainButtonsPressedListener {
		public void onPrevPressed();

		public void onDeleteAllPressed();

		public void onNextPressed();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_buttons_fragment, container,
				false);

		final Button prevButton = (Button) view.findViewById(R.id.buttonPrev);

		final Button deleteAllButton = (Button) view
				.findViewById(R.id.buttonDeleteAll);

		final Button nextButton = (Button) view.findViewById(R.id.buttonNext);

		prevButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				onMainButtonsPressedListener.onPrevPressed();
			}
		});

		deleteAllButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				onMainButtonsPressedListener.onDeleteAllPressed();
			}
		});

		nextButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				onMainButtonsPressedListener.onNextPressed();
			}
		});

		return view;
	}


	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		try {
			onMainButtonsPressedListener = (OnMainButtonsPressedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnNewItemAddedListener");
		}
	}

}