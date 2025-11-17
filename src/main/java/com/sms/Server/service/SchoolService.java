package com.sms.Server.service;

import com.sms.Server.entity.School;
import com.sms.Server.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository schoolRepository;

    public School createSchool(School school) {
        // Validate registration number uniqueness
        if (schoolRepository.existsByRegistrationNumber(school.getRegistrationNumber())) {
            throw new RuntimeException("School with this registration number already exists");
        }

        // Validate email uniqueness
        if (schoolRepository.existsByEmail(school.getEmail())) {
            throw new RuntimeException("School with this email already exists");
        }

        // Set audit information
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth != null ? auth.getName() : "system";
        school.setCreatedBy(currentUser);
        school.setUpdatedBy(currentUser);

        // Validate enrollment doesn't exceed capacity
        if (school.getCurrentEnrollment() != null && school.getStudentCapacity() != null) {
            if (school.getCurrentEnrollment() > school.getStudentCapacity()) {
                throw new RuntimeException("Current enrollment cannot exceed student capacity");
            }
        }

        // Validate qualified teachers don't exceed total teachers
        if (school.getQualifiedTeachers() != null && school.getTotalTeachers() != null) {
            if (school.getQualifiedTeachers() > school.getTotalTeachers()) {
                throw new RuntimeException("Qualified teachers cannot exceed total teachers");
            }
        }

        return schoolRepository.save(school);
    }

    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    public Optional<School> getSchoolById(Long id) {
        return schoolRepository.findById(id);
    }

    public Optional<School> getSchoolByRegistrationNumber(String registrationNumber) {
        return schoolRepository.findByRegistrationNumber(registrationNumber);
    }

    public School updateSchool(Long id, School updatedSchool) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("School not found"));

        // Check if registration number is being changed and if it's unique
        if (!school.getRegistrationNumber().equals(updatedSchool.getRegistrationNumber())) {
            if (schoolRepository.existsByRegistrationNumber(updatedSchool.getRegistrationNumber())) {
                throw new RuntimeException("School with this registration number already exists");
            }
        }

        // Check if email is being changed and if it's unique
        if (!school.getEmail().equals(updatedSchool.getEmail())) {
            if (schoolRepository.existsByEmail(updatedSchool.getEmail())) {
                throw new RuntimeException("School with this email already exists");
            }
        }

        // Update all fields
        school.setName(updatedSchool.getName());
        school.setRegistrationNumber(updatedSchool.getRegistrationNumber());
        school.setEducationLevel(updatedSchool.getEducationLevel());
        school.setOwnershipType(updatedSchool.getOwnershipType());
        school.setCategory(updatedSchool.getCategory());
        school.setBoardingType(updatedSchool.getBoardingType());
        school.setGenderType(updatedSchool.getGenderType());
        school.setDistrict(updatedSchool.getDistrict());
        school.setRegion(updatedSchool.getRegion());
        school.setCounty(updatedSchool.getCounty());
        school.setParish(updatedSchool.getParish());
        school.setVillage(updatedSchool.getVillage());
        school.setEmail(updatedSchool.getEmail());
        school.setPhone(updatedSchool.getPhone());
        school.setWebsite(updatedSchool.getWebsite());
        school.setCurriculum(updatedSchool.getCurriculum());
        school.setLanguageOfInstruction(updatedSchool.getLanguageOfInstruction());
        school.setStudentCapacity(updatedSchool.getStudentCapacity());
        school.setCurrentEnrollment(updatedSchool.getCurrentEnrollment());
        school.setExaminationCenterNumber(updatedSchool.getExaminationCenterNumber());
        school.setTotalTeachers(updatedSchool.getTotalTeachers());
        school.setQualifiedTeachers(updatedSchool.getQualifiedTeachers());
        school.setHasLibrary(updatedSchool.getHasLibrary());
        school.setHasLaboratory(updatedSchool.getHasLaboratory());
        school.setHasComputerLab(updatedSchool.getHasComputerLab());
        school.setHasDormitories(updatedSchool.getHasDormitories());
        school.setHasPlayground(updatedSchool.getHasPlayground());
        school.setHasElectricity(updatedSchool.getHasElectricity());
        school.setHasWaterSupply(updatedSchool.getHasWaterSupply());
        school.setHeadteacherName(updatedSchool.getHeadteacherName());
        school.setHeadteacherPhone(updatedSchool.getHeadteacherPhone());
        school.setHeadteacherEmail(updatedSchool.getHeadteacherEmail());
        school.setFoundingYear(updatedSchool.getFoundingYear());
        school.setAccreditationStatus(updatedSchool.getAccreditationStatus());
        school.setDescription(updatedSchool.getDescription());
        school.setStatus(updatedSchool.getStatus());

        // Set audit information
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth != null ? auth.getName() : "system";
        school.setUpdatedBy(currentUser);

        // Validate enrollment doesn't exceed capacity
        if (school.getCurrentEnrollment() != null && school.getStudentCapacity() != null) {
            if (school.getCurrentEnrollment() > school.getStudentCapacity()) {
                throw new RuntimeException("Current enrollment cannot exceed student capacity");
            }
        }

        // Validate qualified teachers don't exceed total teachers
        if (school.getQualifiedTeachers() != null && school.getTotalTeachers() != null) {
            if (school.getQualifiedTeachers() > school.getTotalTeachers()) {
                throw new RuntimeException("Qualified teachers cannot exceed total teachers");
            }
        }

        return schoolRepository.save(school);
    }

    public void deleteSchool(Long id) {
        if (!schoolRepository.existsById(id)) {
            throw new RuntimeException("School not found");
        }
        schoolRepository.deleteById(id);
    }

    // Query methods for Ugandan education system
    public List<School> getSchoolsByStatus(School.SchoolStatus status) {
        return schoolRepository.findByStatus(status);
    }

    public List<School> getSchoolsByEducationLevel(School.EducationLevel educationLevel) {
        return schoolRepository.findByEducationLevel(educationLevel);
    }

    public List<School> getSchoolsByOwnershipType(School.OwnershipType ownershipType) {
        return schoolRepository.findByOwnershipType(ownershipType);
    }

    public List<School> getSchoolsByRegion(School.Region region) {
        return schoolRepository.findByRegion(region);
    }

    public List<School> getSchoolsByDistrict(String district) {
        return schoolRepository.findByDistrict(district);
    }

    public List<School> getSchoolsByCategory(School.SchoolCategory category) {
        return schoolRepository.findByCategory(category);
    }

    public List<School> searchSchools(String keyword) {
        return schoolRepository.findByNameContainingIgnoreCaseOrRegistrationNumberContainingIgnoreCase(keyword, keyword);
    }

    public List<School> searchSchoolsByLocation(String location) {
        return schoolRepository.findByDistrictContainingIgnoreCaseOrCountyContainingIgnoreCase(location, location);
    }

    // Advanced search and filter methods
    public List<School> findSchoolsWithFilters(String name, String registrationNumber, School.Region region,
                                             String district, School.EducationLevel educationLevel,
                                             School.OwnershipType ownershipType, School.AccreditationStatus accreditationStatus,
                                             School.SchoolStatus status) {
        return schoolRepository.findSchoolsWithFilters(name, registrationNumber, region, district,
                                                     educationLevel, ownershipType, accreditationStatus, status);
    }

    // Validation methods
    public boolean isRegistrationNumberAvailable(String registrationNumber) {
        return !schoolRepository.existsByRegistrationNumber(registrationNumber);
    }

    public boolean isEmailAvailable(String email) {
        return !schoolRepository.existsByEmail(email);
    }

    public boolean isCapacityValid(Long schoolId, Integer newEnrollment) {
        Optional<School> school = schoolRepository.findById(schoolId);
        if (school.isPresent() && school.get().getStudentCapacity() != null) {
            return newEnrollment <= school.get().getStudentCapacity();
        }
        return true; // If no capacity set, allow any enrollment
    }

    // Statistics methods
    public long getTotalSchools() {
        return schoolRepository.count();
    }

    public long getSchoolsCountByRegion(School.Region region) {
        return schoolRepository.countByRegion(region);
    }

    public long getSchoolsCountByEducationLevel(School.EducationLevel educationLevel) {
        return schoolRepository.countByEducationLevel(educationLevel);
    }

    public long getSchoolsCountByOwnershipType(School.OwnershipType ownershipType) {
        return schoolRepository.countByOwnershipType(ownershipType);
    }

    public long getSchoolsCountByStatus(School.SchoolStatus status) {
        return schoolRepository.countByStatus(status);
    }

    public long getSchoolsWithElectricity() {
        return schoolRepository.countByHasElectricity();
    }

    public long getSchoolsWithWaterSupply() {
        return schoolRepository.countByHasWaterSupply();
    }

    public long getSchoolsWithLibrary() {
        return schoolRepository.countByHasLibrary();
    }

    public long getSchoolsWithLaboratory() {
        return schoolRepository.countByHasLaboratory();
    }

    public long getSchoolsWithComputerLab() {
        return schoolRepository.countByHasComputerLab();
    }

    // Bulk operations
    public void deleteSchools(List<Long> schoolIds) {
        for (Long id : schoolIds) {
            if (schoolRepository.existsById(id)) {
                schoolRepository.deleteById(id);
            }
        }
    }

    public List<School> updateSchoolsStatus(List<Long> schoolIds, School.SchoolStatus status) {
        List<School> updatedSchools = new ArrayList<>();
        for (Long id : schoolIds) {
            Optional<School> schoolOpt = schoolRepository.findById(id);
            if (schoolOpt.isPresent()) {
                School school = schoolOpt.get();
                school.setStatus(status);

                // Set audit information
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                String currentUser = auth != null ? auth.getName() : "system";
                school.setUpdatedBy(currentUser);

                updatedSchools.add(schoolRepository.save(school));
            }
        }
        return updatedSchools;
    }
}