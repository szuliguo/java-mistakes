package org.geekbang.time.commonmistakes.test;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Legal
 * @date 2021/2/22
 * 1. ZonedDateTime 与 Timestamp 之间的转化
 * https://blog.csdn.net/feichen2016/article/details/71601329
 * 2. String 类型转ZonedDateTime
 * https://blog.csdn.net/qq_42938196/article/details/110239199?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.control&dist_request_id=&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.control
 * https://blog.csdn.net/qq_33036061/article/details/107062494?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_title-1&spm=1001.2101.3001.4242
 * 3.遍历zonedDateTime
 * https://segmentfault.com/q/1010000007862493
 */


public class TimeTest {

    public static void main(String[] args) {

        WeekLoopTime basicLoopTime = new WeekLoopTime();
        basicLoopTime.setLoops("0");
        basicLoopTime.setName("name");
        basicLoopTime.setMode("mode");
        testExtends(basicLoopTime);

        Integer a = null;
        String aa = Optional.ofNullable(a).map(String::valueOf).orElse("c");

        String zonedTimeStr = "%s 00:00";
        ZonedDateTime zonedTime = getStartTimeForDayYYMMDD("2021-03-08");
        System.out.println(zonedTime);
        System.out.println(Timestamp.from(zonedTime.toInstant()).getTime());

        // 转为当地时区时间
        DateTimeFormatter beijingFormatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm").withZone(ZoneId.of("UTC-8"));
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(String.format(zonedTimeStr, "20210308"), beijingFormatter);
        ZonedDateTime plus = zonedDateTime.plusDays(1);
        // -1
        System.out.println(zonedDateTime.compareTo(plus));

        System.out.println(zonedDateTime);
        System.out.println(plus);
//        System.out.println(zonedDateTime.getDayOfWeek().getValue());
//        System.out.println(zonedDateTime.getSecond());
        // zonedDateTime 转为时间戳
        //System.out.println(Timestamp.from(zonedDateTime.toInstant()).getTime());
        System.out.println(zonedDateTime.toInstant().getEpochSecond());

        // Java:
        long t = System.currentTimeMillis();
        System.out.println("long = " + t);

// current time zone:
        SimpleDateFormat sdf_default = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println(sdf_default.format(t));

// +8:00 time zone:
        SimpleDateFormat sdf_8 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        sdf_8.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        System.out.println("GMT+8:00 = " + sdf_8.format(t));

// +7:00 time zone:
        SimpleDateFormat sdf_7 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        sdf_7.setTimeZone(TimeZone.getTimeZone("GMT+7:00"));
        System.out.println("GMT+7:00 = " + sdf_7.format(t));

// -9:00 time zone:
        SimpleDateFormat sdf_la = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        sdf_la.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        System.out.println("America/Los_Angeles = " + sdf_la.format(t));


        System.out.println(String.format("%s", "a", "d"));

//        System.out.println(LinkageTimerUtils.getTimeStamp("America/Los_Angeles", "20210221", "19:41"));
    }


    public static ZonedDateTime getStartTimeForDayYYMMDD(String dateString) {
        if (dateString!=null && dateString!="") {
            String[] split = dateString.split("-");
            return ZonedDateTime.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), 0, 0, 0, 0, ZoneId.of("UTC-8"));
        } else {          return null;
        }
    }

    public static void testExtends(BasicLoopTime loopTime) {

        LoopTime time = new LoopTime();
        BeanUtils.copyProperties(loopTime, time);
        System.out.println("mode:" + time.getMode());

    }

}

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
class LoopTime {
    private String mode;
    private String loops;
    private String name;

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class WeekLoopTime extends BasicLoopTime {
    private String mode;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class BasicLoopTime {
    private String loops;
    private String name;
}
