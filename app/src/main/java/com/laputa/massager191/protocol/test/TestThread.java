package com.laputa.massager191.protocol.test;


import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;

import com.laputa.massager191.base.BaseApp;
import com.laputa.massager191.protocol.bean.MycjMassagerInfo;
import com.laputa.massager191.protocol.core.MassagerProtocolNotifyManager;
import com.laputa.massager191.protocol.core.ProtocolBroadcast;
import com.laputa.massager191.protocol.notify.OnProtocolNotifyListenerBasedapter;
import com.laputa.massager191.protocol.util.DataUtil;
import com.laputa.massager191.util.Laputa;

/**
 * Created by Administrator on 2016/9/12.
 */
public class TestThread extends Thread {
    public final static String[] PROTOCOLS = {
            "DA010000"
            , "DF01CD010100"
            , "D9CC0100"
            , "D80A0101" //3
            , "D70100CC010000"// 444
            , "D600AA00BB0100"//555
            , "D5010A010000"//666
            , "D4AA0100"//777
            , "D30102010000"
            , "D2020100"//999
            , "D1010100"// 10
            , "D001020300AA00AA040101BB0100000000000000"
    };
    private boolean isDebug = true;
    private MassagerProtocolNotifyManager manager;
    private Context context;

    public TestThread(Context context) {
        this.context = context;
    }

    private void e(String msg) {
        Laputa.e(  msg);
    }

    private void i(String msg) {
        Laputa.i(  msg);
    }

    @Override
    public void run() {
        super.run();
        try {

            for (int i = 0; i < PROTOCOLS.length; i++) {
                Thread.sleep(2000);
                Log.e("laputa"," ---> " + i);
                parseData(DataUtil.hexStringToByte(PROTOCOLS[i]));

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    private void parseData(byte[] data) {
        if (manager == null) {
            manager = new MassagerProtocolNotifyManager(context,
                    new OnProtocolNotifyListenerBasedapter() {

                        @Override
                        public void onConfig(String desc, int count) {
                            super.onConfig(desc, count);
                            i(desc);
                            BaseApp.count = count;
                        }

                        @Override
                        public void onParseTime(String desc, int leftTime,
                                                int settingTime, int massager) {
                            super.onParseTime(desc, leftTime, settingTime, massager);
                            i(desc);
                            if(massager == 0){
                                if (BaseApp.info1 != null) {
                                    BaseApp.info1.setLeftTime(leftTime);
                                    BaseApp.info1.setSettingTime(settingTime);
                                }
                            } else if(massager == 1){
                                if (BaseApp.info2 != null) {
                                    BaseApp.info2.setLeftTime(leftTime);
                                    BaseApp.info2.setSettingTime(settingTime);
                                }
                            } else if(massager == 2){
                                if (BaseApp.info3 != null) {
                                    BaseApp.info3.setLeftTime(leftTime);
                                    BaseApp.info3.setSettingTime(settingTime);
                                }
                            }

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
                        public void onParseChangePatternCallBack(String desc, int success, int pattern, int massager) {
                            super.onParseChangePatternCallBack(desc, success, pattern, massager);
                             i(desc);

                            if(massager == 0){
                                if (BaseApp.info1 != null) {
                                    BaseApp.info1.setPattern(pattern);
                                }
                            } else if(massager == 1){
                                if (BaseApp.info2 != null) {
                                    BaseApp.info2.setPattern(pattern);
                                }
                            } else if(massager == 2){
                                if (BaseApp.info3 != null) {
                                    BaseApp.info3.setPattern(pattern);
                                }
                            }
                        }

                        @Override
                        public void onParsePower(String desc, int power, int massager) {
                            super.onParsePower(desc, power, massager);
                            i(desc);

                            if(massager == 0){
                                if (BaseApp.info1 != null) {
                                    BaseApp.info1.setPower(power);
                                }
                            } else if(massager == 1){
                                if (BaseApp.info2 != null) {
                                    BaseApp.info2.setPower(power);
                                }
                            } else if(massager == 2){
                                if (BaseApp.info3 != null) {
                                    BaseApp.info3.setPower(power);
                                }
                            }

                        }

                        @Override
                        public void onParsePattern(String desc, int pattern, int massager) {
                            super.onParsePattern(desc,pattern,massager);
                            i(desc);

                            if(massager == 0){
                                if (BaseApp.info1 != null) {
                                    BaseApp.info1.setPattern(pattern);
                                }
                            } else if(massager == 1){
                                if (BaseApp.info2 != null) {
                                    BaseApp.info2.setPattern(pattern);
                                }
                            } else if(massager == 2){
                                if (BaseApp.info3 != null) {
                                    BaseApp.info3.setPattern(pattern);
                                }
                            }
                        }


                        @Override
                        public void onParseMassagerInfo(String desc,
                                                        MycjMassagerInfo info, int massager) {
                            super.onParseMassagerInfo(desc, info, massager);
                            i(desc);

                            if(massager == 0){
                                BaseApp.info1 = info;
                            } else if(massager == 1){
                                BaseApp.info2 = info;
                            } else if(massager == 2){
                                BaseApp.info3 = info;
                            }
                        }

                        @Override
                        public void onParseLoader(String desc, int loader, int massager) {
                            super.onParseLoader(desc, loader, massager);
                            i(desc);
                            if(massager == 0){
                                if (BaseApp.info1 != null) {
                                    BaseApp.info1.setLoader(loader);
                                }
                            } else if(massager == 1){
                                if (BaseApp.info2 != null) {
                                    BaseApp.info2.setLoader(loader);
                                }
                            } else if(massager == 2){
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

                            if(massager == 0){
                                if (BaseApp.info1 != null  && success == 1) {
                                    BaseApp.info1.setSettingTime(settingTime);
                                    BaseApp.info1.setLeftTime(settingTime);
                                }
                            } else if(massager == 1  && success == 1){
                                if (BaseApp.info2 != null) {
                                    BaseApp.info2.setSettingTime(settingTime);
                                    BaseApp.info2.setLeftTime(settingTime);
                                }
                            } else if(massager == 2  && success == 1){
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
                            if(massager == 0){
                                if (BaseApp.info1 != null  && success == 1) {
                                    BaseApp.info1.setPower(power);
                                }
                            } else if(massager == 1  && success == 1){
                                if (BaseApp.info2 != null) {
                                    BaseApp.info2.setPower(power);
                                }
                            } else if(massager == 2  && success == 1){
                                if (BaseApp.info3 != null) {
                                    BaseApp.info3.setPower(power);
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


}
