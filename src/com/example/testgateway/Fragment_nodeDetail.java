package com.example.testgateway;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_nodeDetail extends Fragment {

	/**
	 * Create a new instance of DetailsFragment, initialized to show the text at
	 * 'index'.
	 */
	public static Fragment_nodeDetail newInstance(int index) {
		Fragment_nodeDetail f = new Fragment_nodeDetail();

		// Supply index input as an argument.
		Bundle args = new Bundle();
		args.putInt("index", index);
		f.setArguments(args);

		return f;
	}

	public int getShownIndex() {
		return getArguments().getInt("index", 0);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment

		Toast.makeText(this.getActivity(), "dd", Toast.LENGTH_SHORT).show();

		/*
		 * return inflater .inflate(R.layout.fragment_data_center, container,
		 * false);
		 */
		ScrollView scroller = new ScrollView(getActivity());
		TextView text = new TextView(getActivity());
		int padding = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 4, getActivity().getResources()
						.getDisplayMetrics());
		text.setPadding(padding, padding, padding, padding);
		scroller.addView(text);
		// text.setText(Shakespeare.DIALOGUE[getShownIndex()]);
		if (getShownIndex() != 0)
<<<<<<< HEAD
			text.setText(getShownIndex()+"ci");
=======
			text.setText("hh");
>>>>>>> be8988229fe951efdeaeadb676d31e0d7f649900
		else
			text.setText("��һ��");
		return scroller;
	}
}
