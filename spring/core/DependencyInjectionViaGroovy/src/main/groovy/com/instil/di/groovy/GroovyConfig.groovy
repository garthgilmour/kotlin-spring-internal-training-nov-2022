package com.instil.di.groovy

import com.instil.di.groovy.beans.PaymentEngineMock
import com.instil.di.groovy.beans.PricingEngineMock
import com.instil.di.groovy.beans.SampleDataSource
import com.instil.di.groovy.beans.SampleShop
import com.instil.di.groovy.beans.StockCheckEngineMock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.AbstractApplicationContext

import javax.annotation.PostConstruct

@Configuration
class GroovyConfig {
    @Autowired
    private AbstractApplicationContext context

    @PostConstruct
    registerBeansViaGroovy() {
        var reader = new GroovyBeanDefinitionReader(context)
        reader.beans {
            dataSource(SampleDataSource.class) {
            }
            pricingEngine(PricingEngineMock.class) {
                minimumDiscountAmount = "#{dataSource.discountAmount}"
                percentageDiscount = "#{dataSource.percentageDiscount * 2}"
                prices = "#{dataSource.prices}"
            }
            stockCheckEngine(StockCheckEngineMock.class) {
                testData = "#{dataSource.buildStockData()}"
            }
            paymentEngine(PaymentEngineMock.class, "#{T(com.instil.di.groovy.beans.SampleDataSource).buildBannedCards()}") {
            }
            shop(SampleShop) {
                paymentEngine = paymentEngine
                stockCheckEngine = stockCheckEngine
                pricingEngine = pricingEngine
                shopName = "#{dataSource.CEO.toUpperCase() == 'TIM COOK' ? 'Apple' : 'Microsoft'}"
                openDuringWeekends = "#{dataSource.openDays matches '.*(Saturday|Sunday).*'}"
                juniorStaff = "#{dataSource.staff.?[salary < 50000.0]}"
                seniorStaff = "#{dataSource.staff.?[salary >= 50000.0]}"
                staffNames = "#{dataSource.staff.![name]}"
            }
        }
    }
}

