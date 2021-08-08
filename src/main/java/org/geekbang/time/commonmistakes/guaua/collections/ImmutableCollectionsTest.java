package org.geekbang.time.commonmistakes.guaua.collections;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.util.*;

/**
 * @author Legal
 * @date 2020/12/21
 */
public class ImmutableCollectionsTest {

    /**
     * method 1 : 使用 of 产生不可变集合
     */
    public static final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of("red", "orange");

    class Foo {
        Set<String> bars;

        Foo(Set<String> bars) {
            this.bars = ImmutableSet.copyOf(bars);
        }
    }

    /**
     *  method 2: 使用 copyof
     */
    @Test
    public void testGenerateOf() {
        List<String> list = new ArrayList<>();
        list.add("a");
        List<String> unModifyList = ImmutableList.copyOf(list);
    }

    /**
     * method 3: 使用 builder
     */
    @Test
    public void testGenerateBuilder() {

        final ImmutableSet<String> colors = ImmutableSet.<String>builder()
                .add("RED")
                .build();

    }

    /**
     * 使用 jdk 的 unmodifiableList，原生引用的改变会
     * 影响到unmodifiableList。unmodifiableList并
     * 不是真正意义上的不可变
     */
    @Test
    public void testJdkImmutableCollections() {

        List<String> list = new ArrayList<>();
        list.add("1");
        List<String> unModifyList = Collections.unmodifiableList(list);
        System.out.println(unModifyList.toString());
        list.add("2");
        System.out.println(unModifyList.toString());
    }

    /**
     * ImmutableList.copyOf
     */
    @Test
    public void testGuauaImmutableCollections() {

        List<String> list = new ArrayList<>();
        list.add("a");
        List<String> unModifyList = ImmutableList.copyOf(list);
        System.out.println(unModifyList.toString());
        list.add("b");
        System.out.println(unModifyList.toString());
    }

    /**
     * Arrays.asList 的底层实现是 Arrays.ArrayList(a);
     * 但是此 ArrayList 不是我们平常使用的 ArrayList
     * 其内部是不可变的 private final E[] a
     */
    @Test
    public void testAsList() {

        List<String> list = Arrays.asList("a");
//        报错
//        list.add("b");
//        list.add("c");
    }
}
