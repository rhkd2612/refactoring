package com.study.refactor.playtype;

import com.study.refactor.Performance;
import com.study.refactor.Play;
import com.study.refactor.exception.MyCustomException;
import com.study.refactor.price.ExtraPerformancePrice;
import com.study.refactor.price.PerformancePrice;

public abstract class PerformancePlayType {
    protected PerformancePrice base;
    protected ExtraPerformancePrice extra;
    public PerformancePlayType(){
        setPerformancePrice();
    }
    public abstract PlayType toPlayType();
    protected abstract void setPerformancePrice();

    public int calculateCurrentAmount(Performance perf, Play play){
        int result = this.base.getConstract();
        if(perf.getAudience() > this.extra.getExtraMinAudience()){
            result += this.extra.getConstract() + this.extra.getPerAudience() * (perf.getAudience() - this.extra.getExtraMinAudience());
        }
        result += this.base.getPerAudience() * perf.getAudience();
        return result;
    }

    public static PerformancePlayType initPerformancePlayType(String playType){
        if(playType.equals("tragedy"))
            return new TragedyPlayType();
        if(playType.equals("comedy"))
            return new ComedyPlayType();
        throw new MyCustomException("잘못된 PlayType 정보입니다.");
    }
}
