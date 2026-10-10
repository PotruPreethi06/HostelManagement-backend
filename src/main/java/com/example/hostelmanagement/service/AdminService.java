package com.example.hostelmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.hostelmanagement.config.JwtService;
import com.example.hostelmanagement.dto.AdminLoginRequest;
import com.example.hostelmanagement.dto.LoginResponse;
import com.example.hostelmanagement.entity.Admin;
import com.example.hostelmanagement.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    // Create Admin
    public Admin createAdmin(Admin admin) {

        String encodedPassword =
                passwordEncoder.encode(admin.getPassword());

        admin.setPassword(encodedPassword);

        return adminRepository.save(admin);
    }

    // Get All Admins
    public List<Admin> getAllAdmins() {

        return adminRepository.findAll();
    }

    // Get Admin By ID
    public Admin getAdminById(Long id) {

        return adminRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Admin not found"));
    }

    // Get Admin By Email
    public Admin getAdminByEmail(String email) {

        return adminRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Admin not found"));
    }

    // Update Admin
    public Admin updateAdmin(Long id, Admin updatedAdmin) {

        Admin existingAdmin = adminRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Admin not found"));

        existingAdmin.setName(updatedAdmin.getName());
        existingAdmin.setEmail(updatedAdmin.getEmail());

        // Encode new password
        if (updatedAdmin.getPassword() != null
                && !updatedAdmin.getPassword().isEmpty()) {

            existingAdmin.setPassword(
                    passwordEncoder.encode(
                            updatedAdmin.getPassword()
                    )
            );
        }

        existingAdmin.setPhone(updatedAdmin.getPhone());
        existingAdmin.setRole(updatedAdmin.getRole());

        return adminRepository.save(existingAdmin);
    }

    // Admin Login + JWT
    public LoginResponse login(AdminLoginRequest loginRequest) {

        Admin admin = adminRepository
                .findByEmail(loginRequest.getEmail())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Invalid email or password"
                        ));

        boolean passwordMatches =
                passwordEncoder.matches(
                        loginRequest.getPassword(),
                        admin.getPassword()
                );

        if (!passwordMatches) {

            throw new RuntimeException(
                    "Invalid email or password"
            );
        }

        // Generate JWT token
        String token = jwtService.generateToken(
                admin.getEmail(),
                admin.getRole()
        );

        return new LoginResponse(
                "Login successful",
                token,
                admin.getName(),
                admin.getEmail(),
                admin.getRole()
        );
    }

    // Delete Admin
    public String deleteAdmin(Long id) {

        Admin admin = adminRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Admin not found"));

        adminRepository.delete(admin);

        return "Admin deleted successfully";
    }
}