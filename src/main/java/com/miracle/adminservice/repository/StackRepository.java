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
     * @return List<StackAndJobResponseDto>
     * 전제 스택을 반환합니다.
     * @author kade
     */
    @Query("SELECT new com.miracle.adminservice.dto.response.StackAndJobResponseDto(s.id, s.name) FROM Stack s")
    List<StackAndJobResponseDto> findAllStacks();

    /**
     * @param stackIdSet
     * @return List<Stack>
     * stackIdSet을 받고 해당하는 stack을 반환합니다.
     * @author kade
     */
    List<Stack> findAllByIdIn(Set<Long> stackIdSet);

    /**
     * @param stackName
     * @return Boolean
     * stackName으로 존재 여부를 확인합니다.
     * @author kade
     */
    Boolean existsByName(String stackName);

    /**
     * @author kade
     * @param stackName
     * @return List<Stack>
     * 키워드를 받아 스택 이름에서 조회하여 반환합니다.
     */
    List<Stack> findByNameLike(String stackName);

}
