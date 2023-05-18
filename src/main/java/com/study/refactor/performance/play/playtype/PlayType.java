package com.study.refactor.performance.play.playtype;

public enum PlayType {
    TRAGEDY,
    COMEDY;

    public String toString(){
        return this.name().toLowerCase();
    }
}
