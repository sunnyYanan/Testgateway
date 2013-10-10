package com.example.testgateway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Fragment_listNode extends ListFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// View
		// view1=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_list_node,null);

		// 事件监听
		/*
		 * gridview.setOnItemClickListener(new OnItemClickListener() { public
		 * void onItemClick(AdapterView<?> parent, View v, int position, long
		 * id) { View
		 * viewdialog=LayoutInflater.from(getActivity()).inflate(R.layout
		 * .node_info,null);
		 * 
		 * ListView list = (ListView) viewdialog.findViewById(R.id.ListView01);
		 * 
		 * //生成动态数组，加入数据 ArrayList<HashMap<String, Object>> listItem = new
		 * ArrayList<HashMap<String, Object>>(); for(int i=0;i<10;i++) {
		 * HashMap<String, Object> map = new HashMap<String, Object>();
		 * map.put("ItemImage", R.drawable.img1);//图像资源的ID map.put("ItemTitle",
		 * "Level "+i); map.put("ItemText",
		 * "Finished in 1 Min 54 Secs, 70 Moves! "); listItem.add(map); }
		 * //生成适配器的Item和动态数组对应的元素 SimpleAdapter listItemAdapter = new
		 * SimpleAdapter(getActivity(),listItem,//数据源
		 * R.layout.list_items,//ListItem的XML实现 //动态数组与ImageItem对应的子项 new
		 * String[] {"ItemImage","ItemTitle", "ItemText"},
		 * //ImageItem的XML文件里面的一个ImageView,两个TextView ID new int[]
		 * {R.id.ItemImage,R.id.ItemTitle,R.id.ItemText} );
		 * 
		 * //添加并且显示 list.setAdapter(listItemAdapter); new
		 * AlertDialog.Builder(getActivity
		 * ()).setTitle("节点信息：").setView(viewdialog) .setPositiveButton("确定",
		 * null) .setNegativeButton("取消", null).show();
		 * 
		 * Toast.makeText(getActivity(), "你选择了" + (position + 1) + " 号图片",
		 * Toast.LENGTH_SHORT).show();
		 * 
		 * } });
		 */
		View view = inflater.inflate(R.layout.fragment_list_node, container, false);
		
		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> data1 = new HashMap<String, String>();
		data1.put("nodeId", "1");
		data1.put("nodePower", "aha");
		list.add(data1);
		SimpleAdapter adapter = new SimpleAdapter(this.getActivity(), list,
				R.layout.fragment_node_item, new String[] { "nodeId",
						"nodePower" }, new int[] { R.id.nodeIdShow,
						R.id.nodePowerShow });
		this.setListAdapter(adapter);

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Toast.makeText(this.getActivity(), "hah", Toast.LENGTH_SHORT).show();
	}
}
