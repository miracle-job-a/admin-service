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
@Operation(summary = "직무/스택 수정", description = "직무 또는 스택 수정을 처리하는 API",
        responses = {
                @ApiResponse(responseCode = "200",
                        description = "정상 요청",
                        content = @Content(
                                mediaType = "application/json",
                                examples = {
                                        @ExampleObject(
                                                name = "성공 / 스택",
                                                value = "{\"httpStatus\": 200, \"message\": \"스택이 수정되었습니다.\", \"data\": StackAndJobResponseDto }"),
                                        @ExampleObject(
                                                name = "성공 / 직무",
                                                value = "{\"httpStatus\": 200, \"message\": \"직무가 수정되었습니다.\", \"data\": StackAndJobResponseDto }"),
                                },
                                schema = @Schema(implementation = SuccessApiResponse.class)
                        )),
                @ApiResponse(responseCode = "400",
                        description = "비정상 요청",
                        content = @Content(
                                mediaType = "application/json",
                                examples = {
                                        @ExampleObject(
                                                name = "실패 / 중복 스택",
                                                value = "{\"httpStatus\": 400, \"message\": \"이미 등록된 스택 입니다.\", \"data\": false }"),
                                        @ExampleObject(
                                                name = "실패 / 중복 직무",
                                                value = "{\"httpStatus\": 400, \"message\": \"이미 등록된 직무 입니다.\", \"data\": false }"),
                                        @ExampleObject(
                                                name = "실패 / 미존재 스택 id",
                                                value = "{\"httpStatus\": 400, \"message\": \"존재하지 않는 스택 id 입니다.\", \"data\": false }"),
                                        @ExampleObject(
                                                name = "실패 / 미존재 직무 id",
                                                value = "{\"httpStatus\": 400, \"message\": \"존재하지 않는 직무 id 입니다.\", \"data\": false }"),

                                },
                                schema = @Schema(implementation = CommonApiResponse.class)
                        )),
                @ApiResponse(responseCode = "500",
                        description = "서버 에러",
                        content = @Content(
                                mediaType = "application/json",
                                examples = {
                                        @ExampleObject(
                                                name = "실패 / 스택 수정 실패",
                                                value = "{\"httpStatus\": 500, \"message\": \"스택 수정에 실패하였습니다.\", \"data\": false }"),
                                        @ExampleObject(
                                                name = "실패 / 직무 수정 실패",
                                                value = "{\"httpStatus\": 500, \"message\": \"직무 수정에 실패하였습니다.\", \"data\": false }"),

                                },
                                schema = @Schema(implementation = CommonApiResponse.class)
                        )),
        })
public @interface ApiEditStackOrJob {
}
