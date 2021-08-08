package org.geekbang.time.commonmistakes.other;

import com.google.common.collect.Lists;
import org.geekbang.time.commonmistakes.test.CategoryGroupTimerVO;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.partitioningBy;

/**
 * @author Legal
 * @date 2020/12/12
 */
public class ListTest {

    public static void main(String[] args) {


        Map<String, Object> dpMap = new HashMap<>();
        dpMap.remove("a");
        dpMap.remove("b");

        Object o = null;
        System.out.println("".equalsIgnoreCase((String)o));

        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> list1 = null;
        // list.addAll(list1);

//        list.addAll(null);
        Optional.ofNullable(list1).ifPresent(list::addAll);

        ArrayList<room> arrayList = new ArrayList<>(0);
//        room room1 = new room(2, null);
//        arrayList.add(room1);

        String name = arrayList.stream()
                //.filter(firmware -> 0 == firmware.getAge())
                .findFirst()
                .map(room::getName)
                .orElse("");

        List<String> forList = null;
        Optional.ofNullable(forList)
                .orElse(Collections.emptyList())
                .forEach(e -> { } );


        List<Integer> integerList = Lists.newArrayList(1, 2, 20, 10, 8, 9);
        integerList = integerList.stream().filter(a -> a > 10).collect(Collectors.toList());
        System.out.println(integerList);
        integerList.add(0, 0);
        System.out.println(integerList);


        List<String> stringList = null;
        stringList = Optional.ofNullable(stringList).orElse(Lists.newArrayListWithCapacity(1));
        stringList.add("a");

        List<room> rooms = new ArrayList<room>();
        Map<Boolean, List<room>> map = Optional.ofNullable(rooms).orElse(Collections.emptyList())
                .stream()
                .collect(partitioningBy(obj -> obj.getName().equals("aa")));


        System.out.println("map" + map.get(false));
        System.out.println("map" + map.get(true));

        List<String> deviceIds = null;
        for (String devId : deviceIds) {
            System.out.println(devId);
        }









    }

    static class room {

        private Integer age;
        private String name;

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public room(Integer age, String name) {
            this.age = age;
            this.name = name;
        }
    }
}

