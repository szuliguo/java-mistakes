package org.geekbang.time.commonmistakes.guaua.collections;

import com.google.common.collect.*;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

import java.util.stream.Collectors;

import static org.junit.Assert.assertThat;

/**
 * https://wizardforcel.gitbooks.io/guava-tutorial/content/10.html
 * MultiSet 接口继承了 collection
 * Multiset 可添加相同的元素
 * @author Legal
 * @date 2020/12/21
 */
public class MultiSetTest {

    /**
     * HashMultiSet，TreeMultiSet 无序， LinkedHashMultiset 有序
     *
     */
    @Test
    public void hashMultiset() {

        Multiset<Object> hashMultiset = HashMultiset.create();
        hashMultiset.add("a");
        hashMultiset.add(200);
        hashMultiset.add("a");

        System.out.println(hashMultiset);
        hashMultiset.stream().forEach(item -> System.out.println(item + ""));

        System.out.println("==count:给定元素在multiset中的计数==");
        assertThat(hashMultiset.count("a"), is(2));

        System.out.println("==elementSet:Multiset中不重复元素的集合==");
        assertThat(hashMultiset.elementSet().size(), is(2));

        System.out.println("==entrySet() map的 entrySet");
        hashMultiset.entrySet().forEach(System.out::println);
        hashMultiset.entrySet().stream().map(Multiset.Entry::getElement).collect(Collectors.toList()).forEach(System.out::println);




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
        SortedMultiset<Comparable> treeMultiset = TreeMultiset.create();
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

    /**
     *  SortedMultiset 它支持高效地获取指定范围的子集
     */
    @Test
    public void sortedMultiset() {
        SortedMultiset<Comparable> sortedMultiset = TreeMultiset.create();
        sortedMultiset.add(10);
        sortedMultiset.add(1);
        sortedMultiset.add(11);

        sortedMultiset.stream().forEach(System.out::println);
        sortedMultiset.subMultiset(0, BoundType.CLOSED, 2, BoundType.OPEN).stream().forEach(System.out::println);

    }
}
