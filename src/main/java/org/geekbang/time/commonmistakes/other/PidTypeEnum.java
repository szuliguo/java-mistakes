package org.geekbang.time.commonmistakes.other;

/**
 * @author Legal
 * @date 2021/1/6
 */
public enum PidTypeEnum {

    DANFOSS_WKF(0, "den6kzk0"),
    DANFOSS_ICON(1, "n7dtaka8"),;

    private final Integer type;
    private final String pid;

    PidTypeEnum(Integer type, String pid) {
        this.type = type;
        this.pid = pid;
    }

    public Integer getType() {
        return type;
    }


    public String getPid() {
        return pid;
    }

    public static PidTypeEnum getEnumByCode(Integer type) {
        if (type == null) {
            return null;
        }
        for (PidTypeEnum temp : values()) {
            if (temp.getType().intValue() == type) {
                return temp;
            }
        }
        return null;
    }
}

