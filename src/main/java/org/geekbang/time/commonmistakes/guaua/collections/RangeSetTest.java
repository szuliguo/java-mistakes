package org.geekbang.time.commonmistakes.guaua.collections;

import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;
import org.junit.Test;

import java.util.Iterator;

/**
 * @author Legal
 * @date 2020/10/14
 * https://google.github.io/guava/releases/27.0.1-jre/api/docs/com/google/common/collect/RangeSet.html
 */
public class RangeSetTest {

    public static void main(String[] args) {
    }

    /**
     * rangeSet的功能包括 遍历元素，得到互补元素，某个元素是否包含在rangeSet中 等。
     * 将一个区间添加到其中
     * 并且可以指定区间的开闭
     */

    @Test
    public  void testRangeSet() {

        //添加
        RangeSet rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10));
        rangeSet.add(Range.closed(0, 0));
        System.out.println(rangeSet);


        //遍历
        Iterator<Range> iterator = rangeSet.asRanges().iterator();
        while (iterator.hasNext()) {
            Range next = iterator.next();
            System.out.println(next);
        }

        //contains
        //result:true
        System.out.println(rangeSet.contains(1));
        //result:false
        System.out.println(rangeSet.contains(11));

    }
}
