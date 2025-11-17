package com.sms.Server.controller;

import com.sms.Server.entity.School;
import com.sms.Server.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/schools")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    @PostMapping
    @PreAuthorize("hasRole('SUPERADMIN')")
    public ResponseEntity<?> registerSchool(@RequestBody SchoolRequest request) {
        try {
            School school = new School();
            // Basic Information
            school.setName(request.getName());
            school.setRegistrationNumber(request.getRegistrationNumber());
            school.setEmail(request.getEmail());
            school.setPhone(request.getPhone());
            school.setWebsite(request.getWebsite());

            // Location Information
            school.setRegion(School.Region.valueOf(request.getRegion().toUpperCase()));
            school.setDistrict(request.getDistrict());
            school.setCounty(request.getCounty());
            school.setParish(request.getParish());
            school.setVillage(request.getVillage());

            // Academic Information
            school.setEducationLevel(School.EducationLevel.valueOf(request.getEducationLevel().toUpperCase()));
            school.setCurriculum(School.CurriculumType.valueOf(request.getCurriculumType().toUpperCase()));
            school.setExaminationCenterNumber(request.getExaminationCenterNumber());
            school.setStudentCapacity(request.getStudentCapacity());
            school.setCurrentEnrollment(request.getCurrentEnrollment());
            school.setTotalTeachers(request.getTotalTeachers());
            school.setQualifiedTeachers(request.getQualifiedTeachers());

            // Administration
            school.setOwnershipType(School.OwnershipType.valueOf(request.getOwnershipType().toUpperCase()));
            school.setHeadteacherName(request.getHeadteacherName());
            school.setHeadteacherPhone(request.getHeadteacherPhone());
            school.setHeadteacherEmail(request.getHeadteacherEmail());
            school.setAccreditationStatus(School.AccreditationStatus.valueOf(request.getAccreditationStatus().toUpperCase()));

            // Facilities
            school.setHasElectricity(request.isHasElectricity());
            school.setHasWaterSupply(request.isHasWater());
            school.setHasLibrary(request.isHasLibrary());
            school.setHasLaboratory(request.isHasLaboratory());
            school.setHasComputerLab(request.isHasComputerLab());
            school.setHasPlayground(request.isHasPlayground());
            school.setHasDormitories(request.isHasDormitory());

            // Additional Information
            school.setDescription(request.getDescription());

            School savedSchool = schoolService.createSchool(school);
            return ResponseEntity.ok(Map.of("message", "School registered successfully", "schoolId", savedSchool.getId()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')")
    public ResponseEntity<List<School>> getAllSchools() {
        return ResponseEntity.ok(schoolService.getAllSchools());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')")
    public ResponseEntity<?> getSchoolById(@PathVariable Long id) {
        return schoolService.getSchoolById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

        @PutMapping("/{id}")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public ResponseEntity<?> updateSchool(@PathVariable Long id, @RequestBody SchoolRequest request) {
        try {
            School updatedSchool = new School();
            // Basic Information
            updatedSchool.setName(request.getName());
            updatedSchool.setRegistrationNumber(request.getRegistrationNumber());
            updatedSchool.setEmail(request.getEmail());
            updatedSchool.setPhone(request.getPhone());
            updatedSchool.setWebsite(request.getWebsite());

            // Location Information
            updatedSchool.setRegion(School.Region.valueOf(request.getRegion().toUpperCase()));
            updatedSchool.setDistrict(request.getDistrict());
            updatedSchool.setCounty(request.getCounty());
            updatedSchool.setParish(request.getParish());
            updatedSchool.setVillage(request.getVillage());

            // Academic Information
            updatedSchool.setEducationLevel(School.EducationLevel.valueOf(request.getEducationLevel().toUpperCase()));
            updatedSchool.setCurriculum(School.CurriculumType.valueOf(request.getCurriculumType().toUpperCase()));
            updatedSchool.setExaminationCenterNumber(request.getExaminationCenterNumber());
            updatedSchool.setStudentCapacity(request.getStudentCapacity());
            updatedSchool.setCurrentEnrollment(request.getCurrentEnrollment());
            updatedSchool.setTotalTeachers(request.getTotalTeachers());
            updatedSchool.setQualifiedTeachers(request.getQualifiedTeachers());

            // Administration
            updatedSchool.setOwnershipType(School.OwnershipType.valueOf(request.getOwnershipType().toUpperCase()));
            updatedSchool.setHeadteacherName(request.getHeadteacherName());
            updatedSchool.setHeadteacherPhone(request.getHeadteacherPhone());
            updatedSchool.setHeadteacherEmail(request.getHeadteacherEmail());
            updatedSchool.setAccreditationStatus(School.AccreditationStatus.valueOf(request.getAccreditationStatus().toUpperCase()));

            // Facilities
            updatedSchool.setHasElectricity(request.isHasElectricity());
            updatedSchool.setHasWaterSupply(request.isHasWater());
            updatedSchool.setHasLibrary(request.isHasLibrary());
            updatedSchool.setHasLaboratory(request.isHasLaboratory());
            updatedSchool.setHasComputerLab(request.isHasComputerLab());
            updatedSchool.setHasPlayground(request.isHasPlayground());
            updatedSchool.setHasDormitories(request.isHasDormitory());

            // Additional Information
            updatedSchool.setDescription(request.getDescription());

            School school = schoolService.updateSchool(id, updatedSchool);
            return ResponseEntity.ok(school);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public ResponseEntity<?> deleteSchool(@PathVariable Long id) {
        try {
            schoolService.deleteSchool(id);
            return ResponseEntity.ok(Map.of("message", "School deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Search and Filter Endpoints
    @GetMapping("/search")
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')")
    public ResponseEntity<List<School>> searchSchools(@RequestParam(required = false) String keyword) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            return ResponseEntity.ok(schoolService.searchSchools(keyword.trim()));
        }
        return ResponseEntity.ok(schoolService.getAllSchools());
    }

    @GetMapping("/filter")
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')")
    public ResponseEntity<List<School>> filterSchools(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String registrationNumber,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String district,
            @RequestParam(required = false) String educationLevel,
            @RequestParam(required = false) String ownershipType,
            @RequestParam(required = false) String accreditationStatus,
            @RequestParam(required = false) String status) {

        School.Region regionEnum = region != null ? School.Region.valueOf(region.toUpperCase()) : null;
        School.EducationLevel educationLevelEnum = educationLevel != null ? School.EducationLevel.valueOf(educationLevel.toUpperCase()) : null;
        School.OwnershipType ownershipTypeEnum = ownershipType != null ? School.OwnershipType.valueOf(ownershipType.toUpperCase()) : null;
        School.AccreditationStatus accreditationStatusEnum = accreditationStatus != null ? School.AccreditationStatus.valueOf(accreditationStatus.toUpperCase()) : null;
        School.SchoolStatus statusEnum = status != null ? School.SchoolStatus.valueOf(status.toUpperCase()) : null;

        List<School> schools = schoolService.findSchoolsWithFilters(name, registrationNumber, regionEnum,
                                                                  district, educationLevelEnum, ownershipTypeEnum,
                                                                  accreditationStatusEnum, statusEnum);
        return ResponseEntity.ok(schools);
    }

    @GetMapping("/by-region/{region}")
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')")
    public ResponseEntity<List<School>> getSchoolsByRegion(@PathVariable String region) {
        try {
            School.Region regionEnum = School.Region.valueOf(region.toUpperCase());
            return ResponseEntity.ok(schoolService.getSchoolsByRegion(regionEnum));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(List.of());
        }
    }

    @GetMapping("/by-district/{district}")
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')")
    public ResponseEntity<List<School>> getSchoolsByDistrict(@PathVariable String district) {
        return ResponseEntity.ok(schoolService.getSchoolsByDistrict(district));
    }

    // Validation Endpoints
    @GetMapping("/validate-registration")
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> validateRegistrationNumber(@RequestParam String number) {
        boolean available = schoolService.isRegistrationNumberAvailable(number);
        return ResponseEntity.ok(Map.of("available", available));
    }

    @GetMapping("/validate-email")
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> validateEmail(@RequestParam String email) {
        boolean available = schoolService.isEmailAvailable(email);
        return ResponseEntity.ok(Map.of("available", available));
    }

    @GetMapping("/validate-capacity")
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> validateCapacity(@RequestParam Long schoolId, @RequestParam Integer enrollment) {
        boolean valid = schoolService.isCapacityValid(schoolId, enrollment);
        return ResponseEntity.ok(Map.of("valid", valid));
    }

    // Statistics Endpoints
    @GetMapping("/stats/overview")
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> getSchoolStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalSchools", schoolService.getTotalSchools());
        stats.put("activeSchools", schoolService.getSchoolsByStatus(School.SchoolStatus.ACTIVE));
        stats.put("inactiveSchools", schoolService.getSchoolsByStatus(School.SchoolStatus.INACTIVE));

        // Regional stats
        Map<String, Long> regionalStats = new HashMap<>();
        for (School.Region region : School.Region.values()) {
            regionalStats.put(region.name().toLowerCase(), schoolService.getSchoolsCountByRegion(region));
        }
        stats.put("byRegion", regionalStats);

        // Education level stats
        Map<String, Long> educationStats = new HashMap<>();
        for (School.EducationLevel level : School.EducationLevel.values()) {
            educationStats.put(level.name().toLowerCase(), schoolService.getSchoolsCountByEducationLevel(level));
        }
        stats.put("byEducationLevel", educationStats);

        // Ownership stats
        Map<String, Long> ownershipStats = new HashMap<>();
        for (School.OwnershipType type : School.OwnershipType.values()) {
            ownershipStats.put(type.name().toLowerCase(), schoolService.getSchoolsCountByOwnershipType(type));
        }
        stats.put("byOwnershipType", ownershipStats);

        return ResponseEntity.ok(stats);
    }

    @GetMapping("/stats/facilities")
    @PreAuthorize("hasRole('SUPERADMIN') or hasRole('ADMIN')")
    public ResponseEntity<Map<String, Long>> getFacilitiesStats() {
        Map<String, Long> facilitiesStats = new HashMap<>();
        facilitiesStats.put("withElectricity", schoolService.getSchoolsWithElectricity());
        facilitiesStats.put("withWaterSupply", schoolService.getSchoolsWithWaterSupply());
        facilitiesStats.put("withLibrary", schoolService.getSchoolsWithLibrary());
        facilitiesStats.put("withLaboratory", schoolService.getSchoolsWithLaboratory());
        facilitiesStats.put("withComputerLab", schoolService.getSchoolsWithComputerLab());

        return ResponseEntity.ok(facilitiesStats);
    }

    // Bulk Operations Endpoints
    @PostMapping("/bulk-delete")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public ResponseEntity<?> bulkDeleteSchools(@RequestBody Map<String, List<Long>> request) {
        try {
            List<Long> schoolIds = request.get("schoolIds");
            if (schoolIds == null || schoolIds.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "No school IDs provided"));
            }

            schoolService.deleteSchools(schoolIds);
            return ResponseEntity.ok(Map.of("message", "Successfully deleted " + schoolIds.size() + " schools"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/bulk-update-status")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public ResponseEntity<?> bulkUpdateStatus(@RequestBody Map<String, Object> request) {
        try {
            @SuppressWarnings("unchecked")
            List<Long> schoolIds = (List<Long>) request.get("schoolIds");
            String statusStr = (String) request.get("status");

            if (schoolIds == null || schoolIds.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "No school IDs provided"));
            }

            if (statusStr == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "No status provided"));
            }

            School.SchoolStatus status = School.SchoolStatus.valueOf(statusStr.toUpperCase());
            List<School> updatedSchools = schoolService.updateSchoolsStatus(schoolIds, status);

            return ResponseEntity.ok(Map.of("message", "Successfully updated " + updatedSchools.size() + " schools",
                                          "updatedSchools", updatedSchools));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid status value"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    public static class SchoolRequest {
        // Basic Information
        private String name;
        private String registrationNumber;
        private String email;
        private String phone;
        private String website;

        // Location Information
        private String region;
        private String district;
        private String county;
        private String parish;
        private String village;

        // Academic Information
        private String educationLevel;
        private String curriculumType;
        private String examinationCenterNumber;
        private Integer studentCapacity;
        private Integer currentEnrollment;
        private Integer totalTeachers;
        private Integer qualifiedTeachers;

        // Administration
        private String ownershipType;
        private String headteacherName;
        private String headteacherPhone;
        private String headteacherEmail;
        private String accreditationStatus;

        // Facilities
        private boolean hasElectricity;
        private boolean hasWater;
        private boolean hasLibrary;
        private boolean hasLaboratory;
        private boolean hasComputerLab;
        private boolean hasPlayground;
        private boolean hasDormitory;

        // Additional Information
        private String description;

        // Getters and setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getRegistrationNumber() { return registrationNumber; }
        public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }

        public String getWebsite() { return website; }
        public void setWebsite(String website) { this.website = website; }

        public String getRegion() { return region; }
        public void setRegion(String region) { this.region = region; }

        public String getDistrict() { return district; }
        public void setDistrict(String district) { this.district = district; }

        public String getCounty() { return county; }
        public void setCounty(String county) { this.county = county; }

        public String getParish() { return parish; }
        public void setParish(String parish) { this.parish = parish; }

        public String getVillage() { return village; }
        public void setVillage(String village) { this.village = village; }

        public String getEducationLevel() { return educationLevel; }
        public void setEducationLevel(String educationLevel) { this.educationLevel = educationLevel; }

        public String getCurriculumType() { return curriculumType; }
        public void setCurriculumType(String curriculumType) { this.curriculumType = curriculumType; }

        public String getExaminationCenterNumber() { return examinationCenterNumber; }
        public void setExaminationCenterNumber(String examinationCenterNumber) { this.examinationCenterNumber = examinationCenterNumber; }

        public Integer getStudentCapacity() { return studentCapacity; }
        public void setStudentCapacity(Integer studentCapacity) { this.studentCapacity = studentCapacity; }

        public Integer getCurrentEnrollment() { return currentEnrollment; }
        public void setCurrentEnrollment(Integer currentEnrollment) { this.currentEnrollment = currentEnrollment; }

        public Integer getTotalTeachers() { return totalTeachers; }
        public void setTotalTeachers(Integer totalTeachers) { this.totalTeachers = totalTeachers; }

        public Integer getQualifiedTeachers() { return qualifiedTeachers; }
        public void setQualifiedTeachers(Integer qualifiedTeachers) { this.qualifiedTeachers = qualifiedTeachers; }

        public String getOwnershipType() { return ownershipType; }
        public void setOwnershipType(String ownershipType) { this.ownershipType = ownershipType; }

        public String getHeadteacherName() { return headteacherName; }
        public void setHeadteacherName(String headteacherName) { this.headteacherName = headteacherName; }

        public String getHeadteacherPhone() { return headteacherPhone; }
        public void setHeadteacherPhone(String headteacherPhone) { this.headteacherPhone = headteacherPhone; }

        public String getHeadteacherEmail() { return headteacherEmail; }
        public void setHeadteacherEmail(String headteacherEmail) { this.headteacherEmail = headteacherEmail; }

        public String getAccreditationStatus() { return accreditationStatus; }
        public void setAccreditationStatus(String accreditationStatus) { this.accreditationStatus = accreditationStatus; }

        public boolean isHasElectricity() { return hasElectricity; }
        public void setHasElectricity(boolean hasElectricity) { this.hasElectricity = hasElectricity; }

        public boolean isHasWater() { return hasWater; }
        public void setHasWater(boolean hasWater) { this.hasWater = hasWater; }

        public boolean isHasLibrary() { return hasLibrary; }
        public void setHasLibrary(boolean hasLibrary) { this.hasLibrary = hasLibrary; }

        public boolean isHasLaboratory() { return hasLaboratory; }
        public void setHasLaboratory(boolean hasLaboratory) { this.hasLaboratory = hasLaboratory; }

        public boolean isHasComputerLab() { return hasComputerLab; }
        public void setHasComputerLab(boolean hasComputerLab) { this.hasComputerLab = hasComputerLab; }

        public boolean isHasPlayground() { return hasPlayground; }
        public void setHasPlayground(boolean hasPlayground) { this.hasPlayground = hasPlayground; }

        public boolean isHasDormitory() { return hasDormitory; }
        public void setHasDormitory(boolean hasDormitory) { this.hasDormitory = hasDormitory; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }
}