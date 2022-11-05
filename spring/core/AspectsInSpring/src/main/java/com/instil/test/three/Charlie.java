package com.instil.test.three;

import com.instil.test.Note;
import com.instil.test.Marker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

@Component
public class Charlie implements Collection<String>, Marker {
    public Charlie() {
        System.out.println("Call to Charlie.<init>");
    }


    @Override
    @Note
    public int size() {
        System.out.println("Call to Charlie.size");
        return 0;
    }

    @Override
    public boolean isEmpty() {
        System.out.println("Call to Charlie.isEmpty");
        return false;
    }

    @Override
    @Note
    public boolean contains(Object o) {
        System.out.println("Call to Charlie.contains");
        return false;
    }

    @Override
    public Iterator<String> iterator() {
        System.out.println("Call to Charlie.iterator");
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
        System.out.println("Call to Charlie.toArray()");
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        System.out.println("Call to Charlie.toArray(T[])");
        return null;
    }

    @Override
    @Note
    public boolean add(String e) {
        System.out.println("Call to Charlie.add");
        return false;
    }

    @Override
    public boolean remove(Object o) {
        System.out.println("Call to Charlie.remove");
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        System.out.println("Call to Charlie.containsAll");
        return false;
    }

    @Override
    @Note
    public boolean addAll(Collection<? extends String> c) {
        System.out.println("Call to Charlie.addAll");
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        System.out.println("Call to Charlie.removeAll");
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        System.out.println("Call to Charlie.retainAll");
        return false;
    }

    @Override
    public void clear() {
        System.out.println("Call to Charlie.clear");
    }

}
