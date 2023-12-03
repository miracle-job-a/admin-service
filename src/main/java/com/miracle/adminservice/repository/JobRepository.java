package com.miracle.adminservice.repository;

import com.miracle.adminservice.dto.response.StackAndJobResponseDto;
import com.miracle.adminservice.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    /**
     * @author kade
     * @return List<StackAndJobResponseDto>
     * 전체 직무를 반환합니다.
     */
    @Query("SELECT new com.miracle.adminservice.dto.response.StackAndJobResponseDto(j.id, j.name) FROM Job j")
    List<StackAndJobResponseDto> findAllJobs();

    /**
     * @author kade
     * @param jobIdSet
     * @return List<Job>
     * jobIdSet을 받고 해당하는 직무를 반환합니다.
     */
    List<Job> findAllByIdIn(Set<Long> jobIdSet);

    /**
     * @author kade
     * @param jobName
     * @return Boolean
     * jobName으로 존재 여부를 확인합니다.
     */
    Boolean existsByName(String jobName);
}
