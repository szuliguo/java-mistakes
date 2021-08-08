package org.geekbang.time.commonmistakes.java8.completablefuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Legal
 * @date 2021/3/16
 */
public class CompletableFutureTest2 {

    public static void main(String[] args) {
        useCompletableFutureWithExecutor();
    }


    public static void useCompletableFutureWithExecutor() {


        List<MyTask> tasks = IntStream.range(0, 10)
                .mapToObj(i -> new MyTask(1))
                .collect(Collectors.toList());

        long start = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(Math.min(tasks.size(), 10));
        List<CompletableFuture<Integer>> futures =
                tasks.stream()
                        .map(t -> CompletableFuture.supplyAsync(() -> t.calculate(), executor))
                        .collect(Collectors.toList());

        System.out.println("......test........");
        System.out.println("......test........");
        System.out.println("......test........");

        List<Integer> result =
                futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList());
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread.......");
            }
        }).start();
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("Processed %d tasks in %d millis\n", tasks.size(), duration);
        System.out.println(result);
        executor.shutdown();
    }

}

class MyTask {
    private final int duration;

    public MyTask(int duration) {
        this.duration = duration;
    }

    public int calculate() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(duration * 1000);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
        return duration;
    }
}
