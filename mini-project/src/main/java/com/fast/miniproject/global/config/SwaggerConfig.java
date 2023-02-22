package com.fast.miniproject.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@EnableWebMvc
@Configuration
public class SwaggerConfig extends WebMvcConfigurationSupport {

    private static final String REFERENCE = "Bearer";

    @Bean
    public Docket api() {
        Server serverLocal = new Server("local", "http://localhost:8080", "for local", Collections.emptyList(), Collections.emptyList());
        Server devServer = new Server("test", "https://kingtaeyoon.shop/", "for test", Collections.emptyList(), Collections.emptyList());
        return new Docket(DocumentationType.OAS_30) // 3.0 문서버전으로 세팅
                .servers(serverLocal,devServer)
                .groupName("미니프로젝트 3조")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fast.miniproject"))
//                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .ignoredParameterTypes(AuthenticationPrincipal.class)
                .securityContexts(List.of(securityContext()))
                .securitySchemes(List.of(bearerAuthSecurityScheme()));

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("3조 미니 프로젝트 백앤드")
                .description("금융 상품 추천 서비스 페이지 제작 ")
//                .contact(new Contact("이름","홈페이지","email"))
//                .license("라이센스소유자")
//                .licenseUrl("라이센스URL")
                .version("1.0")
                .build();
    }

    private HttpAuthenticationScheme bearerAuthSecurityScheme() {
        return HttpAuthenticationScheme.JWT_BEARER_BUILDER
                .name(REFERENCE).build();
    }

    //JWT SecurityContext 구성
    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEveryThing");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference(REFERENCE, authorizationScopes));
    }
}