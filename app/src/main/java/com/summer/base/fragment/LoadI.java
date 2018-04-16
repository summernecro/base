package com.summer.base.fragment;

//by summer on 2018-04-16.

import android.content.Context;
import android.view.ViewGroup;

public interface LoadI {

    public void onStartLoading(Context activity, String tag) ;

    public void onStopLoading(String tag) ;

    public void startLoading(Context context,ViewGroup viewGroup);

    public void stopLoading(ViewGroup viewGroup);
}
