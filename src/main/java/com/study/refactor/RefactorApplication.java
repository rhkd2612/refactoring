package com.study.refactor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.study.refactor.playtype.PerformancePlayType;

import static com.study.refactor.PlayLoader.playsMap;

public class RefactorApplication {
	public static String statement(Invoice invoice){
		var result = new StringBuilder();
		result.append(objectsToStrLine(result, "청구 내역 고객명 : ", invoice.getCustomerName()));
		for(var perf : invoice.getPerformances()) {
			Play play = playsMap.get(perf.getPlayId());
			result.append(objectsToStrLine( ": ", play.calculateCurrentAmount(perf), "원, ", perf.getAudience(), "석"));
		}
		result.append(objectsToStrLine( "총액: ", invoice.calculateTotalAmount(), "원"));
		result.append(objectsToStrLine( "적립 포인트: ", invoice.getVolumeCredits(), "점"));
		return result.toString();
	}

	private static String objectsToStrLine(Object... strings){
		StringBuilder builder = new StringBuilder();
		for(var string : strings)
			builder.append(string);
		builder.append('\n');
		return builder.toString();
	}

	public static void main(String[] args){
		if(invalidInput(args))
			return;
		String allPerformanceInfo = args[0];
		String userName = args[1];

		List<Performance> performances = new ArrayList<>();
		List<String> performancesInfo = List.of(allPerformanceInfo.split(","));
		performancesInfo.forEach(a -> {
			try {
				String[] split = a.split("/");
				String tag = split[0], title = split[1];
				PerformancePlayType type = PerformancePlayType.initPerformancePlayType(split[2]);
				int audience = Integer.parseInt(split[3]);

				performances.add(new Performance(tag, audience));
				playsMap.putIfAbsent(tag, new Play(title, type));
			} catch(ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
				throw e;
			}
		});

		Invoice invoices = new Invoice(userName, performances);
		System.out.println(statement(invoices));
	}

	private static boolean invalidInput(String[] args) {
		return args.length < 2;
	}
}
