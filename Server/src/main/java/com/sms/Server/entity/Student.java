package com.sms.Server.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "students")
public class Student extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank
    private String studentId; // e.g., S1/2025/015

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Past
    private LocalDate dateOfBirth;
    private String nationalId;
    private String religion;
    private String disability;

    // Contact
    private String homeAddress;
    private String district;
    private String county;
    private String parish;

    // Parent
    private String parentName;
    @Enumerated(EnumType.STRING)
    private Relationship parentRelationship;
    private String parentContact;
    private String parentOccupation;
    private String parentAddress;

    // Academic
    private LocalDate admissionDate;
    private String className; // e.g., S1
    private String stream; // e.g., East
    @Enumerated(EnumType.STRING)
    private StudentCategory category;
    private String previousSchool;
    private String house;

    // Attachments
    private String passportPhoto; // File path
    private String birthCertificate; // File path

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
    private List<Parent> parents;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getNationalId() { return nationalId; }
    public void setNationalId(String nationalId) { this.nationalId = nationalId; }

    public String getReligion() { return religion; }
    public void setReligion(String religion) { this.religion = religion; }

    public String getDisability() { return disability; }
    public void setDisability(String disability) { this.disability = disability; }

    public String getHomeAddress() { return homeAddress; }
    public void setHomeAddress(String homeAddress) { this.homeAddress = homeAddress; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getCounty() { return county; }
    public void setCounty(String county) { this.county = county; }

    public String getParish() { return parish; }
    public void setParish(String parish) { this.parish = parish; }

    public String getParentName() { return parentName; }
    public void setParentName(String parentName) { this.parentName = parentName; }

    public Relationship getParentRelationship() { return parentRelationship; }
    public void setParentRelationship(Relationship parentRelationship) { this.parentRelationship = parentRelationship; }

    public String getParentContact() { return parentContact; }
    public void setParentContact(String parentContact) { this.parentContact = parentContact; }

    public String getParentOccupation() { return parentOccupation; }
    public void setParentOccupation(String parentOccupation) { this.parentOccupation = parentOccupation; }

    public String getParentAddress() { return parentAddress; }
    public void setParentAddress(String parentAddress) { this.parentAddress = parentAddress; }

    public LocalDate getAdmissionDate() { return admissionDate; }
    public void setAdmissionDate(LocalDate admissionDate) { this.admissionDate = admissionDate; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    public String getStream() { return stream; }
    public void setStream(String stream) { this.stream = stream; }

    public StudentCategory getCategory() { return category; }
    public void setCategory(StudentCategory category) { this.category = category; }

    public String getPreviousSchool() { return previousSchool; }
    public void setPreviousSchool(String previousSchool) { this.previousSchool = previousSchool; }

    public String getHouse() { return house; }
    public void setHouse(String house) { this.house = house; }

    public String getPassportPhoto() { return passportPhoto; }
    public void setPassportPhoto(String passportPhoto) { this.passportPhoto = passportPhoto; }

    public String getBirthCertificate() { return birthCertificate; }
    public void setBirthCertificate(String birthCertificate) { this.birthCertificate = birthCertificate; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<Parent> getParents() { return parents; }
    public void setParents(List<Parent> parents) { this.parents = parents; }
}
