package com.study.refactor.performance;

import com.study.refactor.exception.MyCustomException;
import com.study.refactor.performance.play.playtype.PerformancePlayType;

public class PerformanceForm {
    public String tag;
    public String title;
    public PerformancePlayType type;
    public Integer audience;
    public PerformanceForm(String input) {
        String[] split = input.split("/");
        validatePerformanceInput(split);
        this.tag = split[0];
        this.title = split[1];
        this.type = PerformancePlayType.initPerformancePlayType(split[2]);
        this.audience = Integer.parseInt(split[3]);
    }

    private static void validatePerformanceInput(String[] split) {
        if(split.length < 4)
            throw new MyCustomException("Invalid Input Performance");
    }
}
