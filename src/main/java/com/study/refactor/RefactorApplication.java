package com.study.refactor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.study.refactor.playtype.PerformancePlayType;
import com.study.refactor.playtype.PlayType;

public class RefactorApplication {
	public static String statement(Invoice invoice, Map<String, Play> plays){
		var totalAmount = 0;
		var result = new StringBuilder("청구 내역 고객명 : " + invoice.getCustomerName() + '\n');
		for(var perf : invoice.getPerformances()) {
			Play play = plays.get(perf.getPlayId());
			int thisAmount = play.calculateCurrentAmount(perf, play);
			totalAmount += thisAmount;
			result.append(play.getName()).append(": ")
					.append(thisAmount).append("원, ")
					.append(perf.getAudience()).append("석\n");
		}
		result.append("총액: ")
				.append(totalAmount).append("원\n");

		int volumeCredits = invoice.getVolumeCredits(plays);
		result.append("적립 포인트: ")
				.append(volumeCredits).append("점\n");
		return result.toString();
	}

	public static void main(String[] args) throws Exception {
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
