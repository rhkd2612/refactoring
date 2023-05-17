package com.study.refactor;

import java.util.List;
import java.util.Map;

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

    public int getVolumeCredits(Map<String, Play> plays){
        var result = 0;
        for(var perf : this.getPerformances()) {
            Play play = plays.get(perf.getPlayId());
            result += Math.max(perf.getAudience() - 30, 0);
            if(play.getType() == PlayType.COMEDY)
                result += Math.floor(perf.getAudience() / 5.0f);
        }
        return result;
    }

    public int calculateTotalAmount(Map<String, Play> plays){
        var result = 0;
        for(var perf : this.getPerformances()) {
            Play play = plays.get(perf.getPlayId());
            result += play.calculateCurrentAmount(perf, play);
        }
        return result;
    }
}
