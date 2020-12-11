package org.geekbang.time.commonmistakes.predicate;

import java.util.function.Predicate;

/**
 * @author Legal
 * @date 2020/12/7
 *
 * &&
 */
public class PredicateAND {

    public static void main(String[] args) {

        PredicateAND predicateAND = new PredicateAND();

        System.out.println(predicateAND.testAndMethod("zhangsan"
                , stringOne -> stringOne.equals("zhangsan")
                ,stringTwo -> stringTwo.length() >5
                ,stringThree -> stringThree.length() % 2 == 0));
    }


    public boolean testAndMethod(String stringOne, Predicate<String> predicateOne, Predicate<String> predicateTwo, Predicate<String> predicateThree) {

        return predicateOne.and(predicateTwo).and(predicateThree).test(stringOne);
    }
}
