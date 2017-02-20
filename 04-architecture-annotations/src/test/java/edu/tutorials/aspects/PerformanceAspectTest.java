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
public class PerformanceAspectTest {

    @Autowired
    private PerformanceAspect performanceAspect;

    @Autowired
    private MyService myService;

    @Autowired
    private MyRepository myRepository;

    @Before
    public void setUp() {
        performanceAspect.resetCalled();
    }

    @Test
    public void performanceIsCalledForRepositories() throws Exception {
        assertFalse(performanceAspect.isCalled());
        myRepository.doIt();
        assertTrue(performanceAspect.isCalled());
    }

    @Test
    public void performanceIsCalledForServices() throws Exception {
        assertFalse(performanceAspect.isCalled());
        myService.doIt();
        assertTrue(performanceAspect.isCalled());
    }
}