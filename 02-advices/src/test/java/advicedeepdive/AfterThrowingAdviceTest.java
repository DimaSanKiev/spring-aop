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
public class AfterThrowingAdviceTest {

    @Autowired
    private AfterThrowingAdvice afterThrowingAdvice;

    @Autowired
    private SimpleService simpleService;

    @Before
    public void reset() {
        afterThrowingAdvice.reset();
    }

    @Test
    public void afterThrowingIsNotCalledIfMethodReturnSuccessfully() {
        assertFalse(afterThrowingAdvice.isAfterThrowingCalled());
        simpleService.doSomething();
        assertFalse(afterThrowingAdvice.isAfterThrowingCalled());
    }

    @Test(expected = RuntimeException.class)
    public void afterThrowingIsCalledIfMethodThrowsRuntimeException() {
        assertFalse(afterThrowingAdvice.isAfterThrowingCalled());
        try {
            simpleService.throwsRuntimeException();
        } finally {
            assertTrue(afterThrowingAdvice.isAfterThrowingCalled());
        }
    }

    @Test(expected = Exception.class)
    public void afterThrowingIsNotCalledIfMethodThrowsException() throws Exception {
        assertFalse(afterThrowingAdvice.isAfterThrowingCalled());
        try {
            simpleService.throwsException();
        } finally {
            assertFalse(afterThrowingAdvice.isAfterThrowingCalled());
        }
    }


}
