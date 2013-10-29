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
			case 1:// "数据包类型":
				   map.put(keyString, map.get(keyString));
				break;
			case 2://"源节点编号":
				 map.put(keyString, map.get(keyString));
				break;
			case 3://"sink节点编号":
				 map.put(keyString, map.get(keyString));
				break;
			case 4://"发包时间戳":
				 map.put(keyString, map.get(keyString));
				break;
			case 5://"sink收包时间戳":
				 map.put(keyString, map.get(keyString));
				break;
			case 6://"数据包序列号":
				 map.put(keyString, map.get(keyString));
				break;
			case 7://"周期":
				
				 map.put(keyString, map.get(keyString));
				break;
			case 8://"下一跳点编号":
				 map.put(keyString, map.get(keyString));
				break;
			case 9://"跳数":
				 map.put(keyString, map.get(keyString));
				break;
			case 10://"发包功率":
				 map.put(keyString, map.get(keyString));
				break;
			case 11://"执行任务时间":
				 map.put(keyString, map.get(keyString));
				break;
			case 12://"执行任务时间ms":
				 map.put(keyString, map.get(keyString));
				break;
			case 13://"下一跳节点":
				 map.put(keyString, map.get(keyString));
				break;
			case 14://"采样频率":
				 map.put(keyString, map.get(keyString));
				break;
			case 15://"telosb采样温度":
				float b = Integer.valueOf(map.get(keyString), 16);
				float a =(float) (( b/4096)*3.3);
			//	 int a = (int) ((Integer.valueOf(map.get(keyString), 16)/4096)*3.3);
				 map.put(keyString,a+"V  :"+map.get(keyString));
				break;
			case 16://"节点电压":
				 map.put(keyString, map.get(keyString));
				break;
			case 17://"传感器采样湿度":
				 map.put(keyString, map.get(keyString));
				break;
			case 18://"传感器采样温度":
				 map.put(keyString, map.get(keyString));
				break;
			case 19://"光照强度":
				 map.put(keyString, map.get(keyString));
				break;
			case 20://"二氧化碳浓度":
				 map.put(keyString, map.get(keyString));
				break;
			case 21://"跃阶次数":
				 map.put(keyString, map.get(keyString));
				break;
			case 22://"链路长度":
				 map.put(keyString, map.get(keyString));
				break;
			case 23://"每跳节点":
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
    		case 1:// "数据包类型":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 2://"源节点编号":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 3://"sink节点编号":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 4://"发包时间戳":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 5://"sink收包时间戳":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 6://"数据包序列号":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 7://"周期":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 8://"下一跳点编号":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 9://"跳数":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 10://"发包功率":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 11://"执行任务时间":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 12://"执行任务时间ms":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 13://"下一跳节点":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 14://"采样频率":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 15://"telosb采样温度":
    			map.put(keyString, map.get(keyString)+"度");
    			break;
    		case 16://"节点电压":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 17://"传感器采样湿度":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 18://"传感器采样温度":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 19://"光照强度":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 20://"二氧化碳浓度":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 21://"跃阶次数":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 22://"链路长度":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 23://"每跳节点":
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
    		case 1:// "数据包类型":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 2://"源节点编号":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 3://"sink节点编号":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 4://"发包时间戳":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 5://"sink收包时间戳":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 6://"数据包序列号":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 7://"周期":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 8://"下一跳点编号":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 9://"跳数":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 10://"发包功率":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 11://"执行任务时间":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 12://"执行任务时间ms":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 13://"下一跳节点":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 14://"采样频率":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 15://"telosb采样温度":
    			map.put(keyString, map.get(keyString)+"度");
    			break;
    		case 16://"节点电压":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 17://"传感器采样湿度":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 18://"传感器采样温度":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 19://"光照强度":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 20://"二氧化碳浓度":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 21://"跃阶次数":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 22://"链路长度":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 23://"每跳节点":
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
    		case 1:// "数据包类型":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 2://"源节点编号":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 3://"sink节点编号":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 4://"发包时间戳":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 5://"sink收包时间戳":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 6://"数据包序列号":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 7://"周期":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 8://"下一跳点编号":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 9://"跳数":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 10://"发包功率":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 11://"执行任务时间":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 12://"执行任务时间ms":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 13://"下一跳节点":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 14://"采样频率":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 15://"telosb采样温度":
    			map.put(keyString, map.get(keyString)+"度");
    			break;
    		case 16://"节点电压":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 17://"传感器采样湿度":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 18://"传感器采样温度":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 19://"光照强度":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 20://"二氧化碳浓度":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 21://"跃阶次数":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 22://"链路长度":
    			map.put(keyString, map.get(keyString));
    			break;
    		case 23://"每跳节点":
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
