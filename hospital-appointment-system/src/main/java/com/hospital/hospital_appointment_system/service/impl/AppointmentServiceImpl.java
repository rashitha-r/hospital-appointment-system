package com.hospital.hospital_appointment_system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hospital.hospital_appointment_system.dto.request.AppointmentRequestDTO;
import com.hospital.hospital_appointment_system.dto.response.AppointmentResponseDTO;
import com.hospital.hospital_appointment_system.entity.Appointment;
import com.hospital.hospital_appointment_system.entity.Appointment.AppointmentStatus;
import com.hospital.hospital_appointment_system.entity.Doctor;
import com.hospital.hospital_appointment_system.entity.Patient;
import com.hospital.hospital_appointment_system.exception.ResourceNotFoundException;
import com.hospital.hospital_appointment_system.repository.AppointmentRepository;
import com.hospital.hospital_appointment_system.repository.DoctorRepository;
import com.hospital.hospital_appointment_system.repository.PatientRepository;
import com.hospital.hospital_appointment_system.service.AppointmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentServiceImpl.class);

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public AppointmentResponseDTO bookAppointment(AppointmentRequestDTO requestDTO) {
        logger.info("Booking appointment for patient id: {}", requestDTO.getPatientId());

        Patient patient = patientRepository.findById(requestDTO.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Patient not found with id: " + requestDTO.getPatientId()
                ));

        Doctor doctor = doctorRepository.findById(requestDTO.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Doctor not found with id: " + requestDTO.getDoctorId()
                ));

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(requestDTO.getAppointmentDate());
        appointment.setAppointmentTime(requestDTO.getAppointmentTime());
        appointment.setStatus(AppointmentStatus.PENDING);
        appointment.setNotes(requestDTO.getNotes());

        Appointment savedAppointment = appointmentRepository.save(appointment);
        logger.info("Appointment booked successfully with id: {}", savedAppointment.getId());

        return mapToResponseDTO(savedAppointment);
    }

    @Override
    public AppointmentResponseDTO getAppointmentById(Long id) {
        logger.info("Fetching appointment with id: {}", id);

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Appointment not found with id: " + id
                ));

        return mapToResponseDTO(appointment);
    }

    @Override
    public List<AppointmentResponseDTO> getAllAppointments() {
        logger.info("Fetching all appointments");

        return appointmentRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponseDTO> getAppointmentsByPatient(Long patientId) {
        logger.info("Fetching appointments for patient id: {}", patientId);

        patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Patient not found with id: " + patientId
                ));

        return appointmentRepository.findByPatientId(patientId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponseDTO> getAppointmentsByDoctor(Long doctorId) {
        logger.info("Fetching appointments for doctor id: {}", doctorId);

        doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Doctor not found with id: " + doctorId
                ));

        return appointmentRepository.findByDoctorId(doctorId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentResponseDTO updateAppointmentStatus(Long id, AppointmentStatus status) {
        logger.info("Updating appointment status to {} for id: {}", status, id);

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Appointment not found with id: " + id
                ));

        appointment.setStatus(status);
        Appointment updatedAppointment = appointmentRepository.save(appointment);
        logger.info("Appointment status updated successfully for id: {}", id);

        return mapToResponseDTO(updatedAppointment);
    }

    @Override
    public void cancelAppointment(Long id) {
        logger.info("Cancelling appointment with id: {}", id);

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Appointment not found with id: " + id
                ));

        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(appointment);
        logger.info("Appointment cancelled successfully with id: {}", id);
    }

    private AppointmentResponseDTO mapToResponseDTO(Appointment appointment) {
        AppointmentResponseDTO responseDTO = new AppointmentResponseDTO();
        responseDTO.setId(appointment.getId());
        responseDTO.setPatientId(appointment.getPatient().getId());
        responseDTO.setPatientName(appointment.getPatient().getName());
        responseDTO.setDoctorId(appointment.getDoctor().getId());
        responseDTO.setDoctorName(appointment.getDoctor().getName());
        responseDTO.setDepartmentName(appointment.getDoctor().getDepartment().getName());
        responseDTO.setAppointmentDate(appointment.getAppointmentDate());
        responseDTO.setAppointmentTime(appointment.getAppointmentTime());
        responseDTO.setStatus(appointment.getStatus());
        responseDTO.setNotes(appointment.getNotes());
        return responseDTO;
    }
}