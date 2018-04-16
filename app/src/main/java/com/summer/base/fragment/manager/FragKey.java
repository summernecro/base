package com.summer.base.fragment.manager;

//by summer on 2018-01-12.


import com.summer.base.bean.BaseBean;

public class FragKey  extends BaseBean {

    private String tag;

    private int viewid;

    private int index;

    private int req;

    private FragKey() {
    }

    public FragKey(String tag, int viewid, int index, int req) {
        this.tag = tag;
        this.viewid = viewid;
        this.index = index;
        this.req = req;
    }

    public FragKey(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getViewid() {
        return viewid;
    }

    public void setViewid(int viewid) {
        this.viewid = viewid;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getReq() {
        return req;
    }

    public void setReq(int req) {
        this.req = req;
    }
}
