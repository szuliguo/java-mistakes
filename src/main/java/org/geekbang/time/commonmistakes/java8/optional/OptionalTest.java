package org.geekbang.time.commonmistakes.java8.optional;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;
import org.apache.commons.lang.StringUtils;
import org.apache.zookeeper.Op;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Legal
 * @date 2020/12/7
 *
 *
 * https://www.runoob.com/java/java8-optional-class.html
 */
public class OptionalTest {

    public static void main(String[] args) {

        Map<String, RangeMap<Long, String>> map = new HashMap<>();
        RangeMap<Long, String> rangeMap1 = TreeRangeMap.create();
        rangeMap1.put(Range.closedOpen(8L, 19L), "liguo");
        map.put("0", rangeMap1);

        RangeMap<Long, String> loopRangeMap = map.get("0");
        loopRangeMap.putCoalescing(Range.closedOpen(0L, 7L), "guo");


        for (Map.Entry<String, RangeMap<Long, String>> entry : map.entrySet()) {
            Map<Range<Long>, String> periodMap  = entry.getValue().asMapOfRanges();

            System.out.println(periodMap);
            System.out.println(periodMap.get(0L));
            if (Objects.isNull(periodMap.get(0L))) {
                System.out.println("is null");
            }

        }

        System.out.println(map.get("0").get(2L));


//        Map<String, Integer> map = new HashMap<String, Integer>();
//        map.put("118", 210);
//        map.put("119", 230);
//        System.out.println(JSONObject.toJSONString(map));

        System.out.println(StringUtils.isNumeric(""));

        System.out.println(getLeaveTemperOrDefault());
        String booleanString = null;
        System.out.println(Optional.ofNullable(booleanString)
                .map(Boolean::parseBoolean)
                .orElse(false));


        testOptionalList();

        testOptionalMap();

        OptionalTest optionalTest = new OptionalTest();

        Integer value1 = null;
        Integer value2 = new Integer(10);

        // Optional.ofNullable - 允许传递为 null 参数
        Optional<Integer> a = Optional.ofNullable(value1);

        // Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
        //optional.of 不允许值为null
        Optional<Integer> b = Optional.of(value2);
        System.out.println(optionalTest.sum(a, b));

    }

    public Integer sum(Optional<Integer> a, Optional<Integer> b) {

        // Optional.isPresent - 判断值是否存在

        System.out.println("第一个参数值存在: " + a.isPresent());
        System.out.println("第二个参数值存在: " + b.isPresent());

        // Optional.orElse - 如果值存在，返回它，否则返回默认值
        Integer value1 = a.orElse(new Integer(0));

        //Optional.get - 获取值，值需要存在
        Integer value2 = b.get();
        return value1 + value2;
    }

    public static void testOptionalMap() {

        String result = null;
        Map<String, Object> dpMap = new HashMap<>();
        dpMap.put("1", "11");
        Integer obj = Optional.ofNullable(dpMap.get("1")).map(o -> Integer.valueOf(o.toString())).orElse(1);


        System.out.println(obj);

    }

    public static void testOptionalList() {
        List<String> list = new ArrayList<>();
        list.add("a");
        List<String> list2 = Optional.ofNullable(list).orElse(Collections.emptyList())
                .stream()
                .collect(Collectors.toList());
        System.out.println(list2.toString());
    }


    /**
     * 获取离家温度
     */
    public static Integer getLeaveTemperOrDefault() {
        return Optional.ofNullable("2")
                .filter(StringUtils::isNotBlank)
                .map(Integer::valueOf)
                .orElse(1);
    }

    /**
     * 检查字符串是否是数字
     */
    private static boolean filterNotNumeric(String string) {
        return StringUtils.isNumeric(string);
    }


}
