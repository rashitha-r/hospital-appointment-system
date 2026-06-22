package com.hospital.hospital_appointment_system.dto.request;

import java.time.LocalDate;

import com.hospital.hospital_appointment_system.entity.Patient.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Phone is required")
    private String phone;

    private LocalDate dateOfBirth;

    @NotNull(message = "Gender is required")
    private Gender gender;
}