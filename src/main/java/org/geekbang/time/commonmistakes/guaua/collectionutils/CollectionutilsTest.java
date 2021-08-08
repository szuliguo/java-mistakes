package org.geekbang.time.commonmistakes.guaua.collectionutils;

import com.google.common.base.Function;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import org.junit.Test;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Legal
 * @date 2020/12/22
 * http://bins.top/teh/2019/03/26/Google-Guava-usage.html
 */
public class CollectionutilsTest {

    @Test
    public void listTest() {
        //推断范型，初始化元素和容量的静态工厂方法
        List<String> colorList = Lists.newArrayList();
        List<Integer> intList = Ints.asList(4, 5, 6);
        List<String> theseElements = Lists.newArrayList("alpha", "beta", "gamma");
        List<String> exactly100 = Lists.newArrayListWithCapacity(100);
        List<String> approx100 = Lists.newArrayListWithExpectedSize(100);


        //反转List
        List<String> thoseElements = Lists.reverse(theseElements);
        //指定大小分割
        List<List<String>> conList = Lists.partition(thoseElements, 3);

        //Iterables工具类连接两个Iterable（集合）
        Iterable<Integer> concatenated = Iterables.concat(Ints.asList(1, 2, 3), Ints.asList(4, 5, 6));

        //集合数据转换
        List<String> strList = Lists.newArrayList("1", "2", "3");
        List<Integer> newList = Lists.transform(strList, new Function<String, Integer>() {
            @Override
            public Integer apply(String input) {
                return Integer.valueOf(input);
            }
        });

    }

    @Test
    public void setTest() {

        //推断范型，初始化元素和容量的静态工厂方法
        Set<Integer> sets = Sets.newHashSet(1, 2, 3, 4, 5);
        Set<Integer> sets2 = Sets.newHashSet(4, 5, 6, 7, 8);
        Set<String> setExpected = Sets.newHashSetWithExpectedSize(100);


        // 交集
        Sets.SetView<Integer> intersection = Sets.intersection(sets, sets2);
       // 差集
        Sets.SetView<Integer> setDiff = Sets.difference(sets, sets2);
       // 并集
        Sets.SetView<Integer> union = Sets.union(sets, sets2);
       //笛卡尔积
        /**
         * result:
         * [[gerbil, apple], [gerbil, orange], [gerbil, banana], [hamster, apple], [hamster, orange], [hamster, banana]]
         */
        Set<String> animals = ImmutableSet.of("gerbil", "hamster");
        Set<String> fruits = ImmutableSet.of("apple", "orange", "banana");
        Set<List<String>> product = Sets.cartesianProduct(animals, fruits);
        System.out.println(product);
    }

    @Test
    public void mapsTest() {

        Map<String, String> stringMap = Maps.newLinkedHashMap();
        //两个map比较
        Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> right = ImmutableMap.of("b", 2, "c", 4, "d", 4);
        //Maps.difference(Map, Map)用来比较两个Map以获取所有不同点。该方法返回MapDifference对象，把不同点的维恩图分解
        MapDifference<String, Integer> mapDiff = Maps.difference(left, right);
        //找到键值对都相等的 {"b" => 2}
        mapDiff.entriesInCommon();
       //找到键相等值不相等的 {"c" => 3},{"c" => 4}
        mapDiff.entriesDiffering();
        //找到只存在于左边的，匹配key {"a" => 1}
        mapDiff.entriesOnlyOnLeft();
         //找到只存在于右边的，匹配key {"d" => 5}
        mapDiff.entriesOnlyOnRight();
        //两个map是否相等 false
        System.out.println(mapDiff.areEqual());

        //集合数据转换
        List<String> strList = Lists.newArrayList("1", "2", "3");
        ImmutableMap<String, String> newMap = Maps.uniqueIndex(strList, new Function<String, String>() {
            @Override
            public @Nullable String apply(@Nullable String input) {
                return input;
            }
        });


    }
}
