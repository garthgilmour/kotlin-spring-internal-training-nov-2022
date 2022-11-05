package demos.lifecycle.hooks.via.interfaces;

import demos.lifecycle.common.PaymentEngine;
import demos.lifecycle.common.PricingEngine;
import demos.lifecycle.common.Shop;
import demos.lifecycle.common.StockCheckEngine;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("shop")
public class SampleShop implements Shop, InitializingBean, DisposableBean {
    private final PricingEngine pricingEngine;
    private final StockCheckEngine stockCheckEngine;
    private final PaymentEngine paymentEngine;

    @Autowired
    public SampleShop(@Qualifier("pricing") PricingEngine pricingEngine,
                      @Qualifier("payment") PaymentEngine paymentEngine,
                      @Qualifier("stock") StockCheckEngine stockCheckEngine) {
        super();
        this.pricingEngine = pricingEngine;
        this.paymentEngine = paymentEngine;
        this.stockCheckEngine = stockCheckEngine;
    }

    @Override
    public boolean makePurchase(String itemNo, int quantity, String cardNo) {
        if (stockCheckEngine.check(itemNo) >= quantity) {
            double charge = pricingEngine.price(itemNo, quantity);
            if (paymentEngine.authorize(cardNo, charge)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Spring context just released a shop");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Spring context just created a shop");
    }
}
