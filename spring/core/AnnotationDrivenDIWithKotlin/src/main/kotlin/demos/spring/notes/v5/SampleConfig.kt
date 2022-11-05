package demos.spring.notes.v5

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SampleConfig {
    @Bean
    fun foo() = PricingEngineMock()

    @Bean
    fun bar() = PaymentEngineMock()

    @Bean
    fun zed() = StockCheckEngineMock()
}
