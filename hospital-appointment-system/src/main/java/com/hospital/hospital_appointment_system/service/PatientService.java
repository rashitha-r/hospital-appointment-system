package com.hospital.hospital_appointment_system.service;

import java.util.List;

import com.hospital.hospital_appointment_system.dto.request.PatientRequestDTO;
import com.hospital.hospital_appointment_system.dto.response.PatientResponseDTO;

public interface PatientService {

    PatientResponseDTO createPatient(PatientRequestDTO requestDTO);

    PatientResponseDTO getPatientById(Long id);

    List<PatientResponseDTO> getAllPatients();

    PatientResponseDTO updatePatient(Long id, PatientRequestDTO requestDTO);

    void deletePatient(Long id);
}