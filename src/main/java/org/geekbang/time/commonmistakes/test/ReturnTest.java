package org.geekbang.time.commonmistakes.test;

import org.springframework.util.StringUtils;

/**
 * @author Legal
 * @date 2021/2/1
 */
public class ReturnTest {
    public static void main(String[] args) {
        test(null);
    }

    public static  void test(String a) {

        if (StringUtils.isEmpty(a)) {
            return;
        }

        System.out.println("a");

    }
}

