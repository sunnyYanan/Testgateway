package com.example.testgateway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Fragment_listNode extends ListFragment {
	FragmentManager fManager;
	FragmentTransaction fTransaction;
	Fragment nodeDetialFragment;

	// int currentPos;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_list_node, container,
				false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> data1 = new HashMap<String, String>();
		data1.put("nodeId", "1");
		data1.put("nodePower", "aha");
		list.add(data1);
		Map<String, String> data2 = new HashMap<String, String>();
		data2.put("nodeId", "2");
		data2.put("nodePower", "ddd");
		list.add(data2);
		Map<String, String> data3 = new HashMap<String, String>();
		data3.put("nodeId", "3");
		data3.put("nodePower", "ttt");
		list.add(data3);

		SimpleAdapter adapter = new SimpleAdapter(this.getActivity(), list,
				R.layout.fragment_node_item, new String[] { "nodeId",
						"nodePower" }, new int[] { R.id.nodeIdShow,
						R.id.nodePowerShow });
		this.setListAdapter(adapter);

		// 右侧显示的管理

		fManager = this.getActivity().getSupportFragmentManager();
		nodeDetialFragment = Fragment_nodeDetail.newInstance(0);
		fTransaction = fManager.beginTransaction();
		fTransaction.add(R.id.nodeDetails, nodeDetialFragment);
		fTransaction.commit();

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	private void showDetail(int position) {
		// TODO Auto-generated method stub
		getListView().setItemChecked(position, true);

		// Check what fragment is currently shown, replace if needed.
		Fragment_nodeDetail details = (Fragment_nodeDetail) getFragmentManager()
				.findFragmentById(R.id.nodeDetails);
		if (details == null || details.getShownIndex() != position) {
			// Make new fragment to show this selection.
			details = Fragment_nodeDetail.newInstance(position);

			// Execute a transaction, replacing any existing fragment
			// with this one inside the frame.
			fTransaction = fManager.beginTransaction();
			fTransaction.replace(R.id.nodeDetails, details);
			fTransaction
					.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			fTransaction.commit();
		}
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		// We can display everything in-place with fragments, so update
		// the list to highlight the selected item and show the data.
		showDetail(position);
	}
}
