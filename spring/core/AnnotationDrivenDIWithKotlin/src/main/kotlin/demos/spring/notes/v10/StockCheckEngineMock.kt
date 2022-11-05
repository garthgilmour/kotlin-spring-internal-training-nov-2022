package demos.spring.notes.v10

import demos.spring.notes.common.StockCheckEngine
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component("stock")
class StockCheckEngineMock : StockCheckEngine {
    private var testData: Map<String, Int> = emptyMap()
        @Value("#{dataSource.buildStockData()}")
        set(value) {
            field = value
        }

    override fun check(itemNo: String): Int {
        require(testData.containsKey(itemNo)) { "$itemNo does not exist in test map" }
        return testData[itemNo] ?: 0
    }
}
