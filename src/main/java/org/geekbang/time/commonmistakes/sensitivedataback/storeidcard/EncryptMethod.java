package org.geekbang.time.commonmistakes.sensitivedataback.storeidcard;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * @author Legal
 * @date 2020/10/10
 */

/**
 * 安全字段注解
 * 加在需要加密/解密的方法上
 * 实现自动加解密
 * 通过切面编程可以实现这个功能
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface EncryptMethod {
}
