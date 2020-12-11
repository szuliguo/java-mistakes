package org.geekbang.time.commonmistakes.test;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Legal
 * @date 2020/12/3
 */
public class JsonObjectTest {

    public static void main(String[] args) {

        List<String> devIds = new ArrayList<>();
        devIds.add("a");
        devIds.add("b");

        JSONObject noticeData = new JSONObject();
        noticeData.put("reqType", "danfoss_local_timer");
        noticeData.put("devices", devIds.toArray());

        System.out.println(JSONObject.toJSONString(noticeData));
    }
}
