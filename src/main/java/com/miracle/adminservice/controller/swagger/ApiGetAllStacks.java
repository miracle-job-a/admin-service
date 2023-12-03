package com.miracle.adminservice.controller.swagger;

import com.miracle.adminservice.dto.response.ErrorApiResponse;
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
@Operation(summary = "전체 스택 조회", description = "DB에 등록된 전체 스택을 조회하여 반환합니다.",
        responses = {
                @ApiResponse(responseCode = "200",
                        description = "정상 요청",
                        content = @Content(
                                mediaType = "application/json",
                                examples = {
                                        @ExampleObject(
                                                name = "성공",
                                                value = "{\"httpStatus\": 200, \"message\": \"전체 스택 조회 성공\", \"data\": List<StackResponseDto> }"),
                                },
                                schema = @Schema(implementation = SuccessApiResponse.class)
                        )),
        })
public @interface ApiGetAllStacks {
}
