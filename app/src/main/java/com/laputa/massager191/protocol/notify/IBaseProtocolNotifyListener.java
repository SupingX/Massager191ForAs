package com.laputa.massager191.protocol.notify;


import com.laputa.massager191.protocol.bean.MycjFirmwareVersion;

public interface IBaseProtocolNotifyListener {
	public void onError(String desc);
	public void onParseMycjFirmwareVersion(String desc, MycjFirmwareVersion mfv);
	public void onParseOpenorCloseCallBack(String desc, int success);
	public void onParseElectricity(String desc, int allElect, int leftElect);
	public void onParseTest(String desc);
	public void onParseFactoryResetStatus(String desc, int success);
}
