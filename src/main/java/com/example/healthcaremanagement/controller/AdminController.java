package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.dto.AdminDTO; // Import DTO
import com.example.healthcaremanagement.model.Admin; // Import Entity
import com.example.healthcaremanagement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/")
    public ResponseEntity<AdminDTO> createAdmin(@RequestBody AdminDTO adminDTO) {
        // Convert DTO to Entity
        Admin admin = new Admin();
        admin.setUsername(adminDTO.getUsername());
        admin.setPassword(adminDTO.getPassword());
        admin.setEmail(adminDTO.getEmail());

        // Save entity and convert back to DTO for response
        Admin savedAdmin = adminService.saveAdmin(admin);
        return ResponseEntity.ok(new AdminDTO(savedAdmin.getId(), savedAdmin.getUsername(), savedAdmin.getEmail(), null));
    }

    @GetMapping("/")
    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        List<AdminDTO> adminDTOS = admins.stream()
            .map(a -> new AdminDTO(a.getId(), a.getUsername(), a.getEmail(), null))
            .toList(); // Convert list of entities to list of DTOs
        
        return ResponseEntity.ok(adminDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable Long id) {
        Admin admin = adminService.getAdminById(id);
        if (admin != null) {
            return ResponseEntity.ok(new AdminDTO(admin.getId(), admin.getUsername(), admin.getEmail(), null));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }
}
