package com.laputa.massager191.ble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Context;

import com.laputa.massager191.ble.basic.AbstractLeafBle;
import com.laputa.massager191.ble.callback.ICloseCallback;
import com.laputa.massager191.ble.callback.IConnectCallback;
import com.laputa.massager191.ble.callback.ScanDeviceCallback;
import com.laputa.massager191.ble.callback.IWriteCharacteristicCallback;
import com.litesuits.bluetooth.LiteBluetooth;

/**
 * Created by Administrator on 2016/9/8.
 */
public class LeafBle extends AbstractLeafBle {


    public LeafBle(Context context) {
        super(context);
    }

    @Override
    public boolean connect(String address, IConnectCallback callback) {
        return false;
    }

    @Override
    public boolean close(String address, ICloseCallback callback) {
        return false;
    }

    @Override
    public boolean writeCharacteristic(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, byte[] data, IWriteCharacteristicCallback callback) {
        return false;
    }
}
