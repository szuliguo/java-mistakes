package org.geekbang.time.commonmistakes.predicate;

import java.util.function.Predicate;

/**
 * @author Legal
 * @date 2020/12/7
 */
public class PredicateOr {

    public static void main(String[] args) {

        PredicateOr predicateOr = new PredicateOr();
        System.out.println(predicateOr.testOrMethod("zhangsan"
                , stringOne -> stringOne.equals("zhangsan111")
                ,stringTwo -> stringTwo.length() > 50
                ,stringThree -> stringThree.length() % 2 == 0));

    }


    public boolean testOrMethod(String stringOne, Predicate<String> predicateOne, Predicate<String> predicateTwo, Predicate<String> predicateThree) {

        return predicateOne.or(predicateTwo).or(predicateThree).test(stringOne);
    }
}
