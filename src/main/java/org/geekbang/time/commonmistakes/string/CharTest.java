package org.geekbang.time.commonmistakes.string;

import org.geekbang.time.commonmistakes.json.jsonpath.Device;
import org.geekbang.time.commonmistakes.json.jsonpath.SubDevice;
import org.junit.Test;

import java.util.function.Function;

/**
 * @author Legal
 * @date 2021/9/17
 */
public class CharTest {

    @Test
    public void charTest() {

        String s = "((((()";
        int res = count(s);
        System.out.println(res);
        String reverseStr = new StringBuilder(s).reverse().toString();
        char[] reverseArray = reverseStr.toCharArray();
        for (int i = 0; i < reverseArray.length; i++) {
            reverseArray[i] ^= 1;
        }


        System.out.println(String.valueOf(reverseArray));
        int result = Math.max(res, count(String.valueOf(reverseArray)));
        System.out.println(result);
    }

    private int count(String s) {

        char[] array = s.toCharArray();

        int size = array.length;
        int res = 0;
        int start = 0;
        int count = 0;

        for (int i = 0; i < size; i++) {
            if (array[i] == '(') {
                count++;
            } else {
                count--;
                if (count < 0) {
                    start = i + 1;
                    count = 0;
                } else if (count == 0) {
                    res = Math.max(res, i - start + 1);
                }
            }
        }

        return res;
    }
}
