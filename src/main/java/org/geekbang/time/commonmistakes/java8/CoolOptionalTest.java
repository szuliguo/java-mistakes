package org.geekbang.time.commonmistakes.java8;

import org.junit.Test;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class CoolOptionalTest {

    @Test(expected = IllegalArgumentException.class)
    public void optional() {
        //OptionalInt
        Integer a = 1;
        System.out.println(OptionalInt.of(a).orElse(2));
        //获取值
        assertThat(Optional.of(1).get(), is(1));
        //如果为null,那么就为另外一个值
        assertThat(Optional.ofNullable(null).orElse("A"), is("A"));
        //值是否为null
        assertTrue(OptionalDouble.of(1).isPresent());
        //值是否为null
        assertFalse(OptionalDouble.empty().isPresent());
        //map
        assertThat(Optional.of(1).map(Math::incrementExact).get(), is(2));
        //filter
        assertThat(Optional.of(1).filter(integer -> integer % 2 == 0).orElse(null), is(nullValue()));
        //抛异常
        Optional.empty().orElseThrow(IllegalArgumentException::new);
        //抛异常
        Optional.ofNullable(null).orElseThrow(IllegalArgumentException::new);
        //map + orelse
        String str = Optional.ofNullable(1).map(String::valueOf).orElse("");

    }
}
