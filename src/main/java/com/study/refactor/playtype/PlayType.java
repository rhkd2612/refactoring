package com.study.refactor.playtype;

public enum PlayType {
    TRAGEDY,
    COMEDY;

    public String toString(){
        return this.name().toLowerCase();
    }
}
