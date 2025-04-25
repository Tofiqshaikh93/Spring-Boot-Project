package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.dto.AppointmentDTO; // Import DTO
import com.example.healthcaremanagement.model.Appointment; // Import Entity
import com.example.healthcaremanagement.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/")
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        // Convert DTO to Entity
        Appointment appointment = new Appointment();
        appointment.setPatientId(appointmentDTO.getPatientId());
        appointment.setDoctorId(appointmentDTO.getDoctorId());
        appointment.setAppointmentDateTime(appointmentDTO.getAppointmentDateTime());

        // Save entity and convert back to DTO for response
        Appointment savedAppointment = appointmentService.saveAppointment(appointment);
        return ResponseEntity.ok(new AppointmentDTO(savedAppointment.getId(), savedAppointment.getPatientId(), savedAppointment.getDoctorId(), savedAppointment.getAppointmentDateTime()));
    }

    @GetMapping("/")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        List<AppointmentDTO> appointmentDTOS = appointments.stream()
            .map(a -> new AppointmentDTO(a.getId(), a.getPatientId(), a.getDoctorId(), a.getAppointmentDateTime()))
            .toList(); // Convert list of entities to list of DTOs
        
        return ResponseEntity.ok(appointmentDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable Long id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        if (appointment != null) {
            return ResponseEntity.ok(new AppointmentDTO(appointment.getId(), appointment.getPatientId(), appointment.getDoctorId(), appointment.getAppointmentDateTime()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
