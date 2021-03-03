package org.geekbang.time.commonmistakes.test;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author Legal
 * @date 2021/2/22
 *
 * 多线程并行处理定时任务时，Timer 运行多个 TimeTask 时，
 * 只要其中之一没有捕获抛出的异常，其它任务便会自动终止运行，
 * 使用 ScheduledExecutorService 则没有这个问题。
 */
public class TimerTest {
    public static void main(String[] args) {

        final Timer timer = new Timer();

        // 任务1， 每一秒执行一次
        timer.schedule(new TimerTask() {
            public void run() {

                Random random = new Random();
                int i = random.nextInt(4);
                if (i == 0) {
                    throw new RuntimeException();
                }


                System.out.println("I am running 1 ...");
            }
        }, 0, 1000);
    }
}
