package com.instil;

import com.instil.model.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.BiConsumer;

@SpringBootApplication
public class ConfigApplication {

    @Bean
    CommandLineRunner runner(Settings settings, Book book) {
        BiConsumer<String, Object> console = (name, value) -> {
            System.out.printf("\t'%s' with value %s\n", name, value);
        };

        return args -> {
            System.out.println("\nCustom settings read from system property:");
            console.accept("Book Details", book);

            System.out.println("\nCustom settings read from properties file:");
            console.accept("Max Threads", settings.getMaxThreads());
            console.accept("Server URL", settings.getServerURL());
            console.accept("Sample Person", settings.getSamplePerson());
            console.accept("Sample Employee", settings.getSampleEmployee());
        };
    }

    public static void main(String[] args) {
        setSpringSpecificProperty();

        SpringApplication.run(ConfigApplication.class, args);
    }

    private static void setSpringSpecificProperty() {
        String sampleJSON = "{"
                + "\"title\":\"Dune\","
                + "\"author\":\"Frank Herbert\","
                + "\"year\":1965,"
                + "\"sequels\":5"
                + "}";
        System.setProperty("spring.application.json", sampleJSON);
    }
}
