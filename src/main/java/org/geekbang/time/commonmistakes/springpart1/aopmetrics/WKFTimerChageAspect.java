package org.geekbang.time.commonmistakes.springpart1.aopmetrics;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class WKFTimerChageAspect {


    @After("execution(* org.geekbang.time.commonmistakes.springpart1.aopmetrics.TestController.*(..))")
    public void after(JoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String name = String.format("【%s】【%s】", signature.getDeclaringType().toString(), signature.toLongString());

        //先从方法上获取注解，方法上获取不到注解
        NoticeRequired noticeRequired = signature.getMethod().getAnnotation(NoticeRequired.class);
        if (noticeRequired != null) {
            log.info("TestAspectWithOrder10 @After");
        }
    }

}

