package com.study.refactor.playtype;

import com.study.refactor.Performance;
import com.study.refactor.Play;

public class ComedyPlayType extends PerformancePlayType{
    @Override
    int calculateCurrentAmount(Performance perf, Play play) {
        int result = 30000;
        if(perf.getAudience() > 20){
            result += 10000 + 500 * (perf.getAudience() - 20);
        }
        result += 300 * perf.getAudience();
        return result;
    }
}
