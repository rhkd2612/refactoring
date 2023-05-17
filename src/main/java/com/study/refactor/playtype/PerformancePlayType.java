package com.study.refactor.playtype;

import com.study.refactor.Performance;
import com.study.refactor.Play;

public abstract class PerformancePlayType {
    abstract int calculateCurrentAmount(Performance perf, Play play);
}
