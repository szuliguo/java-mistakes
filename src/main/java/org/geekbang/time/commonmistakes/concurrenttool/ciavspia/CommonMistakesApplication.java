package org.geekbang.time.commonmistakes.concurrenttool.ciavspia;

import com.sun.corba.se.impl.orb.ParserTable;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CommonMistakesApplication {

    public static void main(String[] args) {

        test3(new ConcurrentHashMap<>());
//        test2(new HashMap<>());
//        test(new HashMap<>());
//        test(new ConcurrentHashMap<>());
    }

    private static void test2(Map<String, String> map) {
        log.info("class : {}", map.getClass().getName());
        log.info("putIfAbsent null value :{}", map.putIfAbsent("test", null));

        // 如果值本来不存在 ，那么 返回null
        log.info("putIfAbsent  value : {}", map.putIfAbsent("test1", "2"));
        //  如果已经存在的话，那么就返回原来的值,并且不进行设置
        log.info("putIfAbsent  value : {}", map.putIfAbsent("test1", "3"));
        log.info("putIfAbsent....{}", map.get("test1"));
        log.info("putIfAbsent expensive value : {}", map.putIfAbsent("test4", getValue()));

    }

    private static void test3(Map<String, String> map) {

        log.info("class : {}", map.getClass().getName());

        //putIfAbsent 允许设置null值
        try {
            //computeIfAbsent 不允许设置null值
            log.info("map completeIfAbsent null value :{}", map.computeIfAbsent("test", null));
        } catch (Exception e) {
        }

//        computeIfAbsent 如果不存在的时候，是不会去获取值的。
        log.info("completeIfAbsent test1 value :{}", map.computeIfAbsent("test1", k -> "test4"));
        log.info("completeIfAbsent test1 value :{}", map.computeIfAbsent("test1", k -> "test5"));
        log.info("completeAbsent get value :{}", map.get("test1"));

        log.info("computeIfAbsent expensive value : {}", map.computeIfAbsent("test4", k -> getValue()));

    }

    private static void test(Map<String, String> map) {
        log.info("class : {}", map.getClass().getName());
        try {
            log.info("putIfAbsent null value : {}", map.putIfAbsent("test1", null));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        log.info("test containsKey after putIfAbsent : {}", map.containsKey("test1"));
        log.info("computeIfAbsent null value : {}", map.computeIfAbsent("test2", k -> null));
        log.info("test containsKey after computeIfAbsent : {}", map.containsKey("test2"));
        log.info("putIfAbsent non-null value : {}", map.putIfAbsent("test3", "test3"));
        log.info("computeIfAbsent non-null value : {}", map.computeIfAbsent("test4", k -> "test4"));
        log.info("putIfAbsent expensive value : {}", map.putIfAbsent("test4", getValue()));
        log.info("computeIfAbsent expensive value : {}", map.computeIfAbsent("test4", k -> getValue()));

    }

    private static String getValue() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return UUID.randomUUID().toString();
    }
}

