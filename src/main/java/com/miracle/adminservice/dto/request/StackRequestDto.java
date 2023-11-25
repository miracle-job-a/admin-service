package com.miracle.adminservice.dto.request;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@ToString
@Getter
public class StackRequestDto {

    @Schema(
            description = "스택 id Set은 not null",
            required = true,
            example = "[1,2,3]"
    )
    @NotNull
    private final Set<Long> id;

    public StackRequestDto(Set<Long> id) {
        this.id = id;
    }

    public StackRequestDto() {
        this.id = null;
    }
}
