package org.geekbang.time.commonmistakes.other;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Legal
 * @date 2020/12/10
 */
public class MapTest {

    public static void main(String[] args) {

        HashMap<String, Object> dpMap = Maps.newHashMap();

        Optional.ofNullable(dpMap).map(o -> o.keySet().contains("123")).orElse(false);

        dpMap.put("110", "pause");
        dpMap.put("120", 1);

        String dps = JSONObject.toJSONString(dpMap);
        System.out.println(dps);

        System.out.println(dps.contains("130"));

        String str = null;


        JSONObject jsonObject = JSONObject.parseObject(str);
        Map<String, Object> map = (Map<String, Object>) jsonObject;
        //Map<String, Object> map = new HashMap<>();
        System.out.println(Optional.ofNullable(map).map(o -> o.keySet().contains("110")).orElse(false));



    }
}