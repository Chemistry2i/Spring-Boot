package com.sms.Server.controller;

import com.sms.Server.dto.StudentDTO;
import com.sms.Server.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public StudentDTO createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        return studentService.saveStudent(studentDTO);
    }

    @GetMapping
    public Page<StudentDTO> getAllStudents(Pageable pageable) {
        return studentService.getAllStudents(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        Optional<StudentDTO> student = studentService.getStudentById(id);
        return student.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Add endpoint to get all students as a list (for simple frontend dropdowns, etc.)
    @GetMapping("/all")
    public List<StudentDTO> getAllStudentsList() {
        return studentService.getAllStudents(Pageable.unpaged()).getContent();
    }
}
