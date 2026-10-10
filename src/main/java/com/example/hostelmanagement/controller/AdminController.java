package com.example.hostelmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hostelmanagement.dto.AdminLoginRequest;
import com.example.hostelmanagement.dto.LoginResponse;
import com.example.hostelmanagement.entity.Admin;
import com.example.hostelmanagement.service.AdminService;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // =========================
    // CREATE ADMIN
    // =========================
    @PostMapping
    public ResponseEntity<Admin> createAdmin(
            @RequestBody Admin admin) {

        Admin savedAdmin = adminService.createAdmin(admin);

        return new ResponseEntity<>(
                savedAdmin,
                HttpStatus.CREATED
        );
    }

    // =========================
    // ADMIN LOGIN
    // =========================
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody AdminLoginRequest loginRequest) {

        LoginResponse response =
                adminService.login(loginRequest);

        return ResponseEntity.ok(response);
    }

    // =========================
    // GET ALL ADMINS
    // =========================
    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {

        return ResponseEntity.ok(
                adminService.getAllAdmins()
        );
    }

    // =========================
    // GET ADMIN BY ID
    // =========================
    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                adminService.getAdminById(id)
        );
    }

    // =========================
    // GET ADMIN BY EMAIL
    // =========================
    @GetMapping("/email/{email}")
    public ResponseEntity<Admin> getAdminByEmail(
            @PathVariable String email) {

        return ResponseEntity.ok(
                adminService.getAdminByEmail(email)
        );
    }

    // =========================
    // UPDATE ADMIN
    // =========================
    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(
            @PathVariable Long id,
            @RequestBody Admin admin) {

        return ResponseEntity.ok(
                adminService.updateAdmin(id, admin)
        );
    }

    // =========================
    // DELETE ADMIN
    // =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                adminService.deleteAdmin(id)
        );
    }
}