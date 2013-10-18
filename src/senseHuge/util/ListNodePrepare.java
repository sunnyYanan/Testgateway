package senseHuge.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import senseHuge.model.PackagePattern;
import android.database.Cursor;
import android.util.Log;

import com.example.testgateway.Fragment_listNode;
import com.example.testgateway.MainActivity;
import com.example.testgateway.R;

public class ListNodePrepare {
	public void prepare() {
		Thread listNodeThread = new Thread(new MyThread());
		listNodeThread.start();
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
					if (!Fragment_listNode.nodeId.contains(id)) {
						Fragment_listNode.nodeId.add(id);
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
			for (int i = 0; i < Fragment_listNode.nodeId.size(); i++) {
				item.put("源节点编号", Fragment_listNode.nodeId.get(i));
				computeTheNodePower(Fragment_listNode.nodeId.get(i), item);
			}
			// item.put("节点电压", "11");
			Fragment_listNode.nodeList.add(item);
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

}
