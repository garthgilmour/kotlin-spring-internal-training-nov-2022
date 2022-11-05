package com.instil.aspects;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
public class Pointcuts {
    @Pointcut("within(com.instil.test..*)")
    public void anyJoinPointInTestPackages() {
    }

    @Pointcut("this(com.instil.test.Marker)")
    public void anyJoinPointInMarkerType() {
    }

    @Pointcut("args(java.lang.String,java.lang.Integer)")
    public void anyJoinPointTakingStringAndInteger() {
    }

    @Pointcut("execution(* com.instil.test.one.*.*(..))")
    public void anyJoinPointInFirstTestPackage() {
    }

    @Pointcut("execution(* com.instil.test.two.*.*(..))")
    public void anyJoinPointInSecondTestPackage() {
    }

    @Pointcut("execution(* com.instil.test.three.*.*(..))")
    public void anyJoinPointInThirdTestPackage() {
    }

    @Pointcut("this(com.instil.test.Marker) && args(param1)")
    public void parameterPassing(String param1) {
    }

    @Pointcut("this(com.instil.test.Marker) && @annotation(com.instil.test.Note)")
    public void annotations() {
    }
}
