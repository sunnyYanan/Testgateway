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
	// ������ʾ�Ľڵ�����
	public static List<Map<String, Object>> nodeList = new ArrayList<Map<String, Object>>();
	// ���ҳ�������ʾ�Ĳ��ظ��Ľڵ�id
	public static List<String> nodeId = new ArrayList<String>();
	ListView packageList;
	View dialog;
	TextView packageAfterParse;
	// dialog��ʾ�����ĵ�ǰ�ڵ�����а�������
	List<Map<String, String>> content;
	GridView gridview;

	// �Ҳ��������ʾ
	/*
	 * FragmentManager fManager; Fragment packageDetialFragment;
	 * FragmentTransaction fTransaction;
	 */

	// List<String> powerList = new ArrayList<String>();
	@Override
	public void onAttach(Activity a) {
		super.onAttach(a);
//		init();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// fManager = this.getChildFragmentManager();
		super.onCreate(savedInstanceState);
	}

	// ׼���ڵ�����
	/*public void init() {
		Thread listNodeThread = new Thread(new MyThread());
		listNodeThread.start();
	}*/

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_list_node, container,
				false);
		gridview = (GridView) view.findViewById(R.id.gridview);
		show();
		return view;
	}
	private void show() {
		// TODO Auto-generated method stub
		// ʵ����һ��������
		SimpleAdapter adapter = new SimpleAdapter(this.getActivity(), nodeList,
				R.layout.list_node_page_style, new String[] { "ͼƬ", "Դ�ڵ���",
						"�ڵ��ѹ" }, new int[] { R.id.listNodeImage,
						R.id.listNodeId, R.id.listNodePower });
		gridview.setAdapter(adapter);
		gridview.setOnItemClickListener(new MyItemClickListener());
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
			d.setTitle("��" + (arg2 + 1) + "���ڵ��еİ����ڵ�ID�ǣ�" + nodeId.get(arg2))
					.setView(dialog).setPositiveButton("ȷ��", null);

			// ��ʼ���Ҳ����ʾ

			/*
			 * packageDetialFragment = Fragment_packageDetail.newInstance(0);
			 * fTransaction = getFragmentManager().beginTransaction();
			 * fTransaction.add(R.id.packageRight, packageDetialFragment);
			 * fTransaction.commit();
			 */
			d.show();

		}

		// �õ���Ӧ�ڵ��ȫ����������Ϊ�ڵ�ID
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

			// �����б����¼�
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
				// ȡ����ǰ��Ҫ����������
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
	
}
