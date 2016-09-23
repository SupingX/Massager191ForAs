package com.laputa.massager191.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

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

    public ProgressDialog getProgressDialog(String msg){
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置进度条的形式为圆形转动的进度条
        dialog.setCancelable(true);// 设置是否可以通过点击Back键取消
        dialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
//        dialog.setIcon(R.mipmap.ic_launcher);//
        // 设置提示的title的图标，默认是没有的，如果没有设置title的话只设置Icon是不会显示图标的
//        dialog.setTitle("提示");
        // dismiss监听

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialog) {
                // TODO Auto-generated method stub

            }
        });

        // 监听Key事件被传递给dialog
//        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//
//            @Override
//            public boolean onKey(DialogInterface dialog, int keyCode,
//                                 KeyEvent event) {
//                // TODO Auto-generated method stub
//                return false;
//            }
//        });
        // 监听cancel事件
//        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//
//            @Override
//            public void onCancel(DialogInterface dialog) {
//                // TODO Auto-generated method stub
//
//            }
//        });
        //设置可点击的按钮，最多有三个(默认情况下)
//        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
//                new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // TODO Auto-generated method stub
//
//                    }
//                });
//        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
//                new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // TODO Auto-generated method stub
//
//                    }
//                });
//        dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "中立",
//                new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // TODO Auto-generated method stub
//
//                    }
//                });
        dialog.setMessage(msg);
        dialog.show();
        return dialog;
    }
}
