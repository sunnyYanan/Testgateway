package com.example.testgateway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import senseHuge.model.PackagePattern;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class Fragment_listNode extends Fragment {
	/*
	 * FragmentManager fManager; FragmentTransaction fTransaction; Fragment
	 * nodeDetialFragment;
	 */
	// MainActivity ma;
	List<Map<String, Object>> nodeList = new ArrayList<Map<String, Object>>();
	List<String> nodeId = new ArrayList<String>();

	// List<String> powerList = new ArrayList<String>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_list_node, container,
				false);

		Thread listNodeThread = new Thread(new MyThread());
		listNodeThread.start();

		// 实例化一个适配器
		SimpleAdapter adapter = new SimpleAdapter(this.getActivity(), nodeList,
				R.layout.list_node_page_style, new String[] { "图片", "源节点编号",
						"节点电压" }, new int[] { R.id.listNodeImage,
						R.id.listNodeId, R.id.listNodePower });

		GridView gridview = (GridView) view.findViewById(R.id.gridview);
		gridview.setAdapter(adapter);

		gridview.setOnItemClickListener(new MyItemClickListener());
		return view;
	}

	class MyItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			View dialog = LayoutInflater.from(arg1.getContext()).inflate(
					R.layout.list_node_package_style, null);
			AlertDialog.Builder d = new AlertDialog.Builder(arg1.getContext());
			d.setTitle("第" + (arg2 + 1) + "个节点中的包").setView(dialog)
					.setPositiveButton("确定", null);
			d.show();
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
		// 这样的查找是从最早的记录开始查找的,只查找C1包，因为只有C1包有电压信息
		Cursor cursor = MainActivity.mDb.query("Telosb",
				new String[] { "message" }, "CType=?", new String[] { "C1" },
				null, null, null);
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

	// 在解析后的数据中提取出节点编号和电压，并存入要显示的数据中
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
			} /*
			 * else if (pairs.getKey().equals("节点电压")) // 
			 */
			System.out.println(pairs.getKey() + " =============== "
					+ pairs.getValue());
		}
		// nodeList.add(item);
	}

	private void addNodeIntoList() {
		// TODO Auto-generated method stub
		Map<String, Object> item = new HashMap<String, Object>();
		item.put("图片", R.drawable.ic_launcher);
		for (int i = 0; i < nodeId.size(); i++) {
			item.put("源节点编号", nodeId.get(i));
		}
		item.put("节点电压", "11");
		nodeList.add(item);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
