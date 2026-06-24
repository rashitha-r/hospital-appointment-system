package com.hospital.hospital_appointment_system.controller;

import com.hospital.hospital_appointment_system.dto.request.DoctorRequestDTO;
import com.hospital.hospital_appointment_system.dto.response.DoctorResponseDTO;
import com.hospital.hospital_appointment_system.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private static final Logger logger = LoggerFactory.getLogger(DoctorController.class);

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorResponseDTO> createDoctor(
            @Valid @RequestBody DoctorRequestDTO requestDTO) {
        logger.info("REST request to create doctor");
        DoctorResponseDTO response = doctorService.createDoctor(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> getDoctorById(
            @PathVariable Long id) {
        logger.info("REST request to get doctor by id: {}", id);
        DoctorResponseDTO response = doctorService.getDoctorById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> getAllDoctors() {
        logger.info("REST request to get all doctors");
        List<DoctorResponseDTO> response = doctorService.getAllDoctors();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<DoctorResponseDTO>> getDoctorsByDepartment(
            @PathVariable Long departmentId) {
        logger.info("REST request to get doctors by department id: {}", departmentId);
        List<DoctorResponseDTO> response = doctorService.getDoctorsByDepartment(departmentId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> updateDoctor(
            @PathVariable Long id,
            @Valid @RequestBody DoctorRequestDTO requestDTO) {
        logger.info("REST request to update doctor with id: {}", id);
        DoctorResponseDTO response = doctorService.updateDoctor(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        logger.info("REST request to delete doctor with id: {}", id);
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}