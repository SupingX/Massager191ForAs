package com.laputa.massager191.protocol.core;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.laputa.massager191.protocol.bean.MycjMassagerInfo;

/**
 * 　广播
 *
 * @author zeej
 */
public class ProtocolBroadcast {
    public static final String ACTION_MYCJ_CHANGE_PATTERN_CALL_BACK = "ACTION_MYCJ_CHANGE_PATTERN_CALL_BACK";
    public static final String ACTION_MYCJ_CHANGE_TEMPEATURE_CALL_BACK = "ACTION_MYCJ_CHANGE_TEMPEATURE_CALL_BACK";
    public static final String ACTION_MYCJ_CHANGE_POWER_CALL_BACK = "ACTION_MYCJ_CHANGE_POWER_CALL_BACK";
    public static final String ACTION_MYCJ_CHANGE_TIME_CALL_BACK = "ACTION_MYCJ_CHANGE_TIME_CALL_BACK";
    public static final String ACTION_MYCJ_OPEN_OR_CLOSE_CALL_BACK = "ACTION_MYCJ_OPEN_OR_CLOSE_CALL_BACK";
    /*
     * public static final String ACTION_MYCJ_START = "ACTION_MYCJ_START";
     * public static final String ACTION_MYCJ_STOP = "ACTION_MYCJ_STOP";
     */
    public static final String ACTION_MYCJ_ELECTRICITY = "ACTION_MYCJ_ELECTRICITY";
    public static final String ACTION_MYCJ_FACTORYRESETSTATUS = "ACTION_MYCJ_FACTORYRESETSTATUS";
    public static final String ACTION_MYCJ_FIRMWAREVERSION = "ACTION_MYCJ_FIRMWAREVERSION";
    public static final String ACTION_MYCJ_HEARTRATE = "ACTION_MYCJ_HEARTRATE";
    public static final String ACTION_MYCJ_LOADER = "ACTION_MYCJ_LOADER";
    public static final String ACTION_MYCJ_MASSAGERINFO = "ACTION_MYCJ_MASSAGERINFO";
    public static final String ACTION_MYCJ_PATTERN = "ACTION_MYCJ_PATTERN";
    public static final String ACTION_MYCJ_POWER = "ACTION_MYCJ_POWER";
    public static final String ACTION_MYCJ_TIME = "ACTION_MYCJ_TIME";
    public static final String ACTION_MYCJ_TEMPERATURE = "ACTION_TEMPERATURE";
    public static final String ACTION_MYCJ_ERROR = "ACTION_MYCJ_NON";
    public static final String ACTION_MYCJ_CONFIG = "ACTION_MYCJ_CONFIG";

    public static final String EXTRA_MYCJ_STATUS = "EXTRA_MYCJ_STATUS";
    public static final String EXTRA_MYCJ_PATTERN = "EXTRA_MYCJ_PATTERN";
    public static final String EXTRA_MYCJ_TEMPEATURE = "EXTRA_MYCJ_TEMPEATURE";
    public static final String EXTRA_MYCJ_TEMPEATURE_UNIT = "EXTRA_MYCJ_TEMPEATURE_UNIT";
    public static final String EXTRA_MYCJ_POWER = "EXTRA_MYCJ_POWER";
    public static final String EXTRA_MYCJ_TIME = "EXTRA_MYCJ_TIME";
    public static final String EXTRA_MYCJ_TIME_SETTING = "EXTRA_MYCJ_TIME_SETTING";
    public static final String EXTRA_MYCJ_ELECTRICITY_TOTAL = "EXTRA_MYCJ_ELECTRICITY_TOTAL";
    public static final String EXTRA_MYCJ_ELECTRICITY_LEFT = "EXTRA_MYCJ_ELECTRICITY_LEFT";
    public static final String EXTRA_MYCJ_FIRMWAREVERSION = "EXTRA_MYCJ_FIRMWAREVERSION";
    public static final String EXTRA_MYCJ_FIRMWAREVERSION_CODE = "EXTRA_MYCJ_FIRMWAREVERSION_CODE";
    public static final String EXTRA_MYCJ_HEARTRATE = "EXTRA_MYCJ_HEARTRATE";
    public static final String EXTRA_MYCJ_LOADER = "EXTRA_MYCJ_LOADER";
    public static final String EXTRA_MYCJ_MASSAGERINFO = "EXTRA_MYCJ_MASSAGERINFO";
    public static final String EXTRA_MYCJ_DESC = "EXTRA_MYCJ_DESC";
    public static final String EXTRA_MYCJ_CONFIG = "EXTRA_MYCJ_CONFIG";
    public static final String EXTRA_MYCJ_WITCH = "EXTRA_MYCJ_WITCH";

    private static ProtocolBroadcast mProtocolManager;
    private Context context;

    public ProtocolBroadcast(Context context) {
        this.context = context;
    }

    public static ProtocolBroadcast newInstance(Context context) {
        if (null == mProtocolManager) {
            mProtocolManager = new ProtocolBroadcast(context);
        }
        return mProtocolManager;
    }

    public static IntentFilter getIntentFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_MYCJ_CHANGE_PATTERN_CALL_BACK);
        filter.addAction(ACTION_MYCJ_CHANGE_TEMPEATURE_CALL_BACK);
        filter.addAction(ACTION_MYCJ_CHANGE_POWER_CALL_BACK);
        filter.addAction(ACTION_MYCJ_CHANGE_TIME_CALL_BACK);
        filter.addAction(ACTION_MYCJ_OPEN_OR_CLOSE_CALL_BACK);
        filter.addAction(ACTION_MYCJ_ERROR);
        /*
		 * filter.addAction(ACTION_MYCJ_START);
		 * filter.addAction(ACTION_MYCJ_STOP);
		 */
        filter.addAction(ACTION_MYCJ_ELECTRICITY);
        filter.addAction(ACTION_MYCJ_FACTORYRESETSTATUS);
        filter.addAction(ACTION_MYCJ_FIRMWAREVERSION);
        filter.addAction(ACTION_MYCJ_HEARTRATE);
        filter.addAction(ACTION_MYCJ_LOADER);
        filter.addAction(ACTION_MYCJ_MASSAGERINFO);
        filter.addAction(ACTION_MYCJ_PATTERN);
        filter.addAction(ACTION_MYCJ_POWER);
        filter.addAction(ACTION_MYCJ_TIME);
        filter.addAction(ACTION_MYCJ_TEMPERATURE);
        filter.addAction(ACTION_MYCJ_CONFIG);
        return filter;
    }

    public void sendBroadcastForChangePatternCallBack(int status, int pattern,int witch) {
        Intent intent = new Intent(ACTION_MYCJ_CHANGE_PATTERN_CALL_BACK);
        intent.putExtra(EXTRA_MYCJ_STATUS, status);
        intent.putExtra(EXTRA_MYCJ_WITCH, witch);
        intent.putExtra(EXTRA_MYCJ_PATTERN, pattern);
        context.sendBroadcast(intent);
    }

    public void sendBroadcastForChangeTemperatureCallBack(int status,
                                                          int temp, int tempUnit) {
        Intent intent = new Intent(ACTION_MYCJ_CHANGE_TEMPEATURE_CALL_BACK);
        intent.putExtra(EXTRA_MYCJ_STATUS, status);
        intent.putExtra(EXTRA_MYCJ_TEMPEATURE, temp);
        intent.putExtra(EXTRA_MYCJ_TEMPEATURE_UNIT, tempUnit);
        context.sendBroadcast(intent);
    }

    public void sendBroadcastForChangePowerCallBack(int status, int power,int witch) {
        Intent intent = new Intent(ACTION_MYCJ_CHANGE_POWER_CALL_BACK);
        intent.putExtra(EXTRA_MYCJ_WITCH, witch);
        intent.putExtra(EXTRA_MYCJ_STATUS, status);
        intent.putExtra(EXTRA_MYCJ_POWER, power);
        context.sendBroadcast(intent);
    }

    public void sendBroadcastForChangeTimeCallBack(int status, int time,int witch) {
        Intent intent = new Intent(ACTION_MYCJ_CHANGE_TIME_CALL_BACK);
        intent.putExtra(EXTRA_MYCJ_STATUS, status);
        intent.putExtra(EXTRA_MYCJ_TIME_SETTING, time);
        intent.putExtra(EXTRA_MYCJ_WITCH, witch);
        context.sendBroadcast(intent);
    }

    public void sendBroadcastForOpenOrCloseStatus(String desc, int status) {
        Intent intent = new Intent(ACTION_MYCJ_OPEN_OR_CLOSE_CALL_BACK);
        intent.putExtra(EXTRA_MYCJ_DESC, desc);
        intent.putExtra(EXTRA_MYCJ_STATUS, status);
        context.sendBroadcast(intent);
    }

    public void sendBroadcastForElectricity(String desc, int totalElect, int leftElect) {
        Intent intent = new Intent(ACTION_MYCJ_ELECTRICITY);
        intent.putExtra(EXTRA_MYCJ_DESC, desc);
        intent.putExtra(EXTRA_MYCJ_ELECTRICITY_TOTAL, totalElect);
        intent.putExtra(EXTRA_MYCJ_ELECTRICITY_LEFT, leftElect);
        context.sendBroadcast(intent);
    }

    public void sendBroadcastForFactoryResetStatus(String desc, int status) {
        Intent intent = new Intent(ACTION_MYCJ_FACTORYRESETSTATUS);
        intent.putExtra(EXTRA_MYCJ_DESC, desc);
        intent.putExtra(EXTRA_MYCJ_STATUS, status);
        context.sendBroadcast(intent);
    }

    public void sendBroadcastForFirmwareVersion(String desc, int code, String version) {
        Intent intent = new Intent(ACTION_MYCJ_FIRMWAREVERSION);
        intent.putExtra(EXTRA_MYCJ_DESC, desc);
        intent.putExtra(EXTRA_MYCJ_FIRMWAREVERSION_CODE, code);
        intent.putExtra(EXTRA_MYCJ_FIRMWAREVERSION, version);
        context.sendBroadcast(intent);
    }

    public void sendBroadcastForHeartRate(int heartRate) {
        Intent intent = new Intent(ACTION_MYCJ_HEARTRATE);
        intent.putExtra(EXTRA_MYCJ_HEARTRATE, heartRate);
        context.sendBroadcast(intent);
    }

    public void sendBroadcastForLoader(int loader,int witch) {
        Intent intent = new Intent(ACTION_MYCJ_LOADER);
        intent.putExtra(EXTRA_MYCJ_LOADER, loader);
        intent.putExtra(EXTRA_MYCJ_WITCH, witch);
        context.sendBroadcast(intent);
    }

    public void sendBroadcastForCurrentMassagerInfo(MycjMassagerInfo info,int witch) {
        Intent intent = new Intent(ACTION_MYCJ_MASSAGERINFO);
        intent.putExtra(EXTRA_MYCJ_WITCH, witch);
        intent.putExtra(EXTRA_MYCJ_MASSAGERINFO, info);
        context.sendBroadcast(intent);
    }

    public void sendBroadcastForPattern(int pattern,int witch) {
        Intent intent = new Intent(ACTION_MYCJ_PATTERN);
        intent.putExtra(EXTRA_MYCJ_WITCH, witch);
        intent.putExtra(EXTRA_MYCJ_PATTERN, pattern);
        context.sendBroadcast(intent);
    }

    public void sendBroadcastForPower(int power,int witch) {
        Intent intent = new Intent(ACTION_MYCJ_POWER);
        intent.putExtra(EXTRA_MYCJ_WITCH, witch);
        intent.putExtra(EXTRA_MYCJ_POWER, power);
        context.sendBroadcast(intent);
    }

    public void sendBroadcastForTime(int leftTime, int settingTime,int witch) {
        Intent intent = new Intent(ACTION_MYCJ_TIME);
        intent.putExtra(EXTRA_MYCJ_WITCH, witch);
        intent.putExtra(EXTRA_MYCJ_TIME, leftTime);
        intent.putExtra(EXTRA_MYCJ_TIME_SETTING, settingTime);
        context.sendBroadcast(intent);
    }

    public void sendBroadcastForTemperature(int temp, int tempUnit) {
        Intent intent = new Intent(ACTION_MYCJ_TEMPERATURE);
        intent.putExtra(EXTRA_MYCJ_TEMPEATURE, temp);
        intent.putExtra(EXTRA_MYCJ_TEMPEATURE_UNIT, tempUnit);
        context.sendBroadcast(intent);
    }

    public void sendBroadcastForError(String desc) {
        Intent intent = new Intent(ACTION_MYCJ_ERROR);
        intent.putExtra(EXTRA_MYCJ_DESC, desc);
        context.sendBroadcast(intent);
    }


    public void sendConfig(int count) {
        Intent intent = new Intent(ACTION_MYCJ_CONFIG);
        intent.putExtra(EXTRA_MYCJ_CONFIG, count);
        context.sendBroadcast(intent);
    }
}
