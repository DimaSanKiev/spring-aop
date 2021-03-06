package advicedeepdive;

import configuration.AdviceDeepDiveConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AdviceDeepDiveConfiguration.class)
public class AroundAdviceTest {

    @Autowired
    private AroundAdvice aroundAspect;

    @Autowired
    private SimpleService simpleService;

    @Before
    public void reset() {
        aroundAspect.reset();
    }

    @Test
    public void aroundAdviceIsCalledForSimpleMethod() {
        assertFalse(aroundAspect.isCalled());
        simpleService.doSomething();
        assertTrue(aroundAspect.isCalled());
    }

    @Test(expected = RuntimeException.class)
    public void aroundAdviceIsCalledIfExceptionIsThrown() {
        assertFalse(aroundAspect.isCalled());
        try {
            simpleService.throwsRuntimeException();
        } finally {
            assertTrue(aroundAspect.isCalled());
        }
    }

    @Test
    public void aroundAdviceIsCalledIfValueIsReturned() {
        assertFalse(aroundAspect.isCalled());
        assertThat(simpleService.returnsString(), equalTo("42"));
        assertTrue(aroundAspect.isCalled());
    }

}
