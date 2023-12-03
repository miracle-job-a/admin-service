package com.miracle.adminservice.repository;

import com.miracle.adminservice.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Boolean existsByEmail(String email);
    Optional<Admin> findByEmailAndPassword(String email, String Password);
}
