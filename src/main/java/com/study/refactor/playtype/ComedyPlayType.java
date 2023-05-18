package com.study.refactor.playtype;

import com.study.refactor.price.ExtraPerformancePrice;
import com.study.refactor.price.PerformancePrice;

public class ComedyPlayType extends PerformancePlayType{
    @Override
    public PlayType toPlayType() {
        return PlayType.COMEDY;
    }

    @Override
    protected void setPerformancePrice() {
        this.base = new PerformancePrice(30000, 300);
        this.extra = new ExtraPerformancePrice(10000,500,20);
    }
}
