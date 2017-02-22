package edu.tutorials.aspect;

import edu.tutorials.demo.DemoClass;
import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ManuallyAddedAdvice {

    @Test
    public void addedAdviceIsCalled() throws Exception {
        DemoAspect demoAspect = new DemoAspect();
        DemoClass originalObject = new DemoClass();
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory(
                originalObject);
        proxyFactory.addAspect(demoAspect);
        DemoClass proxy = proxyFactory.getProxy();

        assertFalse(demoAspect.isCalled());
        proxy.adviceMethod();
        assertTrue(demoAspect.isCalled());
    }
}
