package com.study.refactor;

import java.util.ArrayList;
import java.util.List;

import com.study.refactor.exception.MyCustomException;
import com.study.refactor.playtype.PerformancePlayType;

public class RefactorApplication {
    private static class PerformanceForm{
        public String tag;
        public String title;
        public PerformancePlayType type;
        public Integer audience;
        public PerformanceForm(String input) {
            String[] split = input.split("/");
            this.tag = split[0];
            this.title = split[1];
            this.type = PerformancePlayType.initPerformancePlayType(split[2]);
            this.audience = Integer.parseInt(split[3]);
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(statement(new Invoice(args[1], getPerformances(args[0]))));
        } catch(ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            // TODO ExceptionHandler 추가
            throw new MyCustomException(e.getMessage());
        }
    }

    private static List<Performance> getPerformances(String performancesText) {
        List<String> splitPerformancesText = List.of(performancesText.split(","));
        List<Performance> performances = new ArrayList<>();
        splitPerformancesText.forEach(perf -> {
            PerformanceForm form = new PerformanceForm(perf);
            performances.add(new Performance(form.tag, form.audience));
            PlayLoader.putIfAbsent(form.tag, new Play(form.title, form.type));
        });
        return performances;
    }

    public static String statement(Invoice invoice) {
        StringBuilder result = new StringBuilder();
        result.append(objectsToStrLine(result, "청구 내역 고객명 : ", invoice.getCustomerName()));

        List<Performance> performances = invoice.getPerformances();
        for(var perf : performances) {
            Play play = PlayLoader.get(perf.getPlayId());
            result.append(objectsToStrLine(": ", play.calculateCurrentAmount(perf), "원, ", perf.getAudience(), "석"));
        }
        result.append(objectsToStrLine("총액: ", invoice.calculateTotalAmount(), "원"));
        result.append(objectsToStrLine("적립 포인트: ", invoice.getVolumeCredits(), "점"));
        return result.toString();
    }

    private static String objectsToStrLine(Object... strings) {
        StringBuilder builder = new StringBuilder();
        for(var string : strings)
            builder.append(string);
        builder.append('\n');
        return builder.toString();
    }
}
