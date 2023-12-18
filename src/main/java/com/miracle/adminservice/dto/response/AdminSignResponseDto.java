package com.miracle.adminservice.dto.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class AdminSignResponseDto {

    private final Long id;
    private final String email;

    public AdminSignResponseDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
