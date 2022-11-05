package demos.spring.notes.v2

import demos.spring.notes.common.StockCheckEngine
import org.springframework.stereotype.Component

@Component
class StockCheckEngineMock : StockCheckEngine {
    override fun check(itemNo: String): Int {
        return 200
    }
}
