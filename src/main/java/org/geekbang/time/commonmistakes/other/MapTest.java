package org.geekbang.time.commonmistakes.other;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.geekbang.time.commonmistakes.test.CategoryGroupTimerVO;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.partitioningBy;

/**
 * @author Legal
 * @date 2020/12/10
 */
public class MapTest {

    public static  void main(String[] args) {

        HashMap<String, Object> dpMap = Maps.newHashMap();
        System.out.println(dpMap.getOrDefault("a", "ab"));

        Optional.ofNullable(dpMap).map(o -> o.keySet().contains("123")).orElse(false);

        dpMap.put("110", "pause");
        dpMap.put("120", 1);

        String dps = JSONObject.toJSONString(dpMap);
        System.out.println(dps);

        System.out.println(dps.contains("130"));

        String str = null;


        JSONObject jsonObject = JSONObject.parseObject(str);
        Map<String, Object> map = (Map<String, Object>) jsonObject;
        //Map<String, Object> map = new HashMap<>();
        System.out.println(Optional.ofNullable(map).map(o -> o.keySet().contains("110")).orElse(false));


        Map<String, List<String>> manualMap = Maps.newHashMap();
        List<String> listmap = new ArrayList<>();
        listmap.add("a");
       // manualMap.put("1", null);

        List<String> loopsTimers = manualMap.getOrDefault("a", new ArrayList<>(1));
        System.out.println("loopsTimers" + loopsTimers.toString());
        List<String> manualTimers = loopsTimers.stream()
                .filter(str2 -> str2.equalsIgnoreCase("c"))
                .collect(Collectors.toList());
        System.out.println(manualTimers);


        String s = "a";
        s.concat("");

        ArrayList<String> list = null;
        if (Objects.isNull(list)) {
            list = new ArrayList<>();
        }

        list.add("a");

        HashMap<String, String> map3 = new HashMap<>(10);
        System.out.println(map3.getOrDefault("a", "a"));

        List<Integer> list4 = Collections.emptyList();

        Map<Boolean, List<Integer>> map2 =  list4.stream()
               .collect(partitioningBy(i -> i > 0));
        System.out.println(map2.get(true));
        System.out.println(map2.get(false));





    }
}



class Son {

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}