package com.laputa.massager191.ble.basic;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;

import com.laputa.massager191.ble.callback.ICloseCallback;
import com.laputa.massager191.ble.callback.IConnectCallback;
import com.laputa.massager191.ble.callback.ScanDeviceCallback;
import com.laputa.massager191.ble.callback.IWriteCharacteristicCallback;

/**
 * Created by Administrator on 2016/9/8.
 */
public interface ILeafBle {

    public final  static int STATE_DISCONNECTED = 0;
    public final  static int STATE_CONNECTEDING = 1;
    public final  static int STATE_CONNECTED = 2;
    public final  static int STATE_SERVICE_DISCOVERED = 3;


    /**
     * 搜索蓝牙
     * @param callBack
     */
    public boolean startScan(BluetoothAdapter.LeScanCallback callBack);

    public void stopScan(BluetoothAdapter.LeScanCallback callBack);

    /**
     * 开始连接
     * @param address
     * @param callback
     */
    public boolean connect(String address, IConnectCallback callback);

    /**
     * 关闭连接
     * @param address
     * @param callback
     */
    public boolean close(String address,ICloseCallback callback);

    public boolean writeCharacteristic(BluetoothGatt gatt,BluetoothGattCharacteristic characteristic, byte[] data,IWriteCharacteristicCallback callback);



}
