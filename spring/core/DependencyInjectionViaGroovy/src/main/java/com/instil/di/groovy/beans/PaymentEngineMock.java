package com.instil.di.groovy.beans;

import com.instil.di.groovy.contracts.PaymentEngine;

import java.util.List;

public class PaymentEngineMock implements PaymentEngine {
    private final List<String> bannedCards;

    public PaymentEngineMock(List<String> bannedCards) {
        super();
        this.bannedCards = bannedCards;
    }

    public boolean authorize(String cardNo, double amount) {
        if (bannedCards.contains(cardNo)) {
            return false;
        }
        return amount < 1000;
    }
}
