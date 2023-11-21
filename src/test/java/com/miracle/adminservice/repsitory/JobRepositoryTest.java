package com.miracle.adminservice.repsitory;

import com.miracle.adminservice.entity.Job;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JobRepositoryTest {

    @Autowired
    private JobRepository jobRepository;

    @Test
    @DisplayName("직무 추가(저장)")
    void save() {
        Job job1 = new Job(1L, "백엔드 개발자");
        Job job2 = new Job(2L, "프론트엔드 개발자");
        Job job3 = new Job(3L, "서버 개발자");

        Job save1 = jobRepository.save(job1);
        Job save2 = jobRepository.save(job2);
        Job save3 = jobRepository.save(job3);

        Assertions.assertThat(save1.getName()).isEqualTo(job1.getName());
        Assertions.assertThat(save2.getName()).isEqualTo(job2.getName());
        Assertions.assertThat(save3.getName()).isEqualTo(job3.getName());
    }

    @Test
    @DisplayName("전체 직무 조회 성공")
    void findAllJobs() {
        Set<Long> givenIdSet = new HashSet<>();
        givenIdSet.add(1L);
        givenIdSet.add(2L);
        givenIdSet.add(3L);

        List<Job> jobList = jobRepository.findAllByIdIn(givenIdSet);

        Assertions.assertThat(jobList.size()).isEqualTo(givenIdSet.size());
    }

    @Test
    @DisplayName("직무 매칭 성공")
    void findAllByIdIn() {
        Job job1 = new Job(1L, "백엔드 개발자");
        Job job2 = new Job(2L, "프론트엔드 개발자");
        Job job3 = new Job(3L, "서버 개발자");

        Set<Long> givenIdSet = new HashSet<>();
        givenIdSet.add(1L);
        givenIdSet.add(2L);
        givenIdSet.add(3L);

        List<Job> jobList = jobRepository.findAllByIdIn(givenIdSet);

        Assertions.assertThat(jobList.get(0).getName()).isEqualTo(job1.getName());
        Assertions.assertThat(jobList.get(1).getName()).isEqualTo(job2.getName());
        Assertions.assertThat(jobList.get(2).getName()).isEqualTo(job3.getName());
    }
}