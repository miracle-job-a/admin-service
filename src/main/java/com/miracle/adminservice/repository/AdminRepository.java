package com.miracle.adminservice.repository;

import com.miracle.adminservice.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    /**
     * @author kade
     * @param email
     * @return Boolean
     * email을 기준으로 검색하여 admin 계정 유무를 확인합니다.
     */
    Boolean existsByEmail(String email);

    /**
     * @author kade
     * @param email
     * @param Password
     * @return Optional<Admin>
     * email과 password를 이용하여 Admin 정보를 반환합니다.
     */
    Optional<Admin> findByEmailAndPassword(String email, String Password);
}
