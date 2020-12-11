package org.geekbang.time.commonmistakes.callback;

/**
 * @author Legal
 * @date 2020/10/30
 */
public class Teacher implements  Callback {

    private Student student;

    public Teacher(Student student) {
        this.student = student;
    }

    public void askQuestion() {
        student.resolveQuestion(this);
    }

    @Override
    public void tellAnswer(int answer) {
        System.out.print("知道了，你的答案是" + answer);
    }
}
