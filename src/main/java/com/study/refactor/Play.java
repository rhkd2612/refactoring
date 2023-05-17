package com.study.refactor;

import lombok.Getter;

@Getter
public class Play {
    private String name;
    private PlayType type;

    public Play(String name, PlayType type) {
        this.name = name;
        this.type = type;
    }
}
