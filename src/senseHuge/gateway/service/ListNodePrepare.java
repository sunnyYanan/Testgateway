package senseHuge.gateway.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import senseHuge.gateway.model.PackagePattern;
import senseHuge.gateway.ui.Fragment_listNode;
import senseHuge.gateway.ui.MainActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.testgateway.R;

public class ListNodePrepare {
	SQLiteDatabase db;
	public void prepare() {
		Thread listNodeThread = new Thread(new MyThread());
		listNodeThread.start();
	}

	class MyThread implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			getTheNodeInfo();
		}
	}

/*	private void prepareData() {

		// TODO Auto-generated method stub
		// �õ����ݲ�����
		// ������ʱ��Ľ�������,ֻ����C1������Ϊֻ��C1���е�ѹ��Ϣ
		Cursor cursor = MainActivity.mDb.query("Telosb",
				new String[] { "message" }, "CType=?", new String[] { "C1" },
				null, null, "receivetime DESC");
		while (cursor.moveToNext()) {
			String message = cursor.getString(cursor.getColumnIndex("message"));
			// System.out.println("query--->" + message);
			try {
				// �����������
				PackagePattern mpp = MainActivity.xmlTelosbPackagePatternUtil
						.parseTelosbPackage(message);
				getTheNodeInfo(mpp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		cursor.close();
	}*/

	// �ڽ��������������ȡ���ڵ��ţ������ظ��ʹ���Ҫ��ʾ�Ľڵ��б���
	private void getTheNodeInfo() {
		// TODO Auto-generated method stub
		db = MainActivity.mDbhelper.getReadableDatabase();
		Cursor cursor = db.query("Telosb",
				new String[] { "NodeID" }, null, null,
				null, null, "receivetime DESC");
		while (cursor.moveToNext()) {
			String id = cursor.getString(cursor.getColumnIndex("NodeID"));
			System.out.println("id:"+id);
				if (!Fragment_listNode.nodeId.contains(id)) {
					Fragment_listNode.nodeId.add(id);
					addNodeIntoList();
				}
		}
		db.close();
	}

	// ���ڵ���뵽��ʾ�б���
	private void addNodeIntoList() {
		// TODO Auto-generated method stub
		Map<String, Object> item = new HashMap<String, Object>();
		item.put("ͼƬ", R.drawable.ic_launcher);
		for (int i = 0; i < Fragment_listNode.nodeId.size(); i++) {
			item.put("Դ�ڵ���", Fragment_listNode.nodeId.get(i));
			computeTheNodePower(Fragment_listNode.nodeId.get(i), item);
		}
		// item.put("�ڵ��ѹ", "11");
		Fragment_listNode.nodeList.add(item);
	}

	/**
	 * @param string
	 *            �ڵ��ŵ��ַ�����ʶ
	 * @param item
	 *            �������map �õ��ڵ�ĵ�����������������
	 */
	private void computeTheNodePower(String string, Map<String, Object> item) {
		// TODO Auto-generated method stub
		// �õ��ýڵ�����4����¼,�����������ƽ��ֵ
		Cursor cursor = db.query("Telosb",
				new String[] { "message" }, "NodeID=? AND CType=?",
				new String[] { string, "C1" }, null, null, "receivetime DESC");
		int i = 4;
		int cur = 0;
		float[] powers = new float[i];

		while (cursor.moveToNext() && i > 0) {
			String message = cursor.getString(cursor.getColumnIndex("message"));
			try {
				// �����������
				PackagePattern mpp = MainActivity.xmlTelosbPackagePatternUtil
						.parseTelosbPackage(message);
				float power = getTheNodePower(mpp);
				System.out.println("power after "+cur+":"+power);
				powers[cur++] = power;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i--;
		}
		item.put("�ڵ��ѹ", getTheAverage(powers) + "v");
		cursor.close();
	}

	// �����ѹƽ��ֵ
	private float getTheAverage(float[] powers) {
		// TODO Auto-generated method stub
		float powerSum = 0;
		for (int i = 0; i < powers.length; i++) {
			powerSum += powers[i];
		}
		float power = (float) (powerSum / powers.length / 4096 * 2.5);
		float b = (float) (Math.round(power * 1000)) / 1000;
		// (�����100����2λС����,���Ҫ����λ,��4λ,��������100�ĳ�10000)
		return b;
	}

	private float getTheNodePower(PackagePattern mpp) {
		// TODO Auto-generated method stub
		Iterator<?> it = mpp.DataField.entrySet().iterator();
		String power = null;// ʮ�����Ʊ�ʾ
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			if (pairs.getKey().equals("�ڵ��ѹ")) {
				power = pairs.getValue().toString();
				System.out.println("power before:"+power);
			}
		}
		int powerDeci = 0;
		for (int i = power.length() - 1; i >= 0; i--) {
			char n = power.charAt(i);
			int num = 0;
			if (n == 'A') {
				num = 10;
			} else if (n == 'B') {
				num = 11;
			} else if (n == 'C') {
				num = 12;
			} else if (n == 'D') {
				num = 13;
			} else if (n == 'E') {
				num = 14;
			} else if (n == 'F') {
				num = 15;
			} else
				num = Integer.parseInt(String.valueOf(n));

			powerDeci += num * Math.pow(16, 3 - i);
		}
		return powerDeci;
	}

}
