package org.geekbang.time.commonmistakes.shallowsize;

import org.apache.lucene.util.RamUsageEstimator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Legal
 * @date 2021/1/30
 */
public class Test {

    public static void main(String[] args) {

        Integer i = new Integer(999);
        System.out.println(RamUsageEstimator.shallowSizeOf(i));

        Map map = new HashMap(1);
        System.out.println(RamUsageEstimator.shallowSizeOf(map));

    }
}
