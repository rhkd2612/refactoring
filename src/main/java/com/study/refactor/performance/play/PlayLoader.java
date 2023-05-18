package com.study.refactor.performance.play;

import java.util.HashMap;
import java.util.Map;

public class PlayLoader {
    private static final Map<String, Play> playsMap = new HashMap<>();

    public static Play get(String tag){
        return playsMap.get(tag);
    }

    public static void putIfAbsent(String tag, Play play){
        playsMap.putIfAbsent(tag, play);
    }
}
