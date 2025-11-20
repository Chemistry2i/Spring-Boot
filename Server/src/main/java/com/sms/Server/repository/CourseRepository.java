package com.sms.Server.repository;

import com.sms.Server.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    // ...existing code...
}