package com.example.testgateway;

import java.util.ArrayList;
import java.util.List;

import senseHuge.Dao.TelosbDao;
import senseHuge.listener.Listenable;
import senseHuge.listener.MyEvent;
import senseHuge.listener.MySource;
import senseHuge.model.PackagePattern;
import senseHuge.model.RingBuffer;
import senseHuge.model.TelosbPackage;
import senseHuge.service.LocalConfigService;
import senseHuge.util.HttpClientUtil;
import senseHuge.util.SerialUtil;
import senseHuge.util.XmlTelosbPackagePatternUtil;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android_serialport_api.SerialPort;

public class MainActivity extends FragmentActivity {

	private static final int capibity = 50;
	public static boolean isWork = false;
	private static final String tag = "sensehuge:";
	protected static final String tags = "sensehuge:";
	public TelosbDao telosbDao;
	public SerialPort mSerialPort;
	SerialUtil serialUtil = new SerialUtil();
	HttpClientUtil httpClientUtil;
	PackagePattern packagePattern = null;
	XmlTelosbPackagePatternUtil xmlTelosbPackagePatternUtil;
	public HaveData havadata = null;
	// HaveData havadata = new HaveData();
	List<String> list = new ArrayList<String>();
	int listSingnal = 0;

	boolean serverConnect = false;// 服务器是否连接
	boolean serialPortConnect = false;// 串口是否连接

	public RingBuffer<String> ringBuffer = new RingBuffer<String>(capibity);

	//调用以下资源的getvalue方法也可以判断当前的连接状态
	MySource ms;//这个变量应该是多余的，待删除
	MySource httpserverState;
	MySource serialState;
	MySource havePackage;

	FragmentManager manager;
	LinearLayout layout;
	Fragment f_serialPort, f_server, f_listnode, f_nodeSetting, f_alertSetting,
			f_dataCenter, f_aboutUs;
	Button serialPortSetting;
	Button serverSetting;
	Button sinkSetting;
	Button alertSetting;
	Button sinkCheck;
	Button internetSetting;
	Button wifiSetting;
	Button dataCenter;
	Button aboutUs;
	Button quit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 串口，服务器，节点监听事务
		manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		f_serialPort = new Fragment_serialconfig();
		f_server = new Fragment_serverconfig();
		f_listnode = new Fragment_listNode();
		f_nodeSetting = new Fragment_nodeSetting();
		f_alertSetting = new Fragment_alertSetting();
		f_dataCenter = new Fragment_dataCenter();
		f_aboutUs = new Fragment_aboutUs();

		// 得到按钮以及设置按钮监听器
		serialPortSetting = (Button) findViewById(R.id.serialPortSetting);
		serverSetting = (Button) findViewById(R.id.serverSetting);
		sinkSetting = (Button) findViewById(R.id.sinkSetting);
		alertSetting = (Button) findViewById(R.id.alertSetting);
		sinkCheck = (Button) findViewById(R.id.sinkCheck);
		internetSetting = (Button) findViewById(R.id.internetSetting);
		wifiSetting = (Button) findViewById(R.id.wifiSetting);
		dataCenter = (Button) findViewById(R.id.dataCenter);
		aboutUs = (Button) findViewById(R.id.aboutUs);
		quit = (Button) findViewById(R.id.quit);

		serialPortSetting.setOnClickListener(new ButtonClickListener());
		serverSetting.setOnClickListener(new ButtonClickListener());
		sinkSetting.setOnClickListener(new ButtonClickListener());
		alertSetting.setOnClickListener(new ButtonClickListener());
		sinkCheck.setOnClickListener(new ButtonClickListener());
		internetSetting.setOnClickListener(new ButtonClickListener());
		wifiSetting.setOnClickListener(new ButtonClickListener());
		dataCenter.setOnClickListener(new ButtonClickListener());
		aboutUs.setOnClickListener(new ButtonClickListener());
		quit.setOnClickListener(new ButtonClickListener());

		// 默认启动事务：节点
		transaction.add(R.id.fragment_container, f_serialPort);
		transaction.commit();

		init();
		System.out.println("inited:");
	}

	class ButtonClickListener implements OnClickListener {
		FragmentTransaction transaction;

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.serialPortSetting: {
				transaction = manager.beginTransaction();
				transaction.replace(R.id.fragment_container, f_serialPort);
				Log.i(tag, "button1");
				transaction.commit();
				break;
			}
			case R.id.serverSetting: {
				transaction = manager.beginTransaction();
				transaction.replace(R.id.fragment_container, f_server);
				transaction.commit();
				break;
			}
			case R.id.sinkSetting:
				transaction = manager.beginTransaction();
				transaction.replace(R.id.fragment_container, f_nodeSetting);
				transaction.commit();
				break;
			case R.id.alertSetting:
				transaction = manager.beginTransaction();
				transaction.replace(R.id.fragment_container, f_alertSetting);
				transaction.commit();
				break;
			case R.id.sinkCheck: {
				transaction = manager.beginTransaction();
				transaction.replace(R.id.fragment_container, f_listnode);
				transaction.commit();
				break;
			}

			case R.id.internetSetting: {
				startActivity(new Intent(
						android.provider.Settings.ACTION_SETTINGS));
				break;
			}
			case R.id.wifiSetting: {
				startActivity(new Intent(
						android.provider.Settings.ACTION_WIFI_SETTINGS));
				break;
			}
			case R.id.dataCenter: {
				transaction = manager.beginTransaction();
				transaction.replace(R.id.fragment_container, f_dataCenter);
				transaction.commit();
				break;
			}
			case R.id.aboutUs:
				transaction = manager.beginTransaction();
				transaction.replace(R.id.fragment_container, f_aboutUs);
				transaction.commit();
				break;
			case R.id.quit: {
				finish();
				break;
			}
			default: {
				transaction.replace(R.id.fragment_container, f_listnode);
				transaction.commit();
				break;
			}
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		// menu.add(0,1,1,"quit");//类型于asshow = never
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.serialPort_check:
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			dialog.setTitle("串口连接状态");
			if (serialPortConnect) {
				dialog.setMessage("已连接");
			} else {
				dialog.setMessage("未连接");
			}
			dialog.show();
			break;
		case R.id.server_check:
			AlertDialog.Builder dialog2 = new AlertDialog.Builder(this);
			dialog2.setTitle("服务器连接状态");
			if (serverConnect) {
				dialog2.setMessage("已连接");
			} else {
				dialog2.setMessage("未连接");
			}
			dialog2.show();
			break;
		case R.id.quit:
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * mainactivity 初始化
	 */
	public void init() {
		LocalConfigService localConfigService = new LocalConfigService(
				getBaseContext());
		localConfigService.setConfig("webserver", "192.168.10.145");
		// 初始化xml数据包格式并放入packagepattern中
		xmlTelosbPackagePatternUtil = new XmlTelosbPackagePatternUtil(
				getFilesDir().toString());
		/*
		 * System.out
		 * .println(xmlTelosbPackagePatternUtil.getPackagePattern().TelosbDataField
		 * .size() + "__________%%%_______");
		 * Log.i("XmlTelosbPackagePatternUtil",
		 * xmlTelosbPackagePatternUtil.getPackagePattern().TelosbDataField
		 * .size() + "__________%%%_______");
		 */
		// 客户端，服务器，串口，等资源的初始化
		httpClientUtil = new HttpClientUtil(getBaseContext());
		httpserverState = new MySource();
		serialState = new MySource();
		ms = new MySource();
		havePackage = new MySource();
		
		HttpserverState hl = new HttpserverState();
		SerialListener ml = new SerialListener();
		PackageListener pl = new PackageListener();
		httpserverState.addListener(hl);
		serialState.addListener(ml);
		havePackage.addListener(pl);

//		havePackage.setValue(false);

		// 创建数据库表
		telosbDao = new TelosbDao(getBaseContext());

		/*httpserverState.setValue(true);
		serialState.setValue(true);
		httpserverState.setValue(false);*/
	}

	public void ProcessData() {
		if (listSingnal == 0) {
			listSingnal = 1;
		} else {
			return;
		}
		while (list.size() > 0) {
			System.out.println("成功接收数据" + list.get(0));
			list.remove(0);
		}
		listSingnal = 0;
	}

	public class SerialListener implements Listenable {
		@Override
		public void eventChanged(MyEvent e) {
			// TODO Auto-generated method stub
			System.out.println("串口‘s e:" + e.getValue());
			if (serialState.value) {
				System.out.println("串口打开：XXXXXXXXXXXXXXXXXXXXXXXXXXX");
			} else {
				System.out.println("串口关闭中。。。。。XXXXXXXXXXXXXXXXXXXXS");
			}
		}
	}

	public class PackageListener implements Listenable {

		@Override
		public void eventChanged(MyEvent e) {
			// TODO Auto-generated method stub
			System.out.println("包's e:" + e.getValue());
			if (ms.value) {
				System.out.println("接收到数据，数据正在出处理。。。。");
				ProcessData();
			} else {
				System.out.println("数据接收完毕，等待中。。。。");
			}
		}
	}

	public class HttpserverState implements Listenable {
		@Override
		public void eventChanged(MyEvent e) {
			// TODO Auto-generated method stub
			System.out.println("server's e:" + e.getValue());
			if (e.getValue()) {
				System.out.println("服务器已连接：");
			} else {
				System.out.println("服务器已断开：");
			}
		}
	}

	public class HaveData extends Thread {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			// 获取到的数据包
			String headTest = "00FFFF";
			TelosbPackage telosbPackage = new TelosbPackage();
			int i;
			while (isWork) {
				i = serialUtil.findhead(headTest);
				if (i < 0 && serialUtil.stringBuffer.length() > 6) {
					serialUtil.delete(serialUtil.stringBuffer.length() - 6);
				} else if (i > 0) {
					serialUtil.delete(i);
				}
				if (serialUtil.stringBuffer.length() > 300) {
					System.out
							.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
					System.out.println(serialUtil.stringBuffer.toString());
					System.out
							.println("+++++++++++++++++++++++++++++++++++++++++++++");
					String telosbData = serialUtil.getFirstData();
					System.out.println("receive package:" + telosbData);

					if (telosbData == "") {
						System.out.println("empty");

					} else {
						System.out
								.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
						try {
							System.out.println(xmlTelosbPackagePatternUtil
									.parseTelosbPackage(telosbData).getCtype()
									+ ":%%%%%%%%%%%%%%");
						} catch (Exception e) {
							System.out.println("异常");
							e.printStackTrace();
							continue;
						}
//						;
						PackagePattern telosbPackagePattern = null;
						try {

							telosbPackagePattern = xmlTelosbPackagePatternUtil
									.parseTelosbPackage(telosbData);
						} catch (Exception e) {
							System.out.println("异常");
							e.printStackTrace();
							continue;

						}
						if (httpClientUtil.PostTelosbData("", telosbData)) {
							telosbPackage.setStatus("已上传");
						} else {
							telosbPackage.setStatus("未上传");
						}

						telosbPackage.setCtype(telosbPackagePattern.ctype);
						telosbPackage.setMessage(telosbData);
						telosbPackage.setNodeID(telosbPackagePattern.nodeID);
						boolean result = telosbDao
								.insertTelosbPackage(telosbPackage);
						if (result) {
							System.out
									.println("insert telosbPackage to database success%%");
						} else {
							System.out
									.println("insert telosbPackage to database fail！！！    ");
						}
						if (telosbData != null) {
							list.add(telosbData);
							Packagesingnal();
						}
						System.out.println(list.size() + "_____________");
						System.gc();
					}
				}
			}
		}
	}

	public HaveData getHaveData() {
		return new HaveData();
	}

	public void Packagesingnal() {
		havePackage.setValue(true);
	}

	public void Packagewaite() {
		havePackage.setValue(false);
	}

	public RingBuffer<String> getRingBuffer() {
		return ringBuffer;
	}

	public void setRingBuffer(RingBuffer<String> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}

	/**
	 * TEst
	 *//*
	public void createTable() throws Exception {
		DBHelper dbHelper = new DBHelper(this.getBaseContext());
		dbHelper.open();

		String deleteSql = "drop table if exists user ";
		dbHelper.execSQL(deleteSql);

		// id是自动增长的主键，username和 password为字段名， text为字段的类型
		String sql = "CREATE TABLE user (id integer primary key autoincrement, username text, password text)";
		dbHelper.execSQL(sql);
		dbHelper.closeConnection();
	}
	public void insert() throws Exception {
		DBHelper dbHelper = new DBHelper(this.getBaseContext());
		dbHelper.open();
		ContentValues values = new ContentValues(); // 相当于map
		values.put("username", "test");
		values.put("password", "123456");
		dbHelper.insert("user", values);
		dbHelper.closeConnection();
	}*/
}
