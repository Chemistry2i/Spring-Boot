package com.sms.Server.repository;

import com.sms.Server.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParentRepository extends JpaRepository<Parent, Long> {
    Optional<Parent> findByParentId(String parentId);
    List<Parent> findByChild_StudentId(String studentId);
    Optional<Parent> findByEmail(String email);
    List<Parent> findByRole(String role);
}