package com.summer.base.ope;

//by summer on 2018-04-16.

import java.io.Serializable;

public class BaseOpes <A extends BaseUIOpe, B extends BaseDAOpe> implements Serializable {

    /**
     * 操作者
     */
    A ui;
    /**
     * 数据操作者
     */
    B da;


    public BaseOpes(A ui, B da) {
        this.ui = ui;
        this.da = da;
    }

    public B getD() {
        return da;
    }

    public void setDa(B da) {
        this.da = da;
    }

    public A getU() {
        return ui;
    }

    public void setUi(A ui) {
        this.ui = ui;
    }

}
