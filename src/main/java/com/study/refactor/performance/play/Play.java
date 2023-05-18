package com.study.refactor.performance.play;

import com.study.refactor.performance.Performance;
import com.study.refactor.performance.play.playtype.PerformancePlayType;
import com.study.refactor.performance.play.playtype.PlayType;

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
