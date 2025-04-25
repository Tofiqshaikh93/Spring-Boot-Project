package com.example.healthcaremanagement.model;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appointments")  
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Patient ID is mandatory")
    private Long patientId;                // Reference to Patient entity

    @NotNull(message = "Doctor ID is mandatory")
    private Long doctorId;            // Reference to Doctor entity

    @NotNull(message = "Appointment date and time is mandatory")
    @Future(message = "Appointment must be in the future")
    private LocalDateTime appointmentDateTime;                  

    @NotBlank(message = "Status is mandatory")
    private String status;             // e.g., Scheduled, Completed, Canceled

}
