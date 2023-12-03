package com.miracle.adminservice.config;

import com.fasterxml.classmate.TypeResolver;
import com.miracle.adminservice.dto.response.ErrorApiResponse;
import com.miracle.adminservice.dto.response.SuccessApiResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.Example;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EnableOpenApi
@Configuration
public class SwaggerConfig {
    /**
     * http://localhost:60003/swagger-ui/index.html
     */
    @Bean
    public Docket api(TypeResolver typeResolver) {
        List<RequestParameter> requestParameterList = new ArrayList<>();
        RequestParameter sessionId = new RequestParameterBuilder()
                .name("Session-Id")
                .in(ParameterType.HEADER)
                .required(Boolean.TRUE)
                .build();
        RequestParameter miracle = new RequestParameterBuilder()
                .name("Miracle")
                .in(ParameterType.HEADER)
                .required(Boolean.TRUE)
                .build();
        requestParameterList.add(sessionId);
        requestParameterList.add(miracle);
        return new Docket(DocumentationType.OAS_30)
                .globalRequestParameters(requestParameterList)
                .additionalModels(typeResolver.resolve(SuccessApiResponse.class))
                .additionalModels(typeResolver.resolve(ErrorApiResponse.class))
                .apiInfo(apiInfo())
                .globalResponses(HttpMethod.GET, defaultResponse())
                .globalResponses(HttpMethod.POST, defaultResponse())
                .globalResponses(HttpMethod.PUT, defaultResponse())
                .globalResponses(HttpMethod.PATCH, defaultResponse())
                .globalResponses(HttpMethod.DELETE, defaultResponse())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.miracle.adminservice.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Miracle Admin Service API")
                .description("관리자와 연관된 요청들을 처리하는 API")
                .version("1.0.0")
                .build();
    }

    private List<Response> defaultResponse() {
        List<Response> defaultResponseList = new ArrayList<>();
        Response unauthorizedResponse = new ResponseBuilder()
                .code("401")
                .description("인증 실패")
                .isDefault(true)
                .examples(
                        List.of(getUnauthorizedBuild())
                ).build();

        Response serverErrorResponse = new ResponseBuilder()
                .code("500")
                .description("서버 에러")
                .isDefault(true)
                .examples(
                        List.of(getServerErrorBuild(),
                                decodeSecretKeyErrorBuild(),
                                encryptDataErrorBuild(),
                                decryptDataErrorBuild(),
                                generateSecretKeyErrorBuild()
                        )
                ).build();

        defaultResponseList.add(unauthorizedResponse);
        defaultResponseList.add(serverErrorResponse);
        return defaultResponseList;
    }

    private static Example getServerErrorBuild() {
        return new ExampleBuilder()
                .id("1")
                .mediaType("application/json")
                .summary("서버 에러")
                .value(new ErrorApiResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "서버에 문제가 생겼습니다. 다시 시도해주세요.",
                        "500",
                        "RuntimeException"))
                .build();
    }

    private static Example decodeSecretKeyErrorBuild() {
        return new ExampleBuilder()
                .id("2")
                .mediaType("application/json")
                .summary("SecretKey 디코딩 실패")
                .value(new ErrorApiResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "SecretKey 디코딩에 실패하였습니다.",
                        "500",
                        "DecodeSecretKeyException"))
                .build();
    }

    private static Example encryptDataErrorBuild() {
        return new ExampleBuilder()
                .id("3")
                .mediaType("application/json")
                .summary("데이터 암호화 실패")
                .value(new ErrorApiResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "데이터 암호화에 실패하였습니다.",
                        "500",
                        "EncryptDataException"))
                .build();
    }

    private static Example decryptDataErrorBuild() {
        return new ExampleBuilder()
                .id("4")
                .mediaType("application/json")
                .summary("데이터 복호화 실패")
                .value(new ErrorApiResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "데이터 복호화에 실패하였습니다.",
                        "500",
                        "DecryptDataException"))
                .build();
    }

    private static Example generateSecretKeyErrorBuild() {
        return new ExampleBuilder()
                .id("5")
                .mediaType("application/json")
                .summary("SecretKey 생성 실패")
                .value(new ErrorApiResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "SecretKey 생성에 실패하였습니다.",
                        "500",
                        "GenerateSecretKeyException"))
                .build();
    }

    private static Example getUnauthorizedBuild() {
        return new ExampleBuilder()
                .id("1")
                .mediaType("application/json")
                .summary("토큰 인증 실패")
                .value(new ErrorApiResponse(
                        HttpStatus.UNAUTHORIZED.value(),
                        "토큰 값이 일치하지 않습니다.",
                        "401",
                        "UnauthorizedTokenException"))
                .build();
    }
}
