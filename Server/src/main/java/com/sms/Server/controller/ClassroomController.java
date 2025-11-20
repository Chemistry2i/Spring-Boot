package com.sms.Server.controller;

import com.sms.Server.entity.Classroom;
import com.sms.Server.entity.Student;
import com.sms.Server.entity.Teacher;
import com.sms.Server.service.ClassroomService;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {
    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping
    public List<Classroom> getAllClassrooms() {
        return classroomService.getAllClassrooms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classroom> getClassroom(@PathVariable Long id) {
        return classroomService.getClassroom(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Classroom> createClassroom(@Valid @RequestBody Classroom classroom) {
        Classroom created = classroomService.saveClassroom(classroom);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Classroom> updateClassroom(@PathVariable Long id, @Valid @RequestBody Classroom classroom) {
        classroom.setId(id);
        Classroom updated = classroomService.saveClassroom(classroom);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassroom(@PathVariable Long id) {
        classroomService.deleteClassroom(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/students")
    public Classroom assignStudents(@PathVariable Long id, @RequestBody Set<Student> students) {
        return classroomService.assignStudents(id, students);
    }

    @PutMapping("/{id}/teachers")
    public Classroom assignTeachers(@PathVariable Long id, @RequestBody Set<Teacher> teachers) {
        return classroomService.assignTeachers(id, teachers);
    }

    @PutMapping("/{id}/schedule")
    public Classroom scheduleClass(@PathVariable Long id, @RequestBody String schedule) {
        return classroomService.scheduleClass(id, schedule);
    }
}
