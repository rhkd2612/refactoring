package com.study.refactor;

import com.study.refactor.performance.Performance;
import com.study.refactor.performance.playtype.PerformancePlayType;
import com.study.refactor.performance.playtype.PlayType;

import lombok.Getter;

@Getter
public class Play {
    private final String name;
    private final PerformancePlayType type;

    public Play(String name, PerformancePlayType type) {
        this.name = name;
        this.type = type;
    }

    public PlayType getType(){
        return this.type.toPlayType();
    }

    public int calculateCurrentAmount(Performance perf){
        return this.type.calculateCurrentAmount(perf);
    }
}
