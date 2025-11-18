package com.sms.Server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "schools")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "School name is required")
    @Size(min = 2, max = 100, message = "School name must be between 2 and 100 characters")
    @Column(nullable = false)
    private String name;

    // Ugandan Education System Specific Fields
    @NotBlank(message = "School registration number is required")
    @Pattern(regexp = "^[A-Z]{2}\\d{6}$", message = "Invalid school registration number format (e.g., UG123456)")
    @Column(unique = true, nullable = false)
    private String registrationNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EducationLevel educationLevel = EducationLevel.PRIMARY;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OwnershipType ownershipType = OwnershipType.GOVERNMENT;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SchoolCategory category = SchoolCategory.C;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BoardingType boardingType = BoardingType.DAY;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenderType genderType = GenderType.MIXED;

    // Location Information
    @NotBlank(message = "District is required")
    @Column(nullable = false)
    private String district;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Region region = Region.CENTRAL;

    @NotBlank(message = "County/Sub-county is required")
    @Column(nullable = false)
    private String county;

    @NotBlank(message = "Parish is required")
    @Column(nullable = false)
    private String parish;

    @NotBlank(message = "Village is required")
    @Column(nullable = false)
    private String village;

    // Contact Information
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[\\+]?256[\\d]{9}$", message = "Phone number must be in format +256XXXXXXXXX")
    @Column(nullable = false)
    private String phone;

    @Pattern(regexp = "^$|^https?://.*", message = "Website must be a valid URL")
    private String website;

    // Curriculum and Language
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CurriculumType curriculum = CurriculumType.CBC;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LanguageOfInstruction languageOfInstruction = LanguageOfInstruction.ENGLISH;

    // Capacity and Enrollment
    @Min(value = 1, message = "Student capacity must be at least 1")
    @Max(value = 10000, message = "Student capacity cannot exceed 10,000")
    private Integer studentCapacity;

    @Min(value = 0, message = "Current enrollment cannot be negative")
    private Integer currentEnrollment;

    // UNEB Examination Information
    private String examinationCenterNumber;

    // Staff Information
    @Min(value = 0, message = "Number of teachers cannot be negative")
    private Integer totalTeachers;

    @Min(value = 0, message = "Number of qualified teachers cannot be negative")
    private Integer qualifiedTeachers;

    // Facilities
    private Boolean hasLibrary = false;
    private Boolean hasLaboratory = false;
    private Boolean hasComputerLab = false;
    private Boolean hasDormitories = false;
    private Boolean hasPlayground = false;
    private Boolean hasElectricity = false;
    private Boolean hasWaterSupply = false;

    // Administrative Information
    @Size(max = 100, message = "Headteacher name must not exceed 100 characters")
    private String headteacherName;

    @Pattern(regexp = "^$|^[\\+]?256[\\d]{9}$", message = "Headteacher phone must be in format +256XXXXXXXXX")
    private String headteacherPhone;

    @Email(message = "Invalid headteacher email format")
    private String headteacherEmail;

    @Min(value = 1900, message = "Founding year must be after 1900")
    @Max(value = 2025, message = "Founding year cannot be in the future")
    private Integer foundingYear;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccreditationStatus accreditationStatus = AccreditationStatus.RECOGNIZED;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SchoolStatus status = SchoolStatus.ACTIVE;

    // Audit Fields
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private String createdBy;

    private String updatedBy;

    // Getter and Setter methods (added manually due to Lombok compilation issues)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public EducationLevel getEducationLevel() { return educationLevel; }
    public void setEducationLevel(EducationLevel educationLevel) { this.educationLevel = educationLevel; }

    public OwnershipType getOwnershipType() { return ownershipType; }
    public void setOwnershipType(OwnershipType ownershipType) { this.ownershipType = ownershipType; }

    public SchoolCategory getCategory() { return category; }
    public void setCategory(SchoolCategory category) { this.category = category; }

    public BoardingType getBoardingType() { return boardingType; }
    public void setBoardingType(BoardingType boardingType) { this.boardingType = boardingType; }

    public GenderType getGenderType() { return genderType; }
    public void setGenderType(GenderType genderType) { this.genderType = genderType; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public Region getRegion() { return region; }
    public void setRegion(Region region) { this.region = region; }

    public String getCounty() { return county; }
    public void setCounty(String county) { this.county = county; }

    public String getParish() { return parish; }
    public void setParish(String parish) { this.parish = parish; }

    public String getVillage() { return village; }
    public void setVillage(String village) { this.village = village; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }

    public CurriculumType getCurriculum() { return curriculum; }
    public void setCurriculum(CurriculumType curriculum) { this.curriculum = curriculum; }

    public LanguageOfInstruction getLanguageOfInstruction() { return languageOfInstruction; }
    public void setLanguageOfInstruction(LanguageOfInstruction languageOfInstruction) { this.languageOfInstruction = languageOfInstruction; }

    public Integer getStudentCapacity() { return studentCapacity; }
    public void setStudentCapacity(Integer studentCapacity) { this.studentCapacity = studentCapacity; }

    public Integer getCurrentEnrollment() { return currentEnrollment; }
    public void setCurrentEnrollment(Integer currentEnrollment) { this.currentEnrollment = currentEnrollment; }

    public String getExaminationCenterNumber() { return examinationCenterNumber; }
    public void setExaminationCenterNumber(String examinationCenterNumber) { this.examinationCenterNumber = examinationCenterNumber; }

    public Integer getTotalTeachers() { return totalTeachers; }
    public void setTotalTeachers(Integer totalTeachers) { this.totalTeachers = totalTeachers; }

    public Integer getQualifiedTeachers() { return qualifiedTeachers; }
    public void setQualifiedTeachers(Integer qualifiedTeachers) { this.qualifiedTeachers = qualifiedTeachers; }

    public Boolean getHasLibrary() { return hasLibrary; }
    public void setHasLibrary(Boolean hasLibrary) { this.hasLibrary = hasLibrary; }

    public Boolean getHasLaboratory() { return hasLaboratory; }
    public void setHasLaboratory(Boolean hasLaboratory) { this.hasLaboratory = hasLaboratory; }

    public Boolean getHasComputerLab() { return hasComputerLab; }
    public void setHasComputerLab(Boolean hasComputerLab) { this.hasComputerLab = hasComputerLab; }

    public Boolean getHasDormitories() { return hasDormitories; }
    public void setHasDormitories(Boolean hasDormitories) { this.hasDormitories = hasDormitories; }

    public Boolean getHasPlayground() { return hasPlayground; }
    public void setHasPlayground(Boolean hasPlayground) { this.hasPlayground = hasPlayground; }

    public Boolean getHasElectricity() { return hasElectricity; }
    public void setHasElectricity(Boolean hasElectricity) { this.hasElectricity = hasElectricity; }

    public Boolean getHasWaterSupply() { return hasWaterSupply; }
    public void setHasWaterSupply(Boolean hasWaterSupply) { this.hasWaterSupply = hasWaterSupply; }

    public String getHeadteacherName() { return headteacherName; }
    public void setHeadteacherName(String headteacherName) { this.headteacherName = headteacherName; }

    public String getHeadteacherPhone() { return headteacherPhone; }
    public void setHeadteacherPhone(String headteacherPhone) { this.headteacherPhone = headteacherPhone; }

    public String getHeadteacherEmail() { return headteacherEmail; }
    public void setHeadteacherEmail(String headteacherEmail) { this.headteacherEmail = headteacherEmail; }

    public Integer getFoundingYear() { return foundingYear; }
    public void setFoundingYear(Integer foundingYear) { this.foundingYear = foundingYear; }

    public AccreditationStatus getAccreditationStatus() { return accreditationStatus; }
    public void setAccreditationStatus(AccreditationStatus accreditationStatus) { this.accreditationStatus = accreditationStatus; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public SchoolStatus getStatus() { return status; }
    public void setStatus(SchoolStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public String getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(String updatedBy) { this.updatedBy = updatedBy; }

    // Enums for Ugandan Education System
    public enum EducationLevel {
        PRE_PRIMARY, PRIMARY, SECONDARY, VOCATIONAL, TERTIARY
    }

    public enum OwnershipType {
        GOVERNMENT, PRIVATE, COMMUNITY, RELIGIOUS, NGO
    }

    public enum SchoolCategory {
        A, B, C, D
    }

    public enum BoardingType {
        DAY, BOARDING, DAY_AND_BOARDING
    }

    public enum GenderType {
        BOYS_ONLY, GIRLS_ONLY, MIXED
    }

    public enum Region {
        CENTRAL, EASTERN, NORTHERN, WESTERN
    }

    public enum CurriculumType {
        CBC, UCE, UACE, VOCATIONAL, SPECIAL_NEEDS
    }

    public enum LanguageOfInstruction {
        ENGLISH, LUGANDA, RUNYAKITARA, LUSOGA, LUMASABA, OTHER
    }

    public enum AccreditationStatus {
        RECOGNIZED, PROVISIONAL, NOT_RECOGNIZED
    }

    public enum SchoolStatus {
        ACTIVE, INACTIVE, CLOSED, UNDER_REVIEW
    }
}