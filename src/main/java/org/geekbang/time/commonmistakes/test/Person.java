package org.geekbang.time.commonmistakes.test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

/**
 * @author Legal
 * @date 2020/12/2
 */


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person {


    @JsonProperty("person_name")
    private  String personName;


    public static void main(String[] args) throws JsonProcessingException {
        Person p = new Person();
        p.setPersonName("abc");
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(p));
        System.out.println(JSONObject.toJSONString(p));

    }




}
