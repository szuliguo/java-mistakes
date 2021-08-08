package org.geekbang.time.commonmistakes.java8;

import com.google.common.primitives.Ints;
import org.aspectj.weaver.tools.Trace;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author Legal
 * @date 2020/12/20
 */
public class ParallelStreamTest {

    @Test
    public void parallelTest() {
        IntStream.rangeClosed(1, 100).parallel().forEach( i -> {
            System.out.println(LocalDateTime.now() + " : " + i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }

        });
    }

    /**
     * method 1:
     * 使用线程，直接把任务按照线程数均匀分割，分配到不同的线程执行，
     * 使用 countDownLatch 来阻塞主线程，
     * 直到所有的线程都完成了操作，需要我们自己进行分割任务
     * @param taskCount
     * @param threadCount
     * @return
     * @throws InterruptedException
     */
    private int thread(int taskCount, int threadCount) throws  InterruptedException {

        //总操作次数计数器
        AtomicInteger atomicInteger = new AtomicInteger();
        //使用 countDownLatch 来等待所有的线程执行完成
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        //使用IntStream 把数字转化为 thread
        IntStream.rangeClosed(1, threadCount).mapToObj(i -> new Thread(() -> {

            //手动把 taskCount 分成 threadCount，每一份有一个线程执行
            IntStream.rangeClosed(1, taskCount / threadCount).forEach(j -> increment(atomicInteger));
            //每一个线程处理完自己那部分数据以后， countDown 一次
            countDownLatch.countDown();
        })).forEach(Thread::start);

        //直到所有的线程执行完成
        countDownLatch.await();
        //查询计数器当前值
        return atomicInteger.get();
    }


    /**
     * method 2
     *使用 Executors.newFixedThreadPool 来获得固定线程数的线程池，使用 execute 提交所有任务到线程池执行，最后关闭线程池等待所有任务执行完成l.
     * @param taskCount
     * @param threadCount
     * @return
     * @throws InterruptedException
     */
    private int threadPool(int taskCount, int threadCount) throws  InterruptedException {

        //总操作计数器
        AtomicInteger atomicInteger = new AtomicInteger();
        //初始化一个线程数量 = threadCount 的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        //所有任务直接提交到线程池进行处理
        IntStream.rangeClosed(1, taskCount).forEach(i -> executorService.execute(() -> {
            increment(atomicInteger);
        }));
        //提交关闭线程池申请，等待之前所有的任务执行完成
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);
        //查询计数器当前值
        return atomicInteger.get();

    }

    /**
     * method 3
     * ForkJoinPool 和传统的 ThreadPoolExecutor 区别在于，前者对于 n 并行度有 n 个独立队列，后者是共享队列。
     * 如果有大量执行耗时比较短的任务，ThreadPoolExecutor 的单队列就可能会成为瓶颈。这时，使用 ForkJoinPool 性能会更好。
     * @param taskCount
     * @param threadCount
     * @return
     * @throws InterruptedException
     */
    private int forkjoin(int taskCount, int threadCount) throws  InterruptedException {
        //总操作次数计数器
        AtomicInteger atomicInteger = new AtomicInteger();
        //定义一个并行度= threadCount 的ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool(threadCount);
        //所有任务直接提交到线程池处理
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, taskCount).parallel().forEach(i -> increment(atomicInteger)));
        //提交关闭线程池申请，等待之前所有任务执行完成
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        //查询计数器当前值
        return atomicInteger.get();

    }

    /**
     * method 4
     * 第四种方式是，直接使用并行流，并行流使用公共的 ForkJoinPool，也就是 ForkJoinPool.commonPool()。
     * 公共的 ForkJoinPool 默认的并行度是 CPU 核心数 -1，原因是对于 CPU 绑定的任务分配超过 CPU 个数的线程没有意义。
     * 由于并行流还会使用主线程执行任务，也会占用一个 CPU 核心，所以公共 ForkJoinPool 的并行度即使 -1 也能用满所有 CPU 核心。
     * @param taskCount
     * @param threadCount
     * @return
     */
    private int stream(int taskCount, int threadCount) {

        //设置公共 ForkJoinPool 的并行度
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", String.valueOf(threadCount));
        //总操作次数计数器
        AtomicInteger atomicInteger = new AtomicInteger();
        //由于我们设置了公共ForkJoinPool的并行度，直接使用parallel提交任务即可
        IntStream.rangeClosed(1, taskCount).parallel().forEach(i -> increment(atomicInteger));
        //查询计数器当前值
        return atomicInteger.get();
    }


    /**
     * CompletableFuture.runAsync 方法可以指定一个线程池，一般会在使用 CompletableFuture 的时候用到
     * @param taskCount
     * @param threadCount
     * @return
     * @throws InterruptedException
     */
    private int completableFuture(int taskCount, int threadCount) throws InterruptedException, ExecutionException {

        //总操作计数器
        AtomicInteger atomicInteger = new AtomicInteger();
        //自定义一个并行度 = threadCount的 ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool(threadCount);
        //使用 completableFuture.runAsync通过指定线程池异步执行任务
        CompletableFuture.runAsync(() -> IntStream.rangeClosed(1, taskCount).parallel().forEach(i -> increment(atomicInteger)), forkJoinPool).get();
        return atomicInteger.get();

    }



    private void increment(AtomicInteger atomicInteger) {

        atomicInteger.incrementAndGet();

        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
