package org.geekbang.time.commonmistakes.guaua.collections;

import com.google.common.collect.*;

import java.util.*;

/**
 * @author Legal
 * @date 2021/2/4
 */
public class RangeSetTest2 {

    public static void main(String[] args) {
        rangeSetTest2();
    }

    /**
     * 测试点击在家/离家数据
     */
    public static void rangeSetTest2() {
        RangeSet<TimePeriod> rangeSet = TreeRangeSet.create();

        TimePeriod t1 = new TimePeriod();
        t1.setStartTime(1);
        t1.setEndTime(2);
        t1.setLevel(0);

        TimePeriod t2 = new TimePeriod();
        t2.setStartTime(1);
        t2.setEndTime(10);
        t2.setLevel(-1);


        TimePeriod t3 = new TimePeriod();
        t3.setStartTime(1);
        t3.setEndTime(90);
        t3.setLevel(-2);

        TimePeriod t4 = new TimePeriod();
        t4.setStartTime(8);
        t4.setEndTime(20);
        t4.setLevel(-3);

        List<TimePeriod> list = new ArrayList<>();
        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);

        // 按照 level 进行排序
        Collections.sort(list);
        System.out.println(list.toString());

        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();


        //创建rangeMap
        for (int i = 0; i < list.size(); i++) {

            TimePeriod timePeriod = list.get(i);
            rangeMap.putCoalescing(Range.closed(timePeriod.getStartTime(), timePeriod.getEndTime()), "fooo" + i);
        }
        //{[1,10] => "foo"}
        //查看rangeMap的内容
        System.out.println("range2" + rangeMap.toString());
    }

    public static void rangeSetTest() {
        RangeSet<Integer> rangeSet = TreeRangeSet.create();

        rangeSet.add(Range.closed(1, 10));
        System.out.println("rangeSet:" + rangeSet);

        rangeSet.add(Range.closedOpen(11, 15));
        System.out.println("rangeSet:" + rangeSet);

        rangeSet.add(Range.open(15, 20));
        System.out.println("rangeSet:" + rangeSet);

        rangeSet.add(Range.openClosed(0, 0));
        System.out.println("rangeSet:" + rangeSet);

        rangeSet.remove(Range.open(5, 10));
        System.out.println("rangeSet:" + rangeSet);

        RangeSet<Integer> complement = rangeSet.complement();
        // [(-∞‥1), (5‥10), (10‥11), [15‥15], [20‥+∞)]
        System.out.println(complement);

        // rangeSet与一个range的交集
        RangeSet<Integer> subRangeSet = rangeSet.subRangeSet(Range.closed(15, 30));
        // subRangeSet:[(15‥20)]
        System.out.println("subRangeSet:" + subRangeSet);

        Set<Range<Integer>> set = rangeSet.asRanges();
        // set:[[1‥5], [10‥10], [11‥15), (15‥20)]
        System.out.println("set:" + set);

        // true
        System.out.println("contains:" + rangeSet.contains(10));

        // rangeContaining:[10‥10]
        System.out.println("rangeContaining:" + rangeSet.rangeContaining(10));

        // true
        System.out.println("encloses:" + rangeSet.encloses(Range.closed(1, 5)));

        // span:[1‥20)
        System.out.println("span:" + rangeSet.span());

    }


}

class TimePeriod implements Comparable<TimePeriod> {

    private Integer level;
    private Integer startTime;
    private Integer endTime;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    @Override
    public int compareTo(TimePeriod o) {
        return getLevel() - o.getLevel();
    }

    @Override
    public String toString() {
        return "TimePeriod{" +
                "level=" + level +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}


/**
 * 以 loops 为维度
 * "111111": 表示的是每天都循环这个
 * "1000000": 星期日
 * "0100000" : 星期一
 */
class Event {

    // normal， holiday, holiday_sat, manual, pause
    private String mode;
    // at_home/leaving_home
    private String status;
    // loops
    private String loops;
    //

}
