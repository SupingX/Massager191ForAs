package com.laputa.massager191.protocol.notify;


import com.laputa.massager191.protocol.bean.MycjMassagerInfo;

public interface IMassagerProtocolNotifyListener {
	void onParseChangePatternCallBack(String desc, int success, int pattern,int massager);
	void onParseMassagerInfo(String desc, MycjMassagerInfo info,int massager);
	void onParseLoader(String desc, int loader,int massager);
	void onParsePattern(String desc, int pattern,int massager);
	void onParsePower(String desc, int power,int massager);
	void onParseChangePowerCallBack(String desc, int success, int power,int massager);
	void onParseTime(String desc, int leftTime, int settingTime,int massager);
	void onParseChangeTimeCallBack(String desc, int success, int settingTime,int massager);
	void onParseTemperature(String desc, int temp, int tempUnit);
	void onParseChangeTemperatureCallBack(String desc, int success, int temp,int tempUnit);
	void onParseChangeHeartRate(String desc, int hr,int massager);
	void onConfig(String desc, int count);
}
