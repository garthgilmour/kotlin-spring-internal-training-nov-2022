package com.instil.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

@Component
public class Book {
    @Value("${title}")
    private String title;
    @Value("${author}")
    private String author;
    @Value("${year}")
    private int year;
    @Value("${sequels}")
    private int sequels;

    @Override
    public String toString() {
        return new StringJoiner(", ", Book.class.getSimpleName() + "[", "]")
                .add("title='" + title + "'")
                .add("author='" + author + "'")
                .add("year=" + year)
                .add("sequels=" + sequels)
                .toString();
    }
}
