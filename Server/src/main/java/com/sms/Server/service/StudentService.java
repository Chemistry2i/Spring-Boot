package com.sms.Server.service;

import com.sms.Server.dto.StudentDTO;
import com.sms.Server.entity.Student;
import com.sms.Server.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        Student student = mapToEntity(studentDTO);
        if (student.getId() == null) {
            student.setStudentId(generateStudentId());
            student.setCreatedAt(LocalDateTime.now());
        }
        Student saved = studentRepository.save(student);
        return mapToDTO(saved);
    }

    public Page<StudentDTO> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable).map(this::mapToDTO);
    }

    public Optional<StudentDTO> getStudentById(Long id) {
        return studentRepository.findById(id).map(this::mapToDTO);
    }

    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    private Student mapToEntity(StudentDTO dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setStudentId(dto.getStudentId());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setGender(dto.getGender());
        student.setDateOfBirth(dto.getDateOfBirth());
        student.setNationalId(dto.getNationalId());
        student.setReligion(dto.getReligion());
        student.setDisability(dto.getDisability());
        student.setHomeAddress(dto.getHomeAddress());
        student.setDistrict(dto.getDistrict());
        student.setCounty(dto.getCounty());
        student.setParish(dto.getParish());
        student.setParentName(dto.getParentName());
        student.setParentRelationship(dto.getParentRelationship());
        student.setParentContact(dto.getParentContact());
        student.setParentOccupation(dto.getParentOccupation());
        student.setParentAddress(dto.getParentAddress());
        student.setAdmissionDate(dto.getAdmissionDate());
        student.setClassName(dto.getClassName());
        student.setStream(dto.getStream());
        student.setCategory(dto.getCategory());
        student.setPreviousSchool(dto.getPreviousSchool());
        student.setHouse(dto.getHouse());
        student.setPassportPhoto(dto.getPassportPhoto());
        student.setBirthCertificate(dto.getBirthCertificate());
        student.setCreatedAt(dto.getCreatedAt());
        return student;
    }

    private StudentDTO mapToDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setStudentId(student.getStudentId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setGender(student.getGender());
        dto.setDateOfBirth(student.getDateOfBirth());
        dto.setNationalId(student.getNationalId());
        dto.setReligion(student.getReligion());
        dto.setDisability(student.getDisability());
        dto.setHomeAddress(student.getHomeAddress());
        dto.setDistrict(student.getDistrict());
        dto.setCounty(student.getCounty());
        dto.setParish(student.getParish());
        dto.setParentName(student.getParentName());
        dto.setParentRelationship(student.getParentRelationship());
        dto.setParentContact(student.getParentContact());
        dto.setParentOccupation(student.getParentOccupation());
        dto.setParentAddress(student.getParentAddress());
        dto.setAdmissionDate(student.getAdmissionDate());
        dto.setClassName(student.getClassName());
        dto.setStream(student.getStream());
        dto.setCategory(student.getCategory());
        dto.setPreviousSchool(student.getPreviousSchool());
        dto.setHouse(student.getHouse());
        dto.setPassportPhoto(student.getPassportPhoto());
        dto.setBirthCertificate(student.getBirthCertificate());
        dto.setCreatedAt(student.getCreatedAt());
        return dto;
    }

    private String generateStudentId() {
        int year = java.time.LocalDate.now().getYear();
        long count = studentRepository.count() + 1;
        return String.format("S1/%d/%03d", year, count);
    }
}
