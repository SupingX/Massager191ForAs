package com.laputa.massager191.base;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.laputa.massager191.protocol.bean.MycjMassagerInfo;
import com.laputa.massager191.service.BlueService;

/**
 *  按摩器191
 *
 */
public class BaseApp extends Application{
    private final boolean isDebug;

    {
        isDebug = true;
    }
    private static BlueService blueService ;
    public static int count = 2;
    public static MycjMassagerInfo info1 = new MycjMassagerInfo(1,0x11,1,1,1,1,1,1,1);
    public static MycjMassagerInfo info2;
    public static MycjMassagerInfo info3;

    public static BlueService getBlueService(){
        Log.e("laputa","--> getBlueService() : " + blueService);
        return blueService;
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if (service instanceof BlueService.BlueBinder){
                BlueService.BlueBinder binder = (BlueService.BlueBinder) service;
                blueService = binder.getXBlueService();
                e("--> 获取blueService : " + blueService);
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
        Intent blueIntent = new Intent(this,BlueService.class);
        bindService(blueIntent,conn, Context.BIND_AUTO_CREATE);
    }

    private void e(String msg){
        if (isDebug){
            Log.e("laputa",msg);
        }
    }


    public static void showInfo() {
        Log.e("BaseApp","=============info1=============="+info1);
        Log.e("BaseApp","=============info2=============="+info2);
        Log.e("BaseApp","=============info3=============="+info3);
    }
}
