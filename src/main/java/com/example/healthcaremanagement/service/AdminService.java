package com.example.healthcaremanagement.service;

import com.example.healthcaremanagement.model.Admin;

import java.util.List;

public interface AdminService {
    Admin saveAdmin(Admin admin);
    List<Admin> getAllAdmins();
    Admin getAdminById(Long id);
    void deleteAdmin(Long id);
}
