package demos.spring.notes.v9

import demos.spring.notes.common.StockCheckEngine
import org.springframework.stereotype.Component

@Component("stock")
class StockCheckEngineMock : StockCheckEngine {
    override fun check(itemNo: String): Int {
        return 200
    }
}
