package org.geekbang.time.commonmistakes.test;

import com.google.common.collect.ImmutableMap;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author Legal
 * @date 2021/3/16
 */
public class StringTest {

    private static final Map<String, String> MODE_MAP = new ImmutableMap.Builder<String, String>()
            .put("00", "at_home")
            .put("01", "leaving_home")
            .build();

    public static void main(String[] args) {

        String dpValue = "202008181523010001";
        int length = dpValue.length();
        int dateLen = "yyyyMMdd".length();
        int dateTimeLen = "yyyyMMddHHmm".length();
        if (StringUtils.isEmpty(dpValue)) {
            return;
        }

        // 日期
        String date = dpValue.substring(0, dateLen);

        // 模式
        String mode = MODE_MAP.get(dpValue.substring(length - 2, length));
        // 第一次点击时间
        String time = dpValue.substring(dateLen, dateLen + 4);
        // 点击了几次
        //String clickNum = "";
        String clickNum = "at_home".equals(mode) ? dpValue.substring(dateTimeLen, dateTimeLen + 2)
                : dpValue.substring(length - 4, length - 2);

        System.out.println(String.format("date = %s, mode = %s, time = %s, clickNum = %s",
                date, mode, time, clickNum));

    }
}
