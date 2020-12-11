package org.geekbang.time.commonmistakes.test;

import lombok.Data;
import org.thymeleaf.util.MapUtils;

import java.util.LinkedHashMap;

/**
 * @author Legal
 * @date 2020/11/23
 */
@Data
public class DpActionVO extends BaseActionVO {

    /** Action type */
    final String actionType = "dp";

    /**
     * key = dpId,value=dpValue
     */
    private LinkedHashMap<String, Object> dps;



}