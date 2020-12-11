package org.geekbang.time.commonmistakes.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.util.CollectionUtils;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Legal
 * @date 2020/12/1
 */
public class LinkedListTest {

    public static void main(String[] args) {

//        String actions = "[{\"dps\":{\"2\":\"leaving_home\"},\"time\":\"00:00\"}]";
        String actions = "";
        List<DpActionVO> dpActionVOS = parseActionVOs(actions, DpActionVO.class);
        if (CollectionUtils.isEmpty(dpActionVOS) || dpActionVOS.size() != 1) {
            return;
        }

        DpActionVO dpActionVO = dpActionVOS.get(0);
        LinkedHashMap<String, Object> dps = dpActionVO.getDps();
        String workMode = (String) dps.get(2);
        System.out.println(workMode);
    }

    public static <T> List<T> parseActionVOs(String actions, Class<T> targetClass) {
        try {
            List<T>  list = JSONArray.parseArray(actions, targetClass);
            return list;
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException();
        }
    }
}
