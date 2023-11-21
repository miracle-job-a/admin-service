package com.miracle.adminservice.config;

import com.fasterxml.classmate.TypeResolver;
import com.miracle.adminservice.dto.response.ErrorApiResponse;
import com.miracle.adminservice.dto.response.SuccessApiResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableOpenApi
@Configuration
public class SwaggerConfig {
    /**
     * http://localhost:60003/swagger-ui/index.html
     */

    @Bean
    public Docket api(TypeResolver typeResolver) {
        return new Docket(DocumentationType.OAS_30)
                .additionalModels(typeResolver.resolve(SuccessApiResponse.class))
                .additionalModels(typeResolver.resolve(ErrorApiResponse.class))
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.miracle.adminservice.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Miracle Company Service API")
                .description("관리자와 연관된 요청들을 처리하는 API")
                .version("1.0.0")
                .build();
    }
}
