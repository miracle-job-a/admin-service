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
}
