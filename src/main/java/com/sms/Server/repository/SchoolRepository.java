package com.sms.Server.repository;

import com.sms.Server.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, Long> {
    Optional<School> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<School> findByRegistrationNumber(String registrationNumber);
    boolean existsByRegistrationNumber(String registrationNumber);

    // Query methods for Ugandan education system
    List<School> findByStatus(School.SchoolStatus status);
    List<School> findByEducationLevel(School.EducationLevel educationLevel);
    List<School> findByOwnershipType(School.OwnershipType ownershipType);
    List<School> findByRegion(School.Region region);
    List<School> findByDistrict(String district);
    List<School> findByCategory(School.SchoolCategory category);
    List<School> findByBoardingType(School.BoardingType boardingType);
    List<School> findByGenderType(School.GenderType genderType);
    List<School> findByCurriculum(School.CurriculumType curriculum);
    List<School> findByAccreditationStatus(School.AccreditationStatus accreditationStatus);

    // Search methods
    List<School> findByNameContainingIgnoreCaseOrRegistrationNumberContainingIgnoreCase(String name, String registrationNumber);
    List<School> findByDistrictContainingIgnoreCaseOrCountyContainingIgnoreCase(String district, String county);
}