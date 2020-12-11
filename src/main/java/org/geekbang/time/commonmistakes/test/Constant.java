package org.geekbang.time.commonmistakes.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Legal
 * @date 2020/11/30
 */
public class Constant {

    public static final Map<String, Integer> LOOP_STRATEGY = new HashMap<>(10);



       static {
           LOOP_STRATEGY.put("1000000", 1);
           LOOP_STRATEGY.put("0100000", 2);
           LOOP_STRATEGY.put("0010000", 3);
           LOOP_STRATEGY.put("0001000", 4);
           LOOP_STRATEGY.put("0000100", 5);
           LOOP_STRATEGY.put("0000010", 6);
           LOOP_STRATEGY.put("0000001", 7);
       }


}
