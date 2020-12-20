package org.geekbang.time.commonmistakes.generic;

/**
 * @author Legal
 * @date 2020/12/20
 * @param <T>
 */
public class GenericClassTest<T> {

    private  T t;

    public void add(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }


}
