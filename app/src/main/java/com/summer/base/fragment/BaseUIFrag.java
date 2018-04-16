package com.summer.base.fragment;

//by summer on 2018-04-16.

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.summer.base.R;
import com.summer.base.activity.BaseUIAct;
import com.summer.base.fragment.manager.FragM;
import com.summer.base.util.HandleUtil;
import com.summer.base.util.LogUtil;
import com.summer.base.ope.BaseDAOpe;
import com.summer.base.ope.BaseOpes;
import com.summer.base.ope.BaseUIOpe;
import com.summer.base.util.ValueConstant;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseUIFrag<A extends BaseUIOpe, B extends BaseDAOpe> extends Fragment implements View.OnClickListener, View.OnLongClickListener{

    private BaseUIAct activity;

    private  BaseUIFrag frag;

    private long uniqueid;

    private Unbinder unbinder;

    private BaseOpes<A, B> opes;

    private FragIs fragIs = new FragIs();

    private boolean isFiistVisibleinit = false;

    private ViewGroup baseUIRoot;


    private LoadUtil loadUtil;

    private TipUtil tipUtil = new TipUtil();

    private FragM fragM;

    private BaseUIFrag baseUIFrag;


    public BaseUIFrag() {
        baseUIFrag = this;
        LogUtil.E(this.getClass());
        setArguments(new Bundle());
        opes = new BaseOpes<>(null, null);
        initbb(getClass());
        getP().getD().initDA();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uniqueid = System.currentTimeMillis();
        frag = this;
        if(is注册事件总线()){
            EventBus.getDefault().register(this);
        }
        fragIs.onCreate(savedInstanceState);
        LogUtil.E(this.getClass());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseUIAct) {
            activity = (BaseUIAct) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View group = inflater.inflate(getBaseUILayout(),container,false);
        fragIs.onCreateView(inflater,container,savedInstanceState);
        return group;
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initNow();
        HandleUtil.getInstance().postDelayed(new Runnable() {
            @Override
            public void run() {
                baseUIRoot = view.findViewById(R.id.act_base_root);
                initaa(baseUIFrag.getClass());
                baseUIRoot.addView(getP().getU().getBind().getRoot(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                getP().getU().setView(getView());
                getP().getU().initUI();
                unbinder = ButterKnife.bind(baseUIFrag, baseUIRoot);
                initdelay();
            }
        }, delayTime());
        fragIs.onViewCreated(view,savedInstanceState);
    }

    protected int delayTime(){
        return 300;
    }


    public void initdelay() {
        if(getView()==null){
            return;
        }
    }

    public void initNow() {
        if(getView()==null){
            return;
        }
    }

    public void onFristVisible(){
        if(!isFiistVisibleinit){
            onFristVisibleInit();
            HandleUtil.getInstance().postDelayed(new Runnable() {
                @Override
                public void run() {
                    on第一次显示延迟加载();
                }
            }, delayTime());
            isFiistVisibleinit = true;
        }
    }

    protected void on第一次显示延迟加载(){

    }

    protected void onFristVisibleInit(){

    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
        fragIs.onDestroyView();
    }

    /**
     * 重新此方法获取布局文件
     */
    public int getBaseUILayout() {
        return R.layout.act_baseui;
    }

    /**
     * 获取操作类
     */
    public BaseOpes<A, B> getP() {
        return opes;
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
            opes.getU().init(getBaseUIFrag());
            return;
        }
        if (c.getGenericSuperclass() instanceof ParameterizedType) {
            Class<A> a = (Class<A>) ((ParameterizedType) c.getGenericSuperclass()).getActualTypeArguments()[0];
            try {
                Constructor<A> ac = a.getConstructor();
                A aa = ac.newInstance();
                aa.init(getBaseUIFrag());
                opes.setUi(aa);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            initaa(c.getSuperclass());
        }
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onLongClick(View v) {
        return true;
    }

    public void onResult(int req, Bundle bundle) {

    }

    @Override
    public void onDestroy() {
        if(is注册事件总线()){
            EventBus.getDefault().unregister(this);
        }
        fragIs.onDestroy();
        super.onDestroy();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void addFragListener(FragI fragI){
        fragIs.getFragIs().add(fragI);
    }

    public void startLoading(){
        if(getLoadUtil()!=null){
            loadUtil.startLoading(getActivity(), (ViewGroup) getView());
        }
    }

    public void stopLoading(){
        if(getLoadUtil()!=null){
            loadUtil.stopLoading( (ViewGroup) getView());
        }
    }

    public void showTips(String txt){
//        if(getBaseUIAct()==null){
//            return;
//        }
        tipUtil.showTips(getBaseUIAct(),  (ViewGroup) getView(),txt);
    }

    public void removeTips(){
        tipUtil.removeTips( (ViewGroup) getView());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected boolean is注册事件总线(){
        return false;
    }

    public ViewGroup getBaseUIRoot() {
        return baseUIRoot;
    }

    public String get容器() {
        return getArguments().getString(ValueConstant.容器);
    }

    public void set容器(String 容器){
        getArguments().putString(ValueConstant.容器,容器);
    }

    public BaseUIAct getBaseUIAct() {
        return activity;
    }

    public FragM getFragM() {
        return fragM;
    }

    public void setFragM(FragM fragM) {
        this.fragM = fragM;
    }

    public BaseUIFrag getBaseUIFrag() {
        return baseUIFrag;
    }

    public long getUniqueid() {
        return uniqueid;
    }

    public void setLoadUtil(LoadUtil loadUtil) {
        this.loadUtil = loadUtil;
    }

    public LoadUtil getLoadUtil() {
        return loadUtil;
    }
}
