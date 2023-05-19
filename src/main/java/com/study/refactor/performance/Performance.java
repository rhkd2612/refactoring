package com.study.refactor.performance;

import lombok.Getter;

@Getter
public class Performance {
    private final String playId;
    private final int audienceCount;

    public Performance(String playId, int audienceCount) {
        this.playId = playId;
        this.audienceCount = audienceCount;
    }
}
