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

	// 在解析后的数据中提取出节点编号，若无重复就存入要显示的节点列表中
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
		// 得到该节点的最近4条记录,并计算其电量平均值
		Cursor cursor = db.query("Telosb", new String[] { "message" },
				"NodeID=? AND CType=?", new String[] { string, "C1" }, null,
				null, "receivetime DESC");
		int i = 4;
		int cur = 0;
		float[] powers = new float[i];

		while (cursor.moveToNext() && i > 0) {
			String message = cursor.getString(cursor.getColumnIndex("message"));
			try {
				// 解析后的数据
				PackagePattern mpp = MainActivity.xmlTelosbPackagePatternUtil
						.parseTelosbPackage(message);
				float power = getTheNodePower(mpp);
				System.out.println("power after " + cur + ":" + power);
				// 舍弃不正确的数据，大于最大值4096
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
		item.put("节点电压", getTheAverage(powers) + "v");
		cursor.close();
	}

	// 计算电压平均值
	private float getTheAverage(float[] powers) {
		// TODO Auto-generated method stub
		float powerSum = 0;
		for (int i = 0; i < powers.length; i++) {
			powerSum += powers[i];
		}
		float power = (float) (powerSum / powers.length / 4096 * 2.5);
		float b = (float) (Math.round(power * 1000)) / 1000;
		// (这里的100就是2位小数点,如果要其它位,如4位,这里两个100改成10000)
		System.out.println("average power：" + b);
		TrigerTheAlert(b);
		return b;
	}

	private void TrigerTheAlert(float b) {
		// TODO Auto-generated method stub
		String alert = findTheAlertValueSetting();
		MediaPlayer mp = new MediaPlayer();
		// 处理数据
		if (alert != null) {
			// 预警阈值
			int pos = alert.indexOf("%");
			String valueStr = alert.substring(0, pos);
			float value = Float.parseFloat(valueStr);
			System.out.println(b/2.5);
			if (b / 2.5 <= (value / 100)) {
				// 播放音乐
				String musicPath = findTheAlertMusicPath();
				System.out.println(musicPath);
				try {
					mp.setDataSource(musicPath);
					mp.prepareAsync();
					mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
						@Override
						public void onPrepared(MediaPlayer mp) {
							// TODO Auto-generated method stub
							mp.start();// 异步准备数据的方法，service是可以在用户与其他应用交互时仍运行，此时需要wake
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
				"type=?", new String[] { "预警电量" }, null, null, null);
		while (cursor.moveToNext())
			path = cursor.getString(cursor.getColumnIndex("path"));
		cursor.close();
		return path;
	}

	private String findTheAlertValueSetting() {
		// TODO Auto-generated method stub
		String alertSetting = null;
		Cursor cursor = db.query("AlertSetting", new String[] { "value" },
				"type=?", new String[] { "预警电量" }, null, null, null);
		while (cursor.moveToNext()) {
			// 由于写入的规则设定，此时其实只有1条数据
			alertSetting = cursor.getString(cursor.getColumnIndex("value"));
		}
		cursor.close();
		return alertSetting;
	}

	private float getTheNodePower(PackagePattern mpp) {
		// TODO Auto-generated method stub
		Iterator<?> it = mpp.DataField.entrySet().iterator();
		String power = null;// 十六进制表示
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			if (pairs.getKey().equals("节点电压")) {
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
