package com.miracle.adminservice.service;

import com.miracle.adminservice.dto.request.AdminSignRequestDto;
import com.miracle.adminservice.dto.response.*;
import com.miracle.adminservice.encryptor.Encryptors;
import com.miracle.adminservice.entity.Admin;
import com.miracle.adminservice.entity.Job;
import com.miracle.adminservice.entity.Stack;
import com.miracle.adminservice.repository.AdminRepository;
import com.miracle.adminservice.repository.JobRepository;
import com.miracle.adminservice.repository.StackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final JobRepository jobRepository;
    private final StackRepository stackRepository;
    private final AdminRepository adminRepository;
    private final Encryptors encryptors;


    public CommonApiResponse getAlljobs() {
        List<StackAndJobResponseDto> jobList = jobRepository.findAllJobs();
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
        List<StackAndJobResponseDto> data = new ArrayList<>();
        jobList.iterator().forEachRemaining((Job j) -> data.add(new StackAndJobResponseDto(j.getId(), j.getName())));
        return SuccessApiResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("직무 매칭 성공")
                .data(data)
                .build();
    }

    public CommonApiResponse getAllStacks() {
        List<StackAndJobResponseDto> stackList = stackRepository.findAllStacks();
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
        List<StackAndJobResponseDto> data = new ArrayList<>();
        stackList.iterator().forEachRemaining((Stack s) -> data.add(new StackAndJobResponseDto(s.getId(), s.getName())));
        return SuccessApiResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("스택 매칭 성공")
                .data(data)
                .build();
    }

    public CommonApiResponse signUpAdmin(AdminSignRequestDto adminSignUpRequestDto) {
        if (adminRepository.existsByEmail(encryptors.encryptAES(adminSignUpRequestDto.getEmail(), encryptors.getSecretKey()))) {
            return SuccessApiResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST.value())
                    .message("사용할 수 없는 Email 입니다.")
                    .data(Boolean.FALSE)
                    .build();
        }
        Admin admin = new Admin(encryptors.encryptAES(adminSignUpRequestDto.getEmail(), encryptors.getSecretKey()), encryptors.SHA3Algorithm(adminSignUpRequestDto.getPassword()));
        adminRepository.save(admin);
        return SuccessApiResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("회원가입 성공")
                .data(Boolean.TRUE)
                .build();
    }

    public CommonApiResponse loginAdmin(AdminSignRequestDto adminLoginRequestDto) {
        Optional<Admin> byEmailAndPassword = adminRepository.findByEmailAndPassword(encryptors.encryptAES(adminLoginRequestDto.getEmail(),
                encryptors.getSecretKey()), encryptors.SHA3Algorithm(adminLoginRequestDto.getPassword()));
        if (byEmailAndPassword.isEmpty()) {
            return SuccessApiResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST.value())
                    .message("이메일 또는 비밀번호가 일치하지 않습니다.")
                    .data(Boolean.FALSE)
                    .build();
        }
        AdminSignResponseDto adminLoginResponseDto = new AdminSignResponseDto(byEmailAndPassword.get().getId(),
                encryptors.decryptAES(byEmailAndPassword.get().getEmail(), encryptors.getSecretKey()));

        return SuccessApiResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("관리자 로그인 성공")
                .data(adminLoginResponseDto)
                .build();
    }

    public CommonApiResponse addStack(String stackName) {
        if (stackRepository.existsByName(stackName)) {
            return SuccessApiResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST.value())
                    .message("이미 등록된 스택 입니다.")
                    .data(Boolean.FALSE)
                    .build();
        }
        Stack stack = new Stack(stackName);
        Stack save = stackRepository.save(stack);
        if (save.getId() == null) {
            return SuccessApiResponse.builder()
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("스택 등록에 실패하였습니다.")
                    .data(Boolean.FALSE)
                    .build();
        }
        return SuccessApiResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("스택 등록에 성공하였습니다.")
                .data(new StackAndJobResponseDto(stack.getId(), stack.getName()))
                .build();
    }

    public CommonApiResponse addJob(String jobName) {
        if (jobRepository.existsByName(jobName)) {
            return SuccessApiResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST.value())
                    .message("이미 등록된 직무 입니다.")
                    .data(Boolean.FALSE)
                    .build();
        }
        Job job = new Job(jobName);
        Job save = jobRepository.save(job);
        if (save.getId() == null) {
            return SuccessApiResponse.builder()
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("직무 등록에 실패하였습니다.")
                    .data(Boolean.FALSE)
                    .build();
        }
        return SuccessApiResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("직무 등록에 성공하였습니다.")
                .data(new StackAndJobResponseDto(job.getId(), job.getName()))
                .build();
    }

    @Override
    public CommonApiResponse editStack(Long id, String name) {
        if (!stackRepository.existsById(id)) {
            return SuccessApiResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST.value())
                    .message("존재하지 않는 스택 id 입니다.")
                    .data(Boolean.FALSE)
                    .build();
        }
        Optional<Stack> optionalStack = stackRepository.findById(id);
        if (optionalStack.isEmpty()) {
            return SuccessApiResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST.value())
                    .message("존재하지 않는 스택 id 입니다.")
                    .data(Boolean.FALSE)
                    .build();
        }
        if (stackRepository.existsByName(name)) {
            return SuccessApiResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST.value())
                    .message("이미 등록된 직무 입니다.")
                    .data(Boolean.FALSE)
                    .build();
        }
        Stack stack = optionalStack.get();
        stack.setName(name);
        Stack save = stackRepository.save(stack);
        if (!save.getName().equals(name)) {
            return SuccessApiResponse.builder()
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("스택 수정에 실패하였습니다.")
                    .data(Boolean.FALSE)
                    .build();
        }
        return SuccessApiResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("스택이 수정되었습니다.")
                .data(new StackAndJobResponseDto(save.getId(), save.getName()))
                .build();
    }

    @Override
    public CommonApiResponse editJob(Long id, String name) {
        if (!jobRepository.existsById(id)) {
            return SuccessApiResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST.value())
                    .message("존재하지 않는 직무 id 입니다.")
                    .data(Boolean.FALSE)
                    .build();
        }
        Optional<Job> optionalJob = jobRepository.findById(id);
        if (optionalJob.isEmpty()) {
            return SuccessApiResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST.value())
                    .message("존재하지 않는 직무 id 입니다.")
                    .data(Boolean.FALSE)
                    .build();
        }
        if (jobRepository.existsByName(name)) {
            return SuccessApiResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST.value())
                    .message("이미 등록된 직무 입니다.")
                    .data(Boolean.FALSE)
                    .build();
        }
        Job job = optionalJob.get();
        job.setName(name);
        Job save = jobRepository.save(job);
        if (!save.getName().equals(name)) {
            return SuccessApiResponse.builder()
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("직무 수정에 실패하였습니다.")
                    .data(Boolean.FALSE)
                    .build();
        }
        return SuccessApiResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("직무가 수정되었습니다.")
                .data(new StackAndJobResponseDto(save.getId(), save.getName()))
                .build();
    }

    @Override
    public CommonApiResponse searchStack(String name) {
        List<Stack> byNameLike = stackRepository.findByNameLike("%"+name+"%");
        List<StackAndJobResponseDto> result = new ArrayList<>();
        byNameLike.iterator().forEachRemaining((Stack s) -> result.add(new StackAndJobResponseDto(s.getId(), s.getName())));
        return SuccessApiResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("스택 검색 결과")
                .data(result)
                .build();
    }

    @Override
    public CommonApiResponse searchJob(String name) {
        List<Job> byNameLike = jobRepository.findByNameLike("%"+name+"%");
        List<StackAndJobResponseDto> result = new ArrayList<>();
        byNameLike.iterator().forEachRemaining((Job j) -> result.add(new StackAndJobResponseDto(j.getId(), j.getName())));
        return SuccessApiResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("직무 검색 결과")
                .data(result)
                .build();
    }
}

