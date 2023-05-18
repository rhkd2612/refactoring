package com.study.refactor.price;

import lombok.Getter;

@Getter
public class PerformancePrice {
    protected final int constract;
    protected final int perAudience;

    public PerformancePrice(int constract, int perAudience) {
        this.constract = constract;
        this.perAudience = perAudience;
    }
}
