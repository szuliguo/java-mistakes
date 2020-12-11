package org.geekbang.time.commonmistakes.sensitivedataback.storeidcard;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 *
 * 参考：
 * https://www.jianshu.com/p/749259832204
 *
 *
 * 安全字段加密解密切面
 *
 * @author Legal
 * @date 2020/10/10
 */

@Order(Ordered.HIGHEST_PRECEDENCE)
@Aspect
@Component
public class EncryptFieldAop {

    Logger log = LoggerFactory.getLogger(this.getClass());

    private String secretKey;

    @Pointcut("@annotation(org.geekbang.time.commonmistakes.sensitivedataback.storeidcard.EncryptMethod)")
    public void annotationPointCut() {

    }

    @Around("annotationPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object responseObj = null;


        Object requestObj = joinPoint.getArgs()[0];
        try {
            handleEncrypt(requestObj);
            responseObj = joinPoint.proceed();
            handleDecrypt(responseObj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return responseObj;
    }

    /**
     * 处理解密
     * @param responseObj
     * @return
     * @throws IllegalAccessException
     */
    private Object handleDecrypt(Object responseObj) throws IllegalAccessException {

        if (Objects.isNull(responseObj)) {
            return null;
        }

        Field[] fields = responseObj.getClass().getDeclaredFields();
        for (Field field : fields) {
            boolean hasSecureField = field.isAnnotationPresent(EncryptField.class);
            if (hasSecureField) {
                field.setAccessible(true);
                String encryptValue = (String) field.get(responseObj);
                String plaintextValue = AseUtil.decrypt(encryptValue, secretKey);
                field.set(responseObj, plaintextValue);
            }
        }

        return responseObj;

    }


    /**
     * 处理加密
     * @param requestObj
     * @throws IllegalAccessException
     */
    private void handleEncrypt(Object requestObj) throws IllegalAccessException {

        if (Objects.isNull(requestObj)) {
            return;
        }

        Field[] fields = requestObj.getClass().getDeclaredFields();
        for (Field field : fields) {
            boolean hasSecureField = field.isAnnotationPresent(EncryptField.class);
            if (hasSecureField) {
                field.setAccessible(true);
                String plaintextValue = (String) field.get(requestObj);
                String encryptValue = AseUtil.encrypt(plaintextValue, secretKey);
                field.set(requestObj, encryptValue);
            }
        }
    }
}
