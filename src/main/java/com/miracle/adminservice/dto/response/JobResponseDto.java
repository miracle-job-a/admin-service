package com.miracle.adminservice.dto.response;

import com.miracle.adminservice.entity.Job;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode
public class JobResponseDto {

    private final Long id;
    private final String name;

    public JobResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public JobResponseDto(Job job) {
        this.id = job.getId();
        this.name = job.getName();
    }
}
