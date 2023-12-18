package com.miracle.adminservice.controller.swagger;

import com.miracle.adminservice.dto.response.CommonApiResponse;
import com.miracle.adminservice.dto.response.SuccessApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "관리자 로그인", description = "관리자 로그인 후, 정보를 반환합니다.",
        responses = {
                @ApiResponse(responseCode = "200",
                        description = "정상 요청",
                        content = @Content(
                                mediaType = "application/json",
                                examples = {
                                        @ExampleObject(
                                                name = "성공",
                                                value = "{\"httpStatus\": 200, \"message\": \"관리자 로그인 성공\", \"data\": AdminSignResponseDto }"),
                                },
                                schema = @Schema(implementation = SuccessApiResponse.class)
                        )),
                @ApiResponse(responseCode = "400",
                        description = "비정상 요청",
                        content = @Content(
                                mediaType = "application/json",
                                examples = {
                                        @ExampleObject(
                                                name = "실패 / 이메일 또는 비밀번호 불일치",
                                                value = "{\"httpStatus\": 400, \"message\": \"이메일 또는 비밀번호가 일치하지 않습니다.\", \"data\": false }"),
                                        @ExampleObject(
                                                name = "유효성 / 이메일 값 없음",
                                                value = "{\"httpStatus\": 400, \"code\": \"400_1\", \"message\": \"이메일 값이 없습니다.\", \"exception\": \"MethodArgumentNotValidException\" }"),
                                        @ExampleObject(
                                                name = "유효성 / 이메일 형식 오류",
                                                value = "{\"httpStatus\": 400, \"code\": \"400_1\", \"message\": \"이메일 형식 오류.\", \"exception\": \"MethodArgumentNotValidException\" }"),
                                        @ExampleObject(
                                                name = "유효성 / 이메일 길이 오류",
                                                value = "{\"httpStatus\": 400, \"code\": \"400_1\", \"message\": \"이메일 길이가 너무 짧거나, 깁니다.\", \"exception\": \"MethodArgumentNotValidException\" }"),

                                        @ExampleObject(
                                                name = "유효성 /비밀번호 값 없음",
                                                value = "{\"httpStatus\": 400, \"code\": \"400_4\", \"message\": \"비밀번호 값이 없습니다.\", \"exception\": \"MethodArgumentNotValidException\" }"),
                                        @ExampleObject(
                                                name = "유효성 /비밀번호 형식 오류",
                                                value = "{\"httpStatus\": 400, \"code\": \"400_4\", \"message\": \"비밀번호 형식이 맞지 않습니다.\", \"exception\": \"MethodArgumentNotValidException\" }"),
                                        @ExampleObject(
                                                name = "유효성 /비밀번호 길이 오류",
                                                value = "{\"httpStatus\": 400, \"code\": \"400_4\", \"message\": \"비밀번호가 너무 짧습니다.\", \"exception\": \"MethodArgumentNotValidException\" }"),
                                },
                                schema = @Schema(implementation = CommonApiResponse.class)
                        )),
        })
public @interface ApiAdminLogin {
}
