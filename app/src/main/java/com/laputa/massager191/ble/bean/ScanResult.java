package com.laputa.massager191.ble.bean;

import android.bluetooth.BluetoothDevice;

/**
 * Created by Administrator on 2016/9/8.
 */
public class ScanResult {

    private BluetoothDevice device;
    private int rssi;
    private byte[] scanRecord;

    public ScanResult() {
    }

    public ScanResult(BluetoothDevice device, int rssi, byte[] scanRecord) {
        this.device = device;
        this.rssi = rssi;
        this.scanRecord = scanRecord;
    }

    public void setDevice(BluetoothDevice device) {
        this.device = device;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public void setScanRecord(byte[] scanRecord) {
        this.scanRecord = scanRecord;
    }

    public BluetoothDevice getDevice() {

        return device;
    }

    public int getRssi() {
        return rssi;
    }

    public byte[] getScanRecord() {
        return scanRecord;
    }
}
