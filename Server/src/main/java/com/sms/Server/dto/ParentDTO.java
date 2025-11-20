package com.sms.Server.dto;

import com.sms.Server.entity.Relationship;
import com.sms.Server.entity.Status;
import com.sms.Server.entity.ParentRole;

import java.time.LocalDateTime;

public class ParentDTO {
    private Long id;
    private String parentId;
    private String name;
    private String contact;
    private String email;
    private String childId; // Keep as string for API, or create StudentDTO
    private Relationship relationship;
    private String accessPin;
    private Status status;
    private ParentRole role;
    private LocalDateTime createdAt;
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

    public String getChildId() { return childId; }
    public void setChildId(String childId) { this.childId = childId; }

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
