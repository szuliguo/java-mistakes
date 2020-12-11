package org.geekbang.time.commonmistakes.test;

import lombok.Data;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * @author Legal
 * @date 2020/11/23
 */
@Data
public class CategoryGroupTimerVO implements Serializable {

    /** Id */
    private Long id;

    /** Time */
    private String time;

    /** Loops */
    private String loops;

    /** Date */
    private String date;

    /** Timezone id */
    private String timezoneId;

    /** Category id */
    private String categoryId;

    /** Group id */
    private String groupId;

    /** Group order */
    private Integer groupOrder;

    /** Actions */
    private String actions;

    private Integer status;

    public static void main(String[] args) throws JSONException {

        CategoryGroupTimerVO timerVO = new CategoryGroupTimerVO();
        String workMode = "leaving_home";
        String time = "22:30";


        JSONObject obj = new JSONObject();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("2", workMode);
        obj.put("dps", jsonObject);
        obj.put("time", time);
        JSONArray array = new JSONArray();
        array.put(obj);
        timerVO.actions = array.toString();

        System.out.println(timerVO.actions);
    }
}
