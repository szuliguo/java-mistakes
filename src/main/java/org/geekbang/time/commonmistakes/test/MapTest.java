package org.geekbang.time.commonmistakes.test;

import com.google.common.collect.Maps;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Legal
 * @date 2020/11/10
 */
public class MapTest {
    public static void main(String[] args) {
//        Map<String, Object> dpMap = Maps.newHashMapWithExpectedSize(2);
//        dpMap.put("2", null);

        test();

    }


    public static void test() {

        Map<String, Integer> map = new HashMap<>(10);

        map.putAll(Constant.LOOP_STRATEGY);

//        BeanUtils.copyProperties(Constant.LOOP_STRATEGY, map);
        map.put("1000000", 10);
        System.out.println(map.toString());

        System.out.println(Constant.LOOP_STRATEGY.toString());
    }

}
