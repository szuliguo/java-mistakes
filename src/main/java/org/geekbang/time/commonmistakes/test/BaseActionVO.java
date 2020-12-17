package org.geekbang.time.commonmistakes.test;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * @author Legal
 * @date 2020/11/9
 */
public class BaseActionVO {

    @Getter
    @Setter
    private LinkedHashMap<String, Object> ext;


    @Getter
    @Setter
    private Integer id;


    public static void main(String[] args) {
        BaseActionVO vo = new BaseActionVO();
        vo.setId(1);


        LinkedHashMap<String, Object> map = vo.getExt();

        if (Objects.isNull(map)) {
            map = new LinkedHashMap<>(4);
            vo.setExt(map);
        }
        map.put("a", 1);
        map.put("d", "dd");

        System.out.println(vo.getExt().toString());
    }

}
