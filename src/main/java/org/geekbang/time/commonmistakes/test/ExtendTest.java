package org.geekbang.time.commonmistakes.test;

/**
 * @author Legal
 * @date 2021/2/25
 */
public class ExtendTest {

    public static void main(String[] args) {

        Parent son = new Son();
        System.out.println(son.getDesc());

    }

}
