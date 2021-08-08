package org.geekbang.time.commonmistakes.guaua.collections;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Test;

/**
 * @author Legal
 * @date 2020/12/22
 */
public class BitMapTest {


    @Test
    public void testBitMap() {
        // HashBitMap
        HashBiMap<Integer, String> loopMap = HashBiMap.create();
        loopMap.put(0, "1000000");
        loopMap.put(1, "0100000");
        loopMap.put(2, "0010000");
        loopMap.put(3, "0001000");
        loopMap.put(4, "0000100");
        loopMap.put(5, "0000010");
        loopMap.put(6, "0000001");

        //倒转, 只是返回了一个视图
        BiMap<String, Integer> map = loopMap.inverse();
        System.out.println(map);

        //对原引用的修改会影响到 map
        loopMap.put(7, "1100000");
        System.out.println(map);

        //BitMap 具有强一致性, 如果value 重复会出现问题，抛出异常


    }



}
