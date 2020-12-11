package org.geekbang.time.commonmistakes.reflect;

import com.google.common.collect.Maps;
import com.squareup.moshi.Json;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author Legal
 * @date 2020/10/14
 */
public class Person {

    private int age;
    private String name;
    private Father father;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Person() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Father getFather() {
        return father;
    }

    public void setFather(Father father) {
        this.father = father;
    }

    public static void main(String[] args) {

        Person model = new Person();
        Field[] fields = model.getClass().getDeclaredFields();

        for (Field field : fields) {
            System.out.print(field.getName() + "-->" + field.getType() + "-->" + field.getGenericType());
            System.out.print("\r\n");
        }
    }
}
