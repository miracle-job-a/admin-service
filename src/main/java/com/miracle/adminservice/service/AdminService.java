package com.miracle.adminservice.service;

import com.miracle.adminservice.dto.request.AdminSignRequestDto;
import com.miracle.adminservice.dto.response.CommonApiResponse;

import java.util.Set;

public interface AdminService {

    CommonApiResponse getAlljobs();
    CommonApiResponse matchJobs(Set<Long> idSet);

    CommonApiResponse getAllStacks();
    CommonApiResponse matchStacks(Set<Long> idSet);

    CommonApiResponse signUpAdmin(AdminSignRequestDto adminSignUpReqeustDto);
    CommonApiResponse loginAdmin(AdminSignRequestDto adminLoginRequestDto);
}
