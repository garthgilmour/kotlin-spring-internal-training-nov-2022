package demos.lifecycle.lazy.creation;

import demos.lifecycle.common.PaymentEngine;
import demos.lifecycle.common.PricingEngine;
import demos.lifecycle.common.Shop;
import demos.lifecycle.common.StockCheckEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SampleShop implements Shop {
    private PricingEngine pricingEngine;
    private StockCheckEngine stockCheckEngine;
    private PaymentEngine paymentEngine;

    @Autowired
    public void setPricingEngine(PricingEngine pricingEngine) {
        this.pricingEngine = pricingEngine;
    }

    @Autowired
    public void setStockCheckEngine(StockCheckEngine stockCheckEngine) {
        this.stockCheckEngine = stockCheckEngine;
    }

    @Autowired
    public void setPaymentEngine(PaymentEngine paymentEngine) {
        this.paymentEngine = paymentEngine;
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

    @PostConstruct
    public void begin() {
        System.out.println("Spring context just created a shop");
    }

    @PreDestroy
    public void end() {
        System.out.println("Spring context just released a shop");
    }
}
