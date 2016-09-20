package com.laputa.massager191.protocol.bean;


/**
 * 固件版本信息类
 * @author zeej
 */
public class MycjFirmwareVersion {
	/**
	 * 版本号[0,255]
	 */
	private int code;
	/**
	 * 格式  	FA 		：头 
	 * 		20 		：固件版本号code[0,255]
	 * 		MYCJ  	：公司
	 * 		R		:R:正式；B：测试 
	 * 		16 		：年[00,99]
	 * 		10 		：月[01,12]
	 * 		12 		：日[01,31]
	 * 		01 		：num[0,255]
	 * 		01 		：index[0,255]
	 */
	private String name;//格式 FA 20 MYCJR 16 10 12 01 01 
	public MycjFirmwareVersion(int code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	public MycjFirmwareVersion() {
		super();
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return code + name ;
	}
	
	
}
