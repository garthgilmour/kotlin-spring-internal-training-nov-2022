package com.instil;

import com.instil.model.Employee;
import com.instil.model.Person;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

@Validated
@Configuration
@PropertySource("classpath:my-config.properties")

@ConfigurationProperties(prefix = "custom-config")
public class Settings {
    private int maxThreads;
    private String serverURL;
    private Person samplePerson;
    private Employee sampleEmployee;

    public int getMaxThreads() {
        return maxThreads;
    }

    public void setMaxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    public String getServerURL() {
        return serverURL;
    }

    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }

    public Person getSamplePerson() {
        return samplePerson;
    }

    public void setSamplePerson(Person samplePerson) {
        this.samplePerson = samplePerson;
    }

    public Employee getSampleEmployee() {
        return sampleEmployee;
    }

    public void setSampleEmployee(Employee sampleEmployee) {
        this.sampleEmployee = sampleEmployee;
    }
}
