package org.geekbang.time.commonmistakes.test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Legal
 * @date 2020/11/16
 */
public class CompareTest {
    public static void main(String[] args) {

        /**
         * 从小到大
         */
        List<Long> array = Arrays.asList(1L, 0L, 2L, 23L, 5L);
        Iterator<Long> it = array.stream().sorted((t1, t2) -> t1.compareTo(t2)).iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

}
