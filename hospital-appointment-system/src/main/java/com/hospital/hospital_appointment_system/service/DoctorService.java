package com.hospital.hospital_appointment_system.service;

import java.util.List;

import com.hospital.hospital_appointment_system.dto.request.DoctorRequestDTO;
import com.hospital.hospital_appointment_system.dto.response.DoctorResponseDTO;

public interface DoctorService {

    DoctorResponseDTO createDoctor(DoctorRequestDTO requestDTO);

    DoctorResponseDTO getDoctorById(Long id);

    List<DoctorResponseDTO> getAllDoctors();

    List<DoctorResponseDTO> getDoctorsByDepartment(Long departmentId);

    DoctorResponseDTO updateDoctor(Long id, DoctorRequestDTO requestDTO);

    void deleteDoctor(Long id);
}