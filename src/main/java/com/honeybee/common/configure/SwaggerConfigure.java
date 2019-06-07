package com.honeybee.common.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * swagger配置
 */

@Component
@EnableSwagger2  // 开启swagger自动配置
public class SwaggerConfigure {

    // controller路径
    private final String basePackage = "com.honeybee.controller";

    /**
     * 配置接口扫描
     *     1.RequestHandlerSelectors.basePackage(basePackage)  扫描具体路径
     *     2.RequestHandlerSelectors.any() 扫描所有接口
     *     3.RequestHandlerSelectors.none() 不扫描接口
     *     4.RequestHandlerSelectors.withMethodAnnotation(final Class<? extends Annotation> annotation)
     *       通过方法上的注解扫描，如withMethodAnnotation(GetMapping.class)只扫描get请求
     *     5.RequestHandlerSelectors.withClassAnnotation(final Class<? extends Annotation> annotation)
     *       通过类上的注解扫描，如.withClassAnnotation(Controller.class)只扫描有controller注解的类中的接口
     *
     * @return Docket
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                // 配置path过滤规则
                // PathSelectors.any("/*")
                // PathSelectors.none()
                // PathSelectors.regex()
                //.paths(PathSelectors.ant("/*"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("HXY", "", "1473706220@qq.com");
        return new ApiInfo("honeybee", "honeybee", "v1.0",
                "http://localhost:8888", contact, "Honeybee 1.0",
                "", new ArrayList<>());
    }
}
