package org.geekbang.time.commonmistakes.test;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Legal
 * @date 2021/2/22
 */
public class ScheduleTest {
    public static void main(String[] args) {
        Runnable runable = new Runnable(){

            public void run() {
                Random random = new Random();
                int i = random.nextInt(4);
                if (i == 0) {
                    System.out.println("run error....");
                    throw new RuntimeException();
                }
                System.out.println(System.currentTimeMillis()+"...hello");
            }
        };

        Runnable runable2 = new Runnable(){

            public void run() {
                System.out.println(System.currentTimeMillis()+"...runnable2");
            }
        };
        long delay = 0;  //延迟执行时间（秒）
        long period = 1; //执行的时间间隔（秒）
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(runable, delay, period, TimeUnit.SECONDS);
        service.scheduleAtFixedRate(runable2, delay, period, TimeUnit.SECONDS);

    }

}
