package com.sms.Server.controller;

import com.sms.Server.entity.School;
import com.sms.Server.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/schools")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    @PostMapping
    @PreAuthorize("hasRole('SUPERADMIN')")
    public ResponseEntity<?> registerSchool(@RequestBody SchoolRequest request) {
        try {
            School school = schoolService.registerSchool(
                request.getName(),
                request.getAddress(),
                request.getEmail(),
                request.getPhone(),
                request.getWebsite()
            );
            return ResponseEntity.ok(Map.of("message", "School registered successfully", "schoolId", school.getId()));
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
            updatedSchool.setName(request.getName());
            updatedSchool.setAddress(request.getAddress());
            updatedSchool.setEmail(request.getEmail());
            updatedSchool.setPhone(request.getPhone());
            updatedSchool.setWebsite(request.getWebsite());

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

    public static class SchoolRequest {
        private String name;
        private String address;
        private String email;
        private String phone;
        private String website;

        // Getters and setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
        public String getWebsite() { return website; }
        public void setWebsite(String website) { this.website = website; }
    }
}