package org.geekbang.time.commonmistakes.java8;

import org.apache.commons.lang.StringUtils;
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

        Optional<String> optionalEmpty = Optional.empty();
        System.out.println("optionalEmpty .." + optionalEmpty.isPresent());

        String value = null;
        String s = Optional.ofNullable(value)
                //.filter(StringUtils::isNotBlank)
                .map(String::valueOf)
                .orElse("1");
        System.out.println("s= " + s);
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
        int str = Optional.ofNullable("").map(Integer::valueOf).orElse(1);

    }
}
