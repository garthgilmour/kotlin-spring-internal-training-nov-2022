package demos.lifecycle.hooks.via.annotations;

import demos.lifecycle.common.StockCheckEngine;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component("stock")
public class StockCheckEngineMock implements StockCheckEngine {
    public int check(String itemNo) {
        return 200;
    }

    @PostConstruct
    public void begin() {
        System.out.println("Spring context just created a stock check engine");
    }

    @PreDestroy
    public void end() {
        System.out.println("Spring context just released a stock check engine");
    }
}
