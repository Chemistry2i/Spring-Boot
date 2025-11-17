package com.sms.Server.repository;

import com.sms.Server.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    // Advanced search and filter methods
    @Query("SELECT s FROM School s WHERE " +
           "(:name IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
           "(:registrationNumber IS NULL OR LOWER(s.registrationNumber) LIKE LOWER(CONCAT('%', :registrationNumber, '%'))) AND " +
           "(:region IS NULL OR s.region = :region) AND " +
           "(:district IS NULL OR LOWER(s.district) LIKE LOWER(CONCAT('%', :district, '%'))) AND " +
           "(:educationLevel IS NULL OR s.educationLevel = :educationLevel) AND " +
           "(:ownershipType IS NULL OR s.ownershipType = :ownershipType) AND " +
           "(:accreditationStatus IS NULL OR s.accreditationStatus = :accreditationStatus) AND " +
           "(:status IS NULL OR s.status = :status)")
    List<School> findSchoolsWithFilters(@Param("name") String name,
                                       @Param("registrationNumber") String registrationNumber,
                                       @Param("region") School.Region region,
                                       @Param("district") String district,
                                       @Param("educationLevel") School.EducationLevel educationLevel,
                                       @Param("ownershipType") School.OwnershipType ownershipType,
                                       @Param("accreditationStatus") School.AccreditationStatus accreditationStatus,
                                       @Param("status") School.SchoolStatus status);

    // Statistics queries
    @Query("SELECT COUNT(s) FROM School s WHERE s.region = :region")
    long countByRegion(@Param("region") School.Region region);

    @Query("SELECT COUNT(s) FROM School s WHERE s.educationLevel = :educationLevel")
    long countByEducationLevel(@Param("educationLevel") School.EducationLevel educationLevel);

    @Query("SELECT COUNT(s) FROM School s WHERE s.ownershipType = :ownershipType")
    long countByOwnershipType(@Param("ownershipType") School.OwnershipType ownershipType);

    @Query("SELECT COUNT(s) FROM School s WHERE s.status = :status")
    long countByStatus(@Param("status") School.SchoolStatus status);

    @Query("SELECT COUNT(s) FROM School s WHERE s.hasElectricity = true")
    long countByHasElectricity();

    @Query("SELECT COUNT(s) FROM School s WHERE s.hasWaterSupply = true")
    long countByHasWaterSupply();

    @Query("SELECT COUNT(s) FROM School s WHERE s.hasLibrary = true")
    long countByHasLibrary();

    @Query("SELECT COUNT(s) FROM School s WHERE s.hasLaboratory = true")
    long countByHasLaboratory();

    @Query("SELECT COUNT(s) FROM School s WHERE s.hasComputerLab = true")
    long countByHasComputerLab();
}