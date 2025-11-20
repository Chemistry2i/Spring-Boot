package com.sms.Server.service;

import com.sms.Server.dto.ParentDTO;
import com.sms.Server.entity.Parent;
import com.sms.Server.entity.Student;
import com.sms.Server.repository.ParentRepository;
import com.sms.Server.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParentService {

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public ParentDTO saveParent(ParentDTO parentDTO) {
        Parent parent = mapToEntity(parentDTO);
        if (parent.getId() == null) {
            // Validate child exists
            if (parentDTO.getChildId() != null) {
                Optional<Student> student = studentRepository.findByStudentId(parentDTO.getChildId());
                if (student.isPresent()) {
                    parent.setChild(student.get());
                } else {
                    throw new RuntimeException("Student not found");
                }
            }
            if (parentRepository.findByParentId(parent.getParentId()).isPresent()) {
                throw new RuntimeException("Parent ID already exists");
            }
            if (parentRepository.findByEmail(parent.getEmail()).isPresent()) {
                throw new RuntimeException("Email already exists");
            }
            parent.setParentId(generateParentId());
            parent.setAccessPin(generateAccessPin());
        }
        Parent saved = parentRepository.save(parent);
        return mapToDTO(saved);
    }

    public Page<ParentDTO> getAllParents(Pageable pageable) {
        return parentRepository.findAll(pageable).map(this::mapToDTO);
    }

    public Optional<ParentDTO> getParentById(Long id) {
        return parentRepository.findById(id).map(this::mapToDTO);
    }

    public List<ParentDTO> getParentsByChildId(String childId) {
        return parentRepository.findByChild_StudentId(childId).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<ParentDTO> getParentsByRole(String role) {
        return parentRepository.findByRole(role).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Transactional
    public void deleteParent(Long id) {
        parentRepository.deleteById(id);
    }

    private Parent mapToEntity(ParentDTO dto) {
        Parent parent = new Parent();
        parent.setId(dto.getId());
        parent.setParentId(dto.getParentId());
        parent.setName(dto.getName());
        parent.setContact(dto.getContact());
        parent.setEmail(dto.getEmail());
        // Remove setChildId, use Student relationship
        if (dto.getChildId() != null) {
            Optional<Student> student = studentRepository.findByStudentId(dto.getChildId());
            parent.setChild(student.orElse(null));
        }
        parent.setRelationship(dto.getRelationship());
        parent.setAccessPin(dto.getAccessPin());
        parent.setStatus(dto.getStatus());
        parent.setRole(dto.getRole());
        parent.setCreatedAt(dto.getCreatedAt());
        parent.setUpdatedAt(dto.getUpdatedAt());
        return parent;
    }

    private ParentDTO mapToDTO(Parent parent) {
        ParentDTO dto = new ParentDTO();
        dto.setId(parent.getId());
        dto.setParentId(parent.getParentId());
        dto.setName(parent.getName());
        dto.setContact(parent.getContact());
        dto.setEmail(parent.getEmail());
        // Remove getChildId, use Student relationship
        if (parent.getChild() != null) {
            dto.setChildId(parent.getChild().getStudentId());
        }
        dto.setRelationship(parent.getRelationship());
        dto.setAccessPin(parent.getAccessPin());
        dto.setStatus(parent.getStatus());
        dto.setRole(parent.getRole());
        dto.setCreatedAt(parent.getCreatedAt());
        dto.setUpdatedAt(parent.getUpdatedAt());
        return dto;
    }

    private String generateParentId() {
        int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        long count = parentRepository.count() + 1;
        return String.format("PAR/%d/%02d", year, count);
    }

    private String generateAccessPin() {
        return String.format("%04d", new java.util.Random().nextInt(10000));
    }
}
