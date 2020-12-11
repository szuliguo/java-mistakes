package org.geekbang.time.commonmistakes.springpart1.aopmetricsbackup;

import lombok.extern.slf4j.Slf4j;
import org.geekbang.time.commonmistakes.transaction.transactionpropagation.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Legal
 * @date 2020/9/22
 */


// controller 是自动进行监控的
@Slf4j
@RestController
@RequestMapping("metricstest")
public class MetricsController {

    @Autowired
    private UserService userService;

    @GetMapping("transaction")
    public int transaction(@RequestParam("name") String name) {

        try {
            userService.createUser(new UserEntity(name));
        } catch (Exception e) {
            log.error("create user failed because {}", e.getMessage());
        }

        return userService.getUserCount(name);
    }

}
