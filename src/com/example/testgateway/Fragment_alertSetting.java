package com.example.testgateway;

import java.io.IOException;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class Fragment_alertSetting extends Fragment {
	public MainActivity ma;
	private int alertPower;//预警值的设置

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment

		ma = (MainActivity) getActivity();
		/*byte[] buffer = { 'A', 'B', 'C', 'e' };
		try {
			if (ma.mSerialPort != null) {
				ma.mSerialPort.getOutputStream().write(buffer);
			} else {
				System.out.println("has no serialPorts now!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		View v = inflater.inflate(R.layout.fragment_alert_setting, container,
				false);
		Spinner spinner = (Spinner) v.findViewById(R.id.powerSettingSpinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter
				.createFromResource(getActivity(), R.array.powerSetting,
						android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		spinner.setAdapter(adapter);

		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				System.out.println("pos---->" + arg2);
				setAlertPower(arg2);

				if (ma.mSerialPort != null) {
					try {
						ma.mSerialPort.getOutputStream().write(alertPower);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			/**
			 * @param arg2 用户选择 
			 * 设置预警点，5代表5%
			 */
			private void setAlertPower(int arg2) {
				// TODO Auto-generated method stub
				if (arg2 == 0) {
					alertPower = 5;
				} else if (arg2 == 1) {
					alertPower = 10;
				} else if (arg2 == 2) {
					alertPower = 15;
				} else if (arg2 == 3) {
					alertPower = 20;
				} else if (arg2 == 4) {
					alertPower = 25;
				} else if (arg2 == 5) {
					alertPower = 30;
				} else if (arg2 == 6) {
					alertPower = 35;
				}else if (arg2 == 76) {
					alertPower = 40;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		return v;
	}
}
