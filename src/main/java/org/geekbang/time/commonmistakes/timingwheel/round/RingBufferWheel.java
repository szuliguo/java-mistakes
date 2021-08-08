package org.geekbang.time.commonmistakes.timingwheel.round;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Function:Ring Queue, it can be used to delay task.
 *
 * @author crossoverJie
 * Date: 2019-09-20 14:46
 * @since JDK 1.8
 */

@Slf4j
public final class RingBufferWheel {

    private Logger logger = LoggerFactory.getLogger(RingBufferWheel.class);


    /**
     * default ring buffer size
     */
    private static final int STATIC_RING_SIZE = 8;

    private Object[] ringBuffer;

    private int bufferSize;

    /**
     * business thread pool
     */
    private ExecutorService executorService;

    private volatile int size = 0;

    /***
     * task stop sign
     */
    private volatile boolean stop = false;

    /**
     * task start sign
     */
    private volatile AtomicBoolean start = new AtomicBoolean(false);

    /**
     * total tick times
     */
    private AtomicInteger tick = new AtomicInteger();

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private AtomicInteger taskId = new AtomicInteger();
    private Map<Integer, Task> taskMap = new ConcurrentHashMap<>(16);

    /**
     * Create a new delay task ring buffer by default size
     *
     * @param executorService the business thread pool
     */
    public RingBufferWheel(ExecutorService executorService) {
        this.executorService = executorService;
        this.bufferSize = STATIC_RING_SIZE;
        this.ringBuffer = new Object[bufferSize];
    }


    /**
     * Create a new delay task ring buffer by custom buffer size
     *
     * @param executorService the business thread pool
     * @param bufferSize      custom buffer size
     */
    public RingBufferWheel(ExecutorService executorService, int bufferSize) {
        this(executorService);

        if (!powerOf2(bufferSize)) {
            throw new RuntimeException("bufferSize=[" + bufferSize + "] must be a power of 2");
        }
        this.bufferSize = bufferSize;
        this.ringBuffer = new Object[bufferSize];
    }

    /**
     * Add a task into the ring buffer(thread safe)
     *
     * @param task business task extends {@link Task}
     */
    public int addTask(Task task) {
        int key = task.getKey();
        int id;

        log.info("addTask bufferSize = {}", bufferSize);

        try {
            lock.lock();
            // 索引
            int index = mod(key, bufferSize);
            // 设置索引
            task.setIndex(index);
            // 该索引上的任务列表
            Set<Task> tasks = get(index);

            // 第几圈
            int cycleNum = cycleNum(key, bufferSize);
            log.info("addTask index = {}, cycleNum = {}", index, cycleNum);
            // 该索引上的任务列表不为空
            if (tasks != null) {
                task.setCycleNum(cycleNum);
                tasks.add(task);
            // 该索引上的任务列表为空
            } else {
                task.setIndex(index);
                task.setCycleNum(cycleNum);
                Set<Task> sets = new HashSet<>();
                sets.add(task);
                put(key, sets);
            }
            id = taskId.incrementAndGet();
            task.setTaskId(id);
            taskMap.put(id, task);
            size++;
        } finally {
            lock.unlock();
        }

        start();

        return id;
    }


    /**
     * 取消任务
     * Cancel task by taskId
     * @param id unique id through {@link #addTask(Task)}
     * @return
     */
    public boolean cancel(int id) {

        boolean flag = false;
        Set<Task> tempTask = new HashSet<>();

        try {
            lock.lock();
            Task task = taskMap.get(id);
            if (task == null) {
                return false;
            }

            Set<Task> tasks = get(task.getIndex());
            for (Task tk : tasks) {
                if (tk.getKey() == task.getKey() && tk.getCycleNum() == task.getCycleNum()) {
                    size--;
                    flag = true;
                    taskMap.remove(id);
                } else {
                    tempTask.add(tk);
                }

            }
            //update origin data
            ringBuffer[task.getIndex()] = tempTask;
        } finally {
            lock.unlock();
        }

        return flag;
    }

    /**
     * Thread safe
     *
     * @return the size of ring buffer
     */
    public int taskSize() {
        return size;
    }

    /**
     * 时间轮里面的任务数
     * Same with method {@link #taskSize}
     * @return
     */
    public int taskMapSize(){
        return taskMap.size();
    }

    /**
     * 开启时间轮
     * Start background thread to consumer wheel timer, it will always run until you call method {@link #stop}
     */
    public void start() {
        if (!start.get()) {

            if (start.compareAndSet(start.get(), true)) {
                logger.info("Delay task is starting");
                Thread job = new Thread(new TriggerJob());
                job.setName("consumer RingBuffer thread");
                job.start();
                start.set(true);
            }

        }
    }

    /**
     * 停止时间轮
     * Stop consumer ring buffer thread
     *
     * @param force True will force close consumer thread and discard all pending tasks
     *              otherwise the consumer thread waits for all tasks to completes before closing.
     */
    public void stop(boolean force) {
        if (force) {
            logger.info("Delay task is forced stop");
            stop = true;
            executorService.shutdownNow();
        } else {
            logger.info("Delay task is stopping");
            if (taskSize() > 0) {
                try {
                    lock.lock();
                    condition.await();
                    stop = true;
                } catch (InterruptedException e) {
                    logger.error("InterruptedException", e);
                } finally {
                    lock.unlock();
                }
            }
            executorService.shutdown();
        }


    }


    /**
     * 该索引对应的任务队列
     */
    private Set<Task> get(int index) {
        return (Set<Task>) ringBuffer[index];
    }

    /**
     * 在该索引存入任务队列
     */
    private void put(int key, Set<Task> tasks) {
        int index = mod(key, bufferSize);
        ringBuffer[index] = tasks;
    }

    /**
     * Remove and get task list.
     * @param key
     * @return task list
     */
    private Set<Task> remove(int key) {
        Set<Task> tempTask = new HashSet<>();
        Set<Task> result = new HashSet<>();

        Set<Task> tasks = (Set<Task>) ringBuffer[key];
        if (tasks == null) {
            return result;
        }

        for (Task task : tasks) {
            if (task.getCycleNum() == 0) {
                result.add(task);

                size2Notify();
            } else {
                // decrement 1 cycle number and update origin data
                task.setCycleNum(task.getCycleNum() - 1);
                tempTask.add(task);
                // 移除并且释放内存
                // remove task, and free the memory.
                taskMap.remove(task.getTaskId());
            }
        }

        //update origin data
        // 更新索引对应的时间轮数据
        ringBuffer[key] = tempTask;

        return result;
    }

    private void size2Notify() {
        try {
            lock.lock();
            size--;
            // 如果定时任务数量为0，通过condition唤醒，暂停时间轮
            if (size == 0) {
                condition.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    private boolean powerOf2(int target) {
        if (target < 0) {
            return false;
        }
        int value = target & (target - 1);
        if (value != 0) {
            return false;
        }

        return true;
    }

    private int mod(int target, int mod) {
        // equals target % mod
        log.info("mod  before target = {}, mod = {}, tick = {}", target, mod, tick.get());
        target = target + tick.get();
        int result =  target & (mod - 1);
        log.info("mod after target = {}, result = {}", target, result);
        return result;
    }

    private int cycleNum(int target, int mod) {
        //equals target/mod
        return target >> Integer.bitCount(mod - 1);
    }

    /**
     * An abstract class used to implement business.
     */
    public abstract static class Task extends Thread {

        private int index;

        private int cycleNum;

        private int key;

        /**
         * The unique ID of the task
         */
        private int taskId ;

        @Override
        public void run() {
        }

        public int getKey() {
            return key;
        }

        /**
         *
         * @param key Delay time(seconds)
         */
        public void setKey(int key) {
            this.key = key;
        }

        public int getCycleNum() {
            return cycleNum;
        }

        private void setCycleNum(int cycleNum) {
            this.cycleNum = cycleNum;
        }

        public int getIndex() {
            return index;
        }

        private void setIndex(int index) {
            this.index = index;
        }

        public int getTaskId() {
            return taskId;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
        }
    }


    /**
     * 后台任务从时间轮中取出定时任务
     */
    private class TriggerJob implements Runnable {

        @Override
        public void run() {
            int index = 0;
            while (!stop) {
                try {
                    Set<Task> tasks = remove(index);
                    for (Task task : tasks) {
                        executorService.submit(task);
                    }

                    if (++index > bufferSize - 1) {
                        index = 0;
                    }

                    //Total tick number of records
                    tick.incrementAndGet();
                    TimeUnit.SECONDS.sleep(1);

                } catch (Exception e) {
                    logger.error("Exception", e);
                }

            }

            logger.info("Delay task has stopped");
        }
    }
}
