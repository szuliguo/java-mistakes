package org.geekbang.time.commonmistakes.sensitivedataback.storeidcard;

/**
 * @author Legal
 * @date 2020/10/10
 */

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 这个注解是加在字段上的。
 * 加在需要加密/解密的字段上
 */

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface EncryptField {

}