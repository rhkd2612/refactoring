package com.study.refactor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.study.refactor.playtype.PlayType;
import lombok.Getter;

@Getter
public class Invoice {
    private final String customerName;
    private final List<Performance> performances;

    public Invoice(String customerName, List<Performance> performances) {
        this.customerName = customerName;
        this.performances = performances;
    }

    public List<Performance> getPerformances() {
        return Collections.unmodifiableList(performances);
    }

    public int getVolumeCredits(){
        var result = 0;
        for(var perf : this.performances) {
            Play play = PlayLoader.get(perf.getPlayId());
            result += Math.max(perf.getAudience() - 30, 0);
            if(play.getType() == PlayType.COMEDY)
                result += Math.floor(perf.getAudience() / 5.0f);
        }
        return result;
    }

    public int calculateTotalAmount(){
        var result = 0;
        for(var perf : this.performances) {
            Play play = PlayLoader.get(perf.getPlayId());
            result += play.calculateCurrentAmount(perf);
        }
        return result;
    }
}
