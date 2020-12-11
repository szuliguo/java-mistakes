package org.geekbang.time.commonmistakes.timezone;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.FastDateFormat;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Legal
 * @date 2020/10/28
 */
public class TimeZoneTest {

    public static void main(String[] args) {

        Calendar c = Calendar.getInstance();
        System.out.println(c.getTimeZone());

        TimeZone timeZone = TimeZone.getTimeZone("Asia/Shanghai");
        Calendar calendar = Calendar.getInstance(timeZone);
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
        System.out.println(DateFormatUtils.format(calendar, "HH:mm"));

        //fastDateFormat
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        FastDateFormat fastDateFormat = FastDateFormat.getInstance("HH:mm", tz);
        System.out.print("fastDateFormat : " + fastDateFormat.format(System.currentTimeMillis()));
        System.out.print("\r\n");



//        calendar.add(Calendar.DATE, 7);
//        SimpleDateFormat simpleDateFormat =
//                new SimpleDateFormat("EE MMM dd HH:mm:ss zzz yyyy", Locale.US);
//        simpleDateFormat.setTimeZone(timeZone);
//
//        System.out.println("Time zone: " + timeZone.getID());
//        System.out.println("default time zone: " + TimeZone.getDefault().getID());
//        System.out.println();
//
//        System.out.println("UTC:     " + simpleDateFormat.format(calendar.getTime()));
//        System.out.println("Default: " + calendar.getTime());

        System.out.println("getWeekOfDate: " + (calendar.get(Calendar.DAY_OF_WEEK) - 1));

    }


    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

}
