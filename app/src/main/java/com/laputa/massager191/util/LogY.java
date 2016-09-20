package com.laputa.massager191.util;

import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class LogY {
	private static Map<String,Boolean> islogMap = new HashMap<>();
	static {

	}
	public static void add(Class clz , boolean isDebug){
		islogMap.put(clz.getSimpleName(),isDebug);
	}

	public static void e(Class clz,String msg){
		Set<Map.Entry<String, Boolean>> entries = islogMap.entrySet();
		Iterator<Map.Entry<String, Boolean>> iterator = entries.iterator();
		while(iterator.hasNext()){
			Map.Entry<String, Boolean> next = iterator.next();
			if (next.getKey().equals(clz.getSimpleName())) {
				if (next.getValue()){
					Log.e(next.getKey(),msg);
				}
				return;
			}
		}
			
		

	}

	public static  void e(boolean isDebug,String tag ,String msg){
		if (isDebug) {
			Log.e(tag, msg);
		}
	}
	
	public static void i(boolean isDebug,String tag ,String msg){
		if (isDebug) {
			Log.i(tag, msg);
		}
	}
	
}
