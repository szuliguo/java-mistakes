package org.geekbang.time.commonmistakes.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;

/**
 * @author Legal
 * @date 2021/2/25
 */

@Data
public class Son extends Parent {


    @PostConstruct
    public void initParent() {
        System.out.println("####init######");
        super.setDesc("desc");
    }
}
