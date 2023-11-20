package com.miracle.adminservice.dto.request;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@ToString
@Getter
public class JobRequestDto {

    @NotNull
    private final Set<Long> id;

    public JobRequestDto(Set<Long> id) {
        this.id = id;
    }

   public JobRequestDto() {
        this.id = null;
    }
}
