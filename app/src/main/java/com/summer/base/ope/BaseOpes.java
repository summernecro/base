package com.summer.base.ope;

//by summer on 2018-04-16.

import java.io.Serializable;

public class BaseOpes <A extends BaseUIOpe, B extends BaseDAOpe,C extends BaseValue> implements Serializable {
    /**
     * 操作者
     */
    A ui;
    /**
     * 数据操作者
     */
    B da;

    /**数据*/
    C va;




    public BaseOpes(A ui, B da,C va) {
        this.ui = ui;
        this.da = da;
        this.va = va;
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

    public C getV() {
        return va;
    }

    public void setVa(C va) {
        this.va = va;
    }

}
