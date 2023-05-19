package com.study.refactor;

import java.util.ArrayList;
import java.util.List;

import com.study.refactor.exception.MyCustomException;
import com.study.refactor.handler.MyCustomExceptionHandler;
import com.study.refactor.performance.Performance;
import com.study.refactor.performance.PerformanceForm;
import com.study.refactor.performance.invoice.Invoice;
import com.study.refactor.performance.play.Play;
import com.study.refactor.performance.play.PlayLoader;

public class RefactorApplication {
    public static void main(String[] args) {
        setCustomExceptionHandler();
        validateArguments(args);

        List<String> splitPerformancesText = splitArgsToPerformances(args);
        putAllPerformancePlayInfos(splitPerformancesText);
        String invoice = writeInvoice(new Invoice(args[1], collectPerformances(splitPerformancesText)));
        printInvoice(invoice);
    }

    private static void printInvoice(String invoice) {
        System.out.println(invoice);
    }

    private static List<String> splitArgsToPerformances(String[] args) {
        return List.of(args[0].split(","));
    }

    private static void setCustomExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler(new MyCustomExceptionHandler());
    }

    private static void validateArguments(String[] args) {
        if(args.length < 2)
            throw new MyCustomException("Invalid Input : Base");
    }

    private static String writeInvoice(Invoice invoice) {
        StringBuilder result = new StringBuilder();
        result.append(objectsToStrLine("청구 내역 고객명 : ", invoice.getCustomerName()));

        List<Performance> performances = invoice.getPerformances();
        for(var perf : performances) {
            Play play = PlayLoader.get(perf.getPlayId());
            int audienceCount = perf.getAudienceCount();
            result.append(objectsToStrLine(": ", play.calculateCurrentAmount(audienceCount), "원, ", audienceCount, "석"));
        }
        result.append(objectsToStrLine("총액: ", invoice.calculateTotalAmount(), "원"));
        result.append(objectsToStrLine("적립 포인트: ", invoice.getVolumeCredits(), "점"));
        return result.toString();
    }

    private static List<Performance> collectPerformances(List<String> splitPerformancesText) {
        List<Performance> performances = new ArrayList<>();
        splitPerformancesText.forEach(perf -> {
            PerformanceForm form = new PerformanceForm(perf);
            performances.add(new Performance(form.tag, form.audience));
        });
        return performances;
    }

    private static void putAllPerformancePlayInfos(List<String> splitPerformancesText) {
        splitPerformancesText.forEach(perf -> {
            PerformanceForm form = new PerformanceForm(perf);
            PlayLoader.putIfAbsent(form.tag, new Play(form.title, form.type));
        });
    }

    private static String objectsToStrLine(Object... strings) {
        StringBuilder builder = new StringBuilder();
        for(var string : strings)
            builder.append(string);
        builder.append('\n');
        return builder.toString();
    }
}
