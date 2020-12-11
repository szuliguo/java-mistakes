package org.geekbang.time.commonmistakes.guaua;

import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;

/**
 * @author Legal
 * @date 2020/10/14
 */
public class RangeSetTest {

    public static void main(String[] args) {
        testRangeSet();
    }

    /**
     * rangeSet的功能包括 遍历元素，得到互补元素，某个元素是否包含在rangeSet中 等。
     */
    public static void testRangeSet() {

        RangeSet rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10));
        System.out.print(rangeSet);

    }
}
