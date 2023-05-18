package com.study.refactor.performance.playtype;

import com.study.refactor.performance.price.ExtraPerformancePrice;
import com.study.refactor.performance.price.PerformancePrice;

public class TragedyPlayType extends PerformancePlayType{
    @Override
    public PlayType toPlayType() {
        return PlayType.TRAGEDY;
    }

    @Override
    protected void setPerformancePrice() {
        this.base = new PerformancePrice(40000, 0);
        this.extra = new ExtraPerformancePrice(0,1000,30);
    }
}
