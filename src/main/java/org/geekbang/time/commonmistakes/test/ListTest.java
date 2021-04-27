package org.geekbang.time.commonmistakes.test;

import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.xdevapi.JsonArray;
import org.checkerframework.checker.nullness.Opt;
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

        A a11 = new A();
        a11.setName("a11");
        a11.setCount(1);

        String as = JSONObject.toJSONString(a11);
        a11.setCount(2);
        String as2 = JSONObject.toJSONString(a11);

        System.out.println(as);
        System.out.println(as2);

        A a12 = (A) a11.clone();
        a12.setCount(1);
        a12.setName("a12");

        System.out.println(a11.getName() + "-->" + a11.getCount());
        System.out.println(a12.getName() + "-->" + a12.getCount());

        List<A> aList = new ArrayList<>();
        A a1 = new A();
        a1.setName("a1");
        aList.add(a1);
        a1.setName("a2");
        aList.add(a1);
        System.out.println(JSONObject.toJSONString(aList));

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

        A a6 = new A(2, "name");
        List<A> aList2 = new ArrayList<>();
        aList2.add(a6);

        Optional<A> optionalA  = aList2.stream().filter(timer -> timer.getCount() == 2).findAny();
        if (optionalA.isPresent()) {
            System.out.println("##### anyTimer");
            System.out.println(optionalA);
        }

        System.out.println("##### no Timer");

        List<String> strList = Collections.emptyList();
        strList.forEach((s) -> {
            System.out.println();
        });




    }

    static class A implements Cloneable {
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

        @Override
        public Object clone() {
            A stu = null;
            try {
                stu = (A) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return stu;
        }
    }
}
