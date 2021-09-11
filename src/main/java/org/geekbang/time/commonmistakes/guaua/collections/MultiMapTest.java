package org.geekbang.time.commonmistakes.guaua.collections;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * https://wizardforcel.gitbooks.io/guava-tutorial/content/10.html
 * @author Legal
 * @date 2020/12/22
 * MultiMap 主要是用来解决 Map<K, List<V>>, Map<K, Set<V>>这个愚蠢的结构的
 *
 * https://www.itranslater.com/qa/details/2582585356159812608
 */
public class MultiMapTest {
    private static DateTimeFormatter offsetFormatter = DateTimeFormatter.ofPattern("xxx");


    @Test
    public void arrayListMultiMapTest() {

//        String badRegex = "^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\\\/])+$";
//        String bugUrl = "http://www.fapiao.com/dddp-web/pdf/download?request=6e7JGxxxxx4ILd-kExxxxxxxqJ4-CHLmqVnenXC692m74H38sdfdsazxcUmfcOH2fAfY1Vw__%5EDadIfJgiEf";
//        if (bugUrl.matches(badRegex)) {
//            System.out.println("match!!");
//        } else {
//            System.out.println("no match!!");
//        }

        //checkEmail3("482fjalkjflajfaljf@qq.com");
        System.out.println("aaaa");

        ZoneOffset offset = ZoneId.of("Europe/Kiev").getRules().getOffset(Instant.now());
        System.out.println(offsetFormatter.format(offset));

        ZoneOffset offset1 = ZoneId.of("Europe/Copenhagen").getRules().getOffset(Instant.now());
        System.out.println(offsetFormatter.format(offset1));

        ZoneOffset offset2 = ZoneId.of("Europe/London").getRules().getOffset(Instant.now());
        System.out.println(offsetFormatter.format(offset2));


        ZoneId here = ZoneId.of("Europe/Kiev");
        ZonedDateTime hereAndNow = Instant.now().atZone(here);
        String format = String.format("%tz", hereAndNow);
        System.out.println("timeZoneId:=" + format);

        TimeZone tz = TimeZone.getTimeZone("Europe/Kiev");
        String offsetId = tz.toZoneId().getId();
//        String offsetId = tz.toZoneId().getRules().getStandardOffset(Instant.now()).getId();
        System.out.println("timeZoneId:=" + offsetId);

        String json =
                "{\"actionJson\":\"{\\\"category\\\":\\\"holiday\\\",\\\"pid\\\":\\\"den6kzk0\\\",\\\"resId\\\":\\\"bf803516e737956347kecr\\\",\\\"resType\\\":1,\\\"time\\\":0,\\\"timePoint\\\":1,\\\"timeZoneId\\\":\\\"Australia/Perth\\\"}\",\"sigmaxUuid\":\"bf803516e737956347kecr_NhD9zvTAC7ai5DV2NBPsjyGc3zLhvWr4\", \"businessType\":\"SOLUTION_STATISTICS\"}";
        JSONObject data = JSONObject.parseObject(json);
        Object businessType = data.get("businessType");
        JSONObject actionJson = data.getJSONObject("actionJson");
        String pid = actionJson.getString("pid");
        System.out.println("pid = " + pid);
        System.out.println(BusinessEnum.valueOf(String.valueOf(businessType)));
        System.out.println("type = " + getBusinessTypeOrDefault(data));


        ArrayListMultimap<String, String> arrayListMultimap = ArrayListMultimap.<String, String>create();
        arrayListMultimap.put("a", "b");
        arrayListMultimap.put("a", "c");
        arrayListMultimap.put("a", "d");
        arrayListMultimap.put("h", "hh");

        assertThat(arrayListMultimap.get("a").size(), is(3));

    }

    /**
     *
     * 获取 businessType
     */
    private String getBusinessTypeOrDefault(JSONObject data) {

        Object businessType = data.get("businessType");
        if (Objects.nonNull(businessType)) {
            return String.valueOf(businessType);
        }

        JSONObject actionJson = data.getJSONObject("actionJson");
        if (Objects.isNull(actionJson)) {
            return null;
        }

        String pid = actionJson.getString("pid");
        System.out.println("pid = " + pid);
        return null;
    }


    @Test
    public void hashMultiMapTest() {

        Multimap<Integer, Integer> map = HashMultimap.<Integer, Integer>create();

        map.put(1, 2);
        map.put(1, 2);
        map.put(1, 3);
        map.put(1, 4);
        map.put(2, 3);
        map.put(3, 3);
        map.put(4, 3);
        map.put(5, 3);


        map.forEach((k, v) -> {
            String s = k + "--->" + v;
            System.out.println(s);

        });

        Collection<Integer> mapList = map.get(1);
        System.out.println(mapList.toString());


    }

    public static boolean checkEmail3(String email) {
        String regex = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        return Pattern.matches(regex, email);
    }
}
