package com.study.refactor;

import lombok.Getter;

@Getter
public class Performance {
    private String playId;
    private int audience;

    public Performance(String playId, int audience) {
        this.playId = playId;
        this.audience = audience;
    }
}
