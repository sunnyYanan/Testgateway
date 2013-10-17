package com.example.testgateway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import senseHuge.model.PackagePattern;
import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Fragment_listNode extends Fragment {
	// 界面显示的节点数据
	List<Map<String, Object>> nodeList = new ArrayList<Map<String, Object>>();
	// 查找出来的显示的不重复的节点id
	List<String> nodeId = new ArrayList<String>();
	ListView packageList;
	View dialog;
	TextView packageAfterParse;
	// dialog显示出来的当前节点的所有包的内容
	List<Map<String, String>> content;
	GridView gridview;

	// 右侧包内容显示
	/*
	 * FragmentManager fManager; Fragment packageDetialFragment;
	 * FragmentTransaction fTransaction;
	 */

	// List<String> powerList = new ArrayList<String>();
	@Override
	public void onAttach(Activity a) {
		super.onAttach(a);
		init();
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// fManager = this.getChildFragmentManager();
		super.onCreate(savedInstanceState);
	}

	// 准备节点数据
	public void init() {
		Thread listNodeThread = new Thread(new MyThread());
		listNodeThread.start();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_list_node, container,
				false);
		gridview = (GridView) view.findViewById(R.id.gridview);

		// 实例化一个适配器
		SimpleAdapter adapter = new SimpleAdapter(this.getActivity(), nodeList,
				R.layout.list_node_page_style, new String[] { "图片", "源节点编号",
						"节点电压" }, new int[] { R.id.listNodeImage,
						R.id.listNodeId, R.id.listNodePower });
		gridview.setAdapter(adapter);
		gridview.setOnItemClickListener(new MyItemClickListener());
		return view;
	}

	class MyItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			dialog = LayoutInflater.from(arg1.getContext()).inflate(
					R.layout.list_node_package_style, null);

			packageAfterParse = (TextView) dialog
					.findViewById(R.id.packageRight);

			getThePackage(nodeId.get(arg2));
			AlertDialog.Builder d = new AlertDialog.Builder(arg1.getContext());
			d.setTitle("第" + (arg2 + 1) + "个节点中的包，节点ID是：" + nodeId.get(arg2))
					.setView(dialog).setPositiveButton("确定", null);

			// 初始化右侧包显示

			/*
			 * packageDetialFragment = Fragment_packageDetail.newInstance(0);
			 * fTransaction = getFragmentManager().beginTransaction();
			 * fTransaction.add(R.id.packageRight, packageDetialFragment);
			 * fTransaction.commit();
			 */
			d.show();

		}

		// 得到相应节点的全部包，参数为节点ID
		private void getThePackage(String string) {
			// TODO Auto-generated method stub
			Cursor cursor = MainActivity.mDb.query("Telosb", new String[] {
					"Ctype", "status", "message" }, "NodeID=?",
					new String[] { string }, null, null, "receivetime DESC");
			content = new ArrayList<Map<String, String>>();
			Map<String, String> data;
			while (cursor.moveToNext()) {
				data = new HashMap<String, String>();
				String message = cursor.getString(cursor
						.getColumnIndex("message"));
				String status = cursor.getString(cursor
						.getColumnIndex("status"));
				String type = cursor.getString(cursor.getColumnIndex("Ctype"));

				data.put("type", type);
				data.put("status", status);
				data.put("message", message);
				content.add(data);
			}
			putDataIntoPackage(content);
		}

		private void putDataIntoPackage(List<Map<String, String>> content) {
			// TODO Auto-generated method stub
			packageList = (ListView) dialog.findViewById(android.R.id.list);
			SimpleAdapter adapter = new SimpleAdapter(dialog.getContext(),
					content, R.layout.list_node_package, new String[] { "type",
							"status", "message" }, new int[] {
							R.id.packageType, R.id.packageStatus,
							R.id.packageMessage });
			packageList.setAdapter(adapter);

			// 设置列表点击事件
			packageList.setOnItemClickListener(new MyListItemClickListener());
		}

		class MyListItemClickListener implements OnItemClickListener {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				// showDetail(arg2);
				/*
				 * switch (arg2) { case 0: packageAfterParse.setText("0");
				 * break; case 1: packageAfterParse.setText("1"); break; case 2:
				 * packageAfterParse.setText("2"); break; default:
				 * packageAfterParse.setText("qq"); break; }
				 */
				Map<String, String> packageMessage = content.get(arg2);
				Iterator<?> it = packageMessage.entrySet().iterator();
				String message;
				PackagePattern pp;
				// 取出当前需要解析的数据
				while (it.hasNext()) {
					Map.Entry pairs = (Map.Entry) it.next();
					if (pairs.getKey().equals("message")) {
						message = pairs.getValue().toString();
						try {
							pp = MainActivity.xmlTelosbPackagePatternUtil
									.parseTelosbPackage(message);
							showTheParsedPackage(pp);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}

			private void showTheParsedPackage(PackagePattern pp) {
				// TODO Auto-generated method stub
				Iterator<?> it = pp.DataField.entrySet().iterator();
				StringBuffer sb = new StringBuffer();
				while (it.hasNext()) {
					Map.Entry pairs = (Map.Entry) it.next();
					sb.append(pairs.getKey().toString() + ": "
							+ pairs.getValue().toString());
					sb.append("\n");
				}
				packageAfterParse.setText(sb.toString());
			}
			/*
			 * private void showDetail(int position) { // Check what fragment is
			 * currently shown, replace if needed. Fragment_packageDetail
			 * details = (Fragment_packageDetail) getFragmentManager()
			 * .findFragmentById(R.id.packageRight); fTransaction =
			 * getFragmentManager().beginTransaction(); if(details == null) {
			 * details = Fragment_packageDetail.newInstance(-1);
			 * fTransaction.add(R.id.packageRight, details);
			 * 
			 * } else if (details.getShownIndex() != position) { details =
			 * Fragment_packageDetail.newInstance(position);
			 * fTransaction.replace(R.id.packageRight, details); } fTransaction
			 * .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			 * fTransaction.commit(); }
			 */
		}

	}

	class MyThread implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			prepareData();
		}
	}

	private void prepareData() {

		// TODO Auto-generated method stub
		// 得到数据并解析
		// 按接受时间的降序排列,只查找C1包，因为只有C1包有电压信息
		Cursor cursor = MainActivity.mDb.query("Telosb",
				new String[] { "message" }, "CType=?", new String[] { "C1" },
				null, null, "receivetime DESC");
		while (cursor.moveToNext()) {
			String message = cursor.getString(cursor.getColumnIndex("message"));
			System.out.println("query--->" + message);
			try {
				// 解析后的数据
				PackagePattern mpp = MainActivity.xmlTelosbPackagePatternUtil
						.parseTelosbPackage(message);
				getTheNodeInfo(mpp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 在解析后的数据中提取出节点编号，若无重复就存入要显示的节点列表中
	private void getTheNodeInfo(PackagePattern mpp) {
		// TODO Auto-generated method stub
		Iterator<?> it = mpp.DataField.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			if (pairs.getKey().equals("源节点编号")) {
				String id = pairs.getValue().toString();
				if (!nodeId.contains(id)) {
					nodeId.add(id);
					addNodeIntoList();
				}
			}
		}
	}

	// 将节点加入到显示列表中
	private void addNodeIntoList() {
		// TODO Auto-generated method stub
		Map<String, Object> item = new HashMap<String, Object>();
		item.put("图片", R.drawable.ic_launcher);
		for (int i = 0; i < nodeId.size(); i++) {
			item.put("源节点编号", nodeId.get(i));
			computeTheNodePower(nodeId.get(i), item);
		}
		// item.put("节点电压", "11");
		nodeList.add(item);
	}

	/**
	 * @param string
	 *            节点编号的字符串标识
	 * @param item
	 *            待存入的map 得到节点的电量，并存入数据中
	 */
	private void computeTheNodePower(String string, Map<String, Object> item) {
		// TODO Auto-generated method stub
		// 得到该节点的最近2条记录,并计算其电量平均值
		Cursor cursor = MainActivity.mDb.query("Telosb",
				new String[] { "message" }, "NodeID=? AND CType=?",
				new String[] { string, "C1" }, null, null, "receivetime DESC");
		int i = 1;
		int cur = 0;
		String[] powers = new String[i];

		while (cursor.moveToNext() && i > 0) {
			String message = cursor.getString(cursor.getColumnIndex("message"));
			try {
				// 解析后的数据
				PackagePattern mpp = MainActivity.xmlTelosbPackagePatternUtil
						.parseTelosbPackage(message);
				String power = getTheNodePower(mpp);
				powers[cur++] = power;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i--;
		}
		item.put("节点电压", getTheAverage(powers));
	}

	// 计算电压平均值，目前未实现，数据不知道该如何处理
	private Object getTheAverage(String[] powers) {
		// TODO Auto-generated method stub
		return powers[0];
	}

	private String getTheNodePower(PackagePattern mpp) {
		// TODO Auto-generated method stub
		Iterator<?> it = mpp.DataField.entrySet().iterator();
		String power = null;
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			if (pairs.getKey().equals("节点电压")) {
				power = pairs.getValue().toString();
				Log.i("power", power);
			}
		}
		return power;
	}
	/*
	 * public class ImageAdapter extends BaseAdapter { private Context mContext;
	 * 
	 * public ImageAdapter(Context c) { mContext = c; }
	 * 
	 * public int getCount() { return mThumbIds.length; }
	 * 
	 * public Object getItem(int position) { return null; }
	 * 
	 * public long getItemId(int position) { return 0; }
	 * 
	 * // create a new ImageView for each item referenced by the Adapter public
	 * View getView(int position, View convertView, ViewGroup parent) {
	 * ImageView imageView; if (convertView == null) { // if it's not recycled,
	 * initialize some // attributes imageView = new ImageView(mContext);
	 * imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
	 * imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	 * imageView.setPadding(8, 8, 8, 8); } else { imageView = (ImageView)
	 * convertView; }
	 * 
	 * imageView.setImageResource(mThumbIds[position]); return imageView; }
	 * 
	 * // references to our images private Integer[] mThumbIds = {
	 * R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
	 * R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
	 * R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
	 * R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
	 * R.drawable.ic_launcher, R.drawable.ic_launcher, }; }
	 */

	/*
	 * @Override public void onActivityCreated(Bundle savedInstanceState) {
	 * super.onActivityCreated(savedInstanceState); List<Map<String, String>>
	 * list = new ArrayList<Map<String, String>>(); Map<String, String> data1 =
	 * new HashMap<String, String>(); data1.put("nodeId", "1");
	 * data1.put("nodePower", "aha"); list.add(data1); Map<String, String> data2
	 * = new HashMap<String, String>(); data2.put("nodeId", "2");
	 * data2.put("nodePower", "ddd"); list.add(data2); Map<String, String> data3
	 * = new HashMap<String, String>(); data3.put("nodeId", "3");
	 * data3.put("nodePower", "ttt"); list.add(data3);
	 * 
	 * SimpleAdapter adapter = new SimpleAdapter(this.getActivity(), list,
	 * R.layout.fragment_node_item, new String[] { "nodeId", "nodePower" }, new
	 * int[] { R.id.nodeIdShow, R.id.nodePowerShow });
	 * this.setListAdapter(adapter);
	 * 
	 * // 右侧显示的管理
	 * 
	 * fManager = this.getActivity().getSupportFragmentManager();
	 * nodeDetialFragment = Fragment_nodeDetail.newInstance(0); fTransaction =
	 * fManager.beginTransaction(); fTransaction.add(R.id.nodeDetails,
	 * nodeDetialFragment); fTransaction.commit();
	 * 
	 * }
	 */

	/*
	 * private void showDetail(int position) { // TODO Auto-generated method
	 * stub getListView().setItemChecked(position, true);
	 * 
	 * // Check what fragment is currently shown, replace if needed.
	 * Fragment_nodeDetail details = (Fragment_nodeDetail) getFragmentManager()
	 * .findFragmentById(R.id.nodeDetails); if (details == null ||
	 * details.getShownIndex() != position) { // Make new fragment to show this
	 * selection. details = Fragment_nodeDetail.newInstance(position);
	 * 
	 * // Execute a transaction, replacing any existing fragment // with this
	 * one inside the frame. fTransaction = fManager.beginTransaction();
	 * fTransaction.replace(R.id.nodeDetails, details); fTransaction
	 * .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
	 * fTransaction.commit(); } }
	 */
	/*
	 * @Override public void onListItemClick(ListView l, View v, int position,
	 * long id) { super.onListItemClick(l, v, position, id); // We can display
	 * everything in-place with fragments, so update // the list to highlight
	 * the selected item and show the data. showDetail(position); }
	 */
}
