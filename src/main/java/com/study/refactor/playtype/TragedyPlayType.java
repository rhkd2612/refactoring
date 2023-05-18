package com.study.refactor.playtype;

import com.study.refactor.Performance;
import com.study.refactor.Play;
import com.study.refactor.price.ExtraPerformancePrice;
import com.study.refactor.price.PerformancePrice;

public class TragedyPlayType extends PerformancePlayType{
    @Override
    public int calculateCurrentAmount(Performance perf, Play play) {
        int result = 40000;
        if(perf.getAudience() > 30){
            result += 1000 * (perf.getAudience() - 30);
        }
        return result;
    }

    @Override
    public PlayType toPlayType() {
        return PlayType.TRAGEDY;
    }

    @Override
    protected void setPerformancePrice() {
        this.base = new PerformancePrice(40000, 0);
        this.extra = new ExtraPerformancePrice(0,30,1000);
    }
}
