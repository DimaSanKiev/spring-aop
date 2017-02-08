package edu.tutorials.aspect;

import edu.tutorials.configuration.SystemConfiguration;
import edu.tutorials.service.SimpleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SystemConfiguration.class)
public class TraceAnnotationAspectTest {

    @Autowired
    private TraceAnnotationAspect traceAnnotationAspect;

    @Autowired
    private SimpleService simpleService;

    @Before
    public void setUp() {
        traceAnnotationAspect.resetCalled();
    }

    @Test
    public void tracingOnNotAnnotatedMethodIsNotCalled() {
        assertThat(traceAnnotationAspect.getCalled(), is(0));
        simpleService.doSomething();
        assertThat(traceAnnotationAspect.getCalled(), is(0));
    }

    @Test
    public void tracingOnAnnotatedMethodIsCalled() {
        assertThat(traceAnnotationAspect.getCalled(), is(0));
        simpleService.annotated();
        assertThat(traceAnnotationAspect.getCalled(), is(1));
    }

}
