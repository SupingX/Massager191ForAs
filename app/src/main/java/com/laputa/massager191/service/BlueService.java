package com.laputa.massager191.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

import com.laputa.massager191.base.BaseApp;
import com.laputa.massager191.bean.History;
import com.laputa.massager191.ble.blue.core.AbstractSimpleLaputaBlue;
import com.laputa.massager191.ble.blue.core.Configration;
import com.laputa.massager191.ble.blue.core.OnBlueChangedListener;
import com.laputa.massager191.ble.blue.core.SimpleLaputaBlue;
import com.laputa.massager191.ble.blue.util.BondedDeviceUtil;
import com.laputa.massager191.ble.blue.util.SharedPreferenceUtil;
import com.laputa.massager191.db.DbUtil;
import com.laputa.massager191.protocol.bean.MycjMassagerInfo;
import com.laputa.massager191.protocol.core.MassagerProtocolNotifyManager;
import com.laputa.massager191.protocol.core.MassagerProtocolWriteManager;
import com.laputa.massager191.protocol.notify.OnProtocolNotifyListenerBasedapter;
import com.laputa.massager191.util.Laputa;


public class BlueService extends Service {

    private AbstractSimpleLaputaBlue simpleLaputaBlue;

    public AbstractSimpleLaputaBlue getAbstractSimpleLaputaBlue() {
        return simpleLaputaBlue;
    }

    private MassagerProtocolWriteManager write = MassagerProtocolWriteManager.newInstance();


    public void startMassager(MycjMassagerInfo info, int witch) {
        if (info == null) {
            return;
        }
        write(write.writeForStartMassager(info, witch));
    }

    public void setPower(int power, int witch) {
        MassagerProtocolWriteManager write = MassagerProtocolWriteManager.newInstance();
        write(write.writeForChangePower(power, witch));
        if (witch == 0 && BaseApp.info1 != null) {
            BaseApp.info1.setPower(power);

        } else if (witch == 1 && BaseApp.info2 != null) {
            BaseApp.info2.setPower(power);
        } else if (witch == 2 && BaseApp.info3 != null) {
            BaseApp.info3.setPower(power);
        } else if (witch == 0xFF) {
            if (BaseApp.info1 != null) {
                BaseApp.info1.setPower(power);
            }
            if (BaseApp.info2 != null) {

                BaseApp.info2.setPower(power);
            }
            if (BaseApp.info3 != null) {

                BaseApp.info3.setPower(power);
            }
        }
    }

    public void stopMassager(int witch) {
        write(write.writeForStopMassager(witch));
    }

    private Handler mHandler = new Handler() {
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Laputa.e(BlueService.class, "-- onCreate --");
        acquireWakeLock();
        Configration c = new Configration();
        c.UUID_CHARACTERISTIC_NOTIFY = "6e400003-b5a3-f393-e0a9-e50e24dcca9e";
        c.UUID_CHARACTERISTIC_WRITE = "6e400002-b5a3-f393-e0a9-e50e24dcca9e";
        c.UUID_SERVICE = "6e400001-b5a3-f393-e0a9-e50e24dcca9e";
        simpleLaputaBlue = new SimpleLaputaBlue(this, c,
                new OnBlueChangedListener() {
                    @Override
                    public void reconnect(HashSet<String> devices) {
                        e("-->size : " + devices.size());
                        final String addressA = BondedDeviceUtil.get(1,
                                getApplicationContext());
                        if (BluetoothAdapter.checkBluetoothAddress(addressA)) {
                            try {
                                // 当前app存贮的蓝牙
                                BluetoothDevice remoteDevice = simpleLaputaBlue
                                        .getAdapter().getRemoteDevice(addressA);
                                // 所有的绑定蓝牙列表
                                Set<BluetoothDevice> bondedDevices = simpleLaputaBlue
                                        .getAdapter().getBondedDevices();
                                //
                                if (bondedDevices.contains(remoteDevice)) {
                                    i("--> 已绑定 ：" + addressA);
                                    if (!ifAllConnected()) {
                                        connect(remoteDevice);
                                        return;
                                    }
                                } else {
                                    i("--> 未绑定 ：" + addressA);
                                    // 当搜索列表中包含保存的addressA,并且未连接，就连接。
                                    if (devices.contains(addressA)) {
                                        if (!ifAllConnected()) {
                                            connect(remoteDevice);
                                        }
                                    } else {
                                        i("--> 搜索列表无：" + addressA);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                i("--> 重新连接失败！");
                            }

                        } else {
                            i("--> 蓝牙地址不匹配，没有addessA" + addressA);
                        }
                    }

                    @Override
                    public void onStateChanged(String address, int state) {

                    }

                    @Override
                    public void onServiceDiscovered(String address) {
//                        write(DataUtil.hexStringToByte("DA000000"));
                    }

                    @Override
                    public void onCharacteristicChanged(String address,
                                                        byte[] value) {
                        parseData(value);

                    }

                    @Override
                    public boolean isAllConnected() {
                        return ifAllConnected();
                    }
                });
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return new BlueBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public class BlueBinder extends Binder {
        public BlueService getXBlueService() {
            return BlueService.this;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseWakeLock();
        closeAll();

    }

    public void startScan() {
        simpleLaputaBlue.scanDevice(true);
    }

    public void write(byte[] data) {

        simpleLaputaBlue.write(BondedDeviceUtil.get(1, this), data);
    }

    public void write(byte[][] data) {
        simpleLaputaBlue.write(BondedDeviceUtil.get(1, this), data);

    }

    public void stopScan() {
        simpleLaputaBlue.scanDevice(false);
    }

    public synchronized void connect(BluetoothDevice device) {
        simpleLaputaBlue.connect(device.getAddress());
    }

    public boolean ifAllConnected() {
        return simpleLaputaBlue.isConnected(BondedDeviceUtil.get(1, this));
    }

    public void startAutoConnect() {
        simpleLaputaBlue.startAutoConnectTask();
    }

    public void stopAutoConnect() {
        simpleLaputaBlue.stopAutoConnectTask();
    }

    public void closeAll() {
        simpleLaputaBlue.closeAll();
    }


    /**
     * <p>
     * 解析数据
     * </p>
     *
     * @param data
     */
    private void parseData(byte[] data) {
        if (manager == null) {
            manager = new MassagerProtocolNotifyManager(getApplicationContext(),
                    new OnProtocolNotifyListenerBasedapter() {

                        @Override
                        public void onConfig(String desc, int count) {
                            super.onConfig(desc, count);
                            // TODO: 2016/9/12 出厂配置了几个按摩器.
                            i(desc);
                            BaseApp.count = count;

                            // 存贮按摩器定制的个数 .
                            SharedPreferenceUtil.put(getApplicationContext(), BaseApp.SHARE_COUNT, count);
                        }

                        @Override
                        public void onParseTime(String desc, int leftTime,
                                                int settingTime, int massager) {
                            super.onParseTime(desc, leftTime, settingTime, massager);
                            // TODO: 2016/9/12 各个按摩器的按摩时间
                            i(desc);
                            if (massager == 0) {
                                if (BaseApp.info1 != null) {
                                    BaseApp.info1.setLeftTime(leftTime);
                                    BaseApp.info1.setSettingTime(settingTime);
                                }
                            } else if (massager == 1) {
                                if (BaseApp.info2 != null) {
                                    BaseApp.info2.setLeftTime(leftTime);
                                    BaseApp.info2.setSettingTime(settingTime);
                                }
                            } else if (massager == 2) {
                                if (BaseApp.info3 != null) {
                                    BaseApp.info3.setLeftTime(leftTime);
                                    BaseApp.info3.setSettingTime(settingTime);
                                }
                            }

                            BaseApp.showInfo();


                        }

                        @Override
                        public void onParseTemperature(String desc, int temp,
                                                       int tempUnit) {
                            super.onParseTemperature(desc, temp, tempUnit);
                            i(desc);
                            if (BaseApp.info1 != null) {
                                BaseApp.info1.setTemperature(temp);
                                BaseApp.info1.setTempUnit(tempUnit);
                            }
                        }

                        @Override
                        public void onParsePower(String desc, int power, int massager) {
                            super.onParsePower(desc, power, massager);
                            i(desc);
                            // TODO: 2016/9/12  强度

                            if (massager == 0) {
                                if (BaseApp.info1 != null) {
                                    BaseApp.info1.setPower(power);
                                }
                            } else if (massager == 1) {
                                if (BaseApp.info2 != null) {
                                    BaseApp.info2.setPower(power);
                                }
                            } else if (massager == 2) {
                                if (BaseApp.info3 != null) {
                                    BaseApp.info3.setPower(power);
                                }
                            }

                        }

                        @Override
                        public void onParsePattern(String desc, int pattern, int massager) {
                            super.onParsePattern(desc, pattern, massager);
                            i(desc);
                            // TODO: 2016/9/12  模式

                            if (massager == 0) {
                                if (BaseApp.info1 != null) {
                                    BaseApp.info1.setPattern(pattern);
                                }
                            } else if (massager == 1) {
                                if (BaseApp.info2 != null) {
                                    BaseApp.info2.setPattern(pattern);
                                }
                            } else if (massager == 2) {
                                if (BaseApp.info3 != null) {
                                    BaseApp.info3.setPattern(pattern);
                                }
                            }
                        }


                        @Override
                        public void onParseMassagerInfo(String desc,
                                                        final MycjMassagerInfo info, int massager) {
                            super.onParseMassagerInfo(desc, info, massager);

                            i(desc);
                            // TODO: 2016/9/12 按摩信息
                            // 怎样才算停止？
                            // 1。发来的按摩信息为open为0
                            // 2。怎样才可以保存
                            // 3。open为1，并且已经开始了。
                            if (massager == 0) {
                                if (info.getOpen() == 0) {
                                    if (BaseApp.isInfo1Start) {
//                                        Laputa.i("==========================按摩器1结束了==============================");
                                     if (BaseApp.info1 != null) {
//                                         Laputa.i("==========================按摩器不是空结束了==============================");
                                            final History history1 = new History();
                                            history1.setDate(Laputa.dateToString(new Date(), "yyyyMMdd hh:mm:ss"));
                                            history1.setModel(BaseApp.info1.getPattern());
                                            history1.setPower(BaseApp.info1.getPower());
                                            new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                        DbUtil.getInstance(getApplicationContext()).save(history1);
                                                }
                                            }).start();
                                        }
                                    }
//                                    Laputa.i("==========================按摩器清空了==============================");
                                    BaseApp.info1 = null;
                                    BaseApp.isInfo1Start = false;
                                } else {
                                    BaseApp.isInfo1Start = true;
                                    BaseApp.info1 = info;
                                }
                            } else if (massager == 1) {
                                if (info.getOpen() == 0) {
                                    if (BaseApp.isInfo2Start) {
                                     if (BaseApp.info2 != null) {

                                            final History history2 = new History();
                                            history2.setDate(Laputa.dateToString(new Date(), "yyyyMMdd hh:mm:ss"));
                                            history2.setModel(BaseApp.info2.getPattern());
                                            history2.setPower(BaseApp.info2.getPower());
                                            new Thread(new Runnable() {
                                                @Override
                                                public void run() {

                                                        DbUtil.getInstance(getApplicationContext()).save(history2);

                                                }
                                            }).start();
                                        }

                                    }
                                    BaseApp.info2 = null;
                                    BaseApp.isInfo2Start = false;
                                } else {
                                    BaseApp.isInfo2Start = true;
                                    BaseApp.info2 = info;
                                }
                            } else if (massager == 2) {
                                if (info.getOpen() == 0) {
                                    if (BaseApp.isInfo3Start) {
                                     if (BaseApp.info3 != null) {
                                            final History history3 = new History();
                                            history3.setDate(Laputa.dateToString(new Date(), "yyyyMMdd hh:mm:ss"));
                                            history3.setModel(BaseApp.info3.getPattern());
                                            history3.setPower(BaseApp.info3.getPower());
                                            new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                        DbUtil.getInstance(getApplicationContext()).save(history3);
                                                }
                                            }).start();
                                        }
                                    }
                                    BaseApp.isInfo3Start = false;
                                    BaseApp.info3 = null;
                                } else {
                                    BaseApp.isInfo3Start = true;
                                    BaseApp.info3 = info;
                                }
                            }

                            BaseApp.showInfo();
                        }


                        @Override
                        public void onParseLoader(String desc, int loader, int massager) {
                            super.onParseLoader(desc, loader, massager);
                            i(desc);
                            // TODO: 2016/9/12 负载
                            if (massager == 0) {
                                if (BaseApp.info1 != null) {
                                    BaseApp.info1.setLoader(loader);
                                }
                            } else if (massager == 1) {
                                if (BaseApp.info2 != null) {
                                    BaseApp.info2.setLoader(loader);
                                }
                            } else if (massager == 2) {
                                if (BaseApp.info3 != null) {
                                    BaseApp.info3.setLoader(loader);
                                }
                            }

                        }

                        @Override
                        public void onParseChangeTimeCallBack(String desc,
                                                              int success, int settingTime, int massager) {
                            super.onParseTime(desc, success, settingTime, massager);
                            i(desc);
                            // TODO: 2016/9/12 改变时间

                            if (massager == 0) {
                                if (BaseApp.info1 != null && success == 1) {
                                    BaseApp.info1.setSettingTime(settingTime);
                                    BaseApp.info1.setLeftTime(settingTime);
                                }
                            } else if (massager == 1 && success == 1) {
                                if (BaseApp.info2 != null) {
                                    BaseApp.info2.setSettingTime(settingTime);
                                    BaseApp.info2.setLeftTime(settingTime);
                                }
                            } else if (massager == 2 && success == 1) {
                                if (BaseApp.info3 != null) {
                                    BaseApp.info3.setSettingTime(settingTime);
                                    BaseApp.info3.setLeftTime(settingTime);
                                }
                            }


                        }

                        @Override
                        public void onParseChangeTemperatureCallBack(
                                String desc, int success, int temp, int tempUnit) {
                            super.onParseChangeTemperatureCallBack(desc, success, temp, tempUnit);
                            i(desc);

                            if (BaseApp.info1 != null) {
                                BaseApp.info1.setTemperature(temp);
                                BaseApp.info1.setTempUnit(tempUnit);
                            }
                        }

                        @Override
                        public void onParseChangePowerCallBack(String desc,
                                                               int success, int power, int massager) {
                            super.onParseChangePowerCallBack(desc, success, power, massager);
                            i(desc);
                            // TODO: 2016/9/12 改变力度
                            if (massager == 0) {
                                if (BaseApp.info1 != null && success == 1) {
                                    BaseApp.info1.setPower(power);
                                }
                            } else if (massager == 1 && success == 1) {
                                if (BaseApp.info2 != null) {
                                    BaseApp.info2.setPower(power);
                                }
                            } else if (massager == 2 && success == 1) {
                                if (BaseApp.info3 != null) {
                                    BaseApp.info3.setPower(power);
                                }
                            }


                            BaseApp.showInfo();
                        }

                        @Override
                        public void onParseChangePatternCallBack(String desc, int success, int pattern, int massager) {
                            super.onParseChangePatternCallBack(desc, success, pattern, massager);
                            i(desc);
                            // TODO: 2016/9/12  模式改变

                            if (massager == 0) {
                                if (BaseApp.info1 != null) {
                                    BaseApp.info1.setPattern(pattern);
                                }
                            } else if (massager == 1) {
                                if (BaseApp.info2 != null) {
                                    BaseApp.info2.setPattern(pattern);
                                }
                            } else if (massager == 2) {
                                if (BaseApp.info3 != null) {
                                    BaseApp.info3.setPattern(pattern);
                                }
                            }
                        }

                        @Override
                        public void onParseChangeHeartRate(String desc, int hr, int massager) {
                            super.onParseChangeHeartRate(desc, hr, massager);
                            i(desc);
                            if (BaseApp.info1 != null) {
                                BaseApp.info1.setHr(hr);
                            }

                        }

                        @Override
                        public void onError(String desc) {
                            super.onError(desc);
                            i(desc);
                        }
                    });
        }
        manager.parse(data);

    }

    WakeLock wakeLock = null;
    private MassagerProtocolNotifyManager manager;

    // 获取电源锁，保持该服务在屏幕熄灭时仍然获取CPU时，保持运行
    private void acquireWakeLock() {
        if (null == wakeLock) {
            PowerManager pm = (PowerManager) this
                    .getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK
                    | PowerManager.ON_AFTER_RELEASE, "PostLocationService");
            if (null != wakeLock) {
                wakeLock.acquire();
            }
        }
    }

    // 释放设备电源锁
    private void releaseWakeLock() {
        if (null != wakeLock) {
            wakeLock.release();
            wakeLock = null;
        }
    }


    private boolean isDebug = true;

    private void e(String msg) {
        Laputa.e("BlueService", msg);
    }

    private void i(String msg) {
        Laputa.i("BlueService", msg);
    }

}
