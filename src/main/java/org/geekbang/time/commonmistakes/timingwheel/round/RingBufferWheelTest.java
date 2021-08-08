package org.geekbang.time.commonmistakes.timingwheel.round;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class RingBufferWheelTest {

    @Test
    public void ringBufferWheelTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        RingBufferWheel wheel = new RingBufferWheel(executorService, 8);

        for (int i = 0; i < 65; i++) {
            RingBufferWheel.Task task = new Job(i);
            task.setKey(i);
            wheel.addTask(task);
        }

        wheel.start();
        log.info("task size = {}", wheel.taskMapSize());
        wheel.stop(false);
    }

    private class Job extends RingBufferWheel.Task {
        private int num;

        public Job(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            log.info("number = {}", num);
        }
    }

}
