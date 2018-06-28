package com.summer.demo;

//by summer on 2018-04-16.

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.summer.base.activity.BaseUIAct;
import com.summer.base.fragment.manager.FragM;
import com.summer.demo.databinding.ActMainBinding;


public class MainAct extends BaseUIAct<MainUIOpe,MainDAOpe,MainValue> {

    @Override
    protected void initNow() {
        super.initNow();
        FragM.getInstance().clear();
        FragM.getInstance().start(getActivity(),"main",R.id.ll_root,new MainFrag());
    }

    @Override
    public void onBackPressed() {
        FragM.getInstance().finish(getActivity(),"main",false);
    }
}
