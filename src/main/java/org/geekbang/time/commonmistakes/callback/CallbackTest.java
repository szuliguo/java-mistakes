package org.geekbang.time.commonmistakes.callback;

/**
 * @author Legal
 * @date 2020/10/30
 * https://www.cnblogs.com/xrq730/p/6424471.html
 */
public class CallbackTest {

    public static void main(String[] args) {

        Student student = new Ricky();
        Teacher teacher = new Teacher(student);

        teacher.askQuestion();
    }
}
