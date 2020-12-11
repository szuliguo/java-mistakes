package org.geekbang.time.commonmistakes.springpart1.aopmetrics;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RequestMapping("test")
@RestController
public class TestController {

    @Resource
    IUserService userService;

    @GetMapping("test1")
    public void test() {
        userService.count(1);
    }

    @GetMapping("test2")
    public void test2() {

    }
}
