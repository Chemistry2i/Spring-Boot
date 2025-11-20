package com.sms.Server.dto;

import com.sms.Server.entity.Gender;
import com.sms.Server.entity.Relationship;
import com.sms.Server.entity.StudentCategory;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class StudentDTO {
    private Long id;
    private String studentId;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String nationalId;
    private String religion;
    private String disability;
    private String homeAddress;
    private String district;
    private String county;
    private String parish;
    private String parentName;
    private Relationship parentRelationship;
    private String parentContact;
    private String parentOccupation;
    private String parentAddress;
    private LocalDate admissionDate;
    private String className;
    private String stream;
    private StudentCategory category;
    private String previousSchool;
    private String house;
    private String passportPhoto;
    private String birthCertificate;
    private LocalDateTime createdAt;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

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
    public void setPassportPhoto(String passportPhoto) { this.passportPhoto = this.passportPhoto; }

    public String getBirthCertificate() { return birthCertificate; }
    public void setBirthCertificate(String birthCertificate) { this.birthCertificate = birthCertificate; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
