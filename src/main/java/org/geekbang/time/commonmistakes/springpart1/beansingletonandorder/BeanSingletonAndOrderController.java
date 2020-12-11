package org.geekbang.time.commonmistakes.springpart1.beansingletonandorder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("beansingletonandorder")
public class BeanSingletonAndOrderController {

    /**
     * 当使用Autowired来注入List,数组的时候，是把spring 容器中 与 集合中元素类型相同的
     * bean 来构造一个对应的集合。所以这里不会报空指针异常
     */
    @Autowired
    List<SayService> sayServiceList;
    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("test")
    public void test() {
        log.info("====================");
        sayServiceList.forEach(SayService::say);
    }

    /**
     * applicationContext.getBeansOfType(SayService.class).values()
     * 意思是获取 SayService 这个类的所有的实现类
     */
    @GetMapping("test2")
    public void test2() {
        log.info("====================");
        applicationContext.getBeansOfType(SayService.class).values().forEach(SayService::say);
    }
}
