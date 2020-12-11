package org.geekbang.time.commonmistakes.concurrenttool.threadlocal;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.nio.ch.ThreadPool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;



@Slf4j
@RestController
@RequestMapping("threadlocal")
public class ThreadLocalMisuseController {

    private static final ThreadLocal<Integer> currentUser = ThreadLocal.withInitial(() -> null);

    @GetMapping("wrong")
    public Map wrong(@RequestParam("userId") Integer userId) {
        String before  = Thread.currentThread().getName() + ":" + currentUser.get();
        currentUser.set(userId);
        String after  = Thread.currentThread().getName() + ":" + currentUser.get();
        Map result = new HashMap();
        result.put("before", before);
        result.put("after", after);
        return result;
    }

    @GetMapping("right")
    public Map right(@RequestParam("userId") Integer userId) {
        String before  = Thread.currentThread().getName() + ":" + currentUser.get();
        currentUser.set(userId);
        try {
            String after = Thread.currentThread().getName() + ":" + currentUser.get();
            Map result = new HashMap();
            result.put("before", before);
            result.put("after", after);
            return result;
        } finally {
            currentUser.remove();
        }
    }

    @GetMapping("better")
    public int better() throws  InterruptedException {

        //激进线程池
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(10) {
            @Override
            public boolean offer(Runnable e) {
                return false;
            }
        };

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 5,            5, TimeUnit.SECONDS,            queue,
                new ThreadFactoryBuilder().build(),
                (r, executor) -> {
                      try {
                          //等出现拒绝后再加入队列
                          // 如果希望队列满了阻塞线程而不是抛出异常，那么可以注释掉下面三行代码，修改为executor.getQueue().put(r);
                          if (!executor.getQueue().offer(r, 0, TimeUnit.SECONDS)) {
                              throw new RejectedExecutionException("ThreadPool queue full, failed to offer " + r.toString());
                          }
                      } catch (InterruptedException e) {
                          Thread.currentThread().interrupt();
                      }
        });

        //激进线程池实现结束


        AtomicInteger atomicInteger = new AtomicInteger();
        IntStream.rangeClosed(1, 20).forEach(
                i -> {

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    int id = atomicInteger.incrementAndGet();

                    try {
                        threadPool.submit(() -> {
                            log.info("{} started", id);

                            try {
                                TimeUnit.SECONDS.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            log.info("{} finished", id);
                        });
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        log.error("error submitting task {}", id, ex);
                        atomicInteger.decrementAndGet();
                    }


                }
        );

        TimeUnit.SECONDS.sleep(60);
        return atomicInteger.intValue();

    }
}
