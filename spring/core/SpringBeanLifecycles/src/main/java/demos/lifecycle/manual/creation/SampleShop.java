package demos.lifecycle.manual.creation;

import demos.lifecycle.common.PaymentEngine;
import demos.lifecycle.common.PricingEngine;
import demos.lifecycle.common.Shop;
import demos.lifecycle.common.StockCheckEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service("shop")
public class SampleShop implements Shop {
    private final PricingEngine pricingEngine;
    private final StockCheckEngine stockCheckEngine;
    private final PaymentEngine paymentEngine;

    @Autowired
    public SampleShop(ApplicationContext context) {
        super();
        pricingEngine = context.getBean("pricing", PricingEngine.class);
        paymentEngine = context.getBean("payment", PaymentEngine.class);;
        stockCheckEngine = context.getBean("stock", StockCheckEngine.class);;
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
