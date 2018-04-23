package com.summer.base.adpater;

//by summer on 2017-06-20.

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.summer.base.R;
import com.summer.base.listener.other.ViewListener;

import java.util.List;

public class AppsDataBindingAdapter extends RecyclerView.Adapter<AppViewHolder> implements View.OnClickListener, View.OnLongClickListener {


    protected List list;
    protected int vari;
    protected int layout;
    protected ViewListener viewListener;
    protected Context context;
    protected int selecPos = -1;


    public AppsDataBindingAdapter(Context context, int layout, int vari, List list) {
        this.context = context;
        this.layout = layout;
        this.vari = vari;
        this.list = list;
    }

    public AppsDataBindingAdapter(Context context, int layout, int vari, List list, ViewListener viewListener) {
        this.context = context;
        this.layout = layout;
        this.vari = vari;
        this.list = list;
        this.viewListener = viewListener;
    }

    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AppViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layout, parent, false));
    }

    @Override
    public void onBindViewHolder(AppViewHolder holder, int position) {
        ViewDataBinding viewDataBinding = holder.viewDataBinding;
        viewDataBinding.getRoot().setTag(R.id.data, list.get(position));
        viewDataBinding.getRoot().setTag(R.id.position, position);
        viewDataBinding.getRoot().setOnClickListener(this);
        viewDataBinding.getRoot().setOnLongClickListener(this);
        viewDataBinding.setVariable(vari, list.get(position));
        viewDataBinding.executePendingBindings();//加一行，问题解决
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public void onClick(View v) {
        if (viewListener != null) {
            viewListener.onInterupt(ViewListener.TYPE_ONCLICK, v);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (viewListener != null) {
            viewListener.onInterupt(ViewListener.TYPE_ONLONGCLICK, v);
        }
        return true;
    }

    public void setTag(View view,int pos){
        view.setOnClickListener(this);
        view.setTag(R.id.data,list.get(pos));
        view.setTag(R.id.position,pos);

    }

    public void setViewListener(ViewListener viewListener) {
        this.viewListener = viewListener;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
