package senseHuge.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import senseHuge.model.PackagePattern;
import senseHuge.service.PackageParserService;
import senseHuge.service.PackagePatternService;

public class XmlTelosbPackagePatternUtil {
	public PackageParserService packageParserService = new PackageParserService();;
	public String path;
	public PackagePattern packagePattern = null;

	public XmlTelosbPackagePatternUtil(String path) {
		super();
		this.path = path;
		this.inite();
		packagePattern = this.getPackagePattern();
	}

	public PackagePattern getPackagePattern() {
		if (this.packagePattern == null) {
			return PackagePattern();
		} else {
			return this.packagePattern;
		}
	}

	public PackagePattern PackagePattern() {
		// String path;
		// path = getFilesDir().toString();
		PackagePatternService packagePatternService = new PackagePatternService();
		File f = new File(path + "package.xml");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PackagePattern packagePattern = null;
		try {
			packagePattern = packagePatternService.getPackage(inputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return packagePattern;
	}

	public void inite() {
		/*
		 * String path; path = getFilesDir().toString();
		 */
		PackagePatternService packagePatternService = new PackagePatternService();
		PackagePattern packagePattern = new PackagePattern();
		File f = new File(path + "package.xml");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			return;
		}
		OutputStream outPut = null;
		try {
			outPut = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * packagePattern.DataField.put("�ҵ�", "����ť");
		 * packagePattern.DataField.put("two", "two");
		 * packagePattern.DataField.put("three", "three");
		 * packagePattern.DataField.put("ok", "!!!");
		 * packagePattern.DataField.put("first", "first!");
		 */
		Map<String, String> C1 = new LinkedHashMap<String, String>();
		Map<String, String> C2 = new LinkedHashMap<String, String>();
		Map<String, String> C3 = new LinkedHashMap<String, String>();
		Map<String, String> C4 = new LinkedHashMap<String, String>();

		C1.put("���ݰ�����", "019019");
		C1.put("Դ�ڵ��� ", "020021");
		C1.put("sink�ڵ���", "022023");
		C1.put("����ʱ���", "024027");
		C1.put("sink�հ�ʱ���", "028031");
		C1.put("���ݰ����к�", "032033");
		C1.put("����", "034035");
		C1.put("��һ������", "036037");
		C1.put("����", "038039");
		C1.put("��������", "040040");
		C1.put("ִ������ʱ��", "041044");
		C1.put("ִ������ʱ��ms", "045048");
		C1.put("��һ���ڵ�", "049050");
		C1.put("����Ƶ��", "051052");
		C1.put("telosb�����¶�", "053054");
		C1.put("�ڵ��ѹ", "055056");
		C1.put("����������ʪ��", "057058");
		C1.put("�����������¶�", "059060");
		C1.put("����ǿ��", "061062");
		C1.put("������̼Ũ��", "065066");
		C1.put("Ծ�״���", "075075");
		C1.put("��·����", "076076");
		C1.put("ÿ���ڵ�", "077096");

		// packagePattern.list.add(packagePattern.DataField);
		// packagePattern.list.add(packagePattern.DataField);
		// packagePattern.list.add(packagePattern.DataField);
		// packagePattern.list.add(packagePattern.DataField);
		//
		// packagePattern.DataField = null;
		packagePattern.TelosbDataField.put("C1", C1);

		C2.put("���ݰ�����", "019019");
		C2.put("Դ�ڵ��", "020021");
		C2.put("sink�ڵ��", "022023");
		C2.put("����ʱ���", "024027");
		C2.put("�հ�ʱ���", "028031");
		C2.put("���к�", "032033");
		C2.put("����", "034035");
		C2.put("�ھӽڵ�����", "036036");
		C2.put("�ھӽڵ���", "037096");
		C2.put("���ڵ�", "097098");

		packagePattern.TelosbDataField.put("C2", C2);
		C3.put("���ݰ�����", "019019");
		C3.put("Դ�ڵ��", "020021");
		C3.put("sink�ڵ��", "022023");
		C3.put("����ʱ���", "024027");
		C3.put("�հ�ʱ���", "028031");
		C3.put("���к�", "032033");
		C3.put("����", "034035");
		C3.put("���ߴ򿪴���", "036039");
		C3.put("�ɹ��յ�����", "040043");
		C3.put("�ɹ����Ͱ���", "044047");
		C3.put("���ջ���������µĶ�����", "048051");
		C3.put("�ڵ��Լ����������ݰ�����", "052055");
		C3.put("����ʧ����ACK���ش�����δ������", "056059");
		C3.put("����ʧ����ACK�ش������Ѵ����޶���", "060063");
		C3.put("�ش�����", "064067");
		C3.put("·�ɳ��ֻ�·����", "068069");
		C3.put("�ظ��յ�����", "070071");
		C3.put("�ɹ��յ�ACK����", "072075");
		C3.put("���ڵ�������", "076077");
		C3.put("Taskpost������", "078081");
		C3.put("Taskִ�е�����", "082085");
		C3.put("�Ҳ������ڵ����", "086087");
		C3.put("CTP����ʧ�ܴ���", "088091");
		C3.put("CTP��ȥһ��·�ɱ������", "092095");
		C3.put("MAC��Initialbackoff�Ĵ���", "096099");
		C3.put("MAC��Congestionbackoff�Ĵ���", "100103");

		packagePattern.TelosbDataField.put("C3", C3);
		C4.put("���ݰ�����", "019019");
		C4.put("Դ�ڵ��", "020021");
		C4.put("sink�ڵ�", "022023");
		C4.put("����ʱ���", "024027");
		C4.put("�հ�ʱ���", "028031");
		C4.put("���ݰ����к�", "032033");
		C4.put("����", "034035");
		C4.put("·�����鳤�Ⱥ�", "036037");
		C4.put("·���˱ܴ����ܺ�", "038039");
		C4.put("·���ش�����", "040041");
		C4.put("����", "042042");
		C4.put("�ھӴ�С", "043043");
		C4.put("·���ڵ�", "044093");

		packagePattern.TelosbDataField.put("C4", C4);
		// packagePattern.TelosbDataField.remove(C3);
		/*
		 * packagePattern.TelosbDataField.put("C5", packagePattern.DataField);
		 * packagePattern.TelosbDataField.put("C6", packagePattern.DataField);
		 * 
		 * 
		 * 
		 * packagePattern.DataField.put("���ݰ�����", "1919");
		 * packagePattern.DataField.put("Դ�ڵ��� ", "2021");
		 * packagePattern.DataField.put("sink�ڵ���", "2223");
		 * packagePattern.DataField.put("����ʱ���", "2427");
		 * packagePattern.DataField.put("sink�հ�ʱ���", "2831");
		 * packagePattern.DataField.put("���ݰ����к�", "3233");
		 * packagePattern.DataField.put("����", "3435");
		 * packagePattern.DataField.put("��һ������", "3637");
		 * packagePattern.DataField.put("����", "3839");
		 * packagePattern.DataField.put("��������", "4040");
		 * packagePattern.DataField.put("ִ������ʱ��", "4144");
		 * packagePattern.DataField.put("ִ������ʱ��ms", "4548");
		 * packagePattern.DataField.put("��һ���ڵ�", "4950");
		 * packagePattern.DataField.put("����Ƶ��", "5152");
		 * packagePattern.DataField.put("telosb�����¶�", "5354");
		 * packagePattern.DataField.put("�ڵ��ѹ", "5556");
		 * packagePattern.DataField.put("����������ʪ��", "5758");
		 * packagePattern.DataField.put("�����������¶�", "5960");
		 * packagePattern.DataField.put("����ǿ��", "6162");
		 * packagePattern.DataField.put("������̼Ũ��", "6566");
		 * packagePattern.DataField.put("Ծ�״���", "7575");
		 * packagePattern.DataField.put("��·����", "7676");
		 * packagePattern.DataField.put("ÿ���ڵ�", "7796");
		 * 
		 * packagePattern.list.add(packagePattern.DataField);
		 * packagePattern.list.add(packagePattern.DataField);
		 * packagePattern.list.add(packagePattern.DataField);
		 * packagePattern.list.add(packagePattern.DataField);
		 * 
		 * // packagePattern.DataField = null;
		 * packagePattern.TelosbDataField.put("C1", packagePattern.DataField);
		 * packagePattern.DataField.clear();
		 * packagePattern.DataField.put("���ݰ�����", "1919");
		 * packagePattern.DataField.put("Դ�ڵ��", "2021");
		 * packagePattern.DataField.put("sink�ڵ��", "2223");
		 * packagePattern.DataField.put("����ʱ���", "2427");
		 * packagePattern.DataField.put("�հ�ʱ���", "2831");
		 * packagePattern.DataField.put("���к�", "3233");
		 * packagePattern.DataField.put("����", "3435");
		 * packagePattern.DataField.put("�ھӽڵ�����", "3636");
		 * packagePattern.DataField.put("�ھӽڵ���", "3796");
		 * packagePattern.DataField.put("���ڵ�", "9798");
		 * 
		 * 
		 * packagePattern.TelosbDataField.put("C2", packagePattern.DataField);
		 * packagePattern.DataField.clear();
		 * packagePattern.DataField.put("���ݰ�����", "1919");
		 * packagePattern.DataField.put("Դ�ڵ��", "2021");
		 * packagePattern.DataField.put("sink�ڵ��", "2223");
		 * packagePattern.DataField.put("����ʱ���", "2427");
		 * packagePattern.DataField.put("�հ�ʱ���", "2831");
		 * packagePattern.DataField.put("���к�", "3233");
		 * packagePattern.DataField.put("����", "3435");
		 * packagePattern.DataField.put("���ߴ򿪴���", "3639");
		 * packagePattern.DataField.put("�ɹ��յ�����", "4043");
		 * packagePattern.DataField.put("�ɹ����Ͱ���", "4447");
		 * packagePattern.DataField.put("���ջ���������µĶ�����", "4851");
		 * packagePattern.DataField.put("�ڵ��Լ����������ݰ�����", "5255");
		 * packagePattern.DataField.put("����ʧ�ܣ���ACK�����ش�����δ������", "5659");
		 * packagePattern.DataField.put("����ʧ�ܣ���ACK���ش������Ѵ����ޣ�����", "6063");
		 * packagePattern.DataField.put("�ش�����", "6467");
		 * packagePattern.DataField.put("·�ɳ��ֻ�·����", "6869");
		 * packagePattern.DataField.put("�ظ��յ�����", "7071");
		 * packagePattern.DataField.put("�ɹ��յ�ACK����", "7275");
		 * packagePattern.DataField.put("���ڵ�������", "7677");
		 * packagePattern.DataField.put("Task post������", "7881");
		 * packagePattern.DataField.put("Taskִ�е�����", "8285");
		 * packagePattern.DataField.put("�Ҳ������ڵ����", "8687");
		 * packagePattern.DataField.put("CTP ����ʧ�ܴ���", "8891");
		 * packagePattern.DataField.put("CTP��ȥһ��·�ɱ������", "9295");
		 * packagePattern.DataField.put("MAC��Initial backoff�Ĵ���", "9699");
		 * packagePattern.DataField.put("MAC��Congestion backoff�Ĵ���", "100103");
		 * 
		 * 
		 * packagePattern.TelosbDataField.put("C3", packagePattern.DataField);
		 * // packagePattern.DataField.clear();
		 * packagePattern.DataField.put("���ݰ�����", "1919");
		 * packagePattern.DataField.put("Դ�ڵ��", "2021");
		 * packagePattern.DataField.put("sink�ڵ�", "2223");
		 * packagePattern.DataField.put("����ʱ���", "2427");
		 * packagePattern.DataField.put("�հ�ʱ���", "2831");
		 * packagePattern.DataField.put("���ݰ����к�", "3233");
		 * packagePattern.DataField.put("����", "3435");
		 * packagePattern.DataField.put("·�����鳤�Ⱥ�", "3637");
		 * packagePattern.DataField.put("·���˱ܴ����ܺ�", "3839");
		 * packagePattern.DataField.put("·���ش�����", "4041");
		 * packagePattern.DataField.put("����", "4242");
		 * packagePattern.DataField.put("�ھӴ�С", "4343");
		 * packagePattern.DataField.put("·���ڵ�", "4493");
		 * 
		 * packagePattern.TelosbDataField.put("C4", packagePattern.DataField);
		 * packagePattern.TelosbDataField.put("C5", packagePattern.DataField);
		 * packagePattern.TelosbDataField.put("C6", packagePattern.DataField);
		 */

		try {
			packagePatternService.save2(packagePattern, outPut);
			/*
			 * packagePatternService.save(packagePattern, outPut);
			 */
			outPut.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				outPut.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public Map<String, String> Parse(PackagePattern packagePattern,
			String Ctype, String telsobData) {
		Map<String, String> map = new LinkedHashMap<String, String>();

		packagePattern.TelosbDataField.get(Ctype);

		if (packagePattern.getDataField() != null) {
			// System.out.println("packagePattern .getDataField() is null!!!");
			System.out.println("packagePattern.getTelosbDataField() size= "
					+ packagePattern.getTelosbDataField().size());
			System.out
					.println("packagePattern.getTelosbDataField() is containsKey :"
							+ packagePattern.getTelosbDataField().containsKey(
									"C1"));
		} else if (Ctype != null) {
			System.out.println("Ctype =" + Ctype);
			// System.out.println("packagePattern .getDataField().Ctype is null!!!");
		} else if (packagePattern.TelosbDataField.get(Ctype) == null) {
			System.out
					.println("packagePattern.TelosbDataField.get(Ctype) == null!!!");
		}
		System.out.println("Ctype =" + Ctype);
		System.out.println(telsobData);
		System.out.println(packagePattern.TelosbDataField.get(Ctype).size()
				+ "size:");
		for (String string : packagePattern.TelosbDataField.get(Ctype).keySet()) {
			System.out.println(string + "TTTTTTTTTTTTTTTTT");
		}

		Iterator<?> it = packagePattern.TelosbDataField.get(Ctype).entrySet()
				.iterator();

		int num = 0;
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			System.out.println(pairs.getKey() + " = " + pairs.getValue());
			// String data = packageParserService.parser(telsobData,
			// pairs.getValue().toString());
			String value = packageParserService.parseDatafield(telsobData,
					pairs.getValue().toString());

			/*
			 * int begin = Integer.valueOf(value.substring(0, 3),10); int end =
			 * Integer.valueOf(value.substring(3, 6),10);
			 */
			map.put(pairs.getKey().toString(), value);
			if (value.length() > 10) {
				num = 0; // ���ݳ��ȹ��� �޷�ת���� ��C2�����ھӽڵ���
			} else {

				// num = Integer.valueOf(value, 16);
			}
			// System.out.println("data  =  "+value + ";result ="+num);
		}

		return map;
	}

	public Map<String, String> Parse(String Ctype, String telsobData) {
		Map<String, String> map = new LinkedHashMap<String, String>();

		packagePattern.TelosbDataField.get(Ctype);

		if (packagePattern == null) {
			System.out.println("packagePattern is null!!!");
		} else if (packagePattern.getDataField() != null) {
			// System.out.println("packagePattern .getDataField() is null!!!");
			System.out.println("packagePattern.getTelosbDataField() size= "
					+ packagePattern.getTelosbDataField().size());
			System.out
					.println("packagePattern.getTelosbDataField() is containsKey :"
							+ packagePattern.getTelosbDataField().containsKey(
									"C1"));
		} else if (Ctype != null) {
			System.out.println("Ctype =" + Ctype);
			// System.out.println("packagePattern .getDataField().Ctype is null!!!");
		} else if (packagePattern.TelosbDataField.get(Ctype) == null) {
			System.out
					.println("packagePattern.TelosbDataField.get(Ctype) == null!!!");
		}
		System.out.println("Ctype =" + Ctype);
		System.out.println(telsobData);
		System.out.println(packagePattern.TelosbDataField.get(Ctype).size()
				+ "size:");
		for (String string : packagePattern.TelosbDataField.get(Ctype).keySet()) {
			System.out.println(string + "TTTTTTTTTTTTTTTTT");
		}

		Iterator it = packagePattern.TelosbDataField.get(Ctype).entrySet()
				.iterator();

		int num = 0;
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			System.out.println(pairs.getKey() + " = " + pairs.getValue());
			// String data = packageParserService.parser(telsobData,
			// pairs.getValue().toString());
			String value = packageParserService.parseDatafield(telsobData,
					pairs.getValue().toString());

			/*
			 * int begin = Integer.valueOf(value.substring(0, 3),10); int end =
			 * Integer.valueOf(value.substring(3, 6),10);
			 */
			map.put(pairs.getKey().toString(), value);
			if (value.length() > 10) {
				num = 0; // ���ݳ��ȹ��� �޷�ת���� ��C2�����ھӽڵ���
			} else {

				// num = Integer.valueOf(value, 16);
			}
			// System.out.println("data  =  "+value + ";result ="+num);
		}

		return map;
	}

	public PackagePattern parseTelosbPackage(String telsobData,
			PackagePattern packagePattern) {
		PackagePattern telosbPackage = new PackagePattern();

		telosbPackage.setAMtype(packageParserService.parseDatafield(telsobData,
				"008008"));
		telosbPackage.setCtype(packageParserService.parseDatafield(telsobData,
				"019019"));
		telosbPackage.setMessageLength(Integer.valueOf(
				packageParserService.parseDatafield(telsobData, "006006"), 16));
		telosbPackage.setNetworkNum(packageParserService.parseDatafield(
				telsobData, "007007"));
		telosbPackage.setSourceAddr(packageParserService.parseDatafield(
				telsobData, "004005"));
		telosbPackage.setHead(packageParserService.parseDatafield(telsobData,
				"001003"));
		Map<String, String> map = this.Parse(packagePattern,
				packageParserService.parseDatafield(telsobData, "019019"),
				telsobData);
		telosbPackage.setDataField(this.Parse(packagePattern,
				packageParserService.parseDatafield(telsobData, "019019"),
				telsobData));

		return telosbPackage;
	}

	public PackagePattern parseTelosbPackage(String telsobData)
			throws Exception {
		PackagePattern telosbPackage = new PackagePattern();

		telosbPackage.setAMtype(packageParserService.parseDatafield(telsobData,
				"008008"));
		telosbPackage.setCtype(packageParserService.parseDatafield(telsobData,
				"019019"));
		telosbPackage.setMessageLength(Integer.valueOf(
				packageParserService.parseDatafield(telsobData, "006006"), 16));
		telosbPackage.setNetworkNum(packageParserService.parseDatafield(
				telsobData, "007007"));
		telosbPackage.setSourceAddr(packageParserService.parseDatafield(
				telsobData, "004005"));
		telosbPackage.setHead(packageParserService.parseDatafield(telsobData,
				"001003"));
		telosbPackage.setNodeID(packageParserService.parseDatafield(telsobData,
				"020021"));
		// Map<String, String> map = this.Parse(this.getPackagePattern(),
		// packageParserService.parseDatafield(telsobData, "019019"),
		// telsobData);

		// telosbPackage.setDataField(map);
		// packagePattern = this.getPackagePattern();
		if (packagePattern == null) {

			System.out.println("packagePattern == null");
		}
		System.out.println(packagePattern.getTelosbDataField().size()
				+ "==packagePattern.getTelosbDataField().size()");
		/*
		 * if (packagePattern.getTelosbDataField().size() == 0) {
		 * this.packagePattern = this.getPackagePattern(); }
		 */

		String type = "C1C2C3C4";
		String ctype = packageParserService
				.parseDatafield(telsobData, "019019");
		if (!type.contains(ctype)) {
			System.out.println(telsobData+"^^^^^^^^^^^^^^^^^^^error:");
			throw new RuntimeException("demo");
           
		}
		// telosbPackage.setDataField(this.Parse(packagePattern,
		// packageParserService.parseDatafield(telsobData, "019019"),
		// telsobData));

		telosbPackage.setDataField(this.Parse(
				packageParserService.parseDatafield(telsobData, "019019"),
				telsobData));

		testTelosbDatafield(telosbPackage);

		return telosbPackage;
	}

	public void testTelosbDatafield(PackagePattern telosbPackage) {
		for (String string : telosbPackage.DataField.keySet()) {
			System.out.println(string + ";;;;");

		}

		Iterator<?> it = telosbPackage.DataField.entrySet().iterator();

		System.out.println("#########111111111111111111111111##############");
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			System.out.println(pairs.getKey() + " =============== "
					+ pairs.getValue());

		}
	}
}
