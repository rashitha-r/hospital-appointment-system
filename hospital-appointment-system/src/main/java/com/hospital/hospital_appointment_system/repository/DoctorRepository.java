package com.hospital.hospital_appointment_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.hospital_appointment_system.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    
    List<Doctor> findByDepartmentId(Long departmentId);
    
    boolean existsByEmail(String email);
}