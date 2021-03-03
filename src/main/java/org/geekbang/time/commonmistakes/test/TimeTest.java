package org.geekbang.time.commonmistakes.test;


import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * @author Legal
 * @date 2021/2/22
 */
public class TimeTest {

    public static void main(String[] args) {

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

//        System.out.println(LinkageTimerUtils.getTimeStamp("America/Los_Angeles", "20210221", "19:41"));
    }
}
