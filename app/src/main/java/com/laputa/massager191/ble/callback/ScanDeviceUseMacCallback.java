package com.laputa.massager191.ble.callback;

import android.bluetooth.BluetoothDevice;

import com.laputa.massager191.ble.basic.AbstractLeafBle;
import com.laputa.massager191.ble.bean.ScanResult;

import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Administrator on 2016/9/9.
 */
public abstract class ScanDeviceUseMacCallback extends ScanDeviceCallback {

    private String mac;
    private AtomicBoolean hasFount = new AtomicBoolean(false);

    public ScanDeviceUseMacCallback(AbstractLeafBle ble, long timeOut) {
        super(ble, timeOut);
    }

    public ScanDeviceUseMacCallback(AbstractLeafBle ble, long timeOut, String mac) {
        super(ble, timeOut);
        this.mac = mac;

    }

    abstract boolean onDeviceFouned(BluetoothDevice device, int rssi, byte[] scanRecord);

    /**
     * 判断是否找到,可重写.
     * 比如: 地址一样.
     *  或者 scanRecord有特殊的规定
     *  或者 rssi不能太弱
     *  这里默认是地址是否一样
     *
      * @param device
     * @param rssi
     * @param scanRecord
     * @return
     */
   protected boolean isFound(BluetoothDevice device, int rssi, byte[] scanRecord){
       return mac.equalsIgnoreCase(device.getAddress());
   }

    @Override
    public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
        super.onLeScan(device, rssi, scanRecord);

        if (this.hasFount.get() && isFound(device,rssi,scanRecord)) {
            hasFount.set(true);
            onDeviceFouned(device, rssi, scanRecord);
        }
    }
}
