package com.study.refactor.playtype;

import com.study.refactor.Performance;
import com.study.refactor.Play;
import com.study.refactor.price.ExtraPerformancePrice;
import com.study.refactor.price.PerformancePrice;

public class ComedyPlayType extends PerformancePlayType{
    @Override
    public int calculateCurrentAmount(Performance perf, Play play) {
        int result = 30000;
        if(perf.getAudience() > 20){
            result += 10000 + 500 * (perf.getAudience() - 20);
        }
        result += 300 * perf.getAudience();
        return result;
    }

    @Override
    public PlayType toPlayType() {
        return PlayType.COMEDY;
    }

    @Override
    protected void setPerformancePrice() {
        this.base = new PerformancePrice(30000, 300);
        this.extra = new ExtraPerformancePrice(10000,20,500);
    }
}
