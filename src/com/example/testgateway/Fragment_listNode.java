package com.example.testgateway;



import java.util.ArrayList;
import java.util.HashMap;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Fragment_listNode extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view1=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_list_node,null);

		//�¼�����
		/*gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id)
			{
				View viewdialog=LayoutInflater.from(getActivity()).inflate(R.layout.node_info,null);
				
				 ListView list = (ListView) viewdialog.findViewById(R.id.ListView01);  
		          
			        //���ɶ�̬���飬��������  
			        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();  
			        for(int i=0;i<10;i++)  
			        {  
			            HashMap<String, Object> map = new HashMap<String, Object>();  
			            map.put("ItemImage", R.drawable.img1);//ͼ����Դ��ID  
			            map.put("ItemTitle", "Level "+i);  
			            map.put("ItemText", "Finished in 1 Min 54 Secs, 70 Moves! ");  
			            listItem.add(map);  
			        }  
			        //������������Item�Ͷ�̬�����Ӧ��Ԫ��  
			        SimpleAdapter listItemAdapter = new SimpleAdapter(getActivity(),listItem,//����Դ   
			            R.layout.list_items,//ListItem��XMLʵ��  
			            //��̬������ImageItem��Ӧ������          
			            new String[] {"ItemImage","ItemTitle", "ItemText"},   
			            //ImageItem��XML�ļ������һ��ImageView,����TextView ID  
			            new int[] {R.id.ItemImage,R.id.ItemTitle,R.id.ItemText}  
			        );  
			         
			        //��Ӳ�����ʾ  
			        list.setAdapter(listItemAdapter);  
				   new AlertDialog.Builder(getActivity()).setTitle("�ڵ���Ϣ��").setView(viewdialog)
				     .setPositiveButton("ȷ��", null)
				     .setNegativeButton("ȡ��", null).show();

     			Toast.makeText(getActivity(), "��ѡ����" + (position + 1) + " ��ͼƬ", Toast.LENGTH_SHORT).show();
				
			}
		});
*/
		return view1;
	}
}
