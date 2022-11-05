package demos.lifecycle.hooks.via.interfaces;

import demos.lifecycle.common.StockCheckEngine;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component("stock")
public class StockCheckEngineMock implements StockCheckEngine, InitializingBean, DisposableBean {
    public int check(String itemNo) {
        return 200;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Spring context just released a stock check engine");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Spring context just created a stock check engine");
    }
}
