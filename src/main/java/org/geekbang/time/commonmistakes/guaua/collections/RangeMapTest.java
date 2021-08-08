package org.geekbang.time.commonmistakes.guaua.collections;

import com.google.common.collect.*;
import org.hibernate.id.IntegralDataTypeHolder;
import org.junit.Test;

import java.util.*;

/**
 * @author Legal
 * @date 2020/12/22
 * RangeMap: 一个区间映射到某个值
 *
 * https://www.cnblogs.com/houzheng/p/10915314.html#_label6
 *
 * detail:
 * https://techlog.cn/article/list/10183162
 */
public class RangeMapTest {

    @Test
    public void rangeMapTest() {



        RangeMap<Integer, String> test = TreeRangeMap.create();
        test.putCoalescing(Range.closedOpen(5, 10), "at_home");
        test.putCoalescing(Range.closedOpen(1, 5), "at_home");

        System.out.println(test.get(6));

        Map<Range<Integer>, String>  mapTest = test.asMapOfRanges();
        System.out.println(mapTest.toString());

        Map<String, RangeMap<Integer, String>> mapTest2 = new HashMap<>();
        mapTest2.put("", test);
        System.out.println(mapTest2.get(""));


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
        //
        rangeMap.put(Range.closed(1, 13), "fooo");

         rangeMap.put(Range.closed(23, 30), "fp");

         rangeMap.put(Range.closed(14, 18), "ff");

         rangeMap.remove(Range.closed(12, 20));

         //是否连结俩个 range
         rangeMap.putCoalescing(Range.closed(13, 14), "fooo");

        Range<Integer> range2 = rangeMap.span();
        System.out.println("range2" + range2.toString());

        //获取range的map
       Map<Range<Integer>, String>  map = rangeMap.asMapOfRanges();
       System.out.println(map.toString());

       //获取range的逆序map
        Map<Range<Integer>, String> descendingMap = rangeMap.asDescendingMapOfRanges();
        System.out.println(descendingMap.toString());

       //值处于哪一段
       Map.Entry<Range<Integer>, String> entry  = rangeMap.getEntry(2);
       System.out.println("entry" + entry);

       // subRange
        RangeMap<Integer, String> subRange = rangeMap.subRangeMap(Range.closed(1, 20));
        System.out.println("subRange" + subRange.toString());


       // range...
        Set<Range<Integer>>  set =  map.keySet();
        for (Range<Integer> range: set) {
            System.out.println("lower-->"+ range.lowerEndpoint() + ";upper-->" + range.upperEndpoint());
            System.out.println("contains -> " + range.contains(1));
        }

        RangeSet<Integer> rangeSet = TreeRangeSet.create();


        RangeMap<Person, String> personRange = TreeRangeMap.create();

        Person p1 = new Person(0, 2);
        Person p2 = new Person(0, 4);

        Person p3 = new Person(-1, 0);
        Person p4 = new Person(-1, 1);

        personRange.putCoalescing(Range.closedOpen(p1, p2), "at_home");
        personRange.putCoalescing(Range.closed(p3, p4), "priority2");


        System.out.println(personRange.toString());


        RangeMap<Integer, String> numRange = TreeRangeMap.create();
        numRange.putCoalescing(Range.closedOpen(0, 0), "leaving_home");
        numRange.putCoalescing(Range.closedOpen(1, 7), "at_home");
        numRange.putCoalescing(Range.closedOpen(2,6), "leaving_home");

        System.out.println(numRange.toString());

        Map.Entry<Range<Integer>, String> entry2  = numRange.getEntry(6);
        System.out.println("entry2" + entry2);

        Range<Integer> range3 = numRange.span();
        System.out.println("numRange" + range3.toString());


        Map<Range<Integer>, String>  map2 = numRange.asMapOfRanges();
        // range...
        Set<Range<Integer>>  set2 =  map2.keySet();
        for (Range<Integer> range: set2) {
            System.out.println("lower-->"+ range.lowerEndpoint() + ";upper-->" + range.upperEndpoint());
        }




    }

    class Person implements Comparable<Person> {
        private int priority;
        private int age;


        Person(int priority, int age) {
            this.priority = priority;
            this.age = age;
        }

        /**
         * 实现Comparable接口，在类的内部实现比较逻辑，通过覆盖compareTo方法，如此一来，自定义的两个类可以比较大小
         * 将自定义的类放在集合类中，可以使用Collections的sort来自然排序，不用提供比较器。自然排序的实现即compareTo方法
         */
        @Override
        public int compareTo(Person anotherPerson) {
            // 先比较name的大小，若一样再比较age的大小

            // 使用字符串的比较
            int flag = age - anotherPerson.getAge();
            if (flag == 0) {
                // 名字相等，则比较年龄
                return priority - anotherPerson.getPriority();

            } else {
                // 名字不一样，返回名字比较结果
                return flag;
            }
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "priority=" + priority +
                    ", age=" + age +
                    '}';
        }
    }



}
