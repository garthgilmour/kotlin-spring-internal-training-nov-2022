package demos.lifecycle.manual.creation;

import demos.lifecycle.common.PricingEngine;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component("pricing")
public class PricingEngineMock implements PricingEngine {
    public double price(String itemNo, int quantity) {
        return quantity * 5.50;
    }

    @PostConstruct
    public void begin() {
        System.out.println("Spring context just created a pricing engine");
    }

    @PreDestroy
    public void end() {
        System.out.println("Spring context just released a pricing engine");
    }
}
