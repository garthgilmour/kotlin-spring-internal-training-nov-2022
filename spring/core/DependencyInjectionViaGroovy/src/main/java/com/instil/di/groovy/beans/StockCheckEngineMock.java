package com.instil.di.groovy.beans;

import com.instil.di.groovy.contracts.StockCheckEngine;

import java.util.Map;

public class StockCheckEngineMock implements StockCheckEngine {
	private Map<String,Integer> testData;
	public void setTestData(Map<String,Integer> testData) {
		this.testData = testData;
	}
    public int check(String itemNo) {
    	if(!testData.containsKey(itemNo)) {
    		throw new IllegalArgumentException(itemNo + " does not exist in test map");
    	}
        return testData.get(itemNo);
    }
}
