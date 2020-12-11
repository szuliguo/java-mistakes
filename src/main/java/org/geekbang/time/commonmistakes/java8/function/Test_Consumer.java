package org.geekbang.time.commonmistakes.java8.function;

import java.util.function.Consumer;

/**
 *
 * https://www.cnblogs.com/dgwblog/p/11739500.html
 *
 * @author Legal
 * @date 2020/12/9
 */
 class Test_Comsumer {
    public static void generateX(Consumer<String> consumer) {
        consumer.accept("hello consumer");
    }
    public static void main(String[] args) {
        generateX(s -> System.out.println(s));
    }
}
