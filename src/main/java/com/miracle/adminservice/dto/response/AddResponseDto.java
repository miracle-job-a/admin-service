package com.miracle.adminservice.dto.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class AddResponseDto {

    private final Long id;
    private final String name;

    public AddResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
