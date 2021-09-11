package org.geekbang.time.commonmistakes.guaua.collections;

/**
 * @author Legal
 * @date 2021/6/7
 */
public enum BusinessEnum {
    // 统计
    SOLUTION_STATISTICS("statistics", "statistics");

    private String key;
    private String value;

    BusinessEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
