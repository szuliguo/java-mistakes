package org.geekbang.time.commonmistakes.guaua.collections;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://wizardforcel.gitbooks.io/guava-tutorial/content/10.html
 * @author Legal
 * @date 2020/12/22
 * MultiMap 主要是用来解决 Map<K, List<V>>, Map<K, Set<V>>这个愚蠢的结构的
 */
public class MultiMapTest {

    @Test
    public void arrayListMultiMapTest() {

        ArrayListMultimap<String, String> arrayListMultimap = ArrayListMultimap.<String, String>create();
        arrayListMultimap.put("a", "b");
        arrayListMultimap.put("a", "c");
        arrayListMultimap.put("a", "d");
        arrayListMultimap.put("h", "hh");

        assertThat(arrayListMultimap.get("a").size(), is(3));

    }

    @Test
    public void hashMultiMapTest() {

        Multimap<Integer, Integer> map = HashMultimap.<Integer, Integer>create();

        map.put(1, 2);
        map.put(1, 2);
        map.put(1, 3);
        map.put(1, 4);
        map.put(2, 3);
        map.put(3, 3);
        map.put(4, 3);
        map.put(5, 3);


        map.forEach((k, v) -> {
            String s = k + "--->" + v;
            System.out.println(s);

        });

        Collection<Integer> mapList = map.get(1);
        System.out.println(mapList.toString());


    }
}
