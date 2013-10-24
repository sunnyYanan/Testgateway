package senseHuge.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import senseHuge.model.PackagePattern;
import senseHuge.model.TelosbPackage;
import android.content.ContentValues;

import com.example.testgateway.Fragment_serialconfig;
import com.example.testgateway.MainActivity;

public class SerialportDataProcess extends Thread {
//	List<String> list = new ArrayList<String>();//全部包
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		// 获取到的数据包
		String headTest = "00FFFF";
		TelosbPackage telosbPackage = new TelosbPackage();
		int i;
		while (MainActivity.serialPortConnect) {
			i = Fragment_serialconfig.serialUtil.findhead(headTest);
			System.out.println("包头位置："+i);
			System.out.println("未处理前："+Fragment_serialconfig.serialUtil.stringBuffer.toString());
			if (i < 0 && Fragment_serialconfig.serialUtil.stringBuffer.length() > 6) {
				Fragment_serialconfig.serialUtil.delete(Fragment_serialconfig.serialUtil.stringBuffer.length() - 6);
			} else if (i > 0) {
				Fragment_serialconfig.serialUtil.delete(i);
			}
			System.out.println("处理后："+Fragment_serialconfig.serialUtil.stringBuffer.toString());
			if (Fragment_serialconfig.serialUtil.stringBuffer.length() > 300) {
				System.out
						.println("读入的数据+++++++++++++++++++++++++++++++++++");
				System.out.println(Fragment_serialconfig.serialUtil.stringBuffer.toString());
				System.out
						.println("+++++++++++++++++++++++++++++++++++++++++++++");
				String telosbData = Fragment_serialconfig.serialUtil.getFirstData();
				System.out.println("receive package:" + telosbData);

				if (telosbData == "") {
					System.out.println("包为empty");

				} else {
					/*System.out
							.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
					try {
						// 数据解析
						System.out.println(xmlTelosbPackagePatternUtil
								.parseTelosbPackage(telosbData).getCtype()
								+ ":%%%%%%%%%%%%%%");
					} catch (Exception e) {
						System.out.println("异常");
						e.printStackTrace();
						continue;
					}
					// ;
*/					PackagePattern telosbPackagePattern = null;
					try {
						// 数据解析
						telosbPackagePattern = MainActivity.xmlTelosbPackagePatternUtil
								.parseTelosbPackage(telosbData);
					} catch (Exception e) {
						System.out.println("异常");
						e.printStackTrace();
						continue;

					}
					if (MainActivity.httpClientUtil.PostTelosbData("", telosbData)) {
						telosbPackage.setStatus("已上传");
					} else {
						telosbPackage.setStatus("未上传");
					}

					telosbPackage.setCtype(telosbPackagePattern.ctype);
					telosbPackage.setMessage(telosbData);
					telosbPackage.setNodeID(telosbPackagePattern.nodeID);
					
					ContentValues values = new ContentValues(); // 相当于map
					values.put("message", telosbPackage.getMessage());
					values.put("Ctype", telosbPackage.getCtype());
					values.put("NodeID", telosbPackage.nodeID);
					values.put("status", telosbPackage.getStatus());
					Date date = new Date();
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
							"yyyy-MM-dd hh:mm:ss");
					values.put("receivetime", simpleDateFormat.format(date));
					MainActivity.mDb.insert("Telosb", null, values);
					
					/*if (telosbData != null) {
						list.add(telosbData);
//						Packagesingnal();
					}*/
//					System.out.println(list.size() + "_____________");
					System.gc();//垃圾回收
				}
			}
		}
	}
}
