package com.study.refactor;

import java.util.HashMap;
import java.util.Map;

import com.study.refactor.playtype.PerformancePlayType;
import com.study.refactor.playtype.PlayType;

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
        return this.type.calculateCurrentAmount(perf, this);
    }
}
