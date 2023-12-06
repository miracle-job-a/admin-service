package com.miracle.adminservice.controller;

import com.miracle.adminservice.controller.swagger.*;
import com.miracle.adminservice.dto.request.AdminSignRequestDto;
import com.miracle.adminservice.dto.request.JobRequestDto;
import com.miracle.adminservice.dto.request.StackRequestDto;
import com.miracle.adminservice.dto.response.CommonApiResponse;

import com.miracle.adminservice.service.AdminService;
import com.miracle.adminservice.service.AdminServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
@RequestMapping("/v1/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminServiceImpl adminServiceImpl) {
        this.adminService = adminServiceImpl;

    }

    @ApiGetAllJobs
    @ApiDefault
    @GetMapping("/jobs")
    public CommonApiResponse getAllJobs(HttpServletResponse response) {
        CommonApiResponse commonApiResponse = adminService.getAlljobs();
        response.setStatus(commonApiResponse.getHttpStatus());
        return commonApiResponse;
    }


    @ApiMatchJobs
    @ApiDefault
    @PostMapping("/jobs")
    public CommonApiResponse matchJobs(@RequestBody JobRequestDto jobRequestDto, HttpServletResponse response) {
        CommonApiResponse commonApiResponse = adminService.matchJobs(jobRequestDto.getId());
        response.setStatus(commonApiResponse.getHttpStatus());
        return commonApiResponse;
    }

    @ApiGetAllStacks
    @ApiDefault
    @GetMapping("/stacks")
    public CommonApiResponse getAllStacks(HttpServletResponse response) {
        CommonApiResponse commonApiResponse = adminService.getAllStacks();
        response.setStatus(commonApiResponse.getHttpStatus());
        return commonApiResponse;
    }

    @ApiMatchStacks
    @ApiDefault
    @PostMapping("/stacks")
    public CommonApiResponse matchStacks(@RequestBody StackRequestDto stackRequestDto, HttpServletResponse response) {
        CommonApiResponse commonApiResponse = adminService.matchStacks(stackRequestDto.getId());
        response.setStatus(commonApiResponse.getHttpStatus());
        return commonApiResponse;
    }

    @ApiGetJobStacks
    @GetMapping("/jobstacks")
    public CommonApiResponse getAllJobsAndStacks(HttpServletResponse response) {
        CommonApiResponse commonApiResponse = adminService.getAllJobsAndStacks();
        response.setStatus(commonApiResponse.getHttpStatus());
        return commonApiResponse;
    }

    @ApiAdminSignup
    @ApiEncryptor
    @PostMapping("/signup")
    public CommonApiResponse signUpAdmin(@Valid @RequestBody AdminSignRequestDto adminSignUpRequestDto, HttpServletResponse response) {
        CommonApiResponse commonApiResponse = adminService.signUpAdmin(adminSignUpRequestDto);
        response.setStatus(commonApiResponse.getHttpStatus());
        return commonApiResponse;
    }

    @ApiAdminLogin
    @ApiEncryptor
    @PostMapping("/login") //signin
    public CommonApiResponse loginAdmin(@Valid @RequestBody AdminSignRequestDto adminLoginRequestDto, HttpServletResponse response) {
        CommonApiResponse commonApiResponse = adminService.loginAdmin(adminLoginRequestDto);
        response.setStatus(commonApiResponse.getHttpStatus());
        return commonApiResponse;
    }

    @ApiAddStackOrJob
    @ApiDefault
    @GetMapping("/add")
    public CommonApiResponse addStackOrJob(@RequestParam(name = "stackName", required = false) String stackName,
                                           @RequestParam(name = "jobName", required = false) String jobName,
                                           HttpServletResponse response) {
        if (stackName != null) {
            CommonApiResponse commonApiResponse = adminService.addStack(stackName);
            response.setStatus(commonApiResponse.getHttpStatus());
            return commonApiResponse;
        }
        CommonApiResponse commonApiResponse = adminService.addJob(jobName);
        response.setStatus(commonApiResponse.getHttpStatus());
        return commonApiResponse;
    }

    @ApiEditStackOrJob
    @ApiDefault
    @PutMapping("/edit")
    public CommonApiResponse editStackOrJob(@RequestParam(name = "stackId", required = false) Long stackId,
                                            @RequestParam(name = "stackName", required = false) String stackName,
                                            @RequestParam(name = "jobId", required = false) Long jobId,
                                            @RequestParam(name = "jobName", required = false) String jobName,
                                            HttpServletResponse response) {
        if (stackId != null && stackName != null) {
            CommonApiResponse commonApiResponse = adminService.editStack(stackId, stackName);
            response.setStatus(commonApiResponse.getHttpStatus());
            return commonApiResponse;
        }
        CommonApiResponse commonApiResponse = adminService.editJob(jobId, jobName);
        response.setStatus(commonApiResponse.getHttpStatus());
        return commonApiResponse;
    }

    @ApiSearchStackOrJob
    @ApiDefault
    @GetMapping("/search")
    public CommonApiResponse searchStackOrJob(@RequestParam(name = "stackName", required = false) String stackName,
                                              @RequestParam(name = "jobName", required = false) String jobName,
                                              HttpServletResponse response) {
        if (stackName != null) {
            CommonApiResponse commonApiResponse = adminService.searchStack(stackName);
            response.setStatus(commonApiResponse.getHttpStatus());
            return commonApiResponse;
        }
        CommonApiResponse commonApiResponse = adminService.searchJob(jobName);
        response.setStatus(commonApiResponse.getHttpStatus());
        return commonApiResponse;
    }
}
