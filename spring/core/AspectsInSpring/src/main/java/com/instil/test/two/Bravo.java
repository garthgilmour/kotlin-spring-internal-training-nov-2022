package com.instil.test.two;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

@Component
public class Bravo implements Collection<String> {
    public Bravo() {
        System.out.println("Call to TestBravo.<init>");
    }

    @Override
    public int size() {
        System.out.println("Call to Bravo.size");
        return 0;
    }

    @Override
    public boolean isEmpty() {
        System.out.println("Call to Bravo.isEmpty");
        return false;
    }

    @Override
    public boolean contains(Object o) {
        System.out.println("Call to Bravo.contains");
        return false;
    }

    @Override
    public Iterator<String> iterator() {
        System.out.println("Call to Bravo.iterator");
        return new Iterator<>() {
            public boolean hasNext() {
                return false;
            }

            @Override
            public String next() {
                return null;
            }
        };
    }

    @Override
    public Object[] toArray() {
        System.out.println("Call to Bravo.toArray()");
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        System.out.println("Call to Bravo.toArray(T[])");
        return null;
    }

    @Override
    public boolean add(String e) {
        System.out.println("Call to Bravo.add");
        return false;
    }

    @Override
    public boolean remove(Object o) {
        System.out.println("Call to Bravo.remove");
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        System.out.println("Call to Bravo.containsAll");
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        System.out.println("Call to Bravo.addAll");
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        System.out.println("Call to Bravo.removeAll");
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        System.out.println("Call to Bravo.retainAll");
        return false;
    }

    @Override
    public void clear() {
        System.out.println("Call to Bravo.clear");
    }

}
