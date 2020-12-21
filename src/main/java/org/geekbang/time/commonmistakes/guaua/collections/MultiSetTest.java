package org.geekbang.time.commonmistakes.guaua.collections;

import com.google.common.collect.*;
import org.junit.Test;

/**
 * MultiSet 接口继承了 collection
 * Multiset 可添加相同的元素
 * @author Legal
 * @date 2020/12/21
 */
public class MultiSetTest {

    /**
     * HashMultiSet，TreeMultiSet 无序， LinkedHashMultiset 有序
     */
    @Test
    public void hashMultiset() {

        Multiset<Object> hashMultiset = HashMultiset.create();
        hashMultiset.add("a");
        hashMultiset.add(200);
        hashMultiset.add("a");

        System.out.println(hashMultiset);
        hashMultiset.stream().forEach(item -> System.out.println(item + ""));
    }

    @Test
    public void linkedHashMultiset() {

        LinkedHashMultiset linkedHashMultiset = LinkedHashMultiset.create();
        linkedHashMultiset.add("1");
        linkedHashMultiset.add("2");
        linkedHashMultiset.add("3");

        System.out.println(linkedHashMultiset);
        linkedHashMultiset.stream().forEach(item -> System.out.println(item + ""));
    }

    /**
     * HashMultiset 、TreeMultiset 是无序的，LinkedHashMultiset 是有序的
     */
    @Test
    public void treeMultiset() {
        TreeMultiset<Comparable> treeMultiset = TreeMultiset.create();
        treeMultiset.add("中国");
        treeMultiset.add("秦");
        treeMultiset.add("汉");
        treeMultiset.add("秦");
        treeMultiset.add("唐");

        //[中国, 唐, 汉, 秦 x 2]
        System.out.println(treeMultiset);
        //中国 唐 汉 秦 秦
        treeMultiset.stream().forEach(item -> System.out.print(item+" "));
    }
}
