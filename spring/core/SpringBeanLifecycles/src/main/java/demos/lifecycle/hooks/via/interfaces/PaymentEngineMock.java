package demos.lifecycle.hooks.via.interfaces;

import demos.lifecycle.common.PaymentEngine;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component("payment")
public class PaymentEngineMock implements PaymentEngine, InitializingBean, DisposableBean {
    public boolean authorize(String cardNo, double amount) {
        return amount < 1000;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Spring context just released a payment engine");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Spring context just created a payment engine");
    }
}
