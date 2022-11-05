package demos.lifecycle.hooks.via.interfaces;

import demos.lifecycle.common.PricingEngine;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component("pricing")
public class PricingEngineMock implements PricingEngine, InitializingBean, DisposableBean {
    public double price(String itemNo, int quantity) {
        return quantity * 5.50;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Spring context just released a pricing engine");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Spring context just created a pricing engine");
    }
}
