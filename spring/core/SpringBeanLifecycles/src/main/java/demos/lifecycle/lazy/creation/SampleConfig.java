package demos.lifecycle.lazy.creation;

import demos.lifecycle.common.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@ComponentScan(basePackages = "demos.lifecycle.lazy.creation")
public class SampleConfig {

    @Lazy
    @Bean
    public PricingEngine pricing() {
        return new PricingEngineMock();
    }

    @Lazy
    @Bean
    public PaymentEngine payment() {
        return new PaymentEngineMock();
    }

    @Bean
    public StockCheckEngine stockCheck() {
        return new StockCheckEngineMock();
    }

    @Lazy
    @Bean
    public Shop shop() {
        return new SampleShop();
    }
}
