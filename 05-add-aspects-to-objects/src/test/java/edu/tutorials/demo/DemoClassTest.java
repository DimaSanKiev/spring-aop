package edu.tutorials.demo;

import configuration.SystemConfiguration;
import edu.tutorials.aspect.DemoAspect;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SystemConfiguration.class)
public class DemoClassTest {

    @Autowired
    private DemoAspect demoAspect;

    @Autowired
    private DemoClass demoClass;

    @Before
    public void setUp() {
        demoAspect.resetCalled();
    }

    @Test
    public void directCallToAdviceMethodIsTraced() throws Exception {
        assertFalse(demoAspect.isCalled());
        demoClass.adviceMethod();
        assertTrue(demoAspect.isCalled());
    }

    @Test
    public void indirectCallToAdviceMethodIsNotTraced() throws Exception {
        assertFalse(demoAspect.isCalled());
        demoClass.callTheAdviceMethod();
        assertFalse(demoAspect.isCalled());
    }
}