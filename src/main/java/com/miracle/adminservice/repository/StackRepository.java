package com.miracle.adminservice.repository;

import com.miracle.adminservice.dto.response.StackAndJobResponseDto;
import com.miracle.adminservice.entity.Stack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface StackRepository extends JpaRepository<Stack, Long> {

    /**
     * @author kade
     * @return List<StackAndJobResponseDto>
     * 전제 스택을 반환합니다.
     */
    @Query("SELECT new com.miracle.adminservice.dto.response.StackAndJobResponseDto(s.id, s.name) FROM Stack s")
    List<StackAndJobResponseDto> findAllStacks();

    /**
     * @author kade
     * @param stackIdSet
     * @return List<Stack>
     * stackIdSet을 받고 해당하는 stack을 반환합니다.
     */
    List<Stack> findAllByIdIn(Set<Long> stackIdSet);

    /**
     * @author kade
     * @param stackName
     * @return Boolean
     * stackName으로 존재 여부를 확인합니다.
     */
    Boolean existsByName(String stackName);
}
