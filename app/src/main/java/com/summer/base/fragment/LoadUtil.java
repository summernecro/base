package com.summer.base.fragment;

//import com.summer.imga.R;
//import com.victor.loading.rotate.RotateLoading;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;


import java.util.ArrayList;

/**
 * Created by ${viwmox} on 2016-05-10.
 */
public class LoadUtil implements LoadI{


    private static LoadUtil instance;

    public static LoadUtil getInstance() {
        if (instance == null) {
            instance = new LoadUtil();
        }
        return instance;
    }

    @Override
    public void onStartLoading(Context activity, String tag) {

    }

    @Override
    public void onStopLoading(String tag) {

    }

    @Override
    public void startLoading(Context context, ViewGroup viewGroup) {

    }

    @Override
    public void stopLoading(ViewGroup viewGroup) {

    }
}
