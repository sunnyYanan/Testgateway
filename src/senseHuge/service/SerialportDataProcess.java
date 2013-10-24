package senseHuge.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import senseHuge.model.PackagePattern;
import senseHuge.model.TelosbPackage;
import android.content.ContentValues;

import com.example.testgateway.Fragment_serialconfig;
import com.example.testgateway.MainActivity;

public class SerialportDataProcess extends Thread {
//	List<String> list = new ArrayList<String>();//ȫ����
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		// ��ȡ�������ݰ�
		String headTest = "00FFFF";
		TelosbPackage telosbPackage = new TelosbPackage();
		int i;
		while (MainActivity.serialPortConnect) {
			i = Fragment_serialconfig.serialUtil.findhead(headTest);
			System.out.println("��ͷλ�ã�"+i);
			System.out.println("δ����ǰ��"+Fragment_serialconfig.serialUtil.stringBuffer.toString());
			if (i < 0 && Fragment_serialconfig.serialUtil.stringBuffer.length() > 6) {
				Fragment_serialconfig.serialUtil.delete(Fragment_serialconfig.serialUtil.stringBuffer.length() - 6);
			} else if (i > 0) {
				Fragment_serialconfig.serialUtil.delete(i);
			}
			System.out.println("�����"+Fragment_serialconfig.serialUtil.stringBuffer.toString());
			if (Fragment_serialconfig.serialUtil.stringBuffer.length() > 300) {
				System.out
						.println("���������+++++++++++++++++++++++++++++++++++");
				System.out.println(Fragment_serialconfig.serialUtil.stringBuffer.toString());
				System.out
						.println("+++++++++++++++++++++++++++++++++++++++++++++");
				String telosbData = Fragment_serialconfig.serialUtil.getFirstData();
				System.out.println("receive package:" + telosbData);

				if (telosbData == "") {
					System.out.println("��Ϊempty");

				} else {
					/*System.out
							.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
					try {
						// ���ݽ���
						System.out.println(xmlTelosbPackagePatternUtil
								.parseTelosbPackage(telosbData).getCtype()
								+ ":%%%%%%%%%%%%%%");
					} catch (Exception e) {
						System.out.println("�쳣");
						e.printStackTrace();
						continue;
					}
					// ;
*/					PackagePattern telosbPackagePattern = null;
					try {
						// ���ݽ���
						telosbPackagePattern = MainActivity.xmlTelosbPackagePatternUtil
								.parseTelosbPackage(telosbData);
					} catch (Exception e) {
						System.out.println("�쳣");
						e.printStackTrace();
						continue;

					}
					if (MainActivity.httpClientUtil.PostTelosbData("", telosbData)) {
						telosbPackage.setStatus("���ϴ�");
					} else {
						telosbPackage.setStatus("δ�ϴ�");
					}

					telosbPackage.setCtype(telosbPackagePattern.ctype);
					telosbPackage.setMessage(telosbData);
					telosbPackage.setNodeID(telosbPackagePattern.nodeID);
					
					ContentValues values = new ContentValues(); // �൱��map
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
					System.gc();//��������
				}
			}
		}
	}
}
