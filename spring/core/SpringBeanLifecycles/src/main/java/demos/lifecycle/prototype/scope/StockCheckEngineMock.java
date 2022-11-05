package demos.lifecycle.prototype.scope;

import demos.lifecycle.common.StockCheckEngine;
import org.springframework.stereotype.Component;

@Component("stock")
public class StockCheckEngineMock implements StockCheckEngine {
    public int check(String itemNo) {
        return 200;
    }
}
