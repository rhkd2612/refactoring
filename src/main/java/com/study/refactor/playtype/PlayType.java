package com.study.refactor.playtype;

public enum PlayType {
    TRAGEDY,
    COMEDY,
    OTHERS;

    public static PlayType toPlayType(String playType){
        var lowerInput = playType.toUpperCase();
        return PlayType.valueOf(lowerInput);
    }

    public String toString(){
        return this.name().toLowerCase();
    }
}
