package org.geekbang.time.commonmistakes.springpart1.beansingletonandorder;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * 在此处，sayService 可以认为是有状态的
 * 如果是单例模式，那么就会导致oom
 * 继承的时候要考虑父类是有状态的，还是没有状态的
 */
@Slf4j
public abstract class SayService {
    List<String> data = new ArrayList<>();

    public void say() {
        data.add(IntStream.rangeClosed(1, 1000000)
                .mapToObj(__ -> "a")
                .collect(Collectors.joining("")) + UUID.randomUUID().toString());
        log.info("I'm {} size:{}", this, data.size());
    }

    /**
     * 测试 main方法
     * result: [aaaaaaaaaaaae53d85-301d-41e4-8b4a-1cc637b205e6]
     * @param args
     */
    public static void main(String[] args) {

        List<String> data = new ArrayList<>();
        data.add(IntStream.rangeClosed(1, 10)
                .mapToObj(__ -> "a")
                .collect(Collectors.joining("")) + UUID.randomUUID().toString());

        System.out.print(data.toString());
    }

}
