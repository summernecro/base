package com.summer.base.adpater;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;


import com.summer.base.listener.other.OnFinishI;

import java.util.ArrayList;

public class HomePageAdapter extends PagerAdapter {


    Context context;

    ArrayList<View> views;

    OnFinishI onFinishListener;

    private boolean load = false;

    public HomePageAdapter(Context context, ArrayList<View> views, OnFinishI onFinishListener){
        this.context =context;
        this.views = views;
        this.onFinishListener = onFinishListener;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        if(position==views.size()-1 && onFinishListener!=null){
            if(!load){
                onFinishListener.onFnish(null);
                load= true;
            }

        }
        return views.get(position);
    }


}