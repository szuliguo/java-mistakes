package org.geekbang.time.commonmistakes.predicate;

import java.util.function.Predicate;

/**
 * @author Legal
 * @date 2020/12/7
 * 相当于逻辑非 ！
 */
public class PredicateNegate {

    public static void main(String[] args) {

        PredicateNegate predicateNegate = new PredicateNegate();
        System.out.println(predicateNegate.testNageteMethod("zhangsan",stringOne -> stringOne.equals("zhangsan")));

    }


    public boolean testNageteMethod(String stringValue, Predicate<String> predicate) {
        return predicate.test(stringValue);
    }
}
