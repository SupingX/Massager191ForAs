package com.laputa.massager191.ble.basic;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.pm.PackageManager;

import com.laputa.massager191.ble.callback.ScanDeviceCallback;
import com.laputa.massager191.ble.callback.ScanDeviceUseMacCallback;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/9/8.
 */
public abstract class AbstractLeafBle implements ILeafBle {
    private HashMap<String ,BluetoothGatt> gatts = new HashMap<String,BluetoothGatt>();
    private int state = STATE_DISCONNECTED;
    public final  static int STATE_SCAN_STOP = 0;
    public final  static int STATE_SCAN_ING = 1;
    private int scanState = STATE_SCAN_STOP;
    private Context context;
    private BluetoothManager manager;
    private BluetoothAdapter adapter;

    public int getState() {
        return state;
    }

    public boolean isServiceDiscovered(){
        return this.state == STATE_SERVICE_DISCOVERED;
    }

    public AbstractLeafBle(Context context){
        this.context = context;
        initAdapter(context);
    }

    public void initAdapter(Context context) {
        manager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        adapter = manager.getAdapter();
    }

    public boolean checkFeature(Context context){
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
    }

    public boolean isBleEnable(){
        return checkFeature(context)
                && adapter!=null
                && adapter.isEnabled();
    }

    @Override
    public void stopScan(BluetoothAdapter.LeScanCallback callBack) {
        if (isBleEnable()){
            if (callBack instanceof  ScanDeviceCallback){
                ((ScanDeviceCallback) callBack).removeHandlerMsg();
            }

            adapter.stopLeScan(callBack);
            this.scanState = STATE_SCAN_STOP;
        }
    }


    @Override
    public boolean startScan(BluetoothAdapter.LeScanCallback callBack) {
        if (isBleEnable()){
            boolean result = adapter.startLeScan(callBack);
            if (result){
                scanState = STATE_SCAN_ING;
            }
            return result;
        }
        return false;
    }



    public boolean startScan(ScanDeviceCallback callback){
        if (isBleEnable()){
            callback.removeHandlerMsg();;
            boolean result = adapter.startLeScan(callback);
            if (result){
                scanState = STATE_SCAN_ING;
            }else{
                callback.removeHandlerMsg();
            }
        }
        return false;
    }

    public  boolean startScan(ScanDeviceUseMacCallback callback){
        if (isBleEnable()){
            callback.removeHandlerMsg();;
            boolean result = adapter.startLeScan(callback);
            if (result){
                scanState = STATE_SCAN_ING;
            }else{
                callback.removeHandlerMsg();
            }
        }
        return false;
    }




}
