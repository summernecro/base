package com.summer.base.activity;

//by summer on 2018-04-16.

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.summer.base.R;
import com.summer.base.ope.BaseDAOpe;
import com.summer.base.ope.BaseOpes;
import com.summer.base.ope.BaseUIOpe;
import com.summer.base.ope.BaseValue;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

import butterknife.ButterKnife;

public class BaseUIAct <A extends BaseUIOpe, B extends BaseDAOpe,C extends BaseValue> extends AppCompatActivity {

    protected ViewGroup baseUIRoot;

    protected BaseOpes<A, B,C> opes;

    private String moudle;

    private ArrayList<String> moudles = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getBaseUILayout());
        baseUIRoot = findViewById(R.id.act_base_root);
        if(getP().getU().getBind()!=null){
            baseUIRoot.addView(getP().getU().getBind().getRoot(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        getP().getD().initDA();
        getP().getU().initUI();
        initNow();
        ButterKnife.bind(getActivity());
        if(registerEventBus()){
            EventBus.getDefault().register(this);
        }
    }

    protected void initNow(){

    }

    protected int getBaseUILayout() {
        return R.layout.act_baseui;
    }


    public BaseOpes<A, B,C> getP() {
        if (opes == null) {
            opes= new BaseOpes<>(null,null,null);
            initcc(getClass());
            initaa(getClass());
            initbb(getClass());
        }
        return opes;
    }

    private void initcc(Class<?> c) {
        if (c == null) {
            opes.setVa((C)(new BaseValue()));
            return;
        }
        if (c.getGenericSuperclass() instanceof ParameterizedType) {
            Class<C> b = (Class<C>) ((ParameterizedType) c.getGenericSuperclass()).getActualTypeArguments()[2];
            try {
                Constructor<C> bc = b.getConstructor();
                C cc = bc.newInstance();
                opes.setVa(cc);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            initcc(c.getSuperclass());
        }
    }


    private void initbb(Class<?> c) {
        if (c == null) {
            opes.setDa((B)(new BaseDAOpe()));
            return;
        }
        if (c.getGenericSuperclass() instanceof ParameterizedType) {
            Class<B> b = (Class<B>) ((ParameterizedType) c.getGenericSuperclass()).getActualTypeArguments()[1];
            try {
                Constructor<B> bc = b.getConstructor();
                B bb = bc.newInstance();
                opes.setDa(bb);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            initbb(c.getSuperclass());
        }
    }


    private void initaa(Class<?> c) {
        if (c == null) {
            opes.setUi((A)(new BaseUIOpe<ViewDataBinding>()));
            opes.getU().init(getActivity());
            return;
        }
        if (c.getGenericSuperclass() instanceof ParameterizedType) {
            Class<A> a = (Class<A>) ((ParameterizedType) c.getGenericSuperclass()).getActualTypeArguments()[0];
            try {
                Constructor<A> ac = a.getConstructor();
                A aa = ac.newInstance();
                aa.init(getActivity());
                opes.setUi(aa);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            initaa(c.getSuperclass());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        moudles.clear();
        if(registerEventBus()){
            EventBus.getDefault().unregister(this);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    protected boolean registerEventBus(){
        return false;
    }

    public String getMoudle() {
        return moudle;
    }

    public void setMoudle(String moudle) {
        this.moudle = moudle;
        moudles.add(moudle);
    }

    public BaseUIAct<A, B, C> getActivity() {
        return this;
    }

    public ViewGroup getBaseUIRoot() {
        return baseUIRoot;
    }

    public ArrayList<String> getMoudles() {
        return moudles;
    }

}
