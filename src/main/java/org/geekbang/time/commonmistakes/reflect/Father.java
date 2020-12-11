package org.geekbang.time.commonmistakes.reflect;

/**
 * @author Legal
 * @date 2020/10/14
 */
public class Father {

    private int weight;

    public Father(int weight) {
        this.weight = weight;
    }

    public Father() {
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
