package com.laputa.massager191.ble.blue.core;

import java.util.HashSet;

public abstract class DefaultOnBlueChangedListener implements OnBlueChangedListener{

	@Override
	public void onServiceDiscovered(String address) {
		
	}

	@Override
	public void onCharacteristicChanged(String address, byte[] value) {
		
	}

	@Override
	public boolean isAllConnected() {
		return false;
	}

	@Override
	public void reconnect(HashSet<String> devices) {
		
	}

	@Override
	public void onStateChanged(String address, int state) {
		
	}
	
}
