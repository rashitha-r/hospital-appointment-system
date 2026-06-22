package com.hospital.hospital_appointment_system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hospital.hospital_appointment_system.dto.request.PatientRequestDTO;
import com.hospital.hospital_appointment_system.dto.response.PatientResponseDTO;
import com.hospital.hospital_appointment_system.entity.Patient;
import com.hospital.hospital_appointment_system.repository.PatientRepository;
import com.hospital.hospital_appointment_system.service.PatientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private static final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

    private final PatientRepository patientRepository;

    @Override
    public PatientResponseDTO createPatient(PatientRequestDTO requestDTO) {
        logger.info("Creating new patient with email: {}", requestDTO.getEmail());

        Patient patient = new Patient();
        patient.setName(requestDTO.getName());
        patient.setEmail(requestDTO.getEmail());
        patient.setPhone(requestDTO.getPhone());
        patient.setDateOfBirth(requestDTO.getDateOfBirth());
        patient.setGender(requestDTO.getGender());

        Patient savedPatient = patientRepository.save(patient);
        logger.info("Patient created successfully with id: {}", savedPatient.getId());

        return mapToResponseDTO(savedPatient);
    }

    @Override
    public PatientResponseDTO getPatientById(Long id) {
        logger.info("Fetching patient with id: {}", id);

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));

        return mapToResponseDTO(patient);
    }

    @Override
    public List<PatientResponseDTO> getAllPatients() {
        logger.info("Fetching all patients");

        return patientRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PatientResponseDTO updatePatient(Long id, PatientRequestDTO requestDTO) {
        logger.info("Updating patient with id: {}", id);

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));

        patient.setName(requestDTO.getName());
        patient.setEmail(requestDTO.getEmail());
        patient.setPhone(requestDTO.getPhone());
        patient.setDateOfBirth(requestDTO.getDateOfBirth());
        patient.setGender(requestDTO.getGender());

        Patient updatedPatient = patientRepository.save(patient);
        logger.info("Patient updated successfully with id: {}", updatedPatient.getId());

        return mapToResponseDTO(updatedPatient);
    }

    @Override
    public void deletePatient(Long id) {
        logger.info("Deleting patient with id: {}", id);

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));

        patientRepository.delete(patient);
        logger.info("Patient deleted successfully with id: {}", id);
    }

    private PatientResponseDTO mapToResponseDTO(Patient patient) {
        PatientResponseDTO responseDTO = new PatientResponseDTO();
        responseDTO.setId(patient.getId());
        responseDTO.setName(patient.getName());
        responseDTO.setEmail(patient.getEmail());
        responseDTO.setPhone(patient.getPhone());
        responseDTO.setDateOfBirth(patient.getDateOfBirth());
        responseDTO.setGender(patient.getGender());
        return responseDTO;
    }
}