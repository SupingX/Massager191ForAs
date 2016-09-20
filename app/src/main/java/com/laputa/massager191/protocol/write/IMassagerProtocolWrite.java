package com.laputa.massager191.protocol.write;


import com.laputa.massager191.protocol.bean.MycjMassagerInfo;

/**
 * 按摩器统一接口协议 Write
 * 
 * @author zeej
 *
 */
public interface IMassagerProtocolWrite  {
	/**
	 * 获取当前按摩信息 DO
	 * @param which 0 1 2 0xFF 哪一个按摩器 0xFF代表所有
	 * @return
	 */
	public byte[] writeForCurrentMassager(int which);
	
	/**
	 * 获取当前负载 D1
	 * @param which 0 1 2 0xFF 哪一个按摩器 0xFF代表所有
	 * @return
	 */
	public byte[] writeForLoader(int which);
	
	/**
	 * 获取当前模式 D2
	 * @param which 0 1 2 0xFF 哪一个按摩器 0xFF代表所有
	 * @return
	 */
	public byte[] writeForPattern(int which);
	
	/**
	 * 获取当前力度 D4
	 * @param which 0 1 2 0xFF 哪一个按摩器 0xFF代表所有
	 * @return
	 */
	public byte[] writeForPower(int which);
	
	/**
	 * 获取当前时间 D6
	 * @return
	 */
	public byte[] writeForTime();
	
	/**
	 *
	 * 获取当前温度 D8
	 * 
	 * @return
	 */
	public byte[] writeForTemperature();
	
	/**
	 * 获取当前心率 D9
	 * 
	 * @return
	 */
	public byte[] writeForHeartRate();
	
	/**
	 * 开始按摩 DF
	 * @param which 0 1 2 0xFF 哪一个按摩器 0xFF代表所有
	 * @param info
	 * @return
	 */
	public byte[] writeForStartMassager(MycjMassagerInfo info,int which);
	
	/**
	 * 结束按摩
	 * @param which 0 1 2 0xFF 哪一个按摩器 0xFF代表所有
	 * @return
	 */
	public byte[] writeForStopMassager(int which);
	
	/**
	 * 切换模式D3
	 * @param which 0 1 2 0xFF 哪一个按摩器 0xFF代表所有
	 * @param pattern
	 * @return
	 */
	public byte[] writeForChangePattern(int pattern,int which);
	
	/**
	 * 切换力度D5
	 * @param which 0 1 2 0xFF 哪一个按摩器 0xFF代表所有
	 * @return
	 */
	public byte[] writeForChangePower(int power,int which);
	
	/**
	 * 切换时间D6
	 * @param which 0 1 2 0xFF 哪一个按摩器 0xFF代表所有
	 * @param time 单位：秒
	 * @return
	 */
	public byte[] writeForChangeTime(int time,int which);
	
	/**
	 * 切换温度 D9
	 * @param temp
	 * @param unit 单位-0:摄氏度；1:华氏度
	 * @return
	 */
	public byte[] writeForChangeTemperature(int temp, int unit);
	
	
}
