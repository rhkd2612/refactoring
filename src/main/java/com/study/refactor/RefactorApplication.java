package com.study.refactor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.study.refactor.playtype.PerformancePlayType;

public class RefactorApplication {
	public static String statement(Invoice invoice, Map<String, Play> plays){
		var result = new StringBuilder();
		appendStringsOneLine(result, "청구 내역 고객명 : ", invoice.getCustomerName());
		for(var perf : invoice.getPerformances()) {
			Play play = plays.get(perf.getPlayId());
			appendStringsOneLine(result, ": ", play.calculateCurrentAmount(perf, play), "원, ", perf.getAudience(), "석");
		}
		appendStringsOneLine(result, "총액: ", invoice.calculateTotalAmount(plays), "원");
		appendStringsOneLine(result, "적립 포인트: ", invoice.getVolumeCredits(plays), "점");
		return result.toString();
	}

	private static void appendStringsOneLine(StringBuilder builder, Object... strings){
		for(var string : strings)
			builder.append(string);
		builder.append('\n');
	}

	public static void main(String[] args){
		if(invalidInput(args))
			return;
		String allPerformanceInfo = args[0];
		String userName = args[1];

		List<Performance> performances = new ArrayList<>();
		List<String> performancesInfo = List.of(allPerformanceInfo.split(","));
		Map<String, Play> plays = new HashMap<>();

		performancesInfo.forEach(a -> {
			try {
				String[] split = a.split("/");
				String tag = split[0], title = split[1];
				PerformancePlayType type = PerformancePlayType.initPerformancePlayType(split[2]);
				int audience = Integer.parseInt(split[3]);

				performances.add(new Performance(tag, audience));
				plays.put(tag,new Play(title, type));
			} catch(ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
				throw e;
			}
		});

		Invoice invoices = new Invoice(userName, performances);
		System.out.println(statement(invoices, plays));
	}

	private static boolean invalidInput(String[] args) {
		return args.length < 2;
	}
}
