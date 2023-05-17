package com.study.refactor.playtype;

import com.study.refactor.Performance;
import com.study.refactor.Play;

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
}
