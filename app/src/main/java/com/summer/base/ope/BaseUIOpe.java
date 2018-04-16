package com.summer.base.ope;

//by summer on 2018-04-16.

import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;

import com.summer.base.activity.BaseUIAct;
import com.summer.base.fragment.BaseUIFrag;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

public class BaseUIOpe<A extends ViewDataBinding> extends BaseOpe{

    private A bind;
    private BaseUIAct baseUIAct;
    private BaseUIFrag baseUIFrag;
    private View view;


    /**无参构造函数*/
    public BaseUIOpe() {

    }

    /**唯一回调函数*/
    public void initUI(){

    }


    public void init(BaseUIAct baseUIAct) {
        this.baseUIAct = baseUIAct;
        bind = initViewDataBinding();
        bind.executePendingBindings();
    }

    public void init(BaseUIFrag frag) {
        this.baseUIFrag = frag;
        this.baseUIAct = frag.getBaseUIAct();
        bind = initViewDataBinding();
        bind.executePendingBindings();
    }

    public void copy(BaseUIOpe baseUIOpe){
        this.baseUIAct = baseUIOpe.baseUIAct;
        this.baseUIFrag = baseUIOpe.baseUIFrag;
        this.bind = (A) baseUIOpe.bind;
        initUI();
    }


    public A initViewDataBinding() {
        A viewDataBinding = null;
        if (viewDataBinding == null) {
            if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
                Class<A> a = (Class<A>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                Method method = null;
                try {
                    method = a.getMethod("inflate", LayoutInflater.class);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                try {
                    viewDataBinding = (A) method.invoke(null, LayoutInflater.from(baseUIAct));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } else {
                viewDataBinding = getBind();
            }
        }
        return viewDataBinding;
    }


    public A getBind() {
        return bind;
    }


    public BaseUIFrag getBaseUIFrag() {
        return baseUIFrag;
    }

    public BaseUIAct getBaseUIAct() {
        if(baseUIFrag!=null ){
            return baseUIFrag.getBaseUIAct();
        }else{
            return baseUIAct;
        }
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
