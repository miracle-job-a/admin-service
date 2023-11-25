package com.miracle.adminservice.repsitory;

import com.miracle.adminservice.dto.response.JobResponseDto;
import com.miracle.adminservice.dto.response.StackResponseDto;
import com.miracle.adminservice.entity.Job;
import com.miracle.adminservice.entity.Stack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface StackRepository extends JpaRepository<Stack, Long> {

    @Query("SELECT new com.miracle.adminservice.dto.response.StackResponseDto(s.id, s.name) FROM Stack s")
    List<StackResponseDto> findAllStacks();
    List<Stack> findAllByIdIn(Set<Long> stackIdSet);
}
