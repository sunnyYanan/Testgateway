package senseHuge.gateway.util;

import java.util.Iterator;
import java.util.Map;

import android.R.integer;

public class DataSwitchUtil {
    public Map<String, String>  excute_C1(Map<String, String> map){
    	int i = 0;
    	Iterator<?> it = map.entrySet().iterator();
    	while(it.hasNext()){
    		Map.Entry pairs = (Map.Entry) it.next();
    		String keyString = pairs.getKey().toString();
    		switch (++i) {
			case 1:// "���ݰ�����":
				   map.put(keyString, map.get(keyString));
				break;
			case 2://"Դ�ڵ���":
				 map.put(keyString, map.get(keyString));
				break;
			case 3://"sink�ڵ���":
				 map.put(keyString, map.get(keyString));
				break;
			case 4://"����ʱ���":
				 map.put(keyString, map.get(keyString));
				break;
			case 5://"sink�հ�ʱ���":
				 map.put(keyString, map.get(keyString));
				break;
			case 6://"���ݰ����к�":
				 map.put(keyString, map.get(keyString));
				break;
			case 7://"����":
				
				 map.put(keyString, map.get(keyString));
				break;
			case 8://"��һ������":
				 map.put(keyString, map.get(keyString));
				break;
			case 9://"����":
				 map.put(keyString, map.get(keyString));
				break;
			case 10://"��������":
				 map.put(keyString, map.get(keyString));
				break;
			case 11://"ִ������ʱ��":
				 map.put(keyString, map.get(keyString));
				break;
			case 12://"ִ������ʱ��ms":
				 map.put(keyString, map.get(keyString));
				break;
			case 13://"��һ���ڵ�":
				 map.put(keyString, map.get(keyString));
				break;
			case 14://"����Ƶ��":
				 map.put(keyString, map.get(keyString));
				break;
			case 15://"telosb�����¶�":
				float b = Integer.valueOf(map.get(keyString), 16);
				float a =(float) (( b/4096)*3.3);
			//	 int a = (int) ((Integer.valueOf(map.get(keyString), 16)/4096)*3.3);
				 map.put(keyString,a+"V  :"+map.get(keyString));
				break;
			case 16://"�ڵ��ѹ":
				 map.put(keyString, map.get(keyString));
				break;
			case 17://"����������ʪ��":
				 map.put(keyString, map.get(keyString));
				break;
			case 18://"�����������¶�":
				 map.put(keyString, map.get(keyString));
				break;
			case 19://"����ǿ��":
				 map.put(keyString, map.get(keyString));
				break;
			case 20://"������̼Ũ��":
				 map.put(keyString, map.get(keyString));
				break;
			case 21://"Ծ�״���":
				 map.put(keyString, map.get(keyString));
				break;
			case 22://"��·����":
				 map.put(keyString, map.get(keyString));
				break;
			case 23://"ÿ���ڵ�":
				 map.put(keyString, map.get(keyString));
				break;

			default:
				 map.put(keyString, map.get(keyString));
				break;
			}
    	}
    	
    	
    	return map;
    }
    public Map<String, String>  excute_C2(Map<String, String> map){
    	int i = 0;
    	Iterator<?> it = map.entrySet().iterator();
    	while(it.hasNext()){
    		Map.Entry pairs = (Map.Entry) it.next();
    		String keyString = pairs.getKey().toString();
    		switch (++i) {
    		case 1:// "���ݰ�����":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 2://"Դ�ڵ���":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 3://"sink�ڵ���":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 4://"����ʱ���":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 5://"sink�հ�ʱ���":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 6://"���ݰ����к�":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 7://"����":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 8://"��һ������":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 9://"����":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 10://"��������":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 11://"ִ������ʱ��":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 12://"ִ������ʱ��ms":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 13://"��һ���ڵ�":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 14://"����Ƶ��":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 15://"telosb�����¶�":
    			map.put(keyString, map.get(keyString)+"��");
    			break;
    		case 16://"�ڵ��ѹ":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 17://"����������ʪ��":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 18://"�����������¶�":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 19://"����ǿ��":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 20://"������̼Ũ��":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 21://"Ծ�״���":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 22://"��·����":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 23://"ÿ���ڵ�":
    			map.put(keyString, map.get(keyString));
    			break;
    			
    		default:
    			map.put(keyString, map.get(keyString));
    			break;
    		}
    	}
    	
    	
    	return map;
    }
    public Map<String, String>  excute_C3(Map<String, String> map){
    	int i = 0;
    	Iterator<?> it = map.entrySet().iterator();
    	while(it.hasNext()){
    		Map.Entry pairs = (Map.Entry) it.next();
    		String keyString = pairs.getKey().toString();
    		switch (++i) {
    		case 1:// "���ݰ�����":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 2://"Դ�ڵ���":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 3://"sink�ڵ���":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 4://"����ʱ���":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 5://"sink�հ�ʱ���":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 6://"���ݰ����к�":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 7://"����":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 8://"��һ������":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 9://"����":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 10://"��������":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 11://"ִ������ʱ��":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 12://"ִ������ʱ��ms":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 13://"��һ���ڵ�":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 14://"����Ƶ��":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 15://"telosb�����¶�":
    			map.put(keyString, map.get(keyString)+"��");
    			break;
    		case 16://"�ڵ��ѹ":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 17://"����������ʪ��":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 18://"�����������¶�":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 19://"����ǿ��":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 20://"������̼Ũ��":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 21://"Ծ�״���":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 22://"��·����":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 23://"ÿ���ڵ�":
    			map.put(keyString, map.get(keyString));
    			break;
    			
    		default:
    			map.put(keyString, map.get(keyString));
    			break;
    		}
    	}
    	
    	
    	return map;
    }
    public Map<String, String>  excute_C4(Map<String, String> map){
    	int i = 0;
    	Iterator<?> it = map.entrySet().iterator();
    	while(it.hasNext()){
    		Map.Entry pairs = (Map.Entry) it.next();
    		String keyString = pairs.getKey().toString();
    		switch (++i) {
    		case 1:// "���ݰ�����":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 2://"Դ�ڵ���":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 3://"sink�ڵ���":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 4://"����ʱ���":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 5://"sink�հ�ʱ���":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 6://"���ݰ����к�":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 7://"����":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 8://"��һ������":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 9://"����":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 10://"��������":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 11://"ִ������ʱ��":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 12://"ִ������ʱ��ms":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 13://"��һ���ڵ�":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 14://"����Ƶ��":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 15://"telosb�����¶�":
    			map.put(keyString, map.get(keyString)+"��");
    			break;
    		case 16://"�ڵ��ѹ":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 17://"����������ʪ��":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 18://"�����������¶�":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 19://"����ǿ��":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 20://"������̼Ũ��":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 21://"Ծ�״���":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 22://"��·����":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 23://"ÿ���ڵ�":
    			map.put(keyString, map.get(keyString));
    			break;
    			
    		default:
    			map.put(keyString, map.get(keyString));
    			break;
    		}
    	}
    	
    	
    	return map;
    }
    
    
    
}
