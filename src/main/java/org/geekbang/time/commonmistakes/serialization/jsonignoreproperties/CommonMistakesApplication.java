package org.geekbang.time.commonmistakes.serialization.jsonignoreproperties;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.geekbang.time.commonmistakes.common.Utils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CommonMistakesApplication {

    public static void main(String[] args) {
        Utils.loadPropertySource(CommonMistakesApplication.class, "jackson.properties");
        SpringApplication.run(CommonMistakesApplication.class, args);
    }

    /**
     * 此处相当于是自定义了一个ObjectMapper对象。让枚举索引列为索引值，而不是字符串值
     * 自定义 ObjectMapper启用WRITE_ENUMS_USING_INDEX 序列化功能特性时，覆盖了Spring Boot 自动创建的
     * ObjectMapper;而这个自动创建的ObjectMapper设置过FAIL_ON_UNKNOWN_PROPERTIES 反序列化特性为false,以
     * 确保出现未知字段时不要抛出异常
     * @return
     */
//    @Bean
//    public ObjectMapper objectMapper() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX, true);
//        return objectMapper;
//    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> builder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_INDEX);
    }
}

