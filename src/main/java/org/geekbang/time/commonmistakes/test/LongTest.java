package org.geekbang.time.commonmistakes.test;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Legal
 * @date 2021/3/26
 */
public class LongTest {

    public static void main(String[] args) {
        Long start = 0L;
        if (start.equals(0L)) {
            System.out.println("equal.....");
        } else {
            System.out.println("not equal....");
        }
    }
}
