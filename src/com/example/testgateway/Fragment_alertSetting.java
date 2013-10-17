package com.example.testgateway;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Fragment_alertSetting extends Fragment {
	public MainActivity ma;
	private int alertPower;// 预警值的设置
	Button musicSelect;
	static private int openfileDialogId = 0;  
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment

		ma = (MainActivity) getActivity();
		View v = inflater.inflate(R.layout.fragment_alert_setting, container,
				false);
		musicSelect = (Button) v.findViewById(R.id.musicSelect);
		Spinner spinner = (Spinner) v.findViewById(R.id.powerSettingSpinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				getActivity(), R.array.powerSetting,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		spinner.setAdapter(adapter);

		musicSelect.setOnClickListener(new MyButtonListener());
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

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		return v;
	}

	/**
	 * @param arg2
	 *            用户选择 设置预警点，5代表5%
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
		} else if (arg2 == 76) {
			alertPower = 40;
		}
	}

	public class MyButtonListener implements OnClickListener {
		String filepath;
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			 
			Map<String, Integer> images = new HashMap<String, Integer>();  
            // 下面几句设置各文件类型的图标， 需要你先把图标添加到资源文件夹  
          //  images.put(OpenFileDialog.sRoot, R.drawable.ic_launcher);   // 根目录图标  
          //  images.put(OpenFileDialog.sParent, R.drawable.ic_launcher);    //返回上一层的图标  
          //  images.put(OpenFileDialog.sFolder, R.drawable.ic_launcher);   //文件夹图标  
            images.put("mp3", R.drawable.ic_launcher);   //wav文件图标  
         //   images.put(OpenFileDialog.sEmpty, R.drawable.ic_launcher);  
			Dialog dialog = OpenFileDialog.createDialog(openfileDialogId, arg0.getContext(), "打开文件", new FileDialogCallBack() {  
                @Override  
                public void callback(Bundle bundle) {  
                    filepath = bundle.getString("path");  
                }  
            },   
            ".mp3;",  
            images);
			dialog.show();
			System.out.println("path:"+filepath);
		}
	}
}
