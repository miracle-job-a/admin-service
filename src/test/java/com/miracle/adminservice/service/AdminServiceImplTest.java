package com.miracle.adminservice.service;

import com.miracle.adminservice.dto.response.SuccessApiResponse;
import com.miracle.adminservice.entity.Stack;
import com.miracle.adminservice.repository.JobRepository;
import com.miracle.adminservice.repository.StackRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
class AdminServiceImplTest {

    private StackRepository stackRepository = Mockito.mock(StackRepository.class);
    private JobRepository jobRepository = Mockito.mock(JobRepository.class);
    private AdminServiceImpl adminService;

/*
    @BeforeEach
    void setUpTest() {
        adminService = new AdminServiceImpl(jobRepository, stackRepository);
    }
*/

   /* @Test
    @DisplayName("전체 직무 조회 성공")
    void getAlljobs() {
        //given
        List<JobResponseDto> givenResult = new ArrayList<>();
        givenResult.add(new JobResponseDto(1L, "백엔드 개발"));
        givenResult.add(new JobResponseDto(2L, "프론트엔드 개발"));
        givenResult.add(new JobResponseDto(3L, "서버 개발"));

        SuccessApiResponse<Object> givenResponse = SuccessApiResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("전체 직무 조회 성공")
                .data(givenResult)
                .build();

        Mockito.when(jobRepository.findAllJobs()).thenReturn(givenResult);

        SuccessApiResponse result = (SuccessApiResponse) adminService.getAlljobs();

        Assertions.assertThat(result.getHttpStatus()).isEqualTo(givenResponse.getHttpStatus());
        Assertions.assertThat(result.getMessage()).isEqualTo(givenResponse.getMessage());
        Assertions.assertThat(result.getData()).isEqualTo(givenResponse.getData());
    }*/

   /* @Test
    @DisplayName("직무 매칭 성공")
    void matchJobs() {
        Set<Long> givenJobIdSet = new HashSet<>();
        givenJobIdSet.add(1L);
        givenJobIdSet.add(2L);
        givenJobIdSet.add(3L);

        List<Job> givenJobList = new ArrayList<>();
        givenJobList.add(new Job(1L, "백엔드 개발자"));
        givenJobList.add(new Job(2L, "프론트엔드 개발자"));
        givenJobList.add(new Job(3L, "서버 개발자"));

        List<JobResponseDto> givenJobDtoList = new ArrayList<>();
        givenJobDtoList.add(new JobResponseDto(1L, "백엔드 개발자"));
        givenJobDtoList.add(new JobResponseDto(2L, "프론트엔드 개발자"));
        givenJobDtoList.add(new JobResponseDto(3L, "서버 개발자"));

        SuccessApiResponse<Object> givenResponse = SuccessApiResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("직무 매칭 성공")
                .data(givenJobDtoList)
                .build();

        Mockito.when(jobRepository.findAllByIdIn(givenJobIdSet)).thenReturn(givenJobList);

        SuccessApiResponse resultResponse = (SuccessApiResponse) adminService.matchJobs(givenJobIdSet);

        Assertions.assertThat(resultResponse.getHttpStatus()).isEqualTo(givenResponse.getHttpStatus());
        Assertions.assertThat(resultResponse.getMessage()).isEqualTo(givenResponse.getMessage());
        Assertions.assertThat(resultResponse.getData()).isEqualTo(givenResponse.getData());

        verify(jobRepository).findAllByIdIn(givenJobIdSet);
    }*/

  /*  @Test
    @DisplayName("직무 매칭 실패 / Set is empty")
    void matchJobsFail() {
        Set<Long> givenJobIdSet = new HashSet<>();

        List<Job> givenJobList = new ArrayList<>();
        givenJobList.add(new Job(1L, "백엔드 개발자"));
        givenJobList.add(new Job(2L, "프론트엔드 개발자"));
        givenJobList.add(new Job(3L, "서버 개발자"));

        List<JobResponseDto> givenJobDtoList = new ArrayList<>();
        givenJobDtoList.add(new JobResponseDto(1L, "백엔드 개발자"));
        givenJobDtoList.add(new JobResponseDto(2L, "프론트엔드 개발자"));
        givenJobDtoList.add(new JobResponseDto(3L, "서버 개발자"));

        SuccessApiResponse<Object> givenResponse = SuccessApiResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .message("직무 id Set에 값이 없습니다.")
                .data(Boolean.FALSE)
                .build();

        Mockito.when(jobRepository.findAllByIdIn(givenJobIdSet)).thenReturn(null);

        SuccessApiResponse resultResponse = (SuccessApiResponse) adminService.matchJobs(givenJobIdSet);

        Assertions.assertThat(resultResponse.getHttpStatus()).isEqualTo(givenResponse.getHttpStatus());
        Assertions.assertThat(resultResponse.getMessage()).isEqualTo(givenResponse.getMessage());
        Assertions.assertThat(resultResponse.getData()).isEqualTo(givenResponse.getData());
    }
*/
 /*   @Test
    @DisplayName("전체 스택 조회 성공")
    void getAllStacks() {
        List<StackResponseDto> givenResult = new ArrayList<>();
        givenResult.add(new StackResponseDto(1L,"Java"));
        givenResult.add(new StackResponseDto(2L,"Python"));
        givenResult.add(new StackResponseDto(3L,"Html"));

        SuccessApiResponse<Object> givenResponse = SuccessApiResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("전체 스택 조회 성공")
                .data(givenResult)
                .build();

        Mockito.when(stackRepository.findAllStacks()).thenReturn(givenResult);

        SuccessApiResponse result = (SuccessApiResponse) adminService.getAllStacks();

        Assertions.assertThat(result.getHttpStatus()).isEqualTo(givenResponse.getHttpStatus());
        Assertions.assertThat(result.getMessage()).isEqualTo(givenResponse.getMessage());
        Assertions.assertThat(result.getData()).isEqualTo(givenResponse.getData());
    }*/

   /* @Test
    @DisplayName("스택 매칭 성공")
    void matchStacks() {
        Set<Long> givenStackIdSet = new HashSet<>();
        givenStackIdSet.add(1L);
        givenStackIdSet.add(2L);
        givenStackIdSet.add(3L);

        List<Stack> givenStackList = new ArrayList<>();
        givenStackList.add(new Stack(1L, "Java"));
        givenStackList.add(new Stack(2L, "Python"));
        givenStackList.add(new Stack(3L, "Html"));

        List<StackResponseDto> givenStackDtoList = new ArrayList<>();
        givenStackDtoList.add(new StackResponseDto(1L, "Java"));
        givenStackDtoList.add(new StackResponseDto(2L, "Python"));
        givenStackDtoList.add(new StackResponseDto(3L, "Html"));

        SuccessApiResponse<Object> givenResponse = SuccessApiResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("스택 매칭 성공")
                .data(givenStackDtoList)
                .build();

        Mockito.when(stackRepository.findAllByIdIn(givenStackIdSet)).thenReturn(givenStackList);

        SuccessApiResponse resultResponse = (SuccessApiResponse) adminService.matchStacks(givenStackIdSet);

        Assertions.assertThat(resultResponse.getHttpStatus()).isEqualTo(givenResponse.getHttpStatus());
        Assertions.assertThat(resultResponse.getMessage()).isEqualTo(givenResponse.getMessage());
        Assertions.assertThat(resultResponse.getData()).isEqualTo(givenResponse.getData());

        verify(stackRepository).findAllByIdIn(givenStackIdSet);
    }*/
/*

    @Test
    @DisplayName("스택 매칭 실패 / Set is empty")
    void matchStacksFail() {
        Set<Long> givenStackIdSet = new HashSet<>();

        SuccessApiResponse<Object> givenResponse = SuccessApiResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .message("스택 id Set에 값이 없습니다.")
                .data(Boolean.FALSE)
                .build();

        Mockito.when(stackRepository.findAllByIdIn(givenStackIdSet)).thenReturn(null);

        SuccessApiResponse resultResponse = (SuccessApiResponse) adminService.matchStacks(givenStackIdSet);

        Assertions.assertThat(resultResponse.getHttpStatus()).isEqualTo(givenResponse.getHttpStatus());
        Assertions.assertThat(resultResponse.getMessage()).isEqualTo(givenResponse.getMessage());
        Assertions.assertThat(resultResponse.getData()).isEqualTo(givenResponse.getData());
    }
*/

}

