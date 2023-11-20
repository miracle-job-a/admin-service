package com.miracle.adminservice.dto.response;

import com.miracle.adminservice.entity.Stack;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode
public class StackResponseDto {

    private final Long id;
    private final String name;

    public StackResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public StackResponseDto(Stack stack) {
        this.id = stack.getId();
        this.name = stack.getName();
    }
}
