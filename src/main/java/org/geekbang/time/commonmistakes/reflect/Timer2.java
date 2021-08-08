package org.geekbang.time.commonmistakes.reflect;

import org.springframework.stereotype.Service;

/**
 * @author Legal
 * @date 2021/2/24
 */


public class Timer2 implements BaseTimer {

    @Override
    public void print(String a) {
        System.out.println(a);
    }
}
