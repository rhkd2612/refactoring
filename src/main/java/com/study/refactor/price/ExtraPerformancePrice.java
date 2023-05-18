package com.study.refactor.price;

import lombok.Getter;

@Getter
public class ExtraPerformancePrice extends PerformancePrice{
    private final int extraMinAudience;
    public ExtraPerformancePrice(int base, int perAudience, int extraMinAudience) {
        super(base, perAudience);
        this.extraMinAudience = extraMinAudience;
    }
}
