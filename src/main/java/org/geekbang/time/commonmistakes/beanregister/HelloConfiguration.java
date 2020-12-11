package org.geekbang.time.commonmistakes.beanregister;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Legal
 * @date 2020/10/21
 */

@Configuration
@ComponentScan("org.geekbang.time.commonmistakes.beanregister")
@Import(HelloImportBeanDefinitionRegistrar.class)
@RunWith(SpringJUnit4ClassRunner.class)
public abstract  class HelloConfiguration {
}
