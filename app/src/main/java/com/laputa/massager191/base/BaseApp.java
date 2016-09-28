package com.laputa.massager191.base;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.laputa.massager191.bean.Pattern;
import com.laputa.massager191.ble.blue.util.SharedPreferenceUtil;
import com.laputa.massager191.protocol.bean.MycjMassagerInfo;
import com.laputa.massager191.service.BlueService;
import com.laputa.massager191.util.Laputa;

import java.util.Random;

/**
 *  按摩器191
 *
 */
public class BaseApp extends Application{
    private final boolean isDebug;
    public static final String SHARE_COUNT = "SHARE_COUNT";
    public static final int DEFAULT_MASSAGER_COUNT = 3;

    {
        isDebug = true;
    }
    private static BlueService blueService ;
    public static int count = 2;
    public static MycjMassagerInfo info1 = null;
    public static MycjMassagerInfo info2 =  null;
    public static MycjMassagerInfo info3 =  null;
    public static boolean  isInfo1Start =  false;
    public static boolean  isInfo2Start =  false;
    public static boolean  isInfo3Start =  false;


    public static BlueService getBlueService(){
        Laputa.e("laputa","--> getBlueService() : " + blueService);
        return blueService;
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if (service instanceof BlueService.BlueBinder){
                BlueService.BlueBinder binder = (BlueService.BlueBinder) service;
                blueService = binder.getXBlueService();
//                e("--> 获取blueService : " + blueService);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            blueService = null;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        e("=============BaseApp.onCreate()==============");



        // 获取按摩器定制的个数 默认为3个
        count = (int) Laputa.get(this,SHARE_COUNT,DEFAULT_MASSAGER_COUNT);

        Intent blueIntent = new Intent(this,BlueService.class);
        bindService(blueIntent,conn, Context.BIND_AUTO_CREATE);
    }

    public static void test(){
        info1 = new MycjMassagerInfo(1,randomPattern(),1,1,1,1,1,1,1);
        info2 = new MycjMassagerInfo(1,randomPattern(),1,1,1,1,1,1,1);
        info3 = new MycjMassagerInfo(1,randomPattern(),1,1,1,1,1,1,1);
    }

    private static int randomPattern(){
        Pattern[] values = Pattern.values();
        Random r = new Random();
       return values[ r.nextInt(values.length)].code;
    }
    private void e(String msg){
        if (isDebug){
            Laputa.e("laputa",msg);
        }
    }


    public static void showInfo() {
        Laputa.e("BaseApp","=============info1 : "+isInfo1Start+"=============="+info1);
        Laputa.e("BaseApp","=============info2 : "+isInfo2Start+"=============="+info2);
        Laputa.e("BaseApp","=============info3 : "+isInfo3Start+"=============="+info3);
    }

    public static void clear(){
        if (info1 != null) {
            info1 = null;
        }
        if (info2 != null) {
            info2 = null;
        }
        if (info3 != null) {
            info3 = null;
        }
    }
}
