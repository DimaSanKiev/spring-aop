package edu.tutorials.service;

import annotation.Trace;
import org.springframework.stereotype.Service;

@Service
public class SimpleService {

    public void doSomething() {
    }

    @Trace
    public void annotated() {
    }
}
