package com.laputa.massager191.protocol.bean;

/**
 * 解析结果分类
 * @author zeej
 *
 */
public enum Result {
	NON(0), FirmwareVersion(0xFA) // 硬件版本
	, Electricity(0xFC)// 电量
	, OpenorCloseCallBack(0xFB)// 开机关机成功/失败
	, FactoryResetStatus(0xFD)// 恢复出厂设置成功/失败
	, Test(0xFE)

	, Loader(0xD1)// 负载
	, Pattern(0xD2)// 模式
	, Power(0xD4)// 力度
	, Time(0xD6)// 时间：秒
	, Temperature(0xD8)// 温度
	, HeartRate(0xD9)// 心率
	, MassagerInfo(0xD0)// 按摩信息
	, ChangePatternCallBack(0xD3)// 切换模式成功/失败
	, ChangePowerCallBack(0xD5)// 切换力度成功/失败
	, ChangeTimeCallBack(0xD7)// 切换时间成功/失败
	, ChangeTemperatureCallBack(0xDF)// 切换温度成功/失败
	, Config(0xDA)// 配置

	
	
	, START(0xDD)
	, STOP(0xDE)

	;
	private int protocl;

	Result(int protocl) {
		this.protocl = protocl;
	}

	public int getProtocl() {
		return this.protocl;
	}
}
