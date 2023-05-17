package com.study.refactor.playtype;

import com.study.refactor.Performance;
import com.study.refactor.Play;

public abstract class PerformancePlayType {
    public abstract int calculateCurrentAmount(Performance perf, Play play);
    public abstract PlayType toPlayType();

    public static PerformancePlayType initPerformancePlayType(String playType){
        if(playType.equals("tragedy"))
            return new TragedyPlayType();
        if(playType.equals("comedy"))
            return new ComedyPlayType();
        throw new IllegalArgumentException("잘못된 PlayType 정보입니다.");
    }
}
