package demos.lifecycle.prototype.scope;

import demos.lifecycle.common.PaymentEngine;
import demos.lifecycle.common.PricingEngine;
import demos.lifecycle.common.Shop;
import demos.lifecycle.common.StockCheckEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("shop")
@Scope("prototype")
public class SampleShop implements Shop {
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

    public PricingEngine getPricingEngine() {
        return pricingEngine;
    }

    public StockCheckEngine getStockCheckEngine() {
        return stockCheckEngine;
    }

    public PaymentEngine getPaymentEngine() {
        return paymentEngine;
    }
}
