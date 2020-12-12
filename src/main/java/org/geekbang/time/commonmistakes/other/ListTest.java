package org.geekbang.time.commonmistakes.other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Legal
 * @date 2020/12/12
 */
public class ListTest {

    public static void main(String[] args) {

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

