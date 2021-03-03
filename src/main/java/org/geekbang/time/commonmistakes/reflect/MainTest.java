package org.geekbang.time.commonmistakes.reflect;

import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Legal
 * @date 2021/2/24
 */
public class MainTest {

    public static void main(String[] args) {

        List<BaseTimer> list = new ArrayList<>();
        System.out.println(list);
        Reflections reflections = new Reflections("org.geekbang.time.commonmistakes.reflect");
        Set<Class<? extends BaseTimer>> subTypes = reflections.getSubTypesOf(BaseTimer.class);

        subTypes.forEach((type) -> {
            try {
                list.add(type.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } );

        for (int i = 0; i < list.size(); i++) {
            list.get(i).print("cccc");
        }
    }
}
