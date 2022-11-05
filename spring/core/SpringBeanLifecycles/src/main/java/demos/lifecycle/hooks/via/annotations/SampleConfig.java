package demos.lifecycle.hooks.via.annotations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "demos.lifecycle.hooks.via.annotations")
public class SampleConfig {
}
