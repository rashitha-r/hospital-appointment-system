package com.hospital.hospital_appointment_system.dto.response;

import java.time.LocalDate;

import com.hospital.hospital_appointment_system.entity.Patient.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private Gender gender;
}