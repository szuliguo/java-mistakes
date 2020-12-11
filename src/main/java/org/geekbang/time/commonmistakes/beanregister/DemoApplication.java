package org.geekbang.time.commonmistakes.beanregister;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author
 * @date 2020/10/21
 */

@Configuration
@ComponentScan("org.geekbang.time.commonmistakes.beanregister")
@Import(HelloImportBeanDefinitionRegistrar.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DemoApplication.class}) //表示只需要这一个文件
public class DemoApplication extends HelloConfiguration {

    @Autowired
    HelloService helloService;


    @Test
    public void contextLoads() {
        System.out.print(helloService.getClass());

    }
}
