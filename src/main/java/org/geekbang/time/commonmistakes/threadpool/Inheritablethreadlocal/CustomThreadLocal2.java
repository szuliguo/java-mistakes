package org.geekbang.time.commonmistakes.threadpool.Inheritablethreadlocal;

/**
 * @author Legal
 * @date 2021/3/3
 *
 * 可以看到这次的请求是由2个线程共同完成的，在Service中还是可以拿到ThreadLocal的值，
 * 到了Dao中就拿不到了，因为线程已经切换了，
 * 这就是开始讲的ThreadLocal的数据会丢失的问题。
 */
public class CustomThreadLocal2 {
    static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                CustomThreadLocal2.threadLocal.set("猿天地");
                new Service2().call();
            }
        }).start();
    }
}
 class Service2 {
    public void call() {
        System.out.println("Service:" + Thread.currentThread().getName());
        System.out.println("Service:" + CustomThreadLocal2.threadLocal.get());
        //new Dao().call();
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Dao2().call();
            }
        }).start();
    }
}
class Dao2 {
    public void call() {
        System.out.println("==========================");
        System.out.println("Dao:" + Thread.currentThread().getName());
        System.out.println("Dao:" + CustomThreadLocal2.threadLocal.get());
    }
}