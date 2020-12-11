package org.geekbang.time.commonmistakes.json;

import com.alibaba.fastjson.JSONArray;

import java.util.List;

/**
 * @author Legal
 * @date 2020/12/7
 */
public class json {
    public static void main(String[] args) {

        String str = "[\"14b457fffeb92f52\", \"90fd9ffffe4a760b\"]";

        List<String> list = JSONArray.parseArray(str, String.class);
        for (String deviceId : list) {
            System.out.println(deviceId);
        }

    }
}
