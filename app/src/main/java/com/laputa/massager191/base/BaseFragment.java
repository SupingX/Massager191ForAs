package com.laputa.massager191.base;

import android.support.v4.app.Fragment;

import com.laputa.massager191.service.BlueService;

/**
 * Created by Administrator on 2016/9/8.
 */
public class BaseFragment extends Fragment {
    public BlueService getBlueService(){
        return BaseApp.getBlueService();
    }



}
