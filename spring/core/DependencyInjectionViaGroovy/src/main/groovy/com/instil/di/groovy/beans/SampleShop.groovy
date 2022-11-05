package com.instil.di.groovy.beans;

import com.instil.di.groovy.contracts.PaymentEngine;
import com.instil.di.groovy.contracts.PricingEngine;
import com.instil.di.groovy.contracts.StockCheckEngine;
import com.instil.di.groovy.model.Employee;

class SampleShop {
    String shopName;
    PricingEngine pricingEngine;
    StockCheckEngine stockCheckEngine;
    PaymentEngine paymentEngine;
    boolean openDuringWeekends;
    List<Employee> juniorStaff;
    List<Employee> seniorStaff;
    List<String> staffNames;

    boolean makePurchase(String itemNo, int quantity, String cardNo) {
        if (stockCheckEngine.check(itemNo) >= quantity) {
            double charge = pricingEngine.price(itemNo, quantity);
            if (paymentEngine.authorize(cardNo, charge)) {
                return true;
            }
        }
        return false;
    }
}
