package org.geekbang.time.commonmistakes.threadpool.Inheritablethreadlocal;

/**
 * @author Legal
 * @date 2021/3/3
 * https://blog.didispace.com/Spring-Cloud%e4%b8%adHystrix-%e7%ba%bf%e7%a8%8b%e9%9a%94%e7%a6%bb%e5%af%bc%e8%87%b4ThreadLocal%e6%95%b0%e6%8d%ae%e4%b8%a2%e5%a4%b1/
 *
 * 在主类中定义了一个ThreadLocal用来传递数据，然后起了一个线程，在线程中调用Service中的call方法，
 * 并且往Threadlocal中设置了一个值，在Service中获取ThreadLocal中的值，然后再调用Dao中的call方法，
 * 也是获取ThreadLocal中的值
 *
 * 可以看到整个流程都是在同一个线程中执行的，也正确的获取到了ThreadLocal中的值，这种情况是没有问题的。
 *
 */
public class CustomThreadLocal {
    static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                CustomThreadLocal.threadLocal.set("猿天地");
                new Service().call();
            }
        }).start();
    }
}
class Service {
    public void call() {
        System.out.println("Service:" + Thread.currentThread().getName());
        System.out.println("Service:" + CustomThreadLocal.threadLocal.get());
        new Dao().call();
    }
}
class Dao {
    public void call() {
        System.out.println("==========================");
        System.out.println("Dao:" + Thread.currentThread().getName());
        System.out.println("Dao:" + CustomThreadLocal.threadLocal.get());
    }
}
