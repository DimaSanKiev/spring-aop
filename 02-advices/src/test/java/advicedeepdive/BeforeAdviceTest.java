package advicedeepdive;

import configuration.AdviceDeepDiveConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AdviceDeepDiveConfiguration.class)
public class BeforeAdviceTest {

    @Autowired
    private BeforeAdvice beforeAspect;

    @Autowired
    private SimpleService simpleService;

    @Before
    public void reset() {
        beforeAspect.reset();
    }

    @Test
    public void beforeIsCalledIfCorrectMethodIsCalled() {
        assertFalse(beforeAspect.isBeforeCalled());
        simpleService.doSomething();
        assertTrue(beforeAspect.isBeforeCalled());
    }

    @Test
    public void beforeIsNotCalledIfDifferentMethodIsCalled() {
        assertFalse(beforeAspect.isBeforeCalled());
        simpleService.returnsString();
        assertFalse(beforeAspect.isBeforeCalled());
    }

}
