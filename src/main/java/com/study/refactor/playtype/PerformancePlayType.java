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
    public abstract int calculateCurrentAmount(Performance perf, Play play);
    public abstract PlayType toPlayType();
    protected abstract void setPerformancePrice();

    public static PerformancePlayType initPerformancePlayType(String playType){
        if(playType.equals("tragedy"))
            return new TragedyPlayType();
        if(playType.equals("comedy"))
            return new ComedyPlayType();
        throw new MyCustomException("잘못된 PlayType 정보입니다.");
    }
}
