package senseHuge.gateway.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import senseHuge.gateway.model.PackagePattern;
import senseHuge.gateway.ui.Fragment_listNode;
import senseHuge.gateway.ui.MainActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

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

	// �ڽ��������������ȡ���ڵ��ţ������ظ��ʹ���Ҫ��ʾ�Ľڵ��б���
	private void getTheNodeInfo() {
		// TODO Auto-generated method stub
		db = MainActivity.mDbhelper.getReadableDatabase();
		Cursor cursor = db.query("Telosb", new String[] { "NodeID" }, null,
				null, null, null, "receivetime DESC");
		while (cursor.moveToNext()) {
			String id = cursor.getString(cursor.getColumnIndex("NodeID"));
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
		Cursor cursor = db.query("Telosb", new String[] { "message" },
				"NodeID=? AND CType=?", new String[] { string, "C1" }, null,
				null, "receivetime DESC");
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
				System.out.println("power after " + cur + ":" + power);
				// ��������ȷ�����ݣ��������ֵ4096
				if (power > 4096) {
					System.out
							.println("a bad data that has power bigger then Max happens");
					continue;
				}
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
		System.out.println("average power��" + b);
		TrigerTheAlert(b);
		return b;
	}

	private void TrigerTheAlert(float b) {
		// TODO Auto-generated method stub
		String alert = findTheAlertValueSetting();
		MediaPlayer mp = new MediaPlayer();
		// ��������
		if (alert != null) {
			// Ԥ����ֵ
			int pos = alert.indexOf("%");
			String valueStr = alert.substring(0, pos);
			float value = Float.parseFloat(valueStr);
			System.out.println(b/2.5);
			if (b / 2.5 <= (value / 100)) {
				// ��������
				String musicPath = findTheAlertMusicPath();
				System.out.println(musicPath);
				try {
					mp.setDataSource(musicPath);
					mp.prepareAsync();
					mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
						@Override
						public void onPrepared(MediaPlayer mp) {
							// TODO Auto-generated method stub
							mp.start();// �첽׼�����ݵķ�����service�ǿ������û�������Ӧ�ý���ʱ�����У���ʱ��Ҫwake
						}
					});
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	private String findTheAlertMusicPath() {
		// TODO Auto-generated method stub
		String path = null;
		Cursor cursor = db.query("AlertSetting", new String[] { "path" },
				"type=?", new String[] { "Ԥ������" }, null, null, null);
		while (cursor.moveToNext())
			path = cursor.getString(cursor.getColumnIndex("path"));
		cursor.close();
		return path;
	}

	private String findTheAlertValueSetting() {
		// TODO Auto-generated method stub
		String alertSetting = null;
		Cursor cursor = db.query("AlertSetting", new String[] { "value" },
				"type=?", new String[] { "Ԥ������" }, null, null, null);
		while (cursor.moveToNext()) {
			// ����д��Ĺ����趨����ʱ��ʵֻ��1������
			alertSetting = cursor.getString(cursor.getColumnIndex("value"));
		}
		cursor.close();
		return alertSetting;
	}

	private float getTheNodePower(PackagePattern mpp) {
		// TODO Auto-generated method stub
		Iterator<?> it = mpp.DataField.entrySet().iterator();
		String power = null;// ʮ�����Ʊ�ʾ
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			if (pairs.getKey().equals("�ڵ��ѹ")) {
				power = pairs.getValue().toString();
				System.out.println("power before:" + power);
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
