package com.sms.Server.controller;

import com.sms.Server.dto.ParentDTO;
import com.sms.Server.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parents")
public class ParentController {

    @Autowired
    private ParentService parentService;

    @PostMapping
    public ParentDTO createParent(@Valid @RequestBody ParentDTO parentDTO) {
        return parentService.saveParent(parentDTO);
    }

    @GetMapping
    public Page<ParentDTO> getAllParents(Pageable pageable) {
        return parentService.getAllParents(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParentDTO> getParentById(@PathVariable Long id) {
        Optional<ParentDTO> parent = parentService.getParentById(id);
        return parent.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/child/{studentId}")
    public List<ParentDTO> getParentsByChildId(@PathVariable String studentId) {
        return parentService.getParentsByChildId(studentId);
    }
}
