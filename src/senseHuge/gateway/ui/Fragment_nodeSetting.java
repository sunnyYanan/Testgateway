package senseHuge.gateway.ui;

import java.io.IOException;

import senseHuge.gateway.service.FileChooserActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.testgateway.R;

public class Fragment_nodeSetting extends Fragment {
	// 节点设置
	public MainActivity ma;
	private boolean isAvalable;// 标识串口是否已连接
	private int cycle;// 标识设置的周期时间
	Spinner sendCycleSpinner;
	Spinner powerSettingSpinner;
	Button musicChooseButton;
	int alertPower = 15;// 预警值的设置
	String alertMusicPath;// 预警音乐设置
	
	private static String TAG = "MainActivity";
	private static final int REQUEST_CODE = 1;   //请求码
	public static final String EXTRA_FILE_CHOOSER = "file_chooser";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		ma = (MainActivity) getActivity();
		// 默认发包周期是5秒
		cycle = 5;
		if (Fragment_serialconfig.mSerialPort == null) {
			isAvalable = false;
		} else {
			isAvalable = true;
		}

		View v = inflater.inflate(R.layout.fragment_node_setting, container,
				false);

		sendCycleSpinner = (Spinner) v.findViewById(R.id.sendCycleSelect);
		powerSettingSpinner = (Spinner) v
				.findViewById(R.id.powerSettingSpinner);
		musicChooseButton = (Button) v.findViewById(R.id.musicSelect);

		ArrayAdapter<CharSequence> sendCycleAdapter = ArrayAdapter
				.createFromResource(getActivity(), R.array.nodeSettingCycle,
						android.R.layout.simple_spinner_item);
		sendCycleAdapter
				.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

		ArrayAdapter<CharSequence> powerSettingAdapter = ArrayAdapter
				.createFromResource(getActivity(), R.array.powerSetting,
						android.R.layout.simple_spinner_item);
		powerSettingAdapter
				.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

		sendCycleSpinner.setAdapter(sendCycleAdapter);
		powerSettingSpinner.setAdapter(powerSettingAdapter);

		sendCycleSpinner
				.setOnItemSelectedListener(new SendCycleSelectedListener());
		powerSettingSpinner
				.setOnItemSelectedListener(new PowerSettingListener());
		musicChooseButton.setOnClickListener(new MyButtonListener());
		return v;
	}

	public class MyButtonListener implements OnClickListener {
		String filepath;
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent fileChooserIntent = new Intent(arg0.getContext(),
					FileChooserActivity.class);
			startActivityForResult(fileChooserIntent, REQUEST_CODE);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		/*Log.v(TAG, "onActivityResult#requestCode:" + requestCode
				+ "#resultCode:" + resultCode);
		this.getActivity();*/
		if (resultCode == FragmentActivity.RESULT_CANCELED) {
			toast("没有打开文件");
			return;
		}
//		this.getActivity();
		if (resultCode == FragmentActivity.RESULT_OK
				&& requestCode == REQUEST_CODE) {
			// 获取路径名
			String musicPath = data.getStringExtra(EXTRA_FILE_CHOOSER);
//			Log.v(TAG, "onActivityResult # musicPath : " + musicPath);
			if (musicPath != null) {
				toast("Choose File : " + musicPath);
				alertMusicPath = musicPath;// 预警音乐路径设置
			} else
				toast("打开文件失败");
		}
	}

	private void toast(CharSequence hint) {
		Toast.makeText(this.getActivity().getBaseContext(), hint,
				Toast.LENGTH_SHORT).show();
	}

	public class PowerSettingListener implements OnItemSelectedListener {
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			System.out.println("powerSettingpos---->" + arg2);
			setAlertPower(arg2);
			System.out.println("powerSettingValue---->" + alertPower);

			if (isAvalable) {
				try {
					Fragment_serialconfig.mSerialPort.getOutputStream().write(
							alertPower);
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

	public class SendCycleSelectedListener implements OnItemSelectedListener {
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			System.out.println("SendCyclepos---->" + arg2);
			setCycle(arg2);
			System.out.println("SendCycleValue---->" + cycle);
			if (isAvalable) {
				try {
					Fragment_serialconfig.mSerialPort.getOutputStream().write(
							cycle);
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
	}

	/**
	 * @param arg2
	 *            用户选择 设置发包周期，以秒为单位
	 */
	private void setCycle(int arg2) {
		// TODO Auto-generated method stub
		if (arg2 == 0) {
			cycle = 5;
		} else if (arg2 == 1) {
			cycle = 10;
		} else if (arg2 == 2) {
			cycle = 30;
		} else if (arg2 == 3) {
			cycle = 1 * 60;
		} else if (arg2 == 4) {
			cycle = 2 * 60;
		} else if (arg2 == 5) {
			cycle = 5 * 60;
		} else if (arg2 == 6) {
			cycle = 10 * 60;
		}
	}
}
