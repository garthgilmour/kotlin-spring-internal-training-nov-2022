package com.instil

class StockCheckEngineMock(private val testData: Map<String, Int>) : StockCheckEngine {

    override fun check(itemNo: String): Int {
        println("\tChecking stock...")
    	if(!testData.containsKey(itemNo)) {
    		throw IllegalArgumentException("$itemNo does not exist in test map");
    	}
        return testData[itemNo] ?: 0
    }
}
