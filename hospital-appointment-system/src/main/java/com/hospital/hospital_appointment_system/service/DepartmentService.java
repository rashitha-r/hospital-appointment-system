package com.hospital.hospital_appointment_system.service;

import java.util.List;

import com.hospital.hospital_appointment_system.dto.request.DepartmentRequestDTO;
import com.hospital.hospital_appointment_system.dto.response.DepartmentResponseDTO;

public interface DepartmentService {

    DepartmentResponseDTO createDepartment(DepartmentRequestDTO requestDTO);

    DepartmentResponseDTO getDepartmentById(Long id);

    List<DepartmentResponseDTO> getAllDepartments();

    DepartmentResponseDTO updateDepartment(Long id, DepartmentRequestDTO requestDTO);

    void deleteDepartment(Long id);
}