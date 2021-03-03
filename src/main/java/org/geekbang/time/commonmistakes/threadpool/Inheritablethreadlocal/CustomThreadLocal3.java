package org.geekbang.time.commonmistakes.threadpool.Inheritablethreadlocal;

/**
 * @author Legal
 * @date 2021/3/3
 *
 * InheritableThreadLocal就是为了解决这种线程切换导致ThreadLocal拿不到值的问题而产生的。
 *
 */
public class CustomThreadLocal3 {
    static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                CustomThreadLocal3.threadLocal.set("猿天地");
                new Service3().call();
            }
        }).start();
    }
}
class Service3 {
    public void call() {
        System.out.println("Service:" + Thread.currentThread().getName());
        System.out.println("Service:" + CustomThreadLocal3.threadLocal.get());
        //new Dao().call();
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Dao3().call();
            }
        }).start();
    }
}
class Dao3 {
    public void call() {
        System.out.println("==========================");
        System.out.println("Dao:" + Thread.currentThread().getName());
        System.out.println("Dao:" + CustomThreadLocal3.threadLocal.get());
    }
}