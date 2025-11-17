package com.sms.Server.repository;

import com.sms.Server.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, Long> {
    Optional<School> findByEmail(String email);
    boolean existsByEmail(String email);
}