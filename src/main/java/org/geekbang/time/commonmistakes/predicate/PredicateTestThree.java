package org.geekbang.time.commonmistakes.predicate;


import java.util.function.Predicate;

/**
 * @author Legal
 * @date 2020/12/7
 *
 * https://blog.csdn.net/qq_27416233/article/details/83418791
 */
public class PredicateTestThree {

    public static void main(String[] args) {

        PredicateTestThree predicate = new PredicateTestThree();

        System.out.println(predicate.judgeConditionByFunction(12345, value -> String.valueOf(value).length() > 5));
        System.out.println(predicate.judgeConditionByFunction(4, value -> value % 2 == 0));
        System.out.println(predicate.judgeConditionByFunction(-1, value -> value > 10));


    }

    public boolean judgeConditionByFunction(int value, Predicate<Integer> predicate) {
        return predicate.test(value);
    }
}
