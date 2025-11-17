package com.sms.Server.service;

import com.sms.Server.entity.School;
import com.sms.Server.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository schoolRepository;

    public School registerSchool(String name, String address, String email, String phone, String website) {
        if (schoolRepository.existsByEmail(email)) {
            throw new RuntimeException("School with this email already exists");
        }

        School school = new School();
        school.setName(name);
        school.setAddress(address);
        school.setEmail(email);
        school.setPhone(phone);
        school.setWebsite(website);

        return schoolRepository.save(school);
    }

    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    public Optional<School> getSchoolById(Long id) {
        return schoolRepository.findById(id);
    }

    public School updateSchool(Long id, School updatedSchool) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("School not found"));

        school.setName(updatedSchool.getName());
        school.setAddress(updatedSchool.getAddress());
        school.setEmail(updatedSchool.getEmail());
        school.setPhone(updatedSchool.getPhone());
        school.setWebsite(updatedSchool.getWebsite());

        return schoolRepository.save(school);
    }

    public void deleteSchool(Long id) {
        if (!schoolRepository.existsById(id)) {
            throw new RuntimeException("School not found");
        }
        schoolRepository.deleteById(id);
    }
}