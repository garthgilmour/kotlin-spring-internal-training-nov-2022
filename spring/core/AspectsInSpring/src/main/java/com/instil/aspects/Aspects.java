package com.instil.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class Aspects {
    @After("com.instil.aspects.Pointcuts.anyJoinPointInTestPackages()")
    public void eg1() {
        System.out.println("\t\tAfter Advice 1");
    }

    @Before("com.instil.aspects.Pointcuts.anyJoinPointInTestPackages()")
    public void eg2() {
        System.out.println("\t\tBefore Advice 1");
    }

    @After("com.instil.aspects.Pointcuts.anyJoinPointInMarkerType()")
    public void eg3() {
        System.out.println("\t\tAfter Advice 2");
    }

    @Before("com.instil.aspects.Pointcuts.anyJoinPointInMarkerType()")
    public void eg4() {
        System.out.println("\t\tBefore Advice 2");
    }

    @After("com.instil.aspects.Pointcuts.anyJoinPointTakingStringAndInteger()")
    public void eg5() {
        System.out.println("\t\tAfter Advice 3");
    }

    @Before("com.instil.aspects.Pointcuts.anyJoinPointTakingStringAndInteger()")
    public void eg6() {
        System.out.println("\t\tBefore Advice 3");
    }

    @After("com.instil.aspects.Pointcuts.anyJoinPointInFirstTestPackage()")
    public void eg7() {
        System.out.println("\t\tAfter Advice 4");
    }

    @Before("com.instil.aspects.Pointcuts.anyJoinPointInFirstTestPackage()")
    public void eg8() {
        System.out.println("\t\tBefore Advice 4");
    }

    @After("com.instil.aspects.Pointcuts.anyJoinPointInSecondTestPackage()")
    public void eg9() {
        System.out.println("\t\tAfter Advice 5");
    }

    @Before("com.instil.aspects.Pointcuts.anyJoinPointInSecondTestPackage()")
    public void eg10() {
        System.out.println("\t\tBefore Advice 5");
    }

    @After("com.instil.aspects.Pointcuts.anyJoinPointInThirdTestPackage()")
    public void eg11() {
        System.out.println("\t\tAfter Advice 6");
    }

    @Before("com.instil.aspects.Pointcuts.anyJoinPointInThirdTestPackage()")
    public void eg12() {
        System.out.println("\t\tBefore Advice 6");
    }

    @After("com.instil.aspects.Pointcuts.parameterPassing(param1)")
    public void eg13(String param1) {
        System.out.println("\t\tAfter Advice 7 " + param1);
    }

    @Before("com.instil.aspects.Pointcuts.parameterPassing(param1)")
    public void eg14(String param1) {
        System.out.println("\t\tBefore Advice 7 " + param1);
    }

    @After("com.instil.aspects.Pointcuts.annotations()")
    public void eg15() {
        System.out.println("\t\tAfter Advice 8 ");
    }

    @Before("com.instil.aspects.Pointcuts.annotations()")
    public void eg16() {
        System.out.println("\t\tBefore Advice 8 ");
    }

    @Around("com.instil.aspects.Pointcuts.anyJoinPointInTestPackages()")
    public Object eg17(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature targetMethod = (MethodSignature) pjp.getSignature();
        Method actualMethod = pjp.getTarget().getClass().getMethod(targetMethod.getName(), targetMethod.getParameterTypes());

        String targetMethodStr = targetMethod.toShortString();
        String actualMethodStr = actualMethod.toGenericString();

        System.out.printf("\t\tIntercepted call to %s (actually '%s')\n", targetMethodStr, actualMethodStr);

        Object originalReturn = pjp.proceed();

        if (targetMethod.getReturnType() == Integer.TYPE) {
            return 123;
        }
        return originalReturn;
    }
}
