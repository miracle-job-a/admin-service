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

    @ExceptionHandler(value = DecodeSecretKeyException.class)
    public CommonApiResponse decodePrivateKeyHandle(DecodeSecretKeyException e, HttpRequest request) {
        log.info("[DecodeSecretKeyException] : " + e.getMessage());
        return ErrorApiResponse.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .code("500")
                .message("SecretKey 디코딩에 실패하였습니다.")
                .exception("DecodeSecretKeyException")
                .build();
    }

    @ExceptionHandler(value = DecryptDataException.class)
    public CommonApiResponse decrypteDataHandle(DecryptDataException e, HttpRequest request) {
        log.info("[DecryptDataException] : " + e.getMessage());
        return ErrorApiResponse.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .code("500")
                .message("데이터 복호화에 실패하였습니다.")
                .exception("DecryptDataException")
                .build();
    }

    @ExceptionHandler(value = EncryptDataException.class)
    public CommonApiResponse decrypteDataHandle(EncryptDataException e, HttpRequest request) {
        log.info("[EncryptDataException] : " + e.getMessage());
        return ErrorApiResponse.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .code("500")
                .message("데이터 암호화에 실패하였습니다.")
                .exception("EncryptDataException")
                .build();
    }

    //당장 사용하지 않지만 미리 구현
    @ExceptionHandler(value = GenerateSecretKeyException.class)
    public CommonApiResponse generateSecretKeyHandle(GenerateSecretKeyException e, HttpRequest request) {
        log.info("[GenerateSecretKeyException] : " + e.getMessage());
        return ErrorApiResponse.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .code("500")
                .message("SecretKey 생성에 실패하였습니다.")
                .exception("GenerateSecretKeyException")
                .build();
    }
}
