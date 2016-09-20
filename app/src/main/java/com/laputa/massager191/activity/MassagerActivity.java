package com.laputa.massager191.activity;

import android.animation.ObjectAnimator;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laputa.massager191.R;
import com.laputa.massager191.base.BaseActivity;
import com.laputa.massager191.base.BaseApp;
import com.laputa.massager191.ble.blue.broadcast.LaputaBroadcast;
import com.laputa.massager191.ble.blue.core.SimpleLaputaBlue;
import com.laputa.massager191.ble.blue.util.BondedDeviceUtil;
import com.laputa.massager191.protocol.bean.MycjMassagerInfo;
import com.laputa.massager191.protocol.core.ProtocolBroadcast;
import com.laputa.massager191.protocol.core.ProtocolBroadcastReceiver;
import com.laputa.massager191.util.Constant;
import com.laputa.massager191.util.LogY;
import com.laputa.massager191.util.TimeUtil;
import com.laputa.massager191.util.ToastUtil;
import com.laputa.massager191.view.ColorCircleView;
import com.laputa.massager191.adapter.DeviceAdapter;
import com.laputa.massager191.view.DeviceDialog;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MassagerActivity extends BaseActivity {
   static{ LogY.add(MassagerActivity.class,true);}
    private DeviceDialog chooseBlueADialog;
    private List<BluetoothDevice> devices;
    private DeviceAdapter adapter;
    private ImageView ivLoading;
    private boolean isLoading = false;
    private LinearLayout llTab;
    private FrameLayout frTab;
    private int massagerSize = 3;
    private int pattern = 0;
    /**
     * 取值范围{}0 ,1, 2}
     **/
    private int currentTab = 0;


   /*
    // 沉浸式状态栏
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }*/

    private void chen() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_massager);
        chen();
        massagerSize = BaseApp.count;
        devices = new ArrayList<BluetoothDevice>();
        adapter = new DeviceAdapter(devices, this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (null != bundle) {
            pattern = bundle.getInt("pattern");
        }
        initViews();
        // 更新uI
//		updateUi(blueService != null ? blueService.getMassagerInfo() : null);
        updateUi(getCurrentMassagerInfo());
        updateBleStatus(getBlueService() != null && getBlueService().ifAllConnected());
        registerReceiver();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver();
    }

    private void updateTitle(String title) {
        tvTitle.setText(title);
    }

    private void updateUi(MycjMassagerInfo info) {
        updateTitle("按摩模式 : " + pattern);
        boolean start = false;
        boolean load = false;
        int time = 0;
        int power = 0;

        if (null != info && info.getOpen() == 1 && info.getPattern() == pattern) {
            start = info.getOpen() == 1;
            load = info.getLoader() == 1;
            time = info.getLeftTime();
            power = info.getPower();

        } else {
            start = false;
            load = false;
            time = Constant.DEFAULT_TIME;
            power = Constant.DEFAULT_POWER;
        }

        updateStartOrStop(start);
        updateFuzai(load);
        updateTime(time);
        updatePower(power);
        updateTab(currentTab);
    }

    private MycjMassagerInfo getCurrentMassagerInfo() {
        MycjMassagerInfo info = null;
        if (currentTab == 0) {
            info = BaseApp.info1;
        } else if (currentTab == 1) {
            info = BaseApp.info2;
        } else if (currentTab == 2) {
            info = BaseApp.info3;
        }
        return info;
    }

    private void updatePower(int power) {
        ccPower.setProgress(power - 1);
    }

    private void updateTime(int time) {
        tvTime.setText(TimeUtil.getStringTime(time));
    }

    private void updateBleStatus(boolean connect) {
        if (connect) {
//            tvBleStatus.setText("连接成功");
            tvTitle.setTextColor(Color.WHITE);
            ivBleStatus.setImageResource(R.mipmap.ic_ble_icon_1);
        } else {
//            tvBleStatus.setText("按摩器掉线了...");
            tvTitle.setTextColor(Color.parseColor("#B8B8B8"));
            ivBleStatus.setImageResource(R.mipmap.ic_ble_icon_1_miss);
        }
    }

    private void updateFuzai(boolean load) {
        if (load) {
            ivFuzai.setImageResource(R.mipmap.ic_electload_ok);
        } else {
            ivFuzai.setImageResource(R.mipmap.ic_electload);
        }
    }


    /**
     * 根据当前的tag,更新底部tab的选中状态
     *
     * @param currentTab 当前的tab的tag
     */
    private void updateTab(int currentTab) {
        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            if ((Integer) child.getTag() == currentTab) {
                child.setBackgroundResource(R.drawable.bg_ble_selected_cornon);
            } else {
                child.setBackgroundResource(R.drawable.selector_ble);
            }
        }

    }

    /**
     * 加载底部tag
     */
    private void initTab() {
        frTab = (FrameLayout) findViewById(R.id.fr_tab);
        llTab = new LinearLayout(this);
        llTab.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        llTab.setOrientation(LinearLayout.HORIZONTAL);
        llTab.setWeightSum(massagerSize);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        params.gravity = Gravity.CENTER;
        for (int i = 0; i < massagerSize; i++) {
            TextView tvTab = new TextView(this);
            tvTab.setTag(i);
            tvTab.setGravity(Gravity.CENTER);
            tvTab.setPadding(20, 20, 20, 20);
            tvTab.setTextColor(Color.WHITE);
            tvTab.setBackgroundResource(R.drawable.selector_ble);
            tvTab.setText("按摩器-" + i);
            tvTab.setLayoutParams(params);
            llTab.addView(tvTab);
            Log.e("", "--> " + tvTab.getId());
            tvTab.setClickable(true);
            tvTab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("", "----> " + v.getId());
                    currentTab = (Integer) v.getTag();
                    updateTab(currentTab);
                }
            });
        }
        frTab.addView(llTab);
        updateTab(currentTab);
    }

    private void initViews() {
        initTab();
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivHistory = (ImageView) findViewById(R.id.iv_history);
        ccPower = (ColorCircleView) findViewById(R.id.cc_massager_power);
        ivFuzai = (ImageView) findViewById(R.id.iv_massager_fuzai);
        ivStartOrStop = (ImageView) findViewById(R.id.iv_massager_start_or_stop);
        tvTime = (TextView) findViewById(R.id.tv_massage_time);
//        llBleStatus = (LinearLayout) findViewById(R.id.ll_ble_status);
        ivBleStatus = (ImageView) findViewById(R.id.iv_ble);
        ivLoading = (ImageView) findViewById(R.id.iv_loading);
//        tvBleStatus = (TextView) findViewById(R.id.tv_ble_status);
        // 设置监听
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.iv_back:
                        finish();
                        break;
                    case R.id.iv_history:
//                        mHandler.post(new Runnable() {
//                            @Override
//                            public void run() {
                                startActivity(new Intent(MassagerActivity.this,
                                        HistoryActivity.class));
                                overridePendingTransition(R.anim.massager_out, R.anim.history_in);
//                            }
//                        });


//                        new TestThread(getApplicationContext()).start();
                        break;
                    case R.id.iv_massager_start_or_stop:
                        doStartOrStop(getCurrentMassagerInfo());
                        // TODO: 2016/9/13 开始or结束按摩
                        break;
                 /*   case R.id.ll_ble_status:
                    */
                    case R.id.tv_title:
                        if (SimpleLaputaBlue.isEnable(MassagerActivity.this)) {
                            showBleDeviceDialog();
                        } else {
                            AlertDialog.Builder b = new AlertDialog.Builder(MassagerActivity.this)
                                    .setTitle("提示")
                                    .setMessage("蓝牙不可用,是否打开蓝牙!")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            Intent intent = new Intent();
                                            intent.setAction(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                                            startActivity(intent);
                                        }
                                    })
                                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            AlertDialog dialog = b.create();
                            dialog.show();
                        }
                        break;

                    default:
                        break;
                }
            }

        };
        ivBack.setOnClickListener(listener);
        ivHistory.setOnClickListener(listener);
        ivStartOrStop.setOnClickListener(listener);
        tvTitle.setOnClickListener(listener);
        tvTitle.setLongClickable(true);
        tvTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.e("", "--->     长按操作");
                if (getBlueService() != null && getBlueService().ifAllConnected()) {
                    getBlueService().closeAll();
                }
                BondedDeviceUtil.save(1, "", getApplicationContext());
                updateBleStatus(false);
                return true;
            }
        });
//        llBleStatus.setOnClickListener(listener);
        ccPower.setOnTimePointChangeListener(new ColorCircleView.OnTimePointChangeListener() {
            @Override
            public void onChanging(int progress) {
                ivStartOrStop.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onChanged(int progress) {
                ivStartOrStop.setVisibility(View.VISIBLE);
                if (isLoading) {
                    ToastUtil.showCustomToast(MassagerActivity.this, "正在加载...");
                    return;
                }
                startLoading();
                // TODO: 2016/9/13 切换力度 
                if (getBlueService() != null && getBlueService().ifAllConnected()) {
                    getBlueService().setPower(progress, currentTab);
                }
            }
        });

    }

    private void startAnimation(View v) {
        oa = ObjectAnimator.ofFloat(v, "rotation", 0, 360f);
        oa.setDuration(1500);
        oa.setRepeatCount(-1);
        oa.setInterpolator(new LinearInterpolator());
        oa.start();
    }

    private void stopAnimation() {
        if (oa != null) {
            oa.cancel();
        }
    }

    private void updateStartOrStop(boolean start) {
        if (start) {
            ivStartOrStop.setImageResource(R.mipmap.ic_stop_pressed);
        } else {
            ivStartOrStop.setImageResource(R.mipmap.ic_start);
        }
    }

    /**
     * 开始or结束
     *
     * @param info 当前选择的按摩信息.
     */
    private void doStartOrStop(MycjMassagerInfo info) {
        if (isLoading) {
            ToastUtil.showCustomToast(this, "正在加载...");
            return;
        }
        if (null != info && info.getOpen() == 1 && info.getPattern() == pattern) {
            // start = info.getOpen()==1?true:false;
            // load = info.getLoader()==1?true:false;
            // time = info.getLeftTime();
            // power = info.getPower();
            if (getBlueService() != null && getBlueService().ifAllConnected()) {
                getBlueService().stopMassager(currentTab);
            }
        } else {
            if (getBlueService() != null && getBlueService().ifAllConnected()) {
                int open = 1;
                int leftTime = Constant.DEFAULT_TIME;
                int settingTime = Constant.DEFAULT_TIME;
                int power = Constant.DEFAULT_POWER;
                int temperature = 0;
                int tempUnit = 0;
                int loader = 0;
                int hr = 0;
                info = new MycjMassagerInfo(open, pattern, power, leftTime,
                        settingTime, temperature, tempUnit, loader, hr);
                getBlueService().startMassager(info, currentTab);
            }
            startLoading();
            // updateUi(info);
        }
    }

    private void stopLoading() {
        stopAnimation();
        ivLoading.setVisibility(View.GONE);
//        XLog.e("MassagerActivity", "开始/结束 5秒后~");
        isLoading = false;
        ccPower.setIsCanTouch(true);
    }

    private void test() {


    }

    private void startLoading() {
        isLoading = true;
        startAnimation(ivLoading);
        ivLoading.setVisibility(View.VISIBLE);
        ccPower.setIsCanTouch(false);
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                stopLoading();
                // int open = 1;
                // int leftTime = Constant.DEFAULT_TIME;
                // int settingTime = Constant.DEFAULT_TIME;
                // int power = Constant.DEFAULT_POWER;
                // int temperature = 0;
                // int tempUnit = 0;
                // int loader = 0;
                // int hr = 0;
                // MycjMassagerInfo info = new MycjMassagerInfo(open, pattern,
                // power, leftTime, settingTime, temperature, tempUnit, loader,
                // hr);
                // blueService.startMassager(info);
                // ProtocolManager.newInstance(MassagerActivity.this).sendBroadcastForCurrentMassagerInfo(info);;
            }
        }, Constant.DEFAULT_LOADING_TIME);
    }

    private void showBleDeviceDialog() {
        chooseBlueADialog = new DeviceDialog(MassagerActivity.this)
                .builder(adapter)
                .setOnLeftClickListener(getString(R.string.back),
                        new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                chooseBlueADialog.dismiss();
                            }
                        })
                .setOnRightClickListener(getString(R.string.refresh),
                        new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                devices.clear();
                                adapter.notifyDataSetChanged();
                                getBlueService().startScan();
                            }
                        })
                .setOnButtonClickListener(new DeviceDialog.OnButtonClickListener() {
                    @Override
                    public void onListViewSelected(View v, int position) {
                        BluetoothDevice device = devices.get(position);
                        String address = device.getAddress();
                        /*
                         * if (checkBlueIsAdd(address)) {
						 * Toast.makeText(getApplicationContext(),
						 * getString(R.string.device_added),
						 * Toast.LENGTH_SHORT).show(); return; }
						 */

                        // tvBleA.setText(address);

//                        XLog.e("设置A的 address :" + address);
                        BondedDeviceUtil
                                .save(1, address, MassagerActivity.this);
                        getBlueService().connect(device);

                    }
                });
        chooseBlueADialog.show();
    }

    private Handler mHandler = new Handler() {

    };
    private ProtocolBroadcastReceiver receiverData = new ProtocolBroadcastReceiver() {


        @Override
        protected void onChangeMassagerInfo(final MycjMassagerInfo info) {
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    // TODO: 2016/9/12 更新UI按摩器
                    updateUi(getCurrentMassagerInfo());

                }
            });
        }

        @Override
        protected void onChangeLoader(final int loader, final int witch) {
            super.onChangeLoader(loader, witch);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    // 当当前tab 和 witch 一致时,就更新loader
                    if (witch == currentTab) {
                        updateFuzai(loader == 1);
                    }
                }
            });
        }

        @Override
        protected void onChangePowerCallBack(final int stauts, final int power, final int witch) {
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    if (stauts == 1) {
//                        showCustomToast("改变力度成功");
                        if (witch == currentTab) {
                            updatePower(power);
                            stopLoading();
                        }
                    } else {
//                        showCustomToast("改变力度失败");
                    }

                }
            });
        }

        @Override
        protected void onChangePatternCallBack(final int stauts,
                                               final int pattern, int witch) {
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    if (stauts == 1) {
//                        showCustomToast("改变模式成功");

                    } else {
//                        showCustomToast("改变模式失败");
                    }

                }
            });
        }

        @Override
        protected void onChangeTime(final int time, int timeSetting, final int witch) {
            super.onChangeTime(time, timeSetting, witch);

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (witch == currentTab) {
                        updateTime(time);
                    }
                }
            });
        }

        @Override
        protected void onConfig(final int count) {
            super.onConfig(count);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    currentTab = count;
                    updateUi(getCurrentMassagerInfo());
                }
            });

        }

        @Override
        protected void onChangePower(final int power, final int witch) {
            super.onChangePower(power, witch);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (witch == currentTab) {
                        updatePower(power);
                    }
                }
            });


        }

        @Override
        protected void onChangePattern(int i, int pattern) {
            super.onChangePattern(i, pattern);
        }
    };
    private BroadcastReceiver receiverBle = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, final Intent intent) {
            String action = intent.getAction();
            if (action.equals(LaputaBroadcast.ACTION_LAPUTA_DEVICE_FOUND)) {
                Log.e("", "__________- --___------__________-");
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        BluetoothDevice device = intent
                                .getParcelableExtra(LaputaBroadcast.EXTRA_LAPUTA_DEVICE);
                        if (device != null && !devices.contains(device)) {
                            devices.add(device);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
            } else if (action.equals(LaputaBroadcast.ACTION_LAPUTA_STATE)) {
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        int state = intent.getExtras().getInt(
                                LaputaBroadcast.EXTRA_LAPUTA_STATE);
                        updateBleStatus(state == SimpleLaputaBlue.STATE_SERVICE_DISCOVERED);
                        // 掉线后，清除。
                        updateUi(null);
                      /*  if (state == SimpleLaputaBlue.STATE_SERVICE_DISCOVERED) {
                            updateBleStatus(true);
                        } else {
                            updateBleStatus(false);
                        }*/
                    }

                });
            }
        }
    };

    private void registerReceiver() {
        registerReceiver(receiverBle, LaputaBroadcast.getIntentFilter());
        registerReceiver(receiverData, ProtocolBroadcast.getIntentFilter());
    }

    private void unregisterReceiver() {
        unregisterReceiver(receiverBle);
        unregisterReceiver(receiverData);
    }

    private boolean isCanSweep = false;
    private TextView tvTitle;
    private ImageView ivBack;
    private ImageView ivHistory;
    private ColorCircleView ccPower;
    private ImageView ivFuzai;
    private ImageView ivStartOrStop;
    private TextView tvTime;
    //    private LinearLayout llBleStatus;
    private ImageView ivBleStatus;
    //    private TextView tvBleStatus;
    private ObjectAnimator oa;
}
