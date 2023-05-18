package com.study.refactor.price;

import lombok.Getter;

@Getter
public class ExtraPerformancePrice extends PerformancePrice{
    private final int extraMinAudience;
    public ExtraPerformancePrice(int constract, int perAudience, int extraMinAudience) {
        super(constract, perAudience);
        this.extraMinAudience = extraMinAudience;
    }
}
