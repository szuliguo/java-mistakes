package org.geekbang.time.commonmistakes.java8;

import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LambdaTest {

    @Test
    public void lambdavsanonymousclass() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello1");
            }
        }).start();

        new Thread(() -> System.out.println("hello2")).start();
    }

    @Test
    public void functionalInterfaces() {
        //可以看一下java.util.function包
        Supplier<String> supplier = String::new;
        Supplier<String> stringSupplier = () -> "OK";

        //Predicate的例子
        Predicate<Integer> positiveNumber = i -> i > 0;
        Predicate<Integer> evenNumber = i -> i % 2 == 0;
        assertTrue(positiveNumber.and(evenNumber).test(2));

        //Consumer的例子，输出两行abcdefg
        Consumer<String> println = System.out::println;
        println.andThen(println).accept("abcdefg");

        //Function的例子
        Function<String, String> upperCase = String::toUpperCase;
        Function<String, String> duplicate = s -> s.concat(s);
        assertThat(upperCase.andThen(duplicate).apply("test"), is("TESTTEST"));

        //Supplier的例子
        Supplier<Integer> random = () -> ThreadLocalRandom.current().nextInt();
        System.out.println(random.get());

        //BinaryOperator
        BinaryOperator<Integer> add = Integer::sum;
        BinaryOperator<Integer> subtraction = (a, b) -> a - b;
        assertThat(subtraction.apply(add.apply(1, 2), 3), is(0));
    }

    @Test
    public void functionalInterfaces2() {

        // supplier 是一个提供资源的函数式接口，无任何输入，有一个输出
        Supplier<String> stringSupplier = () -> "OK";
        Supplier<String> supplier = String::new;
        Supplier<Integer> random = () -> ThreadLocalRandom.current().nextInt();
        System.out.println(random.get());

        //predicate 输入一个参数，返回布尔值
        Predicate<Integer> positiveNumber  = i -> i > 0;
        Predicate<Integer> evenNumber = i -> i % 2 == 0;
        assertTrue(positiveNumber.and(evenNumber).test(2));

        //consumer 输入一个参数，但是无参数输出
        Consumer<String> println = x -> System.out.println(x);
        println.andThen(println).accept("abcdefg");

        //function 接口是输入一个数据，经过计算以后输出一个数据
        Function<String, String> upperCase = String::toUpperCase;
        Function<String, String> duplicate = s -> s.concat(s);
        assertThat(upperCase.andThen(duplicate).apply("test"), is("TESTTEST"));


        //此接口是输入俩个同类型的参数，输出一个同类型参数的接口
        BinaryOperator<Integer> add = Integer::sum;
        BinaryOperator<Integer> subtraction = (a, b) -> a - b;
        assertThat(subtraction.apply(add.apply(1, 2), 3), is(0));
    }
}
