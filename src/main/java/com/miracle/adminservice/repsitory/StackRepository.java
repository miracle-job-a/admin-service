package com.miracle.adminservice.repsitory;

import com.miracle.adminservice.entity.Stack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StackRepository extends JpaRepository<Stack, Long> {
}
