package demos.spring.notes.v8

import demos.spring.notes.common.StockCheckEngine
import org.springframework.stereotype.Component

@Component("stock")
class StockCheckEngineMock : StockCheckEngine {
    override fun check(itemNo: String): Int {
        return 200
    }
}
