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