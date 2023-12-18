package com.miracle.adminservice.dto.request;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@ToString
@Getter
public class JobRequestDto {

    @Schema(
            description = "직무 id Set은 not null",
            required = true,
            example = "[1,2,3]"
    )
    @NotNull
    private final Set<Long> id;

    public JobRequestDto(Set<Long> id) {
        this.id = id;
    }

   public JobRequestDto() {
        this.id = null;
    }
}
