package com.laputa.massager191.protocol.notify;


import com.laputa.massager191.protocol.bean.MycjFirmwareVersion;
import com.laputa.massager191.protocol.bean.MycjMassagerInfo;

public abstract class OnProtocolNotifyListenerBasedapter implements OnProtocolNotifyListener {

	@Override
	public void onParseChangePatternCallBack(String desc, int success,
			int pattern,int massager) {
		
	}

	@Override
	public void onError(String desc) {
		
	}

	@Override
	public void onParseMycjFirmwareVersion(String desc, MycjFirmwareVersion mfv) {

	}

	@Override
	public void onParseOpenorCloseCallBack(String desc, int success) {
		
	}

	@Override
	public void onParseElectricity(String desc, int allElect, int leftElect) {
		
	}

	@Override
	public void onParseTest(String desc) {

	}

	@Override
	public void onParseFactoryResetStatus(String desc, int success) {

	}

	@Override
	public void onParseMassagerInfo(String desc, MycjMassagerInfo info,int massager) {

	}

	@Override
	public void onParseLoader(String desc, int loader,int massager) {

	}

	@Override
	public void onParsePattern(String desc, int pattern,int massager) {

	}

	@Override
	public void onParsePower(String desc, int power,int massager) {

	}

	@Override
	public void onParseChangePowerCallBack(String desc, int success, int power,int massager) {

	}

	@Override
	public void onParseTime(String desc, int leftTime, int settingTime,int massager) {

	}

	@Override
	public void onParseChangeTimeCallBack(String desc, int success,
			int settingTime,int massager) {

	}

	@Override
	public void onParseTemperature(String desc, int temp, int tempUnit) {

	}

	@Override
	public void onParseChangeTemperatureCallBack(String desc, int success,int temp,
			int tempUnit) {

	}

	@Override
	public void onParseChangeHeartRate(String desc, int hr,int massager) {

	}

	@Override
	public void onConfig(String desc, int count) {

	}
}
