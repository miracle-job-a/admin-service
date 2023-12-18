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
@Operation(summary = "직무/스택 검색", description = "직무 또는 스택 검색을 처리하는 API",
        responses = {
                @ApiResponse(responseCode = "200",
                        description = "정상 요청",
                        content = @Content(
                                mediaType = "application/json",
                                examples = {
                                        @ExampleObject(
                                                name = "성공 / 스택",
                                                value = "{\"httpStatus\": 200, \"message\": \"스택 검색 결과\", \"data\": List<StackAndJobResponseDto> }"),
                                        @ExampleObject(
                                                name = "성공 / 직무",
                                                value = "{\"httpStatus\": 200, \"message\": \"직무 검색 결과\", \"data\": List<StackAndJobResponseDto> }"),
                                },
                                schema = @Schema(implementation = SuccessApiResponse.class)
                        )),
        })
public @interface ApiSearchStackOrJob {
}
