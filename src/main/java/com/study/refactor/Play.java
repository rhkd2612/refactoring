package com.study.refactor;

import lombok.Getter;

@Getter
public class Play {
    private String name;
    private String type;

    public Play(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
