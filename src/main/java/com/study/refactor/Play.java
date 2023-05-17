package com.study.refactor;

import com.study.refactor.playtype.PerformancePlayType;
import com.study.refactor.playtype.PlayType;

import lombok.Getter;

@Getter
public class Play {
    private String name;
    private PerformancePlayType type;

    public Play(String name, PerformancePlayType type) {
        this.name = name;
        this.type = type;
    }
}
