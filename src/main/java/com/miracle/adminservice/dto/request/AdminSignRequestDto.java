package com.miracle.adminservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ToString
@Getter
public class AdminSignRequestDto {

    @Schema(
            description = "관리자 이메일 오류. 이메일 형식을 지켜야함",
            required = true,
            example = "youremail@miracle.com"
    )
    @NotBlank(message = "400_1:이메일 값이 없습니다.")
    @Email(message = "400_1:이메일 형식 오류")
    @Size(min = 1, max = 50, message = "400_1:이메일 길이가 너무 짧거나, 깁니다.")
    private final String email;

    @Schema(
            description = "관리자 비밀번호. 반드시 값을 전송해야함",
            required = true,
            example = "austin123!"
    )
    @Pattern(regexp = "^(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{6,}$", message = "400_4:비밀번호 형식이 맞지 않습니다.")
    @NotBlank(message = "400_4:비밀번호 값이 없습니다.")
    @Size(min = 6, message = "400_4:비밀번호가 너무 짧습니다.")
    private final String password;


    public AdminSignRequestDto() {
        this.email = null;
        this.password = null;
    }
}
