package com.study.refactor.performance.play.playtype;

import com.study.refactor.exception.MyCustomException;
import com.study.refactor.performance.price.ExtraPerformancePrice;
import com.study.refactor.performance.price.PerformancePrice;

public abstract class PerformancePlayType {
    protected PerformancePrice base;
    protected ExtraPerformancePrice extra;
    public PerformancePlayType(){
        setPerformancePrice();
    }
    public abstract PlayType toPlayType();
    protected abstract void setPerformancePrice();

    public int calculateCurrentAmount(int audienceCount){
        int result = this.base.getConstract();
        if(audienceCount > this.extra.getExtraMinAudience()){
            result += this.extra.getConstract() + this.extra.getPerAudience() * (audienceCount - this.extra.getExtraMinAudience());
        }
        result += this.base.getPerAudience() * audienceCount;
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
