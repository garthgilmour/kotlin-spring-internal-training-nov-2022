package com.instil;

import com.instil.test.SampleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class AspectsInSpringApplication {

	@Bean
	public CommandLineRunner runner(SampleService service) {
		return (args) -> service.doWork();
	}

	public static void main(String[] args) {
		SpringApplication.run(AspectsInSpringApplication.class, args);
	}

}
