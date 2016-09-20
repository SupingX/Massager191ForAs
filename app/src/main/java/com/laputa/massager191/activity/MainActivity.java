package com.laputa.massager191.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;

import com.laputa.massager191.R;
import com.laputa.massager191.bean.History;
import com.laputa.massager191.db.DbUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //今夜月明人尽望,不知秋思落谁家.

    private boolean isScaling = false;
    private int pattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }
    private void initViews() {
        RelativeLayout rlPattern_01 = (RelativeLayout) findViewById(R.id.rl_pattern_01);
        RelativeLayout rlPattern_02 = (RelativeLayout) findViewById(R.id.rl_pattern_02);
        RelativeLayout rlPattern_03 = (RelativeLayout) findViewById(R.id.rl_pattern_03);
        RelativeLayout rlPattern_04 = (RelativeLayout) findViewById(R.id.rl_pattern_04);
        RelativeLayout rlPattern_05 = (RelativeLayout) findViewById(R.id.rl_pattern_05);
        RelativeLayout rlPattern_06 = (RelativeLayout) findViewById(R.id.rl_pattern_06);
        RelativeLayout rlPattern_07 = (RelativeLayout) findViewById(R.id.rl_pattern_07);
        RelativeLayout rlPattern_08 = (RelativeLayout) findViewById(R.id.rl_pattern_08);
        RelativeLayout rlPattern_09 = (RelativeLayout) findViewById(R.id.rl_pattern_09);
        RelativeLayout rlPattern_10 = (RelativeLayout) findViewById(R.id.rl_pattern_10);
        RelativeLayout rlPattern_11 = (RelativeLayout) findViewById(R.id.rl_pattern_11);
        RelativeLayout rlPattern_12 = (RelativeLayout) findViewById(R.id.rl_pattern_12);
        View.OnClickListener patternListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!isScaling) {
                    startAnimation(v);
                }
            }
        };
        // 带着未来的憧憬来到了另个陌生城市...
        //
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


    private void startAnimation(View v){
        pattern = 0;
        switch (v.getId()) {
            case R.id.rl_pattern_01:
                pattern = 0b00010001;
                break;
            case R.id.rl_pattern_02:
                pattern = 0b00010010;
                break;
            case R.id.rl_pattern_03:
                pattern = 0b00010011;
                break;
            case R.id.rl_pattern_04:
                pattern = 0b00100001;
                break;
            case R.id.rl_pattern_05:
                pattern = 0b00100010;
                break;
            case R.id.rl_pattern_06:
                pattern = 0b00100011;
                break;
            case R.id.rl_pattern_07:
                pattern = 0b00110001;
                break;
            case R.id.rl_pattern_08:
                pattern = 0b00110010;
                break;
            case R.id.rl_pattern_09:
                pattern = 0b00110011;
                break;
            case R.id.rl_pattern_10:
                pattern = 0b01000001;
                break;
            case R.id.rl_pattern_11:
                pattern = 0b01000010;
                break;
            case R.id.rl_pattern_12:
                pattern = 0b01000011;
                break;
        }
        isScaling = true;
        ObjectAnimator oaX = ObjectAnimator.ofFloat(v, "scaleX", 1f,0.8f,1f);
        ObjectAnimator oaY = ObjectAnimator.ofFloat(v, "scaleY", 1f,0.8f,1f);
        oaX.setDuration(500);
        oaX.setInterpolator(new OvershootInterpolator());
        oaY.setDuration(500);
        oaY.setInterpolator(new OvershootInterpolator());
        AnimatorSet set = new AnimatorSet();
        set.playTogether(oaX,oaY);
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

    private void gotoMassager(){
        Log.e("laputa","laputa :" + pattern);
        Intent intent = new Intent(MainActivity.this,MassagerActivity.class);
        Bundle b = new Bundle();
        b.putInt("pattern", pattern);

        intent.putExtras(b);
        startActivity(intent);


    }

}
