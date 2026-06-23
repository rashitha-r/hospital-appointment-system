package com.hospital.hospital_appointment_system.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.hospital_appointment_system.dto.request.DepartmentRequestDTO;
import com.hospital.hospital_appointment_system.dto.response.DepartmentResponseDTO;
import com.hospital.hospital_appointment_system.service.DepartmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentResponseDTO> createDepartment(
            @Valid @RequestBody DepartmentRequestDTO requestDTO) {
        logger.info("REST request to create department");
        DepartmentResponseDTO response = departmentService.createDepartment(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> getDepartmentById(
            @PathVariable Long id) {
        logger.info("REST request to get department by id: {}", id);
        DepartmentResponseDTO response = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponseDTO>> getAllDepartments() {
        logger.info("REST request to get all departments");
        List<DepartmentResponseDTO> response = departmentService.getAllDepartments();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentRequestDTO requestDTO) {
        logger.info("REST request to update department with id: {}", id);
        DepartmentResponseDTO response = departmentService.updateDepartment(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        logger.info("REST request to delete department with id: {}", id);
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}