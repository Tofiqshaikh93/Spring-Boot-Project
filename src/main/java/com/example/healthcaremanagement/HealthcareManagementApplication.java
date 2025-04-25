package com.example.healthcaremanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;

@SpringBootConfiguration
public class HealthcareManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthcareManagementApplication.class, args);
        System.out.println("Healthcare Management Application is running...");
    }
}
