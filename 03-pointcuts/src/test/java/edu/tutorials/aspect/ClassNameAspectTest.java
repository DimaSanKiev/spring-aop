package edu.tutorials.aspect;

import edu.tutorials.configuration.SystemConfiguration;
import edu.tutorials.repository.SimpleRepository;
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
public class ClassNameAspectTest {

    @Autowired
    private ClassNameAspect aroundTracingAspect;

    @Autowired
    private SimpleService simpleService;

    @Autowired
    private SimpleRepository simpleRepository;

    @Before
    public void setUp() {
        aroundTracingAspect.resetCalled();
    }

    @Test
    public void tracingOnServiceIsCalled() {
        assertThat(aroundTracingAspect.getCalled(), is(0));
        simpleService.doSomething();
        assertThat(aroundTracingAspect.getCalled(), is(1));
    }

    @Test
    public void tracingOnRepositoryIsNotCalled() {
        assertThat(aroundTracingAspect.getCalled(), is(0));
        simpleRepository.doSomething();
        assertThat(aroundTracingAspect.getCalled(), is(0));
    }

}
