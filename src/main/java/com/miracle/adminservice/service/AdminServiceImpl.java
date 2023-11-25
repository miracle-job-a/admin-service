package com.miracle.adminservice.service;

import com.miracle.adminservice.dto.response.CommonApiResponse;
import com.miracle.adminservice.dto.response.JobResponseDto;
import com.miracle.adminservice.dto.response.StackResponseDto;
import com.miracle.adminservice.dto.response.SuccessApiResponse;
import com.miracle.adminservice.entity.Job;
import com.miracle.adminservice.entity.Stack;
import com.miracle.adminservice.repsitory.JobRepository;
import com.miracle.adminservice.repsitory.StackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {

    private final JobRepository jobRepository;
    private final StackRepository stackRepository;

    @Autowired
    public AdminServiceImpl(JobRepository jobRepository, StackRepository stackRepository) {
        this.jobRepository = jobRepository;
        this.stackRepository = stackRepository;
    }

    public CommonApiResponse getAlljobs() {
        List<JobResponseDto> jobList = jobRepository.findAllJobs();
        return SuccessApiResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("전체 직무 조회 성공")
                .data(jobList)
                .build();
    }

    public CommonApiResponse matchJobs(Set<Long> jobIdSet) {
        if (jobIdSet.isEmpty()) {
            return SuccessApiResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST.value())
                    .message("직무 id Set에 값이 없습니다.")
                    .data(Boolean.FALSE)
                    .build();
        }
        List<Job> jobList = jobRepository.findAllByIdIn(jobIdSet);
        List<JobResponseDto> data = new ArrayList<>();
        jobList.iterator().forEachRemaining( (Job j) -> data.add(new JobResponseDto(j)) );
        return SuccessApiResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("직무 매칭 성공")
                .data(data)
                .build();
    }

    public CommonApiResponse getAllStacks() {
        List<StackResponseDto> stackList = stackRepository.findAllStacks();
        return SuccessApiResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("전체 스택 조회 성공")
                .data(stackList)
                .build();
    }

    public CommonApiResponse matchStacks(Set<Long> stackIdSet) {
        if (stackIdSet.isEmpty()) {
            return SuccessApiResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST.value())
                    .message("스택 id Set에 값이 없습니다.")
                    .data(Boolean.FALSE)
                    .build();
        }
        List<Stack> stackList = stackRepository.findAllByIdIn(stackIdSet);
        List<StackResponseDto> data = new ArrayList<>();
        stackList.iterator().forEachRemaining( (Stack s) -> data.add(new StackResponseDto(s)) );
        return SuccessApiResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("스택 매칭 성공")
                .data(data)
                .build();
    }

    public CommonApiResponse getAllJobsAndStacks() {
        List<JobResponseDto> jobList = jobRepository.findAllJobs();
        List<StackResponseDto> stackList = stackRepository.findAllStacks();
        Map<String, Object> map = new HashMap<>();
        map.put("jobs", jobList);
        map.put("stacks", stackList);
        return SuccessApiResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("전체 직무 및 스택 조회")
                .data(map)
                .build();
    }
}
