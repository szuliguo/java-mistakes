package org.geekbang.time.commonmistakes.java8;

import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * @author Legal
 * @date 2020/12/20
 */
public class FuntionTest {


    /**
     * https://blog.csdn.net/anLA_/article/details/78191494
     */
    @Test
    public  void testFunction() {

        //function
        Function<Integer, Integer> function = i -> i * 2;
        //compose
        Function<String, Integer> before = s -> Integer.valueOf(s);
        //andThen
        Function<Integer, String> after = integer -> "after" + String.valueOf(integer);

        assertThat(function.apply(2), is(4));
        assertThat(function.compose(before).apply("3"), is(6));
        assertThat(function.andThen(after).apply(1), is("after2"));




    }
}
