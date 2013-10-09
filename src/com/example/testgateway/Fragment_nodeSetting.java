package com.example.testgateway;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

public class Fragment_nodeSetting extends Fragment{
	// Ω⁄µ„…Ë÷√

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		
		/*RelativeLayout.LayoutParams rootRelativeParams = new RelativeLayout.LayoutParams( 
                100, 100);
		View view = LayoutInflater.from(getActivity())
				.inflate(R.layout.fragment_node_setting, null);
		view.setLayoutParams(rootRelativeParams);*/
		View v = inflater.inflate(R.layout.fragment_node_setting, container, false);

	    Spinner spinner = (Spinner) v.findViewById(R.id.sendCycleSelect);
	    ArrayAdapter<CharSequence> LTRadapter = ArrayAdapter.createFromResource(
				getActivity(), R.array.nodeSettingCycle,
				android.R.layout.simple_spinner_item);
	    LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
	    spinner.setAdapter(LTRadapter);

		spinner.setOnItemSelectedListener(new 
				OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				System.out.println("pos---->" + arg2);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		 return v;
	}

	

}
