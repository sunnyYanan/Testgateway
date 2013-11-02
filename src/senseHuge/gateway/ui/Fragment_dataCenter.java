package senseHuge.gateway.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import senseHuge.gateway.service.DataProvider;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.testgateway.R;

public class Fragment_dataCenter extends ListFragment {
	ListView list;
	Button showAll;
	Button showAsTime;
	private ContentResolver contentResolver;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_data_center, container,
				false);
		list = (ListView) view.findViewById(android.R.id.list);
		contentResolver = this.getActivity().getContentResolver();
		showAll = (Button) view.findViewById(R.id.showAll);
		showAsTime = (Button) view.findViewById(R.id.showAsTime);

		showAll.setOnClickListener(new MyButtonClickListener());
		showAsTime.setOnClickListener(new MyButtonClickListener());

		showAllData();

		return view;
	}

	private class MyButtonClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch (arg0.getId()) {
			case R.id.showAll:
				showAllData();
				break;
			case R.id.showAsTime:
				showAsTime();
				break;
			}
		}

	}

	private void showAsTime() {
		// TODO Auto-generated method stub
		Cursor cursor = contentResolver.query(DataProvider.CONTENT_URI,
				new String[] { "_id", "message", "Ctype", "NodeID", "status",
						"receivetime" }, null, null, "receivetime DESC");
		int i = 2;
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		Map<String,String> item;
		while (cursor.moveToNext() && i > 0) {
			item = new HashMap<String, String>();
			item.put("message", cursor.getString(cursor.getColumnIndex("message")));
			item.put("Ctype", cursor.getString(cursor.getColumnIndex("Ctype")));
			item.put("NodeID", cursor.getString(cursor.getColumnIndex("NodeID")));
			item.put("status", cursor.getString(cursor.getColumnIndex("status")));
			item.put("receivetime", cursor.getString(cursor.getColumnIndex("receivetime")));
			data.add(item);
			i--;
		}
		SimpleAdapter adapter = new SimpleAdapter(this.getActivity(),data,
				R.layout.data_center_style, new String[] { "message", "Ctype",
						"NodeID", "status", "receivetime" }, new int[] {
						R.id.DBmessageShow, R.id.DBCtypeShow,
						R.id.DBNodeIDShow, R.id.DBstatusShow,
						R.id.DBreceivetimeShow });
		/*
		 * ( this.getActivity(), R.layout.data_center_style, cursor, new
		 * String[] { "message", "Ctype", "NodeID", "status", "receivetime" },
		 * new int[] { R.id.DBmessageShow, R.id.DBCtypeShow, R.id.DBNodeIDShow,
		 * R.id.DBstatusShow, R.id.DBreceivetimeShow },
		 * CursorAdapter.FLAG_AUTO_REQUERY);
		 */
		list.setAdapter(adapter);
		this.getActivity().startManagingCursor(cursor); // ���Һ�ر��α�
	}

	private void showAllData() {
		// TODO Auto-generated method stub

		// Cursor cursor = MainActivity.mDb.query("Telosb", new String[] {
		// "message", "Ctype", "NodeID", "status", "receivetime" }, null,
		// null, null, null, "receivetime DESC");
		Cursor cursor = contentResolver.query(DataProvider.CONTENT_URI,
				new String[] { "_id", "message", "Ctype", "NodeID", "status",
						"receivetime" }, null, null, "receivetime DESC");

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				this.getActivity(), R.layout.data_center_style, cursor,
				new String[] { "message", "Ctype", "NodeID", "status",
						"receivetime" }, new int[] { R.id.DBmessageShow,
						R.id.DBCtypeShow, R.id.DBNodeIDShow, R.id.DBstatusShow,
						R.id.DBreceivetimeShow },
				CursorAdapter.FLAG_AUTO_REQUERY);
		list.setAdapter(adapter);
		this.getActivity().startManagingCursor(cursor); // ���Һ�ر��α�
	}
}
