package org.geekbang.time.commonmistakes.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Legal
 * @date 2020/10/14
 */
public class BaseService {

    private Class<?> entityClass;
    private String entityClassName;

    public BaseService() {

        Type e = this.getClass().getGenericSuperclass();
        if (e instanceof ParameterizedType) {
           this.entityClass = (Class<?>) (((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
           this.entityClassName = this.entityClass.getSimpleName();
        }
    }
}
