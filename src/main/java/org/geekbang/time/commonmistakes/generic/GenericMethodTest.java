package org.geekbang.time.commonmistakes.generic;

/**
 * @author Legal
 * @date 2020/12/20
 * 泛型方法 test
 */
public class GenericMethodTest {

    // 泛型方法 printArray
    public static <E> void printArray(E[] inputArray) {

        //输出数组元素
        for (E element : inputArray) {
            System.out.printf("%s", element);
        }
        System.out.println();

    }

    public static <T extends Comparable<T>> T maximum(T x, T y, T z) {

        T max = x;
        if (y.compareTo(max) > 0) {
            max = y;
        }

        if (z.compareTo(max) > 0) {
            max = z;
        }

        return max;
    }
}
