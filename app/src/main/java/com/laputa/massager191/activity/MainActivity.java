package com.laputa.massager191.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;

import com.laputa.massager191.R;
import com.laputa.massager191.base.BaseApp;
import com.laputa.massager191.bean.Pattern;
import com.laputa.massager191.ble.blue.broadcast.LaputaBroadcast;
import com.laputa.massager191.ble.blue.core.SimpleLaputaBlue;
import com.laputa.massager191.protocol.bean.MycjMassagerInfo;
import com.laputa.massager191.protocol.core.ProtocolBroadcast;
import com.laputa.massager191.protocol.core.ProtocolBroadcastReceiver;
import com.laputa.massager191.util.Laputa;


public class MainActivity extends AppCompatActivity {
    //今夜月明人尽望,不知秋思落谁家.

    private boolean isScaling = false;
    private int pattern;
    private static Handler mHandler = new Handler() {
    };
    private RelativeLayout rlPattern_01;
    private RelativeLayout rlPattern_02;
    private RelativeLayout rlPattern_03;
    private RelativeLayout rlPattern_04;
    private RelativeLayout rlPattern_05;
    private RelativeLayout rlPattern_06;
    private RelativeLayout rlPattern_07;
    private RelativeLayout rlPattern_08;
    private RelativeLayout rlPattern_09;
    private RelativeLayout rlPattern_10;
    private RelativeLayout rlPattern_11;
    private RelativeLayout rlPattern_12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);
        initViews();

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver();
//        initMassagering();
        initMassageringWithColor();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver();
    }

    private int getResult(int a, int b , int c){
        int status =0b000;
        Laputa.e("status : "+status );
        status ^=a;
        Laputa.e("status : "+status );
        status ^=b;
        Laputa.e("status : "+status );
        status ^=c;
        Laputa.e("status : "+status );
        return status;
    }
    /**
     *  更新那个按摩模式下 有按摩器正在按摩.
     *
     */
    private void initMassagering() {
        clear();
        if (
                (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_01.code)
                        || (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_01.code)
                        || (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_01.code
                )) {
            rlPattern_01.setBackgroundResource(R.drawable.bg_massagering_011);

        }
        if (
                (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_02.code)
                        || (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_02.code)
                        || (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_02.code
                )) {
            rlPattern_02.setBackgroundResource(R.drawable.bg_massagering_011);
        }
        if (
                (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_03.code)
                        || (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_03.code)
                        || (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_03.code
                )) {
            rlPattern_03.setBackgroundResource(R.drawable.bg_massagering_011);
        }
        if (
                (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_04.code)
                        || (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_04.code)
                        || (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_04.code
                )) {
            rlPattern_04.setBackgroundResource(R.drawable.bg_massagering_011);
        }

        if (
                (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_05.code)
                        || (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_05.code)
                        || (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_05.code
                )) {
            rlPattern_05.setBackgroundResource(R.drawable.bg_massagering_011);
        }
        if (
                (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_06.code)
                        || (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_06.code)
                        || (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_06.code
                )) {
            rlPattern_06.setBackgroundResource(R.drawable.bg_massagering_011);
        }
        if (
                (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_07.code)
                        || (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_07.code)
                        || (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_07.code
                )) {
            rlPattern_07.setBackgroundResource(R.drawable.bg_massagering_011);
        }
        if (
                (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_08.code)
                        || (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_08.code)
                        || (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_08.code
                )) {
            rlPattern_08.setBackgroundResource(R.drawable.bg_massagering_011);
        }
        if (
                (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_09.code)
                        || (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_09.code)
                        || (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_09.code
                )) {
            rlPattern_09.setBackgroundResource(R.drawable.bg_massagering_011);
        }
        if (
                (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_10.code)
                        || (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_10.code)
                        || (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_10.code
                )) {
            rlPattern_10.setBackgroundResource(R.drawable.bg_massagering_011);
        }
        if (
                (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_11.code)
                        || (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_11.code)
                        || (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_11.code
                )) {
            rlPattern_11.setBackgroundResource(R.drawable.bg_massagering_011);
        }
        if (
                (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_12.code)
                        || (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_12.code)
                        || (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_12.code
                )) {
            rlPattern_12.setBackgroundResource(R.drawable.bg_massagering_011);
        }
    }

    private int getMassageringColor(int status){
        switch (status){
            case 0b000:
                return R.drawable.bg_massagering_000;
            case 0b001:
                // 红
                return R.drawable.bg_massagering_001;
            case 0b010:
                // 绿
                return R.drawable.bg_massagering_010;
            case 0b100:
                // 蓝
                return R.drawable.bg_massagering_100;
            case 0b011:
                // 红+绿
                return R.drawable.bg_massagering_011;
            case 0b101:
                // 红+蓝
                return R.drawable.bg_massagering_101;
            case 0b110:
                // 绿+蓝
                return R.drawable.bg_massagering_110;
            case 0b111:
                // 红+绿+蓝
                return R.drawable.bg_massagering_111;
        }
        return R.drawable.bg_massagering_000;
    }

    private void initMassageringWithColor() {
        clear();
        if (
                (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_01.code)
                        || (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_01.code)
                        || (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_01.code
                )) {
            int a = (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_01.code)?0b001:0b000;
            int b = (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_01.code)?0b010:0b000;
            int c = (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_01.code)?0b100:0b000;
            int status = getResult( a, b, c);
            rlPattern_01.setBackgroundResource(getMassageringColor(status));

        }
        if (
                (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_02.code)
                        || (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_02.code)
                        || (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_02.code
                )) {
            int a = (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_02.code)?0b001:0b000;
            int b = (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_02.code)?0b010:0b000;
            int c = (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_02.code)?0b100:0b000;
            int status = getResult( a, b, c);
            rlPattern_02.setBackgroundResource(getMassageringColor(status));
        }
        if (
                (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_03.code)
                        || (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_03.code)
                        || (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_03.code
                )) {
            int a = (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_03.code)?0b001:0b000;
            int b = (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_03.code)?0b010:0b000;
            int c = (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_03.code)?0b100:0b000;
            int status = getResult( a, b, c);
            rlPattern_03.setBackgroundResource(getMassageringColor(status));
        }
        if (
                (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_04.code)
                        || (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_04.code)
                        || (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_04.code
                )) {
            int a = (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_04.code)?0b001:0b000;
            int b = (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_04.code)?0b010:0b000;
            int c = (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_04.code)?0b100:0b000;
            int status = getResult( a, b, c);
            rlPattern_04.setBackgroundResource(getMassageringColor(status));
        }

        if (
                (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_05.code)
                        || (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_05.code)
                        || (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_05.code
                )) {
            int a = (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_05.code)?0b001:0b000;
            int b = (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_05.code)?0b010:0b000;
            int c = (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_05.code)?0b100:0b000;
            int status = getResult( a, b, c);
            rlPattern_05.setBackgroundResource(getMassageringColor(status));
        }
        if (
                (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_06.code)
                        || (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_06.code)
                        || (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_06.code
                )) {
            int a = (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_06.code)?0b001:0b000;
            int b = (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_06.code)?0b010:0b000;
            int c = (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_06.code)?0b100:0b000;
            int status = getResult( a, b, c);
            rlPattern_06.setBackgroundResource(getMassageringColor(status));
        }
        if (
                (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_07.code)
                        || (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_07.code)
                        || (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_07.code
                )) {
            int a = (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_07.code)?0b001:0b000;
            int b = (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_07.code)?0b010:0b000;
            int c = (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_07.code)?0b100:0b000;
            int status = getResult( a, b, c);
            rlPattern_07.setBackgroundResource(getMassageringColor(status));
        }
        if (
                (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_08.code)
                        || (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_08.code)
                        || (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_08.code
                )) {
            int a = (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_08.code)?0b001:0b000;
            int b = (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_08.code)?0b010:0b000;
            int c = (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_08.code)?0b100:0b000;
            int status = getResult( a, b, c);
            rlPattern_08.setBackgroundResource(getMassageringColor(status));
        }
        if (
                (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_09.code)
                        || (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_09.code)
                        || (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_09.code
                )) {
            int a = (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_09.code)?0b001:0b000;
            int b = (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_09.code)?0b010:0b000;
            int c = (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_09.code)?0b100:0b000;
            int status = getResult( a, b, c);
            rlPattern_09.setBackgroundResource(getMassageringColor(status));
        }
        if (
                (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_10.code)
                        || (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_10.code)
                        || (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_10.code
                )) {
            int a = (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_10.code)?0b001:0b000;
            int b = (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_10.code)?0b010:0b000;
            int c = (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_10.code)?0b100:0b000;
            int status = getResult( a, b, c);
            rlPattern_10.setBackgroundResource(getMassageringColor(status));
        }
        if (
                (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_11.code)
                        || (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_11.code)
                        || (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_11.code
                )) {
            int a = (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_11.code)?0b001:0b000;
            int b = (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_11.code)?0b010:0b000;
            int c = (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_11.code)?0b100:0b000;
            int status = getResult( a, b, c);
            rlPattern_11.setBackgroundResource(getMassageringColor(status));
        }
        if (
                (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_12.code)
                        || (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_12.code)
                        || (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_12.code
                )) {
            int a = (BaseApp.info1 != null && BaseApp.info1.getOpen() == 1 && BaseApp.info1.getPattern() == Pattern.Pattern_12.code)?0b001:0b000;
            int b = (BaseApp.info2 != null && BaseApp.info2.getOpen() == 1 && BaseApp.info2.getPattern() == Pattern.Pattern_12.code)?0b010:0b000;
            int c = (BaseApp.info3 != null && BaseApp.info3.getOpen() == 1 && BaseApp.info3.getPattern() == Pattern.Pattern_12.code)?0b100:0b000;
            int status = getResult( a, b, c);
            rlPattern_12.setBackgroundResource(getMassageringColor(status));
        }

    }

    private void clear() {
        rlPattern_01.setBackgroundResource(R.drawable.bg_massagering_000);
        rlPattern_02.setBackgroundResource(R.drawable.bg_massagering_000);
        rlPattern_03.setBackgroundResource(R.drawable.bg_massagering_000);
        rlPattern_04.setBackgroundResource(R.drawable.bg_massagering_000);
        rlPattern_05.setBackgroundResource(R.drawable.bg_massagering_000);
        rlPattern_06.setBackgroundResource(R.drawable.bg_massagering_000);
        rlPattern_07.setBackgroundResource(R.drawable.bg_massagering_000);
        rlPattern_08.setBackgroundResource(R.drawable.bg_massagering_000);
        rlPattern_09.setBackgroundResource(R.drawable.bg_massagering_000);
        rlPattern_10.setBackgroundResource(R.drawable.bg_massagering_000);
        rlPattern_11.setBackgroundResource(R.drawable.bg_massagering_000);
        rlPattern_12.setBackgroundResource(R.drawable.bg_massagering_000);
    }


    private void initViews() {
        rlPattern_01 = (RelativeLayout) findViewById(R.id.rl_pattern_01);
        rlPattern_02 = (RelativeLayout) findViewById(R.id.rl_pattern_02);
        rlPattern_03 = (RelativeLayout) findViewById(R.id.rl_pattern_03);
        rlPattern_04 = (RelativeLayout) findViewById(R.id.rl_pattern_04);
        rlPattern_05 = (RelativeLayout) findViewById(R.id.rl_pattern_05);
        rlPattern_06 = (RelativeLayout) findViewById(R.id.rl_pattern_06);
        rlPattern_07 = (RelativeLayout) findViewById(R.id.rl_pattern_07);
        rlPattern_08 = (RelativeLayout) findViewById(R.id.rl_pattern_08);
        rlPattern_09 = (RelativeLayout) findViewById(R.id.rl_pattern_09);
        rlPattern_10 = (RelativeLayout) findViewById(R.id.rl_pattern_10);
        rlPattern_11 = (RelativeLayout) findViewById(R.id.rl_pattern_11);
        rlPattern_12 = (RelativeLayout) findViewById(R.id.rl_pattern_12);
        View.OnClickListener patternListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!isScaling) {
                    startAnimation(v);
                }
            }
        };
        rlPattern_01.setOnClickListener(patternListener);
        rlPattern_02.setOnClickListener(patternListener);
        rlPattern_03.setOnClickListener(patternListener);
        rlPattern_04.setOnClickListener(patternListener);
        rlPattern_05.setOnClickListener(patternListener);
        rlPattern_06.setOnClickListener(patternListener);
        rlPattern_07.setOnClickListener(patternListener);
        rlPattern_08.setOnClickListener(patternListener);
        rlPattern_09.setOnClickListener(patternListener);
        rlPattern_10.setOnClickListener(patternListener);
        rlPattern_11.setOnClickListener(patternListener);
        rlPattern_12.setOnClickListener(patternListener);
    }


    private void startAnimation(View v) {
        pattern = 0;
        switch (v.getId()) {
            case R.id.rl_pattern_01:
//                pattern = 0b00010001;
                pattern = 0x11;
                break;
            case R.id.rl_pattern_02:
//                pattern = 0b00010010;
                pattern = 0x12;
                break;
            case R.id.rl_pattern_03:
                pattern = 0x13;
//                pattern = 0b00010011;
                break;
            case R.id.rl_pattern_04:
                pattern = 0x14;
//                pattern = 0b00100001;
                break;
            case R.id.rl_pattern_05:
                pattern = 0x15;
//                pattern = 0b00100010;
                break;
            case R.id.rl_pattern_06:
                pattern = 0x17;
//                pattern = 0b00100011;
                break;
            case R.id.rl_pattern_07:
                pattern = 0x23;
//                pattern = 0b00110001;
                break;
            case R.id.rl_pattern_08:
                pattern = 0x49;
//                pattern = 0b00110010;
                break;
            case R.id.rl_pattern_09:
                pattern = 0x31;
//                pattern = 0b00110011;
                break;
            case R.id.rl_pattern_10:
                pattern = 0x34;
//                pattern = 0b01000001;
                break;
            case R.id.rl_pattern_11:
                pattern = 0x36;
//                pattern = 0b01000010;
                break;
            case R.id.rl_pattern_12:
                pattern = 0x42;
//                pattern = 0b01000011;
                break;
        }
        isScaling = true;
        ObjectAnimator oaX = ObjectAnimator.ofFloat(v, "scaleX", 1f, 0.8f, 1f);
        ObjectAnimator oaY = ObjectAnimator.ofFloat(v, "scaleY", 1f, 0.8f, 1f);
        oaX.setDuration(500);
        oaX.setInterpolator(new OvershootInterpolator());
        oaY.setDuration(500);
        oaY.setInterpolator(new OvershootInterpolator());
        AnimatorSet set = new AnimatorSet();
        set.playTogether(oaX, oaY);
        set.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isScaling = false;
                gotoMassager();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isScaling = false;
            }
        });
        set.start();
    }

    private void registerReceiver() {
        registerReceiver(receiverBle, LaputaBroadcast.getIntentFilter());
        registerReceiver(receiverData, ProtocolBroadcast.getIntentFilter());
    }

    private void unregisterReceiver() {
        unregisterReceiver(receiverBle);
        unregisterReceiver(receiverData);
    }

    private void gotoMassager() {
        Laputa.e("laputa", "laputa :" + pattern);
        Intent intent = new Intent(MainActivity.this, MassagerActivity.class);
        Bundle b = new Bundle();
        b.putInt("pattern", pattern);
        intent.putExtras(b);
        startActivity(intent);
    }

    private long lastTime = 0L;
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastTime > 2000){
            lastTime = currentTime;
            return ;
        }
        if (BaseApp.getBlueService() != null && BaseApp.getBlueService().ifAllConnected()) {
            BaseApp.getBlueService().closeAll();
        }
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 1000);
//        BaseApp.test();
//        initMassageringWithColor();

    }


    private ProtocolBroadcastReceiver receiverData = new ProtocolBroadcastReceiver() {
        @Override
        protected void onChangeMassagerInfo(final MycjMassagerInfo info, int witch) {
            super.onChangeMassagerInfo(info, witch);
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    initMassageringWithColor();
                }
            });
        }
    };

    private BroadcastReceiver receiverBle = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, final Intent intent) {
            String action = intent.getAction();
           if (action.equals(LaputaBroadcast.ACTION_LAPUTA_STATE)) {
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        int state = intent.getExtras().getInt(
                                LaputaBroadcast.EXTRA_LAPUTA_STATE);
                        // 掉线后，清除。
                        if (state != SimpleLaputaBlue.STATE_SERVICE_DISCOVERED){
                            BaseApp.clear();
                            initMassageringWithColor();
                        }

                      /*
                        if (state == SimpleLaputaBlue.STATE_SERVICE_DISCOVERED) {
                            updateBleStatus(true);
                        } else {
                            updateBleStatus(false);
                        }
                     */


//                        just so so
                    }

                });
            }
        }
    };
}
