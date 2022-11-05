package demos.spring.notes.v8;

import demos.spring.notes.common.PricingEngine;

public class PricingEngineMock implements PricingEngine {
    public double price(String itemNo, int quantity) {
        return quantity * 5.50;
    }
}
