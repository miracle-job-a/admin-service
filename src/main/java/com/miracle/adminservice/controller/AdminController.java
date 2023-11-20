package com.miracle.adminservice.controller;

import com.miracle.adminservice.dto.request.JobRequestDto;
import com.miracle.adminservice.dto.request.StackRequestDto;
import com.miracle.adminservice.dto.response.CommonApiResponse;
import com.miracle.adminservice.dto.response.JobResponseDto;
import com.miracle.adminservice.service.AdminService;
import com.miracle.adminservice.service.AdminServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/v1/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminServiceImpl adminServiceImpl) {
        this.adminService = adminServiceImpl;
    }

    @GetMapping("/jobs")
    public CommonApiResponse getAllJobs() {
        return adminService.getAlljobs();
    }

    @PostMapping("/jobs")
    public CommonApiResponse matchJobs(@RequestBody JobRequestDto jobRequestDto, HttpServletResponse response) {
        CommonApiResponse commonApiResponse = adminService.matchJobs(jobRequestDto.getId());
        response.setStatus(adminService.matchJobs(jobRequestDto.getId()).getHttpStatus());
        return commonApiResponse;
    }


    @GetMapping("/stacks")
    public CommonApiResponse getAllStacks() {
        return adminService.getAllStacks();
    }

    @PostMapping("/stacks")
    public CommonApiResponse matchStacks(@RequestBody StackRequestDto stackRequestDto, HttpServletResponse response) {
        CommonApiResponse commonApiResponse = adminService.matchStacks(stackRequestDto.getId());
        response.setStatus(commonApiResponse.getHttpStatus());
        return commonApiResponse;
    }
}
