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

import com.hospital.hospital_appointment_system.dto.request.PatientRequestDTO;
import com.hospital.hospital_appointment_system.dto.response.PatientResponseDTO;
import com.hospital.hospital_appointment_system.service.PatientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(
            @Valid @RequestBody PatientRequestDTO requestDTO) {
        logger.info("REST request to create patient");
        PatientResponseDTO response = patientService.createPatient(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable Long id) {
        logger.info("REST request to get patient by id: {}", id);
        PatientResponseDTO response = patientService.getPatientById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        logger.info("REST request to get all patients");
        List<PatientResponseDTO> response = patientService.getAllPatients();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(
            @PathVariable Long id,
            @Valid @RequestBody PatientRequestDTO requestDTO) {
        logger.info("REST request to update patient with id: {}", id);
        PatientResponseDTO response = patientService.updatePatient(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        logger.info("REST request to delete patient with id: {}", id);
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}