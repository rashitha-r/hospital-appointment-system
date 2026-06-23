package com.hospital.hospital_appointment_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.hospital_appointment_system.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(String name);
    boolean existsByName(String name);
}