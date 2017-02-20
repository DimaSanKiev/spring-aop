package edu.tutorials.repository;

import org.springframework.stereotype.Repository;

@Repository
public class MyRepository {

    public void doIt() {
    }

    public void throwException() {
        throw new RuntimeException();
    }
}
