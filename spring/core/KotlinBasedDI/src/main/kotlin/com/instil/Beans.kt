package com.instil

import org.springframework.context.support.beans

fun beans() = beans {
    bean<PricingEngine> {
        PricingEngineMock(
                DataSource.discountAmount(),
                DataSource.percentageDiscount() * 2,
                DataSource.prices())
    }
    bean<StockCheckEngine> {
        StockCheckEngineMock(DataSource.buildStockData())
    }
    bean<PaymentEngine> {
        PaymentEngineMock(DataSource.buildBannedCards())
    }
    bean<Shop>("shopWithMocks")
}
