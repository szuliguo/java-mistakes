package org.geekbang.time.commonmistakes.predicate;

import java.util.function.Predicate;

/**
 * @author Legal
 * @date 2020/12/7
 */
public class PredicateIsEquals {


    public static void main(String[] args) {

        PredicateIsEquals predicate = new PredicateIsEquals();

        String strNull = null;
        System.out.println(predicate.testMethodIsEquals("zhangsan","zhangsan"));
        System.out.println("~~~   ~~~   ~~~   ~~~");
        System.out.println(predicate.testMethodIsEquals("zhangsan","lisi"));
        System.out.println("~~~   ~~~   ~~~   ~~~");
        System.out.println(predicate.testMethodIsEquals(strNull,"zhangsan")); /* 我们来Debug一下这个程序*/

    }



    public boolean testMethodIsEquals(String strValue, String strValue2) {

        return Predicate.isEqual(strValue).test(strValue2);
    }

}
