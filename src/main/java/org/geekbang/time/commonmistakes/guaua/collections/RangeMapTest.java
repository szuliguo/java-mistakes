package org.geekbang.time.commonmistakes.guaua.collections;

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;
import org.junit.Test;

/**
 * @author Legal
 * @date 2020/12/22
 * RangeMap: 一个区间映射到某个值
 */
public class RangeMapTest {

    @Test
    public void rangeMapTest() {

        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        //{[1,10] => "foo"}
        rangeMap.put(Range.closed(1, 10), "foo");
        System.out.println(rangeMap.get(2));
        //{[1,3] => "foo", (3,6) => "bar", [6,10] => "foo"}
        rangeMap.put(Range.open(3, 6), "bar");
        //{[1,3] => "foo", (3,6) => "bar", [6,10] => "foo", (10,20) => "foo"}
        rangeMap.put(Range.open(10, 20), "foo");
        //{[1,3] => "foo", (3,5) => "bar", (11,20) => "foo"}
        rangeMap.remove(Range.closed(5, 11));

    }
}
