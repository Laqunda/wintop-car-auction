package com.wintop.ms.carauction.core.config;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: 付陈林.
 * @Description:
 * @Date: 13:32 on 2018/3/13.
 * @Modified by:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("ningmengcar")
                .ignoredParameterTypes(HttpSession.class, Authentication.class, HttpServletRequest.class, HttpServletResponse.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wintop.ms.carauction.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("针对柠檬竞价项目开发的一些列的RESTful APIs  开发团队：运通集团信息技术部。")
                .description("感谢大家的辛苦！接口的提供者包括以下程序猿：付陈林、梁廷森、陈小桂、张籽娟、李凯。")
                .termsOfServiceUrl("http://www.ningmengcar.com/")
                .license("程序猿-你不懂的忧伤")
                .version("1.0")
                .build();
    }
}
