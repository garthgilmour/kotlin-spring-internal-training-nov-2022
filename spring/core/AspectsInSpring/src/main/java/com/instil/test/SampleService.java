package com.instil.test;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SampleService {
    private final Collection<String> dependency1;
    private final Collection<String> dependency2;
    private final Collection<String> dependency3;

    public SampleService(
            @Qualifier("alpha")
            Collection<String> dependency1,
            @Qualifier("bravo")
            Collection<String> dependency2,
            @Qualifier("charlie")
            Collection<String> dependency3
    ) {
        this.dependency1 = dependency1;
        this.dependency2 = dependency2;
        this.dependency3 = dependency3;
    }

    private void useDependency(Collection<String> input) {
        System.out.println("----------------");
        System.out.println("Testing " + input.getClass().getName());
        System.out.println("\t 'isEmpty' returns " + input.isEmpty());
        System.out.println("\t 'remove' returns " + input.remove("foo"));
        System.out.println("\t 'add' returns " + input.add("foo"));
        System.out.println("\t 'toArray' returns " + input.toArray());
        System.out.println("\t 'size' returns " + input.size());
    }

    public void doWork() {
        useDependency(dependency1);
        useDependency(dependency2);
        useDependency(dependency3);
    }
}
