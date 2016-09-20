package com.laputa.massager191.protocol.write;

/**
 * MYCJ公共部分硬件统一协议 Write
 * 
 * @author zeej
 *
 */
public interface  IBaseProtocolWrite {
	
	/**
	 * 请求当前固件信息	FA
	 * 
	 * @return 
	 */
	public byte[] writeForFirmwareVersion();
			 
	/**
	 * 开机关机 FB
	 * 
	 * @param open 1：开机；0：关机
	 * @return
	 */
	public byte[] writeForOpenOrClose(int open);
	
	/**
	 * 请求电量FC
	 * 
	 * @return
	 */
	public byte[] writeForElectricity();
	
	/**
	 * 恢复出厂设置FD
	 * 
	 * @return
	 */
	public byte[] writeForFactoryResetStatus();
	
	/**
	 * 测试FE
	 * 
	 * @return
	 */
	public byte[] writeForTest();
	
}
