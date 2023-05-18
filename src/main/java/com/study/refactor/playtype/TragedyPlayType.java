package com.study.refactor.playtype;

import com.study.refactor.Performance;
import com.study.refactor.Play;
import com.study.refactor.price.ExtraPerformancePrice;
import com.study.refactor.price.PerformancePrice;

public class TragedyPlayType extends PerformancePlayType{
    @Override
    public int calculateCurrentAmount(Performance perf, Play play) {
        int result = this.base.getConstract();
        if(perf.getAudience() > this.extra.getExtraMinAudience()){
            result += this.extra.getConstract() + this.extra.getPerAudience() * (perf.getAudience() - this.extra.getExtraMinAudience());
        }
        result += this.base.getPerAudience() * perf.getAudience();
        return result;
    }

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
