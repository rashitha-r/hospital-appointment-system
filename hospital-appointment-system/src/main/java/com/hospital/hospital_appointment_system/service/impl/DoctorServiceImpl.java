package com.hospital.hospital_appointment_system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hospital.hospital_appointment_system.dto.request.DoctorRequestDTO;
import com.hospital.hospital_appointment_system.dto.response.DoctorResponseDTO;
import com.hospital.hospital_appointment_system.entity.Department;
import com.hospital.hospital_appointment_system.entity.Doctor;
import com.hospital.hospital_appointment_system.exception.DuplicateResourceException;
import com.hospital.hospital_appointment_system.exception.ResourceNotFoundException;
import com.hospital.hospital_appointment_system.repository.DepartmentRepository;
import com.hospital.hospital_appointment_system.repository.DoctorRepository;
import com.hospital.hospital_appointment_system.service.DoctorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private static final Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);

    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public DoctorResponseDTO createDoctor(DoctorRequestDTO requestDTO) {
        logger.info("Creating new doctor: {}", requestDTO.getName());

        if (doctorRepository.existsByEmail(requestDTO.getEmail())) {
            throw new DuplicateResourceException(
                "Doctor already exists with email: " + requestDTO.getEmail()
            );
        }

        Department department = departmentRepository.findById(requestDTO.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Department not found with id: " + requestDTO.getDepartmentId()
                ));

        Doctor doctor = new Doctor();
        doctor.setName(requestDTO.getName());
        doctor.setSpecialization(requestDTO.getSpecialization());
        doctor.setEmail(requestDTO.getEmail());
        doctor.setPhone(requestDTO.getPhone());
        doctor.setDepartment(department);

        Doctor savedDoctor = doctorRepository.save(doctor);
        logger.info("Doctor created successfully with id: {}", savedDoctor.getId());

        return mapToResponseDTO(savedDoctor);
    }

    @Override
    public DoctorResponseDTO getDoctorById(Long id) {
        logger.info("Fetching doctor with id: {}", id);

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Doctor not found with id: " + id
                ));

        return mapToResponseDTO(doctor);
    }

    @Override
    public List<DoctorResponseDTO> getAllDoctors() {
        logger.info("Fetching all doctors");

        return doctorRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorResponseDTO> getDoctorsByDepartment(Long departmentId) {
        logger.info("Fetching doctors by department id: {}", departmentId);

        departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Department not found with id: " + departmentId
                ));

        return doctorRepository.findByDepartmentId(departmentId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorResponseDTO updateDoctor(Long id, DoctorRequestDTO requestDTO) {
        logger.info("Updating doctor with id: {}", id);

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Doctor not found with id: " + id
                ));

        Department department = departmentRepository.findById(requestDTO.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Department not found with id: " + requestDTO.getDepartmentId()
                ));

        doctor.setName(requestDTO.getName());
        doctor.setSpecialization(requestDTO.getSpecialization());
        doctor.setEmail(requestDTO.getEmail());
        doctor.setPhone(requestDTO.getPhone());
        doctor.setDepartment(department);

        Doctor updatedDoctor = doctorRepository.save(doctor);
        logger.info("Doctor updated successfully with id: {}", updatedDoctor.getId());

        return mapToResponseDTO(updatedDoctor);
    }

    @Override
    public void deleteDoctor(Long id) {
        logger.info("Deleting doctor with id: {}", id);

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Doctor not found with id: " + id
                ));

        doctorRepository.delete(doctor);
        logger.info("Doctor deleted successfully with id: {}", id);
    }

    private DoctorResponseDTO mapToResponseDTO(Doctor doctor) {
        DoctorResponseDTO responseDTO = new DoctorResponseDTO();
        responseDTO.setId(doctor.getId());
        responseDTO.setName(doctor.getName());
        responseDTO.setSpecialization(doctor.getSpecialization());
        responseDTO.setEmail(doctor.getEmail());
        responseDTO.setPhone(doctor.getPhone());
        responseDTO.setDepartmentId(doctor.getDepartment().getId());
        responseDTO.setDepartmentName(doctor.getDepartment().getName());
        return responseDTO;
    }
}