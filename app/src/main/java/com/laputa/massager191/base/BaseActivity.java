package com.laputa.massager191.base;

import android.support.v7.app.AppCompatActivity;

import com.laputa.massager191.R;
import com.laputa.massager191.dialog.AbstractLaputaDialog;
import com.laputa.massager191.dialog.LaputaAlertDialog;
import com.laputa.massager191.service.BlueService;

/**
 * Created by Administrator on 2016/9/8.
 */
public class BaseActivity  extends AppCompatActivity {

    public BlueService getBlueService(){
        return BaseApp.getBlueService();
    }

    public boolean isBleEnable(){
        return true;
    }

    public AbstractLaputaDialog showAlertDialog(String msg){
        AbstractLaputaDialog dialog = new LaputaAlertDialog(this, R.layout.view_laputa_alert_dialog)
                .builder()
                .setCancelable(true)
//    	.setCanceledOnTouchOutside(true)
                .setMsg(msg)
                ;
        dialog.show();
        return dialog;
    }
}
