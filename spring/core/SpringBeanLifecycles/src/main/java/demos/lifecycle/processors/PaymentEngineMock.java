package demos.lifecycle.processors;

import demos.lifecycle.common.PaymentEngine;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component("payment")
public class PaymentEngineMock implements PaymentEngine {
    public boolean authorize(String cardNo, double amount) {
        return amount < 1000;
    }

    @PostConstruct
    public void begin() {
        System.out.println("Spring context just created a payment engine");
    }

    @PreDestroy
    public void end() {
        System.out.println("Spring context just released a payment engine");
    }
}
