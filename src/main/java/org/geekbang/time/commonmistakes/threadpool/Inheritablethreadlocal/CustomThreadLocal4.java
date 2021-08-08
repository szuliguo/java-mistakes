package org.geekbang.time.commonmistakes.threadpool.Inheritablethreadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Legal
 * @date 2021/3/3
 *
 * 正确的应该是从1到100，由于线程的复用，值被替换掉了才会出现不正确的结果
 *
 * 可以使用transmittable-thread-local来改造有问题的代码
 */
public class CustomThreadLocal4 {
    static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
    static ExecutorService pool = Executors.newFixedThreadPool(2);
    public static void main(String[] args) {
        for(int i=0;i<100;i++) {
            int j = i;
            pool.execute(new Thread(new Runnable() {
                @Override
                public void run() {
                    CustomThreadLocal4.threadLocal.set("猿天地"+j);
                    new Service4().call();
                }
            }));
        }
    }
}
class Service4 {
    public void call() {
        CustomThreadLocal4.pool.execute(new Runnable() {
            @Override
            public void run() {
                new Dao4().call();
            }
        });
    }
}
class Dao4 {
    public void call() {
        System.out.println("Dao:" + CustomThreadLocal4.threadLocal.get());
    }
}