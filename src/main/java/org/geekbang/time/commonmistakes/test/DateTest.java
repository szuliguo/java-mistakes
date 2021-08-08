package org.geekbang.time.commonmistakes.test;

import org.apache.commons.lang.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalField;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Legal
 * @date 2020/11/25
 */
public class DateTest {


    public static void main(String[] args) {

        String timeZoneId = "Asia/Shanghai";

        System.out.println(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of(timeZoneId)).getDayOfWeek().getValue());

        //System.out.println(string2Date("Asia/Shanghai", "20201028", "yyyyMMdd"));
        //System.out.println(getNowDate("Asia/Shanghai"));
        Date now = getNowDate("Asia/Shanghai");
        System.out.println(addDay("Asia/Shanghai", now, -1 ));
        System.out.println("now" + now);
        System.out.println(getRelativeTime("01:01"));

        Date start = getNowDate("Asia/Shanghai");
        Date end = addDay("Asia/Shanghai", start, 6);
        System.out.println("start:" + start);
        System.out.println("end:" + end);

        TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
        Calendar calendar = Calendar.getInstance(timeZone);
        System.out.println("#####");
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println("#####");
        calendar.setTime(start);


        while (start.compareTo(end) <= 0) {
            //TODO
            System.out.println(start);
            calendar.add(Calendar.DATE, 1);
            start = calendar.getTime();
        }
    }

    /**
     * 将字符串转化为date
     * @param timeZoneId
     * @param date
     * @return
     */
    private static  Date string2Date(String timeZoneId, String date, String pattern) {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        sf.setTimeZone(TimeZone.getTimeZone(timeZoneId));
        try {
            return sf.parse(date);
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * 在指定的日期添加天数
     * @param timeZoneId
     * @param date
     * @param dayAmount
     * @return
     */
    private  static  Date addDay(String timeZoneId, Date date, int dayAmount) {
        TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTime(date);
        calendar.add(Calendar.DATE, dayAmount);
        return calendar.getTime();
    }


    /**
     *  获取date
     * @param timeZoneId 时区
     * @return
     */
    private static Date getNowDate(String timeZoneId) {
        String date = DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMdd", TimeZone.getTimeZone(timeZoneId));
        return string2Date(timeZoneId, date, "yyyyMMdd");

    }

    /**
     * 计算从午夜0:00开始的分钟数
     * @param time 时间，例如 05:20
     * @return
     */
    private static int getRelativeTime(String time) {

        String[] times = time.split(":");
        return Integer.valueOf(times[0]) * 60 + Integer.valueOf(times[1]);
    }


}
