package com.laputa.massager191.ble.blue.core;

import java.util.HashSet;

public interface OnBlueChangedListener {
	void onServiceDiscovered(String address);
	void onCharacteristicChanged(String address, byte[] value);
	boolean isAllConnected();
	void reconnect(HashSet<String> devices);
	void onStateChanged(String address, int state);
}
