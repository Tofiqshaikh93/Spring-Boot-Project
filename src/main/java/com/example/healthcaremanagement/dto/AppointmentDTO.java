package com.example.healthcaremanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    private Long id;
    private Long patientId; // Reference to Patient entity
    private Long doctorId;  // Reference to Doctor entity
    private LocalDateTime appointmentDateTime; // Date and time of the appointment
}
