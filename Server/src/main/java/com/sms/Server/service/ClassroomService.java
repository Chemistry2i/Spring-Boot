package com.sms.Server.service;

import com.sms.Server.entity.Classroom;
import com.sms.Server.entity.Student;
import com.sms.Server.entity.Teacher;
import com.sms.Server.repository.ClassroomRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClassroomService {
    private final ClassroomRepository classroomRepository;

    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    public Optional<Classroom> getClassroom(Long id) {
        return classroomRepository.findById(id);
    }

    public Classroom saveClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    public void deleteClassroom(Long id) {
        classroomRepository.deleteById(id);
    }

    public Classroom assignStudents(Long classroomId, Set<Student> students) {
        Classroom classroom = classroomRepository.findById(classroomId).orElseThrow();
        classroom.setStudents(students);
        return classroomRepository.save(classroom);
    }

    public Classroom assignTeachers(Long classroomId, Set<Teacher> teachers) {
        Classroom classroom = classroomRepository.findById(classroomId).orElseThrow();
        classroom.setTeachers(teachers);
        return classroomRepository.save(classroom);
    }

    public Classroom scheduleClass(Long classroomId, String schedule) {
        Classroom classroom = classroomRepository.findById(classroomId).orElseThrow();
        classroom.setSchedule(schedule);
        return classroomRepository.save(classroom);
    }
}
