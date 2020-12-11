package org.geekbang.time.commonmistakes.java8.function;

import java.util.function.Supplier;

/**
 * @author Legal
 * @date 2020/12/10
 */
public class Test_Supplier {
    private static String test_Supplier(Supplier<String> suply) {
        return suply.get(); //供应者接口
    }
    public static void main(String[] args) {
        // 产生的数据作为 sout 作为输出
        System.out.println(test_Supplier(()->"产生数据"));

        System.out.println(String.valueOf(new Supplier<String>() {
            @Override
            public String get() {
                return "产生数据";
            }
        }));
    }
}
