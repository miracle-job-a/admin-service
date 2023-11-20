package com.miracle.adminservice.dto.request;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@ToString
@Getter
public class StackRequestDto {

    @NotNull
    private final Set<Long> id;

    public StackRequestDto(Set<Long> id) {
        this.id = id;
    }

    public StackRequestDto() {
        this.id = null;
    }
}
