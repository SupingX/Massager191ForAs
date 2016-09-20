package com.laputa.massager191.protocol.util;


public class LogUtil {
	public static boolean isDebug = false;

	public static void log(String msg){
		if (isDebug ) {
			System.out.println(msg);
		}
		
	}
	
	
}
