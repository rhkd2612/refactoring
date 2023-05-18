package com.study.refactor.price;

import lombok.Getter;

@Getter
public class PerformancePrice {
    protected final int base;
    protected final int perAudience;

    public PerformancePrice(int base, int perAudience) {
        this.base = base;
        this.perAudience = perAudience;
    }
}
