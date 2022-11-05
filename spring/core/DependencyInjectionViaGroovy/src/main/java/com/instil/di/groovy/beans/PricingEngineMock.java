package com.instil.di.groovy.beans;

import com.instil.di.groovy.contracts.PricingEngine;

import java.util.List;

public class PricingEngineMock implements PricingEngine {
    private List<Double> prices;
    private int priceCount = -1;
    private double minimumDiscountAmount;
    private int percentageDiscount;

    public void setMinimumDiscountAmount(double minimumDiscountAmount) {
        this.minimumDiscountAmount = minimumDiscountAmount;
    }

    public void setPercentageDiscount(int percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }

    public void setPrices(List<Double> prices) {
        this.prices = prices;
    }

    public double price(String itemNo, int quantity) {
        if (++priceCount == prices.size()) {
            priceCount = 0;
        }
        double price = quantity * prices.get(priceCount);
        if (price >= minimumDiscountAmount) {
            price = price - (price * (percentageDiscount / 100));
        }
        return price;
    }
}
