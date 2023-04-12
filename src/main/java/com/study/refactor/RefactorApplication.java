package com.study.refactor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RefactorApplication {
	private static class Invoices{
		private String customerName;
		private List<Performance> performances;

		public Invoices(String customerName, List<Performance> performances) {
			this.customerName = customerName;
			this.performances = performances;
		}
	}

	public static String statement(Invoices invoice, Map<String, Play> plays) throws Exception {
		var totalAmount = 0;
		var volumeCredits = 0;
		var result = "청구 내역 고객명 : " + invoice.customerName + '\n';

		for(var perf : invoice.performances){
			Play play = plays.get(perf.getPlayId());
			var thisAmount = 0;

			switch(play.getType()){
				case "tragedy":
					thisAmount = 40000;
					if(perf.getAudience() > 30){
						thisAmount += 1000 * (perf.getAudience() - 30);
					}
					break;
				case "comedy":
					thisAmount = 30000;
					if(perf.getAudience() > 20){
						thisAmount += 10000 + 500 * (perf.getAudience() - 20);
					}
					thisAmount += 300 * perf.getAudience();
					break;
				default:
					throw new Exception("알 수 없는 장르");
			}

			volumeCredits += Math.max(perf.getAudience() - 30, 0);
			if("comedy".equals(play.getType())) volumeCredits += Math.floor(perf.getAudience() / 5);

			result += play.getName() + ": " + thisAmount + "원, " + perf.getAudience() + "석\n";
			totalAmount += thisAmount;
		}

		result += "총액: " + totalAmount + "원\n";
		result += "적립 포인트: " + volumeCredits + "점\n";
		return result;
	}

	public static void main(String[] args) throws Exception {
		System.out.println("안녕");
		if(args.length < 2)
			return;
		List<Performance> performances = new ArrayList<>();
		List<String> performancesInfo = List.of(args[0].split(","));
		Invoices invoices = new Invoices(args[1], performances);
		Map<String, Play> plays = new HashMap<>();

		performancesInfo.forEach(a -> {
			try {
				String[] split = a.split("/");
				String tag = split[0], title = split[1], type = split[2];
				int audience = Integer.parseInt(split[3]);

				performances.add(new Performance(tag, audience));
				plays.put(tag,new Play(title, type));
			} catch(ArrayIndexOutOfBoundsException e) {
				System.out.println(e.getMessage());
			}
		});

		System.out.println(statement(invoices, plays));
	}
}
