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
public class AfterReturningAdviceTest {

    @Autowired
    private AfterReturningAdvice afterReturningAdvice;

    @Autowired
    private SimpleService simpleService;

    @Before
    public void reset() {
        afterReturningAdvice.reset();
    }

    @Test
    public void afterReturningIsNotCalledIfReturnTypeDoesntMatch() {
        assertFalse(afterReturningAdvice.isAfterReturningCalled());
        simpleService.returnsInt();
        assertFalse(afterReturningAdvice.isAfterReturningCalled());
    }

    @Test(expected = RuntimeException.class)
    public void afterReturningIsNotCalledIfExceptionIsThrown() {
        assertFalse(afterReturningAdvice.isAfterReturningCalled());
        try {
            simpleService.returnsStringAndThrowsRuntimeException();
        } finally {
            assertFalse(afterReturningAdvice.isAfterReturningCalled());
        }
    }

    @Test
    public void afterReturningIsCalledIfReturnTypeMatches() {
        assertFalse(afterReturningAdvice.isAfterReturningCalled());
        simpleService.returnsString();
        assertTrue(afterReturningAdvice.isAfterReturningCalled());
    }

}
