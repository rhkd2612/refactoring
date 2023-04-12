package com.study.refactor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RefactorApplication {
	private static class Invoices{
		private String customerName;
		private List<Performances> performances;

		private static class Performances{
			private String playId;
			private int audience;

			public Performances(String playId, int audience) {
				this.playId = playId;
				this.audience = audience;
			}
		}

		public Invoices(String customerName, List<Performances> performances) {
			this.customerName = customerName;
			this.performances = performances;
		}
	}

	public static String statement(Invoices invoice, Map<String, Play> plays) throws Exception {
		var totalAmount = 0;
		var volumeCredits = 0;
		var result = "청구 내역 고객명 : " + invoice.customerName + '\n';

		for(var perf : invoice.performances){
			Play play = plays.get(perf.playId);
			var thisAmount = 0;

			switch(play.getType()){
				case "tragedy":
					thisAmount = 40000;
					if(perf.audience > 30){
						thisAmount += 1000 * (perf.audience - 30);
					}
					break;
				case "comedy":
					thisAmount = 30000;
					if(perf.audience > 20){
						thisAmount += 10000 + 500 * (perf.audience - 20);
					}
					thisAmount += 300 * perf.audience;
					break;
				default:
					throw new Exception("알 수 없는 장르");
			}

			volumeCredits += Math.max(perf.audience - 30, 0);
			if("comedy".equals(play.getType())) volumeCredits += Math.floor(perf.audience / 5);

			result += play.getName() + ": " + thisAmount + "원, " + perf.audience + "석\n";
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
		List<Invoices.Performances> performances = new ArrayList<>();
		List<String> performancesInfo = List.of(args[0].split(","));
		Invoices invoices = new Invoices(args[1], performances);
		Map<String, Play> plays = new HashMap<>();

		performancesInfo.forEach(a -> {
			try {
				String[] split = a.split("/");
				String tag = split[0], title = split[1], type = split[2];
				int audience = Integer.parseInt(split[3]);

				performances.add(new Invoices.Performances(tag, audience));
				plays.put(tag,new Play(title, type));
			} catch(ArrayIndexOutOfBoundsException e) {
				System.out.println(e.getMessage());
			}
		});

		System.out.println(statement(invoices, plays));
	}
}
