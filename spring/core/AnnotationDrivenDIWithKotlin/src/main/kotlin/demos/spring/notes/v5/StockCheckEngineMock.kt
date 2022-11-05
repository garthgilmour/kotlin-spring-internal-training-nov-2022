package demos.spring.notes.v5

import demos.spring.notes.common.StockCheckEngine

class StockCheckEngineMock : StockCheckEngine {
    override fun check(itemNo: String): Int {
        return 200
    }
}
