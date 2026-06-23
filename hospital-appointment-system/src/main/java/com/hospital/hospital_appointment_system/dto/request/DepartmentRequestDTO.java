package com.hospital.hospital_appointment_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequestDTO {

    @NotBlank(message = "Department name is required")
    private String name;

    private String description;
}