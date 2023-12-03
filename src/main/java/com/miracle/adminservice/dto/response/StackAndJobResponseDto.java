package com.miracle.adminservice.dto.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class StackAndJobResponseDto {

    private final Long id;
    private final String name;

    public StackAndJobResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
