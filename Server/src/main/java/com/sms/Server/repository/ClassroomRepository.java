package com.sms.Server.repository;

import com.sms.Server.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    // ...existing code...
}