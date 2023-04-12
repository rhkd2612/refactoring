package com.study.refactor;

import java.util.List;

import lombok.Getter;

@Getter
public class Invoice {
    private String customerName;
    private List<Performance> performances;

    public Invoice(String customerName, List<Performance> performances) {
        this.customerName = customerName;
        this.performances = performances;
    }
}
