package com.instil.test.one;

import com.instil.test.Marker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

@Component
public class Alpha implements Collection<String>, Marker {
    public Alpha() {
        System.out.println("Call to TestAlpha.<init>");
    }

    @Override
    public int size() {
        System.out.println("Call to Alpha.size");
        return 0;
    }

    @Override
    public boolean isEmpty() {
        System.out.println("Call to Alpha.isEmpty");
        return false;
    }

    @Override
    public boolean contains(Object o) {
        System.out.println("Call to Alpha.contains");
        return false;
    }

    @Override
    public Iterator<String> iterator() {
        System.out.println("Call to Alpha.iterator");
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
        System.out.println("Call to Alpha.toArray()");
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        System.out.println("Call to Alpha.toArray(T[])");
        return null;
    }

    @Override
    public boolean add(String e) {
        System.out.println("Call to Alpha.add");
        return false;
    }

    @Override
    public boolean remove(Object o) {
        System.out.println("Call to Alpha.remove");
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        System.out.println("Call to Alpha.containsAll");
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        System.out.println("Call to Alpha.addAll");
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        System.out.println("Call to Alpha.removeAll");
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        System.out.println("Call to Alpha.retainAll");
        return false;
    }

    @Override
    public void clear() {
        System.out.println("Call to Alpha.clear");
    }

}
