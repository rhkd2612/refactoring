package com.study.refactor.performance.playtype;

public enum PlayType {
    TRAGEDY,
    COMEDY;

    public String toString(){
        return this.name().toLowerCase();
    }
}
