package edu.tutorials.aspect;

public class CallTracker {

    private boolean called;

    public boolean isCalled() {
        return called;
    }

    public void resetCalled() {
        called = false;
    }

    public void trackCall() {
        called = true;
    }
}
