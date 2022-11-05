package demos.spring.notes.v6

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SampleConfig {
    @Bean(name=["pricing"])
    fun foo() = PricingEngineMock()

    @Bean(name=["payment"])
    fun bar() = PaymentEngineMock()

    @Bean(name=["stock"])
    fun zed() = StockCheckEngineMock()
}
