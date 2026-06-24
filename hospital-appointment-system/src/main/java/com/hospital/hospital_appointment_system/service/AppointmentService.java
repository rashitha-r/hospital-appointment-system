package com.hospital.hospital_appointment_system.service;

import java.util.List;

import com.hospital.hospital_appointment_system.dto.request.AppointmentRequestDTO;
import com.hospital.hospital_appointment_system.dto.response.AppointmentResponseDTO;
import com.hospital.hospital_appointment_system.entity.Appointment.AppointmentStatus;

public interface AppointmentService {

    AppointmentResponseDTO bookAppointment(AppointmentRequestDTO requestDTO);

    AppointmentResponseDTO getAppointmentById(Long id);

    List<AppointmentResponseDTO> getAllAppointments();

    List<AppointmentResponseDTO> getAppointmentsByPatient(Long patientId);

    List<AppointmentResponseDTO> getAppointmentsByDoctor(Long doctorId);

    AppointmentResponseDTO updateAppointmentStatus(Long id, AppointmentStatus status);

    void cancelAppointment(Long id);
}