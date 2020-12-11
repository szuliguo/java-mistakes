package org.geekbang.time.commonmistakes.callback;

/**
 * @author Legal
 * @date 2020/10/30
 * 一个叫做 ricky 的同学解决老师提出的问题
 */
public class Ricky implements Student {

    @Override
    public void resolveQuestion(Callback callback)  {

        //模拟解决问题

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 回调，告诉老师作业写了多久
        callback.tellAnswer(3);

    }
}
