package com.hospital.hospital_appointment_system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hospital.hospital_appointment_system.dto.request.DepartmentRequestDTO;
import com.hospital.hospital_appointment_system.dto.response.DepartmentResponseDTO;
import com.hospital.hospital_appointment_system.entity.Department;
import com.hospital.hospital_appointment_system.exception.DuplicateResourceException;
import com.hospital.hospital_appointment_system.exception.ResourceNotFoundException;
import com.hospital.hospital_appointment_system.repository.DepartmentRepository;
import com.hospital.hospital_appointment_system.service.DepartmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO requestDTO) {
        logger.info("Creating new department: {}", requestDTO.getName());

        if (departmentRepository.existsByName(requestDTO.getName())) {
            throw new DuplicateResourceException(
                "Department already exists with name: " + requestDTO.getName()
            );
        }

        Department department = new Department();
        department.setName(requestDTO.getName());
        department.setDescription(requestDTO.getDescription());

        Department savedDepartment = departmentRepository.save(department);
        logger.info("Department created successfully with id: {}", savedDepartment.getId());

        return mapToResponseDTO(savedDepartment);
    }

    @Override
    public DepartmentResponseDTO getDepartmentById(Long id) {
        logger.info("Fetching department with id: {}", id);

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Department not found with id: " + id
                ));

        return mapToResponseDTO(department);
    }

    @Override
    public List<DepartmentResponseDTO> getAllDepartments() {
        logger.info("Fetching all departments");

        return departmentRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentResponseDTO updateDepartment(Long id, DepartmentRequestDTO requestDTO) {
        logger.info("Updating department with id: {}", id);

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Department not found with id: " + id
                ));

        department.setName(requestDTO.getName());
        department.setDescription(requestDTO.getDescription());

        Department updatedDepartment = departmentRepository.save(department);
        logger.info("Department updated successfully with id: {}", updatedDepartment.getId());

        return mapToResponseDTO(updatedDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {
        logger.info("Deleting department with id: {}", id);

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Department not found with id: " + id
                ));

        departmentRepository.delete(department);
        logger.info("Department deleted successfully with id: {}", id);
    }

    private DepartmentResponseDTO mapToResponseDTO(Department department) {
        DepartmentResponseDTO responseDTO = new DepartmentResponseDTO();
        responseDTO.setId(department.getId());
        responseDTO.setName(department.getName());
        responseDTO.setDescription(department.getDescription());
        return responseDTO;
    }
}