package com.laputa.massager191.ble.callback;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.Looper;

import com.laputa.massager191.ble.basic.AbstractLeafBle;
import com.laputa.massager191.ble.basic.ILeafBle;
import com.laputa.massager191.ble.bean.ScanResult;

import java.util.HashSet;

/**
 * Created by Administrator on 2016/9/8.
 */
public abstract class ScanDeviceCallback implements BluetoothAdapter.LeScanCallback {
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private AbstractLeafBle ble;
    private long timeOut = 10 * 1000L;

    public ScanDeviceCallback(AbstractLeafBle ble,long timeOut) {
        this.ble = ble;
        this.timeOut = timeOut;
    }

    @Override
    public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
        ScanResult result = new ScanResult(device, rssi, scanRecord);
        onScaning(result);
    }

    public void notifyScanStarted() {
        try{


        if (timeOut > 0) {
            removeHandlerMsg();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (ble != null){
                        ble.stopScan(ScanDeviceCallback.this);
                        onScanFinish();
                    }
                }
            },timeOut);
        }
        }catch (Exception e){
            onScanException(e);
        }
    }

    public void removeHandlerMsg() {
        mHandler.removeCallbacksAndMessages(null);
    }
    public void setBle(AbstractLeafBle ble) {
        this.ble = ble;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public ILeafBle getBle() {
        return ble;
    }



    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }
    public abstract void onScaning(ScanResult result);

    public abstract void onScanFinish();

    public abstract void onScanException(Exception e);

}
