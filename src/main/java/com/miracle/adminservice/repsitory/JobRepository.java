package com.miracle.adminservice.repsitory;

import com.miracle.adminservice.dto.response.JobResponseDto;
import com.miracle.adminservice.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    @Query("SELECT new com.miracle.adminservice.dto.response.JobResponseDto(j.id, j.name) FROM Job j")
    List<JobResponseDto> findAllJobs();

    List<Job> findAllByIdIn(Set<Long> jobIdSet);
}
