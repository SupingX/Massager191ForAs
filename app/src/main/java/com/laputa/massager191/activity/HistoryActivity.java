package com.laputa.massager191.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.laputa.massager191.R;
import com.laputa.massager191.adapter.HistoryAdapter;
import com.laputa.massager191.base.BaseActivity;
import com.laputa.massager191.bean.History;
import com.laputa.massager191.db.DbUtil;
import com.laputa.massager191.util.DateUtil;
import com.laputa.massager191.view.AlphaImageView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryActivity extends BaseActivity implements  View.OnClickListener {
    private List<History> historys;
    private ListView lvHistory;
    private HistoryAdapter adapter;
    private Date date;
    private TextView tvDate;
    private AlphaImageView imgPrevious;
    private AlphaImageView imgNext;
    private AlphaImageView imgBack;
    private AlphaImageView imgClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
         historys = new ArrayList<History>();
        initViews();

//        DbUtil.getInstance(this).loadDataForTest();

        lvHistory = (ListView) findViewById(R.id.lv_history);

        adapter = new HistoryAdapter(historys,this);
        lvHistory.setAdapter(adapter);

        loadHistroy();
    }
    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.massager_in, R.anim.history_out);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                overridePendingTransition(R.anim.massager_in, R.anim.history_out);

//                List<History> list =DbUtil.getInstance(getApplicationContext()).find(DateUtil.dateToString(date,"yyyyMM"));
//                List<History> list =DbUtil.getInstance(getApplicationContext()).find(null);
//                Log.e("laputa", "list :" + list);
                break;
            case R.id.img_previous:
                date = DateUtil.getDateOfDiffDay(date, -1);
                updateDate(date);
//                new LoadHistoryAsyncTask().execute(date);
                loadHistroy();
                break;
            case R.id.img_next:
                date = DateUtil.getDateOfDiffDay(date, 1);
                updateDate(date);
//                new LoadHistoryAsyncTask().execute(date);
                loadHistroy();
                break;
            case R.id.img_clear:

//			History h = new History(DateUtil.dateToString(new Date(), "yyyyMMdd hh:mm:ss"), 12, 2);
//			h.save();
//
//			List<History> list = DataSupport.findAll(History.class);
//			Log.i("xpl","list : " +  list);

           /*     clearDialog = new AlertDialog(HistoryActivity.this).builder().setMsg(getString(R.string.check_for_clear_history)).setNegativeButton(getString(R.string.cancel), new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearDialog.dismiss();
                    }
                }).setPositiveButton(getString(R.string.clear), new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        DataSupport.deleteAll(History.class);
                        historys.clear();
                        adapter.notifyDataSetChanged();
                        clearDialog.dismiss();
                    }
                });
                clearDialog.show();*/
                break;
            default:
                break;
        }
    }

    private void initViews() {
        tvDate = (TextView) findViewById(R.id.tv_date);
        imgPrevious = (AlphaImageView) findViewById(R.id.img_previous);
        imgNext = (AlphaImageView) findViewById(R.id.img_next);
        imgBack = (AlphaImageView) findViewById(R.id.img_back);
        imgClear = (AlphaImageView) findViewById(R.id.img_clear);

        date = new Date();
        updateDate(date);
        tvDate.setText(DateUtil.dateToString(date, "yyyy/MM/dd"));
        imgPrevious.setOnClickListener(this);
        imgClear.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        imgNext.setOnClickListener(this);


    }

    private void loadHistroy() {
//		for (int i = 0; i < 100; i++) {
//			historys.add(new History(DateUtil.dateToString(date, "yyyyMMdd hh:mm:ss"), i,new Random().nextInt(5)));
//		}
        new LoadHistoryAsyncTask().execute(date);
    }

    private void updateDate(Date date) {
        tvDate.setText(DateUtil.dateToString(date, "yyyy/MM/dd"));
    }
//    private LoadingDialog loadingDialog;
    private class LoadHistoryAsyncTask extends AsyncTask<Date, Void, List<History>> {
    private ProgressDialog progressDialog;

// 29 30   .

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        /*    loadingDialog = new LoadingDialog(HistoryActivity.this).builder().setCancelable(false)
                    .setCanceledOnTouchOutside(false);
            loadingDialog.show();*/
            progressDialog = getProgressDialog("查询中...");
            
        }

        @Override
        protected List<History> doInBackground(Date... params) {
            Date date = params[0];
//            List<History> list = LitPalManager.instance().getHistiryListByMonth(date);
            List<History> list =DbUtil.getInstance(getApplicationContext()).find(DateUtil.dateToString(date,"yyyyMMdd"));
            Log.e("laputa", "list :" + list);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return list;
        }

        @Override
        protected void onPostExecute(List<History> result) {
//            loadingDialog.dismiss();
            historys.clear();
            if (result != null && result.size() > 0) {
                historys.addAll(result);
            }
            adapter.notifyDataSetChanged();
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            super.onPostExecute(result);
        }
    }
}
