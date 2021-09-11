package org.geekbang.time.commonmistakes.guaua.strings;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Legal
 * @date 2021/9/11
 */

@Data
@NoArgsConstructor
public class SubDevice {

    private String sub;

    public SubDevice(String sub) {
        this.sub = sub;
    }
}
