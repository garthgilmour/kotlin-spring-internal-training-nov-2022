package com.instil.di.groovy.contracts;

public interface PaymentEngine {

    boolean authorize(String cardNo, double amount);

}
