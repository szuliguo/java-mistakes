package org.geekbang.time.commonmistakes.test;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Long num = 0L;
        if (num.equals(0L)) {
            System.out.println("equals...");
        }

        System.out.println(ImmutableList.copyOf(Collections.emptyList()));

        /**星期列表**/
        List<String> WEEK_LOOP = new ImmutableList.Builder<String>()
                .add("1000000")
                .add("0100000")
                .add("0010000")
                .add("0001000")
                .add("0000100")
                .add("0000010")
                .add("0000001")
                .build();
        Runnable dummyTask = () -> {
            try {
                System.out.println();
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {
            }
        };

        // 使用CompletableFuture
        List<CompletableFuture<Void>> futures = WEEK_LOOP.stream()
                .map(loops -> {
                    return CompletableFuture.runAsync(() -> {
                        System.out.println(loops);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                })
                .collect(Collectors.toList());
        CompletableFuture<Void> headerFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[] {}));
        try {
            headerFuture.whenComplete((aVoid, throwable) -> {
                // 下发dp点 1.预加热; 2.模式 3.温度
                System.out.println("Joined finished");
            }).join();
        } catch (Exception e) {
            log.warn("CompletableFuture execute error.. ", e);
        }

//        CompletableFuture<Void> f1 = CompletableFuture.runAsync(dummyTask);
//        CompletableFuture<Void> f2 = CompletableFuture.runAsync(dummyTask);
//        f1 = f1.whenComplete((aVoid, throwable) -> System.out.println("Completed f1"));
//        f2 = f2.whenComplete((aVoid, throwable) -> System.out.println("Completed f2"));
//        CompletableFuture[] all = {f1, f2};
//        CompletableFuture<Void> allOf = CompletableFuture.allOf(all);
//        allOf.whenComplete((aVoid, throwable) -> {
//            System.out.println("Completed allOf" +  Thread.currentThread().getId());
//        }).join();
//        allOf.join();
        System.out.println("Joined" +  Thread.currentThread().getId());
    }


}
