package senseHuge.gateway.ui;

import senseHuge.gateway.service.DataProvider;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.testgateway.R;

public class Fragment_dataCenter extends ListFragment {
	ListView list;
	private ContentResolver contentResolver;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_data_center, container,
				false);
		list = (ListView) view.findViewById(android.R.id.list);
		contentResolver = this.getActivity().getContentResolver();

		showData();

		return view;
	}

	private void showData() {
		// TODO Auto-generated method stub

		// Cursor cursor = MainActivity.mDb.query("Telosb", new String[] {
		// "message", "Ctype", "NodeID", "status", "receivetime" }, null,
		// null, null, null, "receivetime DESC");
		Cursor cursor = contentResolver.query(DataProvider.CONTENT_URI,
				new String[] { "message", "Ctype", "NodeID", "status",
						"receivetime" }, null, null, "receivetime DESC");

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				this.getActivity(), R.layout.data_center_style, cursor,
				new String[] {"message", "Ctype", "NodeID", "status",
						"receivetime" }, new int[] {R.id.DBmessageShow,
						R.id.DBCtypeShow, R.id.DBNodeIDShow, R.id.DBstatusShow,
						R.id.DBreceivetimeShow },
				CursorAdapter.FLAG_AUTO_REQUERY);
		list.setAdapter(adapter);
//		this.getActivity().startManagingCursor(cursor);  //查找后关闭游标

	}
}
