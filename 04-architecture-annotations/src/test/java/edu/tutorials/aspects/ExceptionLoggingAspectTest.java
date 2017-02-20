package edu.tutorials.aspects;

import edu.tutorials.repository.MyRepository;
import edu.tutorials.service.MyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/system-configuration.xml")
public class ExceptionLoggingAspectTest {

    @Autowired
    private ExceptionLoggingAspect exceptionLoggingAspect;

    @Autowired
    private MyRepository myRepository;

    @Before
    public void setUp() {
        exceptionLoggingAspect.resetCalled();
    }

    @Test
    public void exceptionLoggingIsNotCalledIfNoExceptionIsThrown() throws Exception {
        assertFalse(exceptionLoggingAspect.isCalled());
        myRepository.doIt();
        assertFalse(exceptionLoggingAspect.isCalled());
    }

    @Test(expected = RuntimeException.class)
    public void exceptionLoggingIsCalledIfExceptionIsThrown() throws Exception {
        assertFalse(exceptionLoggingAspect.isCalled());
        try {
            myRepository.throwException();
        } finally {
            assertTrue(exceptionLoggingAspect.isCalled());
        }
    }
}