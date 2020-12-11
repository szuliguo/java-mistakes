package org.geekbang.time.commonmistakes.callback;

import rx.Completable;

import javax.xml.transform.Result;
import java.util.concurrent.CompletableFuture;

/**
 * 俩个任务 串行执行
 * https://www.liaoxuefeng.com/wiki/1252599548343744/1306581182447650
 * @author Legal
 * @date 2020/10/31
 */
public class CompletableFutureTest1 {
    public static void main(String[] args) throws InterruptedException {
        //创建异步执行任务
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(CompletableFutureTest1::fetchPrice);
        // 如果执行成功
        cf.thenAccept((result) -> {
           System.out.println("price: " + result);
        });
        //如果执行异常
        cf.exceptionally((e) -> {
           e.printStackTrace();
            return null;
        });
        // 主线程不要立刻结束，否则 completableFuture 默认使用的线程池就会立刻关闭
        Thread.sleep(200);
    }

    static Double fetchPrice() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (Math.random() < 0.3) {
            throw new RuntimeException("fetch price failed");
        }

        return 5 + Math.random() * 20;
    }
}
