package com.study.refactor.performance.invoice;

import java.util.Collections;
import java.util.List;

import com.study.refactor.performance.Performance;
import com.study.refactor.performance.play.Play;
import com.study.refactor.performance.play.PlayLoader;
import com.study.refactor.performance.play.playtype.PlayType;
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
            result += Math.max(perf.getAudienceCount() - 30, 0);
            if(play.getType() == PlayType.COMEDY)
                result += Math.floor(perf.getAudienceCount() / 5.0f);
        }
        return result;
    }

    public int calculateTotalAmount(){
        var result = 0;
        for(var perf : this.performances) {
            Play play = PlayLoader.get(perf.getPlayId());
            result += play.calculateCurrentAmount(perf.getAudienceCount());
        }
        return result;
    }
}
