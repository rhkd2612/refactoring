package com.study.refactor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RefactorApplication {
	public static String statement(Invoice invoice, Map<String, Play> plays) throws Exception {
		var totalAmount = 0;
		var volumeCredits = 0;
		var result = "청구 내역 고객명 : " + invoice.getCustomerName() + '\n';

		for(var perf : invoice.getPerformances()){
			Play play = plays.get(perf.getPlayId());
			int thisAmount = calculateCurrentAmount(perf, play);

			volumeCredits += Math.max(perf.getAudience() - 30, 0);
			if("comedy".equals(play.getType())) volumeCredits += Math.floor(perf.getAudience() / 5);

			result += play.getName() + ": " + thisAmount + "원, " + perf.getAudience() + "석\n";
			totalAmount += thisAmount;
		}

		result += "총액: " + totalAmount + "원\n";
		result += "적립 포인트: " + volumeCredits + "점\n";
		return result;
	}

	private static int calculateCurrentAmount(Performance perf, Play play) throws Exception {
		var result = 0;
		switch(play.getType()){
			case "tragedy":
				result = 40000;
				if(perf.getAudience() > 30){
					result += 1000 * (perf.getAudience() - 30);
				}
				break;
			case "comedy":
				result = 30000;
				if(perf.getAudience() > 20){
					result += 10000 + 500 * (perf.getAudience() - 20);
				}
				result += 300 * perf.getAudience();
				break;
			default:
				throw new Exception("알 수 없는 장르");
		}
		return result;
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
				String tag = split[0], title = split[1], type = split[2];
				int audience = Integer.parseInt(split[3]);

				performances.add(new Performance(tag, audience));
				plays.put(tag,new Play(title, type));
			} catch(ArrayIndexOutOfBoundsException e) {
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
