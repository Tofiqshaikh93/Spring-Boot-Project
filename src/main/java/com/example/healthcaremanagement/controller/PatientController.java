package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.dto.PatientDTO;      // Import DTO
import com.example.healthcaremanagement.model.Patient;         // Import Entity
import com.example.healthcaremanagement.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/")
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO) {
        // Convert DTO to Entity (you can use a mapping library here)
        Patient patient = new Patient();
        patient.setName(patientDTO.getName());
        patient.setEmail(patientDTO.getEmail());
        patient.setPhoneNumber(patientDTO.getPhoneNumber());

        // Save entity and convert back to DTO for response
        Patient savedPatient = patientService.savePatient(patient);
        return ResponseEntity.ok(new PatientDTO(savedPatient.getId(), savedPatient.getName(), savedPatient.getEmail(), savedPatient.getPhoneNumber()));
    }

    @GetMapping("/")
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        List<PatientDTO> patientDTOS = patients.stream()
            .map(p -> new PatientDTO(p.getId(), p.getName(), p.getEmail(), p.getPhoneNumber()))
            .toList(); // Convert list of entities to list of DTOs
        
        return ResponseEntity.ok(patientDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            return ResponseEntity.ok(new PatientDTO(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhoneNumber()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
