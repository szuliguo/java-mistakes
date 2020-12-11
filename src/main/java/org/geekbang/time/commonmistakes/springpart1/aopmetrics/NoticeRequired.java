package org.geekbang.time.commonmistakes.springpart1.aopmetrics;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Legal
 * @date 2020/10/27
 */
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    public @interface NoticeRequired {

        int type();
    }
