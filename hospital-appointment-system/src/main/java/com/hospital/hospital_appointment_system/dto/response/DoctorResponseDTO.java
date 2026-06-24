package com.hospital.hospital_appointment_system.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorResponseDTO {

    private Long id;
    private String name;
    private String specialization;
    private String email;
    private String phone;
    private Long departmentId;
    private String departmentName;
}