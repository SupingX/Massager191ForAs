package com.laputa.massager191.protocol.core;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.laputa.massager191.protocol.bean.MycjMassagerInfo;

public class ProtocolBroadcastReceiver extends BroadcastReceiver {
    /**
     * 设置初始的masagerinfo 可重写
     *
     * @param info 原来的massagerInfo
     * @param witch
     * @return settingInfo 初始值的info
     */
    protected MycjMassagerInfo setDefaultMassagerInfo(MycjMassagerInfo info, int witch) {
        if (info == null) {
            info = new MycjMassagerInfo(0, 0, 1, 10 * 60, 10 * 60, 10, 0, 0, 0);
        } else {
            if (info.getOpen() == 0) {
                info = new MycjMassagerInfo(0, 0, 1, 10 * 60, 10 * 60, 10, 0, 0, 0);
            }
        }
        return info;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Bundle bundle = intent.getExtras();
        if (TextUtils.equals(action, ProtocolBroadcast.ACTION_MYCJ_CHANGE_PATTERN_CALL_BACK)) {
            int witch = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_WITCH);
            int stauts = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_STATUS);
            int pattern = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_PATTERN);
            onChangePatternCallBack(stauts, pattern,witch);
        } else if (TextUtils.equals(action, ProtocolBroadcast.ACTION_MYCJ_CHANGE_POWER_CALL_BACK)) {
            int witch = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_WITCH);
            int stauts = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_STATUS);
            int power = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_POWER);
            onChangePowerCallBack(stauts, power,witch);
        } else if (TextUtils.equals(action, ProtocolBroadcast.ACTION_MYCJ_CHANGE_TEMPEATURE_CALL_BACK)) {
            int stauts = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_STATUS);
            int temp = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_TEMPEATURE);
            int tempUnit = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_TEMPEATURE_UNIT);
            onChangeTempeatureCallBack(stauts, temp, tempUnit);
        } else if (TextUtils.equals(action, ProtocolBroadcast.ACTION_MYCJ_CHANGE_TIME_CALL_BACK)) {
            int stauts = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_STATUS);
            int time = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_TIME_SETTING);
            onChangeTimeCallBack(stauts, time);
        } else if (TextUtils.equals(action, ProtocolBroadcast.ACTION_MYCJ_ELECTRICITY)) {
            int totalElect = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_STATUS);
            int leftElect = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_TIME_SETTING);
            onChangeElectricityCallBack(totalElect, leftElect);
        } else if (TextUtils.equals(action, ProtocolBroadcast.ACTION_MYCJ_FACTORYRESETSTATUS)) {
            int status = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_STATUS);
            onChangeFactoryResetCallBack(status);
        } else if (TextUtils.equals(action, ProtocolBroadcast.ACTION_MYCJ_FIRMWAREVERSION)) {
            String version = bundle.getString(ProtocolBroadcast.EXTRA_MYCJ_FIRMWAREVERSION);
            onChangeFirmwareVersion(version);
        } else if (TextUtils.equals(action, ProtocolBroadcast.EXTRA_MYCJ_HEARTRATE)) {
            int heartRate = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_HEARTRATE);
            onChangeHeartRate(heartRate);
        } else if (TextUtils.equals(action, ProtocolBroadcast.ACTION_MYCJ_LOADER)) {
            int witch = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_WITCH);
            int loader = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_LOADER);
            onChangeLoader(loader,witch);
        } else if (TextUtils.equals(action, ProtocolBroadcast.ACTION_MYCJ_MASSAGERINFO)) {
            MycjMassagerInfo info = bundle.getParcelable(ProtocolBroadcast.EXTRA_MYCJ_MASSAGERINFO);
            int witch = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_WITCH);
//			LogUtil.log("广播接受的info ：" + info.toString());
            MycjMassagerInfo data = setDefaultMassagerInfo(info,witch);
            onChangeMassagerInfo(data,witch);
        } else if (TextUtils.equals(action, ProtocolBroadcast.ACTION_MYCJ_ERROR)) {

        } else if (TextUtils.equals(action, ProtocolBroadcast.ACTION_MYCJ_OPEN_OR_CLOSE_CALL_BACK)) {
            int status = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_STATUS);
            onChangeOpenOrCloseCallBack(status);
        } else if (TextUtils.equals(action, ProtocolBroadcast.ACTION_MYCJ_PATTERN)) {
            int pattern = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_PATTERN);
            int witch = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_WITCH);
            onChangePattern(pattern,witch);
        } else if (TextUtils.equals(action, ProtocolBroadcast.ACTION_MYCJ_POWER)) {
            int power = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_POWER);
            int witch = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_WITCH);
            onChangePower(power,witch);
        } else if (TextUtils.equals(action, ProtocolBroadcast.ACTION_MYCJ_TEMPERATURE)) {
            int temp = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_TEMPEATURE);
            int tempUnit = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_TEMPEATURE_UNIT);
            onChangeTemperature(temp, tempUnit);
        } else if (TextUtils.equals(action, ProtocolBroadcast.ACTION_MYCJ_TIME)) {
            int witch = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_WITCH);
            int time = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_TIME);
            int timeSetting = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_TIME_SETTING);
            onChangeTime(time, timeSetting,witch);
        }else if (TextUtils.equals(action, ProtocolBroadcast.ACTION_MYCJ_CONFIG)) {
            int count = bundle.getInt(ProtocolBroadcast.EXTRA_MYCJ_CONFIG);
            onConfig(count);

        }
    }


    // ^^^^^^^^^^^^^^^^^^^^^^^^ 子类重写 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~!!!!!!!!!!@@@@@@@########$$$$%%%%%%^^^^^^^^^^^^^^^&&&&&&&&&&*************((((((((((())))))))))))_______+++
    // [- -]zzZ
    //

    private static boolean isDebug = true;
    private void e(String msg){
        if (isDebug){
            Log.e("ProtocolBR","------------------->"+msg+"<---------------------");
        }
    }


    protected void onConfig(int count) {
        e("onConfig()       count : "+count );
    }


    protected void onChangeTime(int time, int timeSetting, int witch) {
        e("onChangeTime()       timeSetting : "+timeSetting +",witch : "+ witch);
    }


    protected void onChangeTemperature(int temp, int tempUnit) {
        e("onChangeTemperature()       temp : "+temp +",tempUnit : "+ tempUnit);
    }


    protected void onChangePower(int power, int witch) {
        e("onChangePower()       power : "+power +",witch : "+ witch);
    }

    protected void onChangePattern(int witch, int pattern) {
        e("onChangePattern()       pattern : "+pattern +",witch : "+witch);
    }

    protected void onChangeOpenOrCloseCallBack(int status) {
        e("onChangeOpenOrCloseCallBack()       status : "+status );
    }

    protected void onChangeMassagerInfo(MycjMassagerInfo info,int witch) {
        e("onChangeMassagerInfo()       MycjMassagerInfo : "+info +",witch : "+witch);
    }

    protected void onChangeLoader(int loader, int witch) {
        e("onChangeLoader()       loader : "+loader +",witch : "+witch);
    }

    protected void onChangeHeartRate(int heartRate) {
        e("onChangeHeartRate()       heartRate : "+heartRate );
    }

    protected void onChangeFirmwareVersion(String version) {
        e("onChangeFirmwareVersion()       version : "+version );
    }

    protected void onChangeFactoryResetCallBack(int status) {
        e("onChangeFactoryResetCallBack()       status : "+status );
    }

    protected void onChangeElectricityCallBack(int totalElect, int leftElect) {
        e("onChangeElectricityCallBack()       totalElect : "+totalElect +",leftElect : "+leftElect);
    }

    protected void onChangeTimeCallBack(int stauts, int time) {
        e("onChangeTimeCallBack()       stauts : "+stauts +",time : "+time);
    }

    protected void onChangeTempeatureCallBack(int stauts, int temp, int tempUnit) {
        e("onChangeTempeatureCallBack()       stauts : "+stauts +",temp : "+temp);
    }

    protected void onChangePowerCallBack(int stauts, int power, int witch) {
        e("onChangePowerCallBack()       power : "+power +",witch : "+witch);
    }

    protected void onChangePatternCallBack(int stauts, int pattern, int witch) {
        e("onChangePatternCallBack()       pattern : "+pattern +",witch : "+witch);
    }

}
