package com.miracle.adminservice.exception;



import com.miracle.adminservice.dto.response.CommonApiResponse;
import com.miracle.adminservice.dto.response.ErrorApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    /**
     * 401_1 토큰 인증
     */
    @ExceptionHandler(value = UnauthorizedTokenException.class)
    public CommonApiResponse unauthorizedTokenHandle(UnauthorizedTokenException e) {
        log.info("[unauthorizedTokenHandle] : " + e.getMessage());

        return ErrorApiResponse.builder()
                .httpStatus(HttpStatus.UNAUTHORIZED.value())
                .code("401")
                .message("토큰 값이 일치하지 않습니다.")
                .exception("UnauthorizedTokenException")
                .build();
    }


    /**
     * 500 서버에러
     */
    @ExceptionHandler(value = RuntimeException.class)
    public CommonApiResponse runtimeHandle(RuntimeException e) {
        log.info("[runtimeHandle] : " + e.getMessage());

        return ErrorApiResponse.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .code("500")
                .message("서버에 문제가 생겼습니다. 다시 시도해주세요.")
                .exception("RuntimeException")
                .build();
    }
}
