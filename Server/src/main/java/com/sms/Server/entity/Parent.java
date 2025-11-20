package com.sms.Server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "parents")
@EntityListeners(AuditingEntityListener.class)
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank
    private String parentId; // e.g., PAR/2025/01

    @NotBlank
    private String name;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$")
    private String contact;

    @Email
    @Column(unique = true)
    @NotBlank
    private String email;

    @ManyToOne
    @JoinColumn(name = "child_id")
    @JsonIgnore
    private Student child;

    @Enumerated(EnumType.STRING)
    private Relationship relationship;

    private String accessPin;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @Enumerated(EnumType.STRING)
    private ParentRole role = ParentRole.PARENT;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getParentId() { return parentId; }
    public void setParentId(String parentId) { this.parentId = parentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Student getChild() { return child; }
    public void setChild(Student child) { this.child = child; }

    public Relationship getRelationship() { return relationship; }
    public void setRelationship(Relationship relationship) { this.relationship = relationship; }

    public String getAccessPin() { return accessPin; }
    public void setAccessPin(String accessPin) { this.accessPin = accessPin; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public ParentRole getRole() { return role; }
    public void setRole(ParentRole role) { this.role = role; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}