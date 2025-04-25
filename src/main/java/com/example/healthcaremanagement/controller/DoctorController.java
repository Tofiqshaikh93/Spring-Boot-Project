package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.dto.DoctorDTO; // Import DTO
import com.example.healthcaremanagement.model.Doctor; // Import Entity
import com.example.healthcaremanagement.service.DoctorService;
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
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/")
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorDTO doctorDTO) {
        // Convert DTO to Entity
        Doctor doctor = new Doctor();
        doctor.setName(doctorDTO.getName());
        doctor.setSpecialty(doctorDTO.getSpecialization());

        // Save entity and convert back to DTO for response
        Doctor savedDoctor = doctorService.saveDoctor(doctor);
        return ResponseEntity.ok(new DoctorDTO(savedDoctor.getId(), savedDoctor.getName(), savedDoctor.getSpecialty()));
    }

    @GetMapping("/")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        List<DoctorDTO> doctorDTOS = doctors.stream()
            .map(d -> new DoctorDTO(d.getId(), d.getName(), d.getSpecialty()))
            .toList(); // Convert list of entities to list of DTOs
        
        return ResponseEntity.ok(doctorDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor != null) {
            return ResponseEntity.ok(new DoctorDTO(doctor.getId(), doctor.getName(), doctor.getSpecialty()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
