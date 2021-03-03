package org.geekbang.time.commonmistakes.threadpool.Inheritablethreadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Legal
 * @date 2021/3/3
 */
public class CustomThreadLocal5 {
    static TransmittableThreadLocal<String> threadLocal = new TransmittableThreadLocal<>();
    static ExecutorService pool =  TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(2));
    public static void main(String[] args) {
        for(int i=0;i<100;i++) {
            int j = i;
            pool.execute(new Thread(new Runnable() {
                @Override
                public void run() {
                    CustomThreadLocal5.threadLocal.set("猿天地"+j);
                    new Service5().call();
                }
            }));
        }
    }
}
class Service5 {
    public void call() {
        CustomThreadLocal5.pool.execute(new Runnable() {
            @Override
            public void run() {
                new Dao5().call();
            }
        });
    }
}
class Dao5 {
    public void call() {
        System.out.println("Dao:" + CustomThreadLocal5.threadLocal.get());
    }
}
