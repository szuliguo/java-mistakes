package org.geekbang.time.commonmistakes.guaua.strings;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.codec.binary.Base64;

/**
 * @author Legal
 * @date 2020/12/22
 * http://bins.top/teh/2019/03/26/Google-Guava-usage.html
 */
public class StringUtilTest {

    public static final String FORMATTER3 = "yyyy-MM-dd HH:mm";

    @Test
    public void JoinerTest() throws IOException {

        String json = "{\"gmt_create\":1603093905,\"runtime_env\":\"prod\",\"is_sub\":\"1\",\"owner_id\":\"26296188\",\"local_key_new\":\"\",\"uuid\":\"6cf865a70b89c97f2bgozq\",\"custom_name\":\"\",\"product_key\":\"\",\"product_id\":\"n7dtaka8\",\"http_last_time\":0,\"id\":\"6cf865a70b89c97f2bgozq\",\"time_zone_id\":\"Asia/Shanghai\",\"id_keyword2\":\"6cf865a70b89c97f2bgozq\",\"product_uid\":\"ay1577096816804oP4lN\",\"lat\":\"\",\"gmt_upgrading\":0,\"uid_keyword\":\"ay1602501177767lGW2e\",\"ver_sw\":\"0.0.0\",\"upgrade_text\":\"\",\"phase\":1,\"local_key\":\"\",\"ip\":\"\",\"biz_type\":309188,\"product_name\":\"Icon RT\",\"active_time\":1604287204,\"country_code\":\"\",\"port\":-1,\"support_ack\":\"0\",\"name\":\"Icon RT 14\",\"reset_factory\":\"1\",\"etag\":\"\",\"status\":0,\"city_id\":\"\",\"access_type\":3,\"ver_baseline\":\"\",\"dev_attribute\":0,\"icon\":\"smart/icon/ay1577096816804oP4lN/7616fb0ecb60d3ee6d05b617e33d46bd.png\",\"lon\":\"\",\"product_code\":\"\",\"gmt_modified\":1604287447,\"uid\":\"ay1602501177767lGW2e\",\"upgrade_status\":\"0\",\"skill\":\"\",\"is_online\":\"0\",\"ability\":0,\"lang\":\"zh\",\"mqtt_last_time\":0,\"pkid\":1318097491626819643,\"is_active\":\"1\",\"time_zone\":\"+08:00\",\"dev_etag\":\"\",\"sec_key_new\":\"\",\"ver_protocol\":\"\",\"category\":\"wk\",\"username\":\"chong.li@qq.com\"}";
        Device device = JSONObject.parseObject(json, Device.class);
        System.out.println(JSONObject.toJSONString(device));

        System.out.println("####################################");
        System.out.println(getCurTsByZoneId("Europe/London"));
        System.out.println(getTimerStampByDash("2021-05-19",39600,"Europe/London"));
        System.out.println(getTimerStampByDash("2021-05-20",0,"Europe/London"));
        System.out.println("####################################");

        System.out.println(Arrays.asList("").toString());
        System.out.println(JSONObject.toJSONString(Arrays.asList("add", "ad")));

        Long value = 190L;
        System.out.println(Objects.equals(value, 0L));

        System.out.println(Collections.EMPTY_LIST.contains("a"));

        Map<String, List<String>> map12 = new HashMap<>();
        map12.put("a", null);
        List<String> defaultList = map12.getOrDefault("s", new ArrayList<>(1));
        if (defaultList.isEmpty()) {
            System.out.println("is empty");
        }
        defaultList.add("d");
        defaultList.stream().filter(str -> "a".equalsIgnoreCase(str)).collect(Collectors.toList());
        System.out.println(map12.getOrDefault("a", Collections.emptyList()));

        /**
         * 连接器
         */

        List<String> list = Lists.newArrayList("a", null, "b");
        /**
         * result : a|b
         */
        System.out.println(Joiner.on("|").skipNulls().join(list));
        /**
         * result : a|none|b
         */
        System.out.println(Joiner.on("|").useForNull("none").join(list));
        /**
         * result : a/b/
         */
        System.out.println(Joiner.on("/").join("a", "b", ""));

        FileWriter fileWriter = new FileWriter(new File("/Users/legal/Desktop/test2"));
        Joiner.on("#").useForNull(" ").appendTo(fileWriter, list);
        fileWriter.flush();
        fileWriter.close();


        Map<String, String> map = Maps.newLinkedHashMap();
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
        //k1=v1&k2=v2&k3=v3
        System.out.println(Joiner.on("&").withKeyValueSeparator("=").join(map));

        ImmutableMap<String, String> map2 = ImmutableMap.of("id", "1", "name", "jack");
        //id=1&name=jack
        System.out.println(Joiner.on("&").withKeyValueSeparator("=").join(map2));
    }

    @Test
    public void splitterTest() {

        /**
         * 拆分器
         */

        Map<String, String> splitMap = Splitter.on("&").withKeyValueSeparator("=").split("id=1&name=jack");
        // {id=1, name=jack}
        System.out.println(splitMap.toString());
        //[foo, bar, , baz]
        System.out.println(Splitter.on(' ').trimResults().split("+91       9880865706"));
        System.out.println(Lists.newArrayList(Splitter.on('|').trimResults().split("foo|bar|baz")));
    }

    @Test
    public void stringUtilTest() {

        //向右填充x
        System.out.println(Strings.padEnd("12345", 10, 'x'));
       //向左填充x
        System.out.println(Strings.padStart("2", 2, '0'));
      //判断字符串是否为空
        System.out.println(Strings.isNullOrEmpty(null));
     //生成重复字符串
        System.out.println(Strings.repeat("123", 3));
     //获取左边公共字符串
        System.out.println(Strings.commonPrefix("abc123", "abc456"));
      //获取右边公共字符串
        System.out.println(Strings.commonSuffix("123abc", "456abc"));


    }



    @Test
        public void matcherTest() {

        String string = "+91 9880865706 ";
        //移除control字符
        String noControl = CharMatcher.javaIsoControl().removeFrom(string);
        //只保留数字字符
        String theDigits = CharMatcher.digit().retainFrom(string);

        System.out.println(theDigits);
        //用*号替换所有数字
        String noDigits = CharMatcher.javaDigit().replaceFrom(string, "*");
        // 只保留数字和小写字母
        String lowerAndDigit = CharMatcher.javaDigit().or(CharMatcher.javaLowerCase()).retainFrom(string);



        System.out.println(string);
        System.out.println(getPayload("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL29wZW5pZC5vcmFuZ2UuZXMiLCJzdWIiOiJTX1RVWUEtMjAwLWVHTlJxUkphT01sYUlqblpnYkY4TWdpR09FOStJR0FWMytLaWtsbFpvTXk2MkowTkg4Y09xUi91dVNDVTFwSTQiLCJhdWQiOlsieEpDb2ZpNkFNMCJdLCJleHAiOjE2Mjc1NjkyOTAsImlhdCI6MTYyNzU2NTY5MCwiYWNyIjoiMiIsImFtciI6WyJQQVNTV09SRCJdLCJhdXRoX3RpbWUiOjE2Mjc1NjU2NTB9.q-HjvRXVpuhIb-Zwyi1CIJa3KJkB2NgTNvilBu5VF-4"));


        System.out.println(String.format("%s : %d is before now %d ", "filedName", 1L, 2L));



    }

    /**
     * 将字符串转化为date
     * @param timeZoneId 时区
     * @param date       日期
     * @return 格式化后的date
     */
    public static  Date string2Date(String timeZoneId, String date, String pattern) {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        sf.setTimeZone(TimeZone.getTimeZone(timeZoneId));
        try {
            return sf.parse(date);
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * 根据date,time,timeZoneId 获取时间戳
     *
     * @param date       yyyy-mm-dd 例如:2021-03-28
     * @param time       HH:mm 例如 10:30
     * @param timeZoneId 时区
     * @return timestamp(秒 ）
     */
    public static long getTimerStampByDash(String date, int time, String timeZoneId) {
        StringBuilder sb = new StringBuilder();
        sb.append(date).append(" 00:00");
        // 转为当地时区时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATTER3).withZone(ZoneId.of(timeZoneId));
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(sb.toString(), formatter);
        return zonedDateTime.toInstant().getEpochSecond() + time;
    }

    /**
     * 根据时区获取当前的ts
     *
     * @param zoneId 时区
     * @return 当前的ts
     */
    public static long getCurTsByZoneId(String zoneId) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of(zoneId));
        return now.toInstant().getEpochSecond();
    }

    /**
     *  解析JWT, 获取payload
     */
    public static JSONObject getPayload(String token) {
        final List<String> tokens = splitToken(token);
        return JSONObject.parseObject(new String(new Base64().decode(tokens.get(1))));
    }


    /**
     * 将JWT字符串拆分为3部分，无加密算法则最后一部分是""
     *
     * @param token JWT Token
     * @return 三部分内容
     */
    private static List<String> splitToken(String token) {
        final List<String> tokens = Lists.newArrayList(Splitter.on('.').trimResults().split(token));
        if (3 != tokens.size()) {
        }
        return tokens;
    }


}
