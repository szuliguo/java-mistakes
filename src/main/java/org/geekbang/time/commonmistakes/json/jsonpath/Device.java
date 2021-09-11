package org.geekbang.time.commonmistakes.json.jsonpath;

import lombok.Data;

/**
 * @author Legal
 * @date 2021/7/21
 */

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Device {

    private String id;
    private String uid;
    private String timeZone;
    private String productId;
    private String uuid;
    private Boolean isExists;
    private Integer accessType;
    private Integer ability;
    private Boolean sub;
    private String timeZoneId;
    private String ownerId;
    private String mac;
    private Long activeTime;

    private String name;
    private String customName;

    private SubDevice subDevice;


    public Device(String name, SubDevice subDevice) {
        this.name = name;
        this.subDevice = subDevice;
    }
}
