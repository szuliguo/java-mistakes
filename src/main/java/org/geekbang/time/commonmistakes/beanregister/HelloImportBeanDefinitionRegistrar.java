package org.geekbang.time.commonmistakes.beanregister;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import java.util.Map;

/**
 * @author Legal
 * @date 2020/10/21
 */
public class HelloImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

//        BeanDefinition beanDefinition = new GenericBeanDefinition();
//        beanDefinition.setBeanClassName(HelloService.class.getName());
//        MutablePropertyValues values = beanDefinition.getPropertyValues();
//        values.addPropertyValue("id", 1);
//        values.addPropertyValue("name", "ZhangSan");
        //这里注册bean
//        registry.registerBeanDefinition("helloService", beanDefinition );

        //扫描注解
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(ComponentScan.class.getName());

        //test
        System.out.println("#############################");
        System.out.println(annotationAttributes.toString());
        System.out.println("##############################");


        String[] basePakcages = (String[]) annotationAttributes.get("basePackages");

        //test
        System.out.println("#############################");
        System.out.println(basePakcages);
        System.out.println("##############################");


        //扫描类
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry, false);
        TypeFilter helloServiceFilter = new AssignableTypeFilter(HelloService.class);

        scanner.addIncludeFilter(helloServiceFilter);
        scanner.scan(basePakcages);
    }
}
