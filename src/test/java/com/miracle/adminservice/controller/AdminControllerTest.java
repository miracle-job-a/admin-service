package com.miracle.adminservice.controller;

import com.google.gson.Gson;
import com.miracle.adminservice.dto.request.JobRequestDto;
import com.miracle.adminservice.dto.request.StackRequestDto;
import com.miracle.adminservice.dto.response.ErrorApiResponse;
import com.miracle.adminservice.dto.response.JobResponseDto;
import com.miracle.adminservice.dto.response.StackResponseDto;
import com.miracle.adminservice.dto.response.SuccessApiResponse;
import com.miracle.adminservice.service.AdminServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminController.class)
@MockBean(JpaMetamodelMappingContext.class)
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AdminServiceImpl adminService;

    @Test
    @DisplayName("전체 직무 조회 성공")
    void getAllJobs() throws Exception {
        List<JobResponseDto> givenJobDtoList = new ArrayList<>();
        givenJobDtoList.add(new JobResponseDto(1L, "백엔드 개발자"));
        givenJobDtoList.add(new JobResponseDto(2L, "프론트엔드 개발자"));
        givenJobDtoList.add(new JobResponseDto(3L, "서버 개발자"));

        SuccessApiResponse<Object> givenResponse = SuccessApiResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("전체 직무 조회 성공")
                .data(givenJobDtoList)
                .build();

        given(adminService.getAlljobs()).willReturn(givenResponse);

        mockMvc.perform(
                get("/v1/admin/jobs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.httpStatus").value(givenResponse.getHttpStatus()))
                .andExpect(jsonPath("$.message").value(givenResponse.getMessage()))
                .andExpect(jsonPath("$.data.size()").value(givenJobDtoList.size()))
                .andDo(print())
                .andReturn();

        verify(adminService).getAlljobs();
    }

    @Test
    @DisplayName("직무 매칭 성공")
    void matchJobs() throws Exception {

        Set<Long> givenJobIdSet = new HashSet<>();
        givenJobIdSet.add(1L);
        givenJobIdSet.add(2L);
        givenJobIdSet.add(3L);

        JobRequestDto jobRequestDto = new JobRequestDto(givenJobIdSet);

        List<JobResponseDto> givenJobDtoList = new ArrayList<>();
        givenJobDtoList.add(new JobResponseDto(1L, "백엔드 개발자"));
        givenJobDtoList.add(new JobResponseDto(2L, "프론트엔드 개발자"));
        givenJobDtoList.add(new JobResponseDto(3L, "서버 개발자"));

        SuccessApiResponse<Object> givenResponse = SuccessApiResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("직무 매칭 성공")
                .data(givenJobDtoList)
                .build();

        Gson gson = new Gson();
        String json = gson.toJson(jobRequestDto);

        given(adminService.matchJobs(givenJobIdSet)).willReturn(givenResponse);

        mockMvc.perform(
                post("/v1/admin/jobs")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.httpStatus").value(givenResponse.getHttpStatus()))
                .andExpect(jsonPath("$.message").value(givenResponse.getMessage()))
                .andExpect(jsonPath("$.data.size()").value(givenJobDtoList.size()))
                .andDo(print())
                .andReturn();

        verify(adminService).matchJobs(givenJobIdSet);
    }

    @Test
    @DisplayName("직무 매칭 실패 / Set is empty")
    void matchJobsFail() throws Exception {

        Set<Long> givenJobIdSet = new HashSet<>();

        JobRequestDto jobRequestDto = new JobRequestDto(givenJobIdSet);


        SuccessApiResponse<Object> givenResponse = SuccessApiResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .message("직무 id Set에 값이 없습니다.")
                .data(Boolean.FALSE)
                .build();

        Gson gson = new Gson();
        String json = gson.toJson(jobRequestDto);

        given(adminService.matchJobs(givenJobIdSet)).willReturn(givenResponse);

        mockMvc.perform(
                        post("/v1/admin/jobs")
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.httpStatus").value(givenResponse.getHttpStatus()))
                .andExpect(jsonPath("$.message").value(givenResponse.getMessage()))
                .andExpect(jsonPath("$.data").value(Boolean.FALSE))
                .andDo(print())
                .andReturn();

        verify(adminService).matchJobs(givenJobIdSet);
    }

    @Test
    @DisplayName("전체 스택 조회 성공")
    void getAllStacks() throws Exception {
        List<StackResponseDto> givenStackDtoList = new ArrayList<>();
        givenStackDtoList.add(new StackResponseDto(1L, "Java"));
        givenStackDtoList.add(new StackResponseDto(2L, "Python"));
        givenStackDtoList.add(new StackResponseDto(3L, "Html"));

        SuccessApiResponse<Object> givenResponse = SuccessApiResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("전체 스택 조회 성공")
                .data(givenStackDtoList)
                .build();

        given(adminService.getAllStacks()).willReturn(givenResponse);

        mockMvc.perform(
                        get("/v1/admin/stacks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.httpStatus").value(givenResponse.getHttpStatus()))
                .andExpect(jsonPath("$.message").value(givenResponse.getMessage()))
                .andExpect(jsonPath("$.data.size()").value(givenStackDtoList.size()))
                .andDo(print())
                .andReturn();

        verify(adminService).getAllStacks();
    }

    @Test
    @DisplayName("스택 매칭 성공")
    void matchStacks() throws Exception {

        Set<Long> givenStackIdSet = new HashSet<>();
        givenStackIdSet.add(1L);
        givenStackIdSet.add(2L);
        givenStackIdSet.add(3L);

        StackRequestDto stackRequestDto = new StackRequestDto(givenStackIdSet);

        List<StackResponseDto> givenStackDtoList = new ArrayList<>();
        givenStackDtoList.add(new StackResponseDto(1L, "Java"));
        givenStackDtoList.add(new StackResponseDto(2L, "Python"));
        givenStackDtoList.add(new StackResponseDto(3L, "Html"));

        SuccessApiResponse<Object> givenResponse = SuccessApiResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("스택 매칭 성공")
                .data(givenStackDtoList)
                .build();

        Gson gson = new Gson();
        String json = gson.toJson(stackRequestDto);

        given(adminService.matchStacks(givenStackIdSet)).willReturn(givenResponse);

        mockMvc.perform(
                        post("/v1/admin/stacks")
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.httpStatus").value(givenResponse.getHttpStatus()))
                .andExpect(jsonPath("$.message").value(givenResponse.getMessage()))
                .andExpect(jsonPath("$.data.size()").value(givenStackDtoList.size()))
                .andDo(print())
                .andReturn();

        verify(adminService).matchStacks(givenStackIdSet);
    }

    @Test
    @DisplayName("스택 매칭 실패 / Set is empty")
    void matchStacksFail() throws Exception {

        Set<Long> givenStackIdSet = new HashSet<>();
        givenStackIdSet.add(1L);
        givenStackIdSet.add(2L);
        givenStackIdSet.add(3L);

        StackRequestDto stackRequestDto = new StackRequestDto(givenStackIdSet);

        List<StackResponseDto> givenStackDtoList = new ArrayList<>();
        givenStackDtoList.add(new StackResponseDto(1L, "Java"));
        givenStackDtoList.add(new StackResponseDto(2L, "Python"));
        givenStackDtoList.add(new StackResponseDto(3L, "Html"));

        SuccessApiResponse<Object> givenResponse = SuccessApiResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .message("스택 매칭 성공")
                .data(givenStackDtoList)
                .build();

        Gson gson = new Gson();
        String json = gson.toJson(stackRequestDto);

        given(adminService.matchStacks(givenStackIdSet)).willReturn(givenResponse);

        mockMvc.perform(
                        post("/v1/admin/stacks")
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.httpStatus").value(givenResponse.getHttpStatus()))
                .andExpect(jsonPath("$.message").value(givenResponse.getMessage()))
                .andExpect(jsonPath("$.data.size()").value(givenStackDtoList.size()))
                .andDo(print())
                .andReturn();

        verify(adminService).matchStacks(givenStackIdSet);
    }

    @Test
    @DisplayName("토큰 불일치 예외 테스트")
    void unauthorizedTokenFail() throws Exception {

        HttpServletRequest request = mock(HttpServletRequest.class);

        String privateKey = "TkwkdsladkdlrhdnjfrmqdhodlfjgrpaksgdlwnjTdjdy";
        String sessionId = "sessionId";
        String result = sessionId + privateKey;
        int token = 000000000000;

        Set<Long> givenJobIdSet = new HashSet<>();

        JobRequestDto jobRequestDto = new JobRequestDto(givenJobIdSet);

        ErrorApiResponse givenResponse = ErrorApiResponse.builder()
                .httpStatus(HttpStatus.UNAUTHORIZED.value())
                .code("401")
                .message("토큰 값이 일치하지 않습니다.")
                .exception("UnauthorizedTokenException")
                .build();


        given(request.getHeader("sessionId")).willReturn(sessionId);
        given(request.getIntHeader("miracle")).willReturn(token);
        given(adminService.matchJobs(givenJobIdSet)).willReturn(givenResponse);

        Gson gson = new Gson();
        String content = gson.toJson(jobRequestDto);

        mockMvc.perform(
                        post("/v1/admin/jobs")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.httpStatus").value(givenResponse.getHttpStatus()))
                .andExpect(jsonPath("$.code").value(givenResponse.getCode()))
                .andExpect(jsonPath("$.message").value(givenResponse.getMessage()))
                .andExpect(jsonPath("$.exception").value(givenResponse.getException()))
                .andExpect(jsonPath("$.data").doesNotExist())
                .andDo(print())
                .andReturn();
    }
}