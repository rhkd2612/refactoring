package com.study.refactor.performance;

import lombok.Getter;

@Getter
public class Performance {
    private final String playId;
    private final int audience;

    public Performance(String playId, int audience) {
        this.playId = playId;
        this.audience = audience;
    }
}
