package org.geekbang.time.commonmistakes.springpart1.aopmetrics;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 * TestAspectWithOrder10 相当于是一个切面
 * execution 这些相当于切点
 * 方法里面的相当于一些增强操作
 */
@Aspect
@Component
@Order(10)
@Slf4j
public class TestAspectWithOrder10 {

//    @Before("execution(* org.geekbang.time.commonmistakes.springpart1.aopmetrics.TestController.*(..))")
//    public void before(JoinPoint joinPoint) throws Throwable {
//        log.info("TestAspectWithOrder10 @Before");
//    }

    @After("@annotation(org.geekbang.time.commonmistakes.springpart1.aopmetrics.NoticeRequired)")
    public void after(JoinPoint joinPoint) throws Throwable {
        log.info("WkfTimerNoticeAspect @After");

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        Method method = signature.getMethod();
        log.info("method name :{} aspect", method.getName());


        NoticeRequired noticeRequired = signature.getMethod().getAnnotation(NoticeRequired.class);
        if (noticeRequired != null) {
            int type = noticeRequired.type();
            System.out.println("#############type########## = " + type);
        }


        //获取参数的名称
        String[] parameterNames = signature.getParameterNames();
        log.info("method name :{},parameterNames:{} ", method.getName(), parameterNames);

        //获取参数的下标
        int countIndex = ArrayUtils.indexOf(parameterNames, "count");

        if (countIndex == -1) {
            System.out.println("##########countIndex = -1");
        }

        //获取参数的值
        Object[] args = joinPoint.getArgs();
        Integer count = (Integer) args[countIndex];
        System.out.println("##########bizType = " + count);

    }

//    @Around("execution(* org.geekbang.time.commonmistakes.springpart1.aopmetrics.TestController.*(..))")
//    public Object around(ProceedingJoinPoint pjp) throws Throwable {
//        log.info("TestAspectWithOrder10 @Around before");
//        Object o = pjp.proceed();
//        log.info("TestAspectWithOrder10 @Around after");
//        return o;
//    }
}

