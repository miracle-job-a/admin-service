package com.miracle.adminservice.dto.request;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class AdminSignRequestDto {

    private final String email;
    private final String password;


    public AdminSignRequestDto() {
        this.email = null;
        this.password = null;
    }
}
