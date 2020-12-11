package org.geekbang.time.commonmistakes.test;

import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.xdevapi.JsonArray;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Legal
 * @date 2020/11/30
 */
public class ListTest {


    public static void main(String[] args) {

        List<String> list3 = new ArrayList<>(1);
        if (CollectionUtils.isEmpty(list3)) {
            System.out.println("list3 is empty");
        }

        Map<String, String> map2 = Collections.EMPTY_MAP;
        map2.get("a");

        Map<String, List<String>> map = new HashMap<>();

        List<String> list2 = map.getOrDefault("1", new ArrayList<>());
        list2.add("a");

        A aa = new A();
        aa.setCount(1);

        List<A> list = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            A temp = new A();
            BeanUtils.copyProperties(aa, temp);
            temp.setName("a" + i);
            list.add(temp);
        }

        System.out.println(JSONObject.toJSONString(list));
        System.out.println(list.get(1).getName());

        //排序
        list = list.stream().filter((a) -> a.getCount() != 0)
                .sorted(((timer1, timer2) -> {
                    return timer1.getCount().compareTo(timer2.getCount());
                }))
                .collect(Collectors.toList());

        System.out.println(list.get(0).getCount());


        System.out.println("#############################");
        List<A> lists = new ArrayList<>();
        List<A> list1 = lists.stream()
                .filter(a -> a.getCount() > 1)
                .collect(Collectors.toList());
        System.out.println(list1);

    }

    static class A {
        private Integer count;
        private String name;

        public A() {
        }

        public A(Integer count, String name) {
            this.count = count;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    }
}
