package org.geekbang.time.commonmistakes.callback;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Legal
 * @date 2020/10/30
 * https://www.cnblogs.com/xrq730/p/6424471.html
 */
public class CallbackTest {

    public static void main(String[] args) {

        String json =
                "{\"allergies\":{\"value\":[{\"name\":\"Jdjd\",\"reaction\":\"Xjdj\"}]},\"date_of_birth\":\"05-05-1909\",\"doctor\":[{\"name\":\"Bdnx\",\"tel\":\"Zbnnx\",\"title\":\"Xnnx\"}],\"height\":{\"unit\":\"INCHES\",\"value\":40},\"medical_conditions\":{\"value\":[{\"name\":\"Ejje\"}]},\"medications\":{\"value\":[{\"name\":\"Djd\"}]},\"username\":\"Shjd\",\"weight\":{\"unit\":\"IBS\",\"value\":100}}\n";
                JSONObject.parseObject(json);

        Student student = new Ricky();
        Teacher teacher = new Teacher(student);

        teacher.askQuestion();
    }
}
